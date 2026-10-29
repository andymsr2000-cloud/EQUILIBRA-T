package pe.gob.pnp.criminalistica.equilibrat.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SecurityValidatorTest {

    @Test
    @DisplayName("DNI con letras debe ser rechazado")
    void validarDocumento_conLetras_lanzaExcepcion() {
        assertThatThrownBy(() -> SecurityValidator.validarDocumentoOchoDigitos("12AB5678"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("8 dígitos");
    }

    @Test
    @DisplayName("DNI con longitud distinta de 8 debe ser rechazado")
    void validarDocumento_longitudInvalida_lanzaExcepcion() {
        assertThatThrownBy(() -> SecurityValidator.validarDocumentoOchoDigitos("1234567"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> SecurityValidator.validarDocumentoOchoDigitos("123456789"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("DNI de 8 dígitos es aceptado")
    void validarDocumento_ochoDigitos_ok() {
        assertThat(SecurityValidator.validarDocumentoOchoDigitos("40000001")).isEqualTo("40000001");
    }
}
