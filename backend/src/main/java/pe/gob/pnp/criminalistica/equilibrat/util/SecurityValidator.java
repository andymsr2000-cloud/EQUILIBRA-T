package pe.gob.pnp.criminalistica.equilibrat.util;

import com.google.common.base.Preconditions;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * Validación estricta de identificadores institucionales y sanitización anti-XSS.
 */
public final class SecurityValidator {

    private static final Pattern OCHO_DIGITOS = Pattern.compile("^\\d{8}$");
    private static final Pattern ETIQUETAS_HTML = Pattern.compile("(?i)<[^>]*>");
    private static final Pattern EVENTOS_JS = Pattern.compile("(?i)on\\w+\\s*=");

    private SecurityValidator() {
    }

    /**
     * Valida que CIP o DNI sean exactamente 8 dígitos numéricos.
     *
     * @param valor CIP o DNI a validar
     * @return el mismo valor si es válido
     * @throws IllegalArgumentException si es nulo, vacío, contiene letras o no tiene 8 dígitos
     */
    public static String validarDocumentoOchoDigitos(String valor) {
        Preconditions.checkArgument(StringUtils.isNotBlank(valor), "El documento es obligatorio");
        String normalizado = StringUtils.trim(valor);
        Preconditions.checkArgument(
                OCHO_DIGITOS.matcher(normalizado).matches(),
                "CIP/DNI debe contener exactamente 8 dígitos numéricos");
        return normalizado;
    }

    /**
     * Elimina etiquetas HTML, atributos de evento y caracteres de control para mitigar XSS.
     */
    public static String sanitizarTexto(String texto) {
        Preconditions.checkNotNull(texto, "El texto no puede ser nulo");
        String limpio = StringUtils.trim(texto);
        limpio = ETIQUETAS_HTML.matcher(limpio).replaceAll("");
        limpio = EVENTOS_JS.matcher(limpio).replaceAll("");
        limpio = StringUtils.replaceEach(
                limpio,
                new String[] {"<", ">", "\"", "'", "`"},
                new String[] {"", "", "", "", ""});
        return StringUtils.normalizeSpace(limpio);
    }
}
