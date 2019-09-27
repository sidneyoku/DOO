package crud.interfaces;

import crud.entidade.Pessoa;
import java.util.List;

/**
 *
 * @author Sidney
 */
public interface IPessoa {
    void save(Pessoa pessoa);
    void remove(Pessoa pessoa);	
    List<Pessoa> getAll();	
    List<Pessoa> getEstadosByNome(String nome);
    Pessoa findById(Integer id);
    void init();
    
}
