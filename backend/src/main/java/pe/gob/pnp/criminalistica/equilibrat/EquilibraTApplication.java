package pe.gob.pnp.criminalistica.equilibrat;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.gob.pnp.criminalistica.equilibrat.controller.AuthController;
import pe.gob.pnp.criminalistica.equilibrat.controller.EvaluacionController;
import pe.gob.pnp.criminalistica.equilibrat.dao.InMemoryPeritoDAO;
import pe.gob.pnp.criminalistica.equilibrat.dao.PeritoDAO;
import pe.gob.pnp.criminalistica.equilibrat.service.AuthService;
import pe.gob.pnp.criminalistica.equilibrat.service.CalculadoraSaludService;
import pe.gob.pnp.criminalistica.equilibrat.service.ReporteExcelService;

/**
 * Punto de entrada MVC: ensambla DAO, servicios y controladores REST.
 */
public final class EquilibraTApplication {

    private static final Logger LOG = LoggerFactory.getLogger(EquilibraTApplication.class);
    private static final int PUERTO = 8080;

    private EquilibraTApplication() {
    }

    public static void main(String[] args) throws Exception {
        PeritoDAO peritoDAO = new InMemoryPeritoDAO();
        CalculadoraSaludService calculadora = new CalculadoraSaludService();
        AuthService authService = new AuthService(peritoDAO);
        ReporteExcelService reporteExcelService = new ReporteExcelService(peritoDAO, calculadora);

        HttpServer server = HttpServer.create(new InetSocketAddress(PUERTO), 0);
        server.createContext("/api/auth/login", new AuthController(authService));
        EvaluacionController evaluacionController =
                new EvaluacionController(calculadora, peritoDAO, reporteExcelService);
        server.createContext("/api/evaluaciones", evaluacionController);
        server.createContext("/api/peritos", evaluacionController);
        server.createContext("/api/reportes/excel", evaluacionController);
        server.setExecutor(null);
        server.start();
        LOG.info("EQUILIBRA-T escuchando en http://localhost:{}", PUERTO);
    }
}
