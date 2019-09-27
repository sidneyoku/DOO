package crud.interfaces;

import crud.entidade.Estado;
import java.util.List;

/**
 *
 * @author Sidney
 */
public interface IEstado {
    void save(Estado estado);
    void remove(Estado estado);	
    List<Estado> getAll();	
    List<Estado> getEstadosByNome(String nome);
    Estado getEstadoByNome(String nome);
    Estado findById(Integer id);
    void init();
}
