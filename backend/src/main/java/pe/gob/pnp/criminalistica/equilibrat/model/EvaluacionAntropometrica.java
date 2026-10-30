
package pe.gob.pnp.criminalistica.equilibrat.model;

import java.time.LocalDateTime;

/**
 * Representa el registro de una evaluación antropométrica
 * realizada a un colaborador.
 *
 * Almacena sus datos físicos, el IMC obtenido,
 * el nivel de riesgo asociado y la fecha de evaluación.
 */
public class EvaluacionAntropometrica {

    private Long id;
    private String dni;
    private double pesoKg;
    private double tallaM;
    private double imc;
    private NivelRiesgo nivelRiesgo;
    private LocalDateTime fechaRegistro;

    // Constructor vacío
    public EvaluacionAntropometrica() {
    }

    // Constructor completo
    public EvaluacionAntropometrica(Long id, String dni, double pesoKg, double tallaM,
                                    double imc, NivelRiesgo nivelRiesgo, LocalDateTime fechaRegistro) {
        this.id = id;
        this.dni = dni;
        this.pesoKg = pesoKg;
        this.tallaM = tallaM;
        this.imc = imc;
        this.nivelRiesgo = nivelRiesgo;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public double getTallaM() {
        return tallaM;
    }

    public void setTallaM(double tallaM) {
        this.tallaM = tallaM;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public NivelRiesgo getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(NivelRiesgo nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Calcula el índice de masa corporal (IMC)
     * utilizando el peso y la talla registrados.
     *
     * Fórmula: peso / (talla * talla).
     */
    public double calcularImc() {
        if (tallaM <= 0) {
            throw new IllegalStateException("La talla debe ser mayor que cero.");
        }

        return pesoKg / (tallaM * tallaM);
    }

    /**
     * Actualiza el IMC almacenado utilizando
     * los valores actuales de peso y talla.
     */
    public void actualizarImc() {
        this.imc = calcularImc();
    }
}