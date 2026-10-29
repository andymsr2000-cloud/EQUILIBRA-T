package pe.gob.pnp.criminalistica.equilibrat.model;

import java.time.LocalDateTime;

/**
 * Alerta de bienestar ocupacional generada a partir de fatiga o IMC crítico/moderado.
 */
public class Alerta {

    private Long id;
    private Long peritoId;
    private String tipo;
    private String mensaje;
    private NivelRiesgo nivelRiesgo;
    private LocalDateTime fecha;
    private boolean atendida;

    public Alerta() {
    }

    public Alerta(Long id, Long peritoId, String tipo, String mensaje,
                  NivelRiesgo nivelRiesgo, LocalDateTime fecha, boolean atendida) {
        this.id = id;
        this.peritoId = peritoId;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.nivelRiesgo = nivelRiesgo;
        this.fecha = fecha;
        this.atendida = atendida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPeritoId() {
        return peritoId;
    }

    public void setPeritoId(Long peritoId) {
        this.peritoId = peritoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public NivelRiesgo getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(NivelRiesgo nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }
}
