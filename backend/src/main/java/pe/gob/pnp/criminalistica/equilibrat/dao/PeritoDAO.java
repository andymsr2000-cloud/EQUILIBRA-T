package pe.gob.pnp.criminalistica.equilibrat.dao;

import java.util.List;
import java.util.Optional;
import pe.gob.pnp.criminalistica.equilibrat.model.Perito;

/**
 * Contrato de persistencia de peritos
 */
public interface PeritoDAO {

    Optional<Perito> buscarPorId(Long id);

    Optional<Perito> buscarPorCip(String cip);

    List<Perito> listarTodos();

    Perito guardar(Perito perito);
}
