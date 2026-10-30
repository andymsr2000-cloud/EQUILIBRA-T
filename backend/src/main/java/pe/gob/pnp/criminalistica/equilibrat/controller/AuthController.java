package pe.gob.pnp.criminalistica.equilibrat.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.util.Map;
import pe.gob.pnp.criminalistica.equilibrat.model.Perito;
import pe.gob.pnp.criminalistica.equilibrat.model.RolUsuario;
import pe.gob.pnp.criminalistica.equilibrat.service.AuthService;

/**
 * Controlador REST encargado de gestionar la autenticación institucional de los usuarios.
 * <p>
 * Implementa {@link HttpHandler} para procesar peticiones HTTP nativas del servidor,
 * gestionando cabeceras CORS (método OPTIONS), validación de métodos permitidos (POST),
 * deserialización de credenciales y el manejo de códigos de estado HTTP ante errores de validación o seguridad.
 * </p>
 */
public class AuthController implements HttpHandler {

    private final AuthService authService;

    /**
     * Constructor que inyecta el servicio de autenticación.
     * 
     * @param authService servicio encargado de la lógica de negocio de autenticación.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Procesa las solicitudes HTTP entrantes para el endpoint de autenticación.
     * 
     * @param exchange el intercambio HTTP que contiene la petición del cliente y permite configurar la respuesta.
     * @throws IOException si ocurre un error de E/S al leer la petición o enviar la respuesta.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Gestión de cabeceras CORS para solicitudes de verificación previa (Preflight OPTIONS)
        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return;
        }

        // Validación estricta para aceptar únicamente peticiones de tipo POST
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            JsonHttp.responder(exchange, 405, Map.of("error", "Método no permitido"));
            return;
        }

        try {
            // Lectura y mapeo del cuerpo de la petición hacia el objeto de transferencia LoginRequest
            LoginRequest request = JsonHttp.leer(exchange, LoginRequest.class);
            RolUsuario rol = request.rol == null ? null : RolUsuario.valueOf(request.rol);
            
            // Autenticación del perito a través del servicio de negocio
            Perito perito = authService.autenticar(request.cip, request.password, rol);
            
            // Respuesta exitosa (200 OK) con los datos clave del usuario autenticado
            JsonHttp.responder(exchange, 200, Map.of(
                    "id", perito.getId(),
                    "cip", perito.getCip(),
                    "nombre", perito.getNombre(),
                    "departamento", perito.getDepartamento(),
                    "rol", perito.getRol().name(),
                    "nivelFatiga", perito.getNivelFatiga()
            ));
        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación o formato de datos incorrectos (400 Bad Request)
            JsonHttp.responder(exchange, 400, Map.of("error", e.getMessage()));
        } catch (SecurityException e) {
            // Manejo de errores de credenciales inválidas o falta de autorización (401 Unauthorized)
            JsonHttp.responder(exchange, 401, Map.of("error", e.getMessage()));
        }
    }

    /**
     * Estructura de datos interna (DTO) que representa el cuerpo de la solicitud de inicio de sesión.
     */
    static final class LoginRequest {
        String cip;
        String password;
        String rol;
    }
}