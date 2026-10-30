package pe.gob.pnp.criminalistica.equilibrat.model;

/**
 * Clasificación semafórica de riesgo ocupacional (IMC o fatiga).
 */
public enum NivelRiesgo {
    /**
     * Human‑readable description of each risk level.
     */
    BAJO("Bajo riesgo"),
    OPTIMO("Riesgo óptimo"),
    MODERADO("Riesgo moderado"),
    CRITICO("Riesgo crítico");

    private final String description;

    NivelRiesgo(String description) {
        this.description = description;
    }

    /**
     * @return description associated to the enum constant.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the enum constant matching the supplied label, case‑insensitive.
     * If no match is found, returns {@code null}.
     */
    public static NivelRiesgo fromLabel(String label) {
        if (label == null) return null;
        for (NivelRiesgo nr : values()) {
            if (nr.name().equalsIgnoreCase(label) || nr.description.equalsIgnoreCase(label)) {
                return nr;
            }
        }
        return null;
    }

    BAJO,
    OPTIMO,
    MODERADO,
    CRITICO
}
