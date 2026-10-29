package pe.gob.pnp.criminalistica.equilibrat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.gob.pnp.criminalistica.equilibrat.dao.PeritoDAO;
import pe.gob.pnp.criminalistica.equilibrat.model.Perito;
import pe.gob.pnp.criminalistica.equilibrat.model.RolUsuario;
import pe.gob.pnp.criminalistica.equilibrat.util.CriptografiaUtil;
import pe.gob.pnp.criminalistica.equilibrat.util.SecurityValidator;

/**
 * Autenticación institucional con verificación de hash PBKDF2 y bitácora de auditoría.
 */
public class AuthService {

    private static final Logger AUDITORIA = LoggerFactory.getLogger("AUDITORIA");
    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

    private final PeritoDAO peritoDAO;

    public AuthService(PeritoDAO peritoDAO) {
        this.peritoDAO = peritoDAO;
    }

    public Perito autenticar(String cip, String password, RolUsuario rolSolicitado) {
        String cipValido = SecurityValidator.validarDocumentoOchoDigitos(cip);
        Perito perito = peritoDAO.buscarPorCip(cipValido).orElse(null);
        if (perito == null || !CriptografiaUtil.verificarPassword(password, perito.getPasswordHash())) {
            AUDITORIA.warn("Intento de acceso fallido CIP={}", cipValido);
            throw new SecurityException("Credenciales institucionales inválidas");
        }
        if (rolSolicitado != null && perito.getRol() != rolSolicitado && perito.getRol() != RolUsuario.ADMIN) {
            AUDITORIA.warn("Rol no autorizado CIP={} rolSolicitado={}", cipValido, rolSolicitado);
            throw new SecurityException("El rol seleccionado no corresponde al usuario");
        }
        AUDITORIA.info("Acceso exitoso CIP={} rol={} nombre={}", cipValido, perito.getRol(), perito.getNombre());
        LOG.info("Sesión iniciada para {}", perito.getNombre());
        return perito;
    }
}
