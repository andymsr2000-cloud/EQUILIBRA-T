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
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

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
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public Long getId() {
        return id;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public void setId(Long id) {
        this.id = id;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public String getCip() {
        return cip;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public void setCip(String cip) {
        this.cip = cip;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public String getDni() {
        return dni;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public void setDni(String dni) {
        this.dni = dni;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public String getNombre() {
        return nombre;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public void setNombre(String nombre) {
        this.nombre = nombre;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public String getDepartamento() {
        return departamento;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public RolUsuario getRol() {
        return rol;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public void setRol(RolUsuario rol) {
        this.rol = rol;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public double getNivelFatiga() {
        return nivelFatiga;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public void setNivelFatiga(double nivelFatiga) {
        this.nivelFatiga = nivelFatiga;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public String getPasswordHash() {
        return passwordHash;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}
    @Override
    public String toString() {
        return "Perito{id=" + id + ", cip='" + cip + '\'' + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + ", departamento='" + departamento + '\'' + ", rol=" + rol + ", nivelFatiga=" + nivelFatiga + '}';
    }

    /**
     * Verifies a plain‑text password against the stored hash.
     * This placeholder uses simple equality; replace with BCrypt in production.
     */
    public boolean verifyPassword(String plain) {
        if (plain == null) return false;
        return plain.equals(passwordHash);
    }

    /**
     * Updates the password hash.
     */
    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}
