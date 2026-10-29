package pe.gob.pnp.criminalistica.equilibrat.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import pe.gob.pnp.criminalistica.equilibrat.model.NivelRiesgo;

/**
 * Reglas de negocio de IMC y fatiga ocupacional (SRP).
 */
public class CalculadoraSaludService {

    static final double UMBRAL_FATIGA_CRITICO = 60.0;
    static final double UMBRAL_FATIGA_MODERADO = 30.0;
    private static final double HORAS_REFERENCIA = 12.0;
    private static final double CARGA_REFERENCIA = 20.0;

    /**
     * IMC = pesoKg / (tallaM * tallaM), redondeado a 1 decimal.
     */
    public double calcularImc(double pesoKg, double tallaM) {
        if (pesoKg <= 0 || tallaM <= 0) {
            throw new IllegalArgumentException("Peso y talla deben ser mayores a cero");
        }
        BigDecimal peso = BigDecimal.valueOf(pesoKg);
        BigDecimal talla = BigDecimal.valueOf(tallaM);
        BigDecimal denominador = talla.multiply(talla);
        return peso.divide(denominador, 1, RoundingMode.HALF_UP).doubleValue();
    }

    public NivelRiesgo clasificarImc(double imc) {
        if (imc < 18.5) {
            return NivelRiesgo.BAJO;
        }
        if (imc <= 24.9) {
            return NivelRiesgo.OPTIMO;
        }
        if (imc <= 29.9) {
            return NivelRiesgo.MODERADO;
        }
        return NivelRiesgo.CRITICO;
    }

    /**
     * Fatiga 0-100: 50% horas trabajadas, 30% carga de expedientes y 20% autopercepción inversa.
     */
    public double calcularFatiga(double horasTrabajadas, int cargaExpedientes, double autopercepcion) {
        double factorHoras = normalizar(horasTrabajadas, HORAS_REFERENCIA) * 0.50;
        double factorCarga = normalizar(cargaExpedientes, CARGA_REFERENCIA) * 0.30;
        double bienestar = Math.max(0.0, Math.min(100.0, autopercepcion));
        double factorAutopercepcionInversa = (100.0 - bienestar) * 0.20;
        double fatiga = factorHoras + factorCarga + factorAutopercepcionInversa;
        return BigDecimal.valueOf(fatiga).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    public NivelRiesgo clasificarFatiga(double fatiga) {
        if (fatiga >= UMBRAL_FATIGA_CRITICO) {
            return NivelRiesgo.CRITICO;
        }
        if (fatiga >= UMBRAL_FATIGA_MODERADO) {
            return NivelRiesgo.MODERADO;
        }
        return NivelRiesgo.BAJO;
    }

    private double normalizar(double valor, double referencia) {
        return Math.max(0.0, Math.min(100.0, (valor / referencia) * 100.0));
    }
}
