
package pe.gob.pnp.criminalistica.equilibrat.model;

import java.time.LocalDate;

/**
 * Representa la jornada laboral de un perito.
 *
 * Registra las horas trabajadas, la cantidad de expedientes
 * asignados y la autopercepción de bienestar del colaborador,
 * permitiendo realizar un seguimiento de su carga laboral.
 */
public class Jornada {

    private Long id;
    private Long peritoId;
    private double horasTrabajadas;
    private int cargaExpedientes;
    private double autopercepcion;
    private LocalDate fecha;

    // Constructor vacío
    public Jornada() {
    }

    // Constructor completo
    public Jornada(Long id, Long peritoId, double horasTrabajadas, int cargaExpedientes,
                   double autopercepcion, LocalDate fecha) {
        this.id = id;
        this.peritoId = peritoId;
        this.horasTrabajadas = horasTrabajadas;
        this.cargaExpedientes = cargaExpedientes;
        this.autopercepcion = autopercepcion;
        this.fecha = fecha;
    }

    // Getters y Setters
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

    /**
     * Comprueba si la jornada supera una cantidad
     * determinada de horas trabajadas.
     *
     * @param limiteHoras límite de horas de referencia.
     * @return true si las horas trabajadas superan el límite.
     */
    public boolean superaLimiteHoras(double limiteHoras) {
        return horasTrabajadas > limiteHoras;
    }

    /**
     * Comprueba si la jornada tiene expedientes asignados.
     *
     * @return true si existe al menos un expediente.
     */
    public boolean tieneExpedientes() {
        return cargaExpedientes > 0;
    }
}
