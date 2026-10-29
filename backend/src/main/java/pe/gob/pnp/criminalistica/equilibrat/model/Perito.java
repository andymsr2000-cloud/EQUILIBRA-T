package pe.gob.pnp.criminalistica.equilibrat.model;

/**
 * Entidad que representa a un colaborador de Criminalística PNP.
 * <p>
 * Conserva identificadores institucionales (CIP/DNI), adscripción orgánica,
 * rol de acceso y el índice de fatiga vigente (0-100).
 * </p>
 */
public class Perito {

    private Long id;
    private String cip;
    private String dni;
    private String nombre;
    private String departamento;
    private RolUsuario rol;
    private double nivelFatiga;
    private String passwordHash;

    public Perito() {
    }

    public Perito(Long id, String cip, String dni, String nombre, String departamento,
                  RolUsuario rol, double nivelFatiga, String passwordHash) {
        this.id = id;
        this.cip = cip;
        this.dni = dni;
        this.nombre = nombre;
        this.departamento = departamento;
        this.rol = rol;
        this.nivelFatiga = nivelFatiga;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public double getNivelFatiga() {
        return nivelFatiga;
    }

    public void setNivelFatiga(double nivelFatiga) {
        this.nivelFatiga = nivelFatiga;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
