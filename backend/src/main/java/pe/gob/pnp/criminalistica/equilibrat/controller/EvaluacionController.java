package pe.gob.pnp.criminalistica.equilibrat.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import pe.gob.pnp.criminalistica.equilibrat.dao.PeritoDAO;
import pe.gob.pnp.criminalistica.equilibrat.model.EvaluacionAntropometrica;
import pe.gob.pnp.criminalistica.equilibrat.model.NivelRiesgo;
import pe.gob.pnp.criminalistica.equilibrat.model.Perito;
import pe.gob.pnp.criminalistica.equilibrat.service.CalculadoraSaludService;
import pe.gob.pnp.criminalistica.equilibrat.service.ReporteExcelService;
import pe.gob.pnp.criminalistica.equilibrat.util.SecurityValidator;

/**
 * Controlador REST para la gestión de evaluaciones antropométricas,
 * consulta del catálogo de peritos y exportación de reportes en Excel.
 */
public class EvaluacionController implements HttpHandler {

    private final CalculadoraSaludService calculadora;
    private final PeritoDAO peritoDAO;
    private final ReporteExcelService reporteExcelService;
    private final List<EvaluacionAntropometrica> evaluaciones = new CopyOnWriteArrayList<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public EvaluacionController(CalculadoraSaludService calculadora,
                                PeritoDAO peritoDAO,
                                ReporteExcelService reporteExcelService) {
        this.calculadora = calculadora;
        this.peritoDAO = peritoDAO;
        this.reporteExcelService = reporteExcelService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Intercepta peticiones CORS de tipo OPTIONS
        if (manejarCors(exchange)) {
            return;
        }

        String ruta = exchange.getRequestURI().getPath();
        String metodo = exchange.getRequestMethod();

        try {
            if (ruta.endsWith("/excel") && "GET".equalsIgnoreCase(metodo)) {
                procesarExportarExcel(exchange);
                return;
            }

            if (ruta.endsWith("/peritos") && "GET".equalsIgnoreCase(metodo)) {
                procesarListarPeritos(exchange);
                return;
            }

            if ("POST".equalsIgnoreCase(metodo)) {
                procesarCrearEvaluacion(exchange);
                return;
            }

            JsonHttp.responder(exchange, 404, Map.of("error", "Recurso no encontrado"));

        } catch (IllegalArgumentException e) {
            JsonHttp.responder(exchange, 400, Map.of("error", e.getMessage()));
        }
    }

    /**
     * Gestiona las cabeceras de seguridad y permisos CORS (Preflight).
     */
    private boolean manejarCors(HttpExchange exchange) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return true;
        }
        return false;
    }

    /**
     * Procesa la exportación de la tabla de fatiga a formato Excel.
     */
    private void procesarExportarExcel(HttpExchange exchange) throws IOException {
        byte[] excel = reporteExcelService.exportarTablaFatiga();
        JsonHttp.responderBinario(
                exchange,
                200,
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                excel);
    }

    /**
     * Obtiene y lista todos los peritos mapeados para la vista pública.
     */
    private void procesarListarPeritos(HttpExchange exchange) throws IOException {
        java.util.List<java.util.Map<String, Object>> payload = peritoDAO.listarTodos().stream()
                .map(this::aMapaPublico)
                .toList();
        JsonHttp.responder(exchange, 200, payload);
    }

    /**
     * Registra una nueva evaluación antropométrica validando el DNI y calculando el IMC.
     */
    private void procesarCrearEvaluacion(HttpExchange exchange) throws IOException {
        EvaluacionRequest request = JsonHttp.leer(exchange, EvaluacionRequest.class);
        String dni = SecurityValidator.validarDocumentoOchoDigitos(request.dni);
        double imc = calculadora.calcularImc(request.pesoKg, request.tallaM);
        NivelRiesgo riesgo = calculadora.clasificarImc(imc);

        EvaluacionAntropometrica evaluacion = new EvaluacionAntropometrica(
                secuencia.getAndIncrement(), dni, request.pesoKg, request.tallaM,
                imc, riesgo, LocalDateTime.now());

        evaluaciones.add(evaluacion);
        JsonHttp.responder(exchange, 201, evaluacion);
    }

    /**
     * Mapea un objeto Perito a un formato seguro de diccionario para respuestas JSON.
     */
    private Map<String, Object> aMapaPublico(Perito perito) {
        return Map.of(
                "id", perito.getId(),
                "cip", perito.getCip(),
                "dni", perito.getDni(),
                "nombre", perito.getNombre(),
                "departamento", perito.getDepartamento(),
                "rol", perito.getRol().name(),
                "nivelFatiga", perito.getNivelFatiga(),
                "nivelRiesgoFatiga", calculadora.clasificarFatiga(perito.getNivelFatiga()).name()
        );
    }

    static final class EvaluacionRequest {
        String dni;
        double pesoKg;
        double tallaM;
    }
}