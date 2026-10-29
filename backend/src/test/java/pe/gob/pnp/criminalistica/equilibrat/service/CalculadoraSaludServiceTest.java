package pe.gob.pnp.criminalistica.equilibrat.service;

import static org.assertj.core.api.Assertions.assertThat;

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
}
