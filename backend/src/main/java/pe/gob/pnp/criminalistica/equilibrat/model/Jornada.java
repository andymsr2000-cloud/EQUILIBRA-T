package pe.gob.pnp.criminalistica.equilibrat.model;

import java.time.LocalDate;

/**
 * Jornada laboral de un perito: horas, carga de expedientes y autopercepción de bienestar.
 */
public class Jornada {

    private Long id;
    private Long peritoId;
    private double horasTrabajadas;
    private int cargaExpedientes;
    private double autopercepcion;
    private LocalDate fecha;

    public Jornada() {
    }

    public Jornada(Long id, Long peritoId, double horasTrabajadas, int cargaExpedientes,
                   double autopercepcion, LocalDate fecha) {
        this.id = id;
        this.peritoId = peritoId;
        this.horasTrabajadas = horasTrabajadas;
        this.cargaExpedientes = cargaExpedientes;
        this.autopercepcion = autopercepcion;
        this.fecha = fecha;
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

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public int getCargaExpedientes() {
        return cargaExpedientes;
    }

    public void setCargaExpedientes(int cargaExpedientes) {
        this.cargaExpedientes = cargaExpedientes;
    }

    public double getAutopercepcion() {
        return autopercepcion;
    }

    public void setAutopercepcion(double autopercepcion) {
        this.autopercepcion = autopercepcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
