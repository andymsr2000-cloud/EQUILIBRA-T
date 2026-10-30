package pe.gob.pnp.criminalistica.equilibrat.dao;

import com.google.common.collect.ImmutableList;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import pe.gob.pnp.criminalistica.equilibrat.model.Perito;
import pe.gob.pnp.criminalistica.equilibrat.model.RolUsuario;
import pe.gob.pnp.criminalistica.equilibrat.util.CriptografiaUtil;

public class InMemoryPeritoDAO implements PeritoDAO {

    static final String CLAVE_DEMO = "EquilibraT2025";

    private final ConcurrentHashMap<Long, Perito> almacen = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public InMemoryPeritoDAO() {
        String hash = CriptografiaUtil.hashearPassword(CLAVE_DEMO);
        
        // Datos semilla institucionales para el piloto
        guardar(new Perito(null, "10000001", "40000001", "Crnl. Jorge Velásquez", "Jefatura Criminalística", RolUsuario.JEFATURA, 0.0, hash));
        guardar(new Perito(null, "20000001", "40000002", "Rosa Linares", "Biología Forense", RolUsuario.PERITO, 65.0, hash));
        guardar(new Perito(null, "20000002", "40000003", "Marcos Castillo", "Balística", RolUsuario.PERITO, 45.0, hash));
        guardar(new Perito(null, "20000003", "40000004", "Elena Ríos", "Química", RolUsuario.PERITO, 20.0, hash));
    }

    @Override
    public Optional<Perito> buscarPorId(Long id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public Optional<Perito> buscarPorCip(String cip) {
        return almacen.values().stream()
                .filter(perito -> perito.getCip().equals(cip))
                .findFirst();
    }

    @SuppressWarnings("null")
    @Override
    public ImmutableList<Perito> listarTodos() {
        return ImmutableList.copyOf(almacen.values());
    }

    @Override
    public Perito guardar(Perito perito) {
        if (perito.getId() == null) {
            perito.setId(secuencia.getAndIncrement());
        }
        almacen.put(perito.getId(), perito);
        return perito;
    }
}