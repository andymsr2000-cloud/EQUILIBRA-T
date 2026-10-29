package pe.gob.pnp.criminalistica.equilibrat.model;

import java.time.LocalDateTime;

/**
 * Registro de evaluación antropométrica de un colaborador.
 * Almacena peso, talla, IMC calculado y el nivel de riesgo asociado.
 */
public class EvaluacionAntropometrica {

    private Long id;
    private String dni;
    private double pesoKg;
    private double tallaM;
    private double imc;
    private NivelRiesgo nivelRiesgo;
    private LocalDateTime fechaRegistro;

    public EvaluacionAntropometrica() {
    }

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
}
