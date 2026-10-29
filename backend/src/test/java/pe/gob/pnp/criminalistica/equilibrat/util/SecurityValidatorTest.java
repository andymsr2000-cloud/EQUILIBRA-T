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
    
    @Test
    @DisplayName("Documento con espacios externos debe aceptarse y normalizarse")
    void validarDocumento_conEspacios_retornaDocumentoNormalizado() {
        assertThat(SecurityValidator.validarDocumentoOchoDigitos("  40000001  "))
                .isEqualTo("40000001");
    }

    @Test
    @DisplayName("Documento nulo o vacío debe ser rechazado")
    void validarDocumento_nuloOVacio_lanzaExcepcion() {
        assertThatThrownBy(() -> SecurityValidator.validarDocumentoOchoDigitos(null))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> SecurityValidator.validarDocumentoOchoDigitos("   "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("El texto con etiquetas HTML debe ser sanitizado")
    void sanitizarTexto_conEtiquetasHtml_eliminaEtiquetas() {
        assertThat(SecurityValidator.sanitizarTexto("<b>Hola</b> mundo"))
                .isEqualTo("Hola mundo");
    }

    @Test
    @DisplayName("El texto nulo debe ser rechazado al sanitizar")
    void sanitizarTexto_nulo_lanzaExcepcion() {
        assertThatThrownBy(() -> SecurityValidator.sanitizarTexto(null))
                .isInstanceOf(NullPointerException.class);
    }
}
