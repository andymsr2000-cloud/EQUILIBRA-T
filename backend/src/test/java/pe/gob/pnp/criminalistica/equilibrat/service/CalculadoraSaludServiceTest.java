package pe.gob.pnp.criminalistica.equilibrat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pe.gob.pnp.criminalistica.equilibrat.model.NivelRiesgo;

class CalculadoraSaludServiceTest {

    private CalculadoraSaludService service;

    @BeforeEach
    void setUp() {
        service = new CalculadoraSaludService();
    }

    @Test
    @DisplayName("IMC de 80.0 kg y 1.80 m debe ser 24.7 OPTIMO")
    void calcularImc_peso80Talla180_retorna247Optimo() {
        double imc = service.calcularImc(80.0, 1.80);

        assertThat(imc).isEqualTo(24.7);
        assertThat(service.clasificarImc(imc)).isEqualTo(NivelRiesgo.OPTIMO);
    }

    @Test
    @DisplayName("Fatiga >= 60 se clasifica como CRITICO")
    void clasificarFatiga_umbral60_esCritico() {
        assertThat(service.clasificarFatiga(60.0)).isEqualTo(NivelRiesgo.CRITICO);
        assertThat(service.clasificarFatiga(65.0)).isEqualTo(NivelRiesgo.CRITICO);
        assertThat(service.clasificarFatiga(59.9)).isNotEqualTo(NivelRiesgo.CRITICO);
    }
    
        @Test
    @DisplayName("El IMC debe rechazar pesos y tallas no positivos")
    void calcularImc_pesoOTallaInvalida_lanzaExcepcion() {
        assertThatThrownBy(() -> service.calcularImc(0.0, 1.70))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> service.calcularImc(-70.0, 1.70))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> service.calcularImc(70.0, 0.0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> service.calcularImc(70.0, -1.70))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("El IMC debe clasificarse correctamente en los límites")
    void clasificarImc_limites_retornaNivelEsperado() {
        assertThat(service.clasificarImc(18.4)).isEqualTo(NivelRiesgo.BAJO);
        assertThat(service.clasificarImc(18.5)).isEqualTo(NivelRiesgo.OPTIMO);
        assertThat(service.clasificarImc(24.9)).isEqualTo(NivelRiesgo.OPTIMO);
        assertThat(service.clasificarImc(25.0)).isEqualTo(NivelRiesgo.MODERADO);
        assertThat(service.clasificarImc(29.9)).isEqualTo(NivelRiesgo.MODERADO);
        assertThat(service.clasificarImc(30.0)).isEqualTo(NivelRiesgo.CRITICO);
    }

    @Test
    @DisplayName("El cálculo del IMC debe redondear a un decimal")
    void calcularImc_resultadoConDecimales_redondeaAUnDecimal() {
        assertThat(service.calcularImc(60.0, 2.0)).isEqualTo(15.0);
        assertThat(service.calcularImc(80.0, 1.80)).isEqualTo(24.7);
    }
}
