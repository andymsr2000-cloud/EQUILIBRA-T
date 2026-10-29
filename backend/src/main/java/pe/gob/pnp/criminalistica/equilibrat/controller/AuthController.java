package pe.gob.pnp.criminalistica.equilibrat.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.util.Map;
import pe.gob.pnp.criminalistica.equilibrat.model.Perito;
import pe.gob.pnp.criminalistica.equilibrat.model.RolUsuario;
import pe.gob.pnp.criminalistica.equilibrat.service.AuthService;

/**
 * Orquestador REST de autenticación institucional.
 */
public class AuthController implements HttpHandler {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return;
        }
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            JsonHttp.responder(exchange, 405, Map.of("error", "Método no permitido"));
            return;
        }
        try {
            LoginRequest request = JsonHttp.leer(exchange, LoginRequest.class);
            RolUsuario rol = request.rol == null ? null : RolUsuario.valueOf(request.rol);
            Perito perito = authService.autenticar(request.cip, request.password, rol);
            JsonHttp.responder(exchange, 200, Map.of(
                    "id", perito.getId(),
                    "cip", perito.getCip(),
                    "nombre", perito.getNombre(),
                    "departamento", perito.getDepartamento(),
                    "rol", perito.getRol().name(),
                    "nivelFatiga", perito.getNivelFatiga()
            ));
        } catch (IllegalArgumentException e) {
            JsonHttp.responder(exchange, 400, Map.of("error", e.getMessage()));
        } catch (SecurityException e) {
            JsonHttp.responder(exchange, 401, Map.of("error", e.getMessage()));
        }
    }

    static final class LoginRequest {
        String cip;
        String password;
        String rol;
    }
}
