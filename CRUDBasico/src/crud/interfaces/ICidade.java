package crud.interfaces;

import crud.entidade.Cidade;
import java.util.List;

/**
 *
 * @author Sidney
 */
public interface ICidade {
    void save(Cidade cidade);
    void remove(Cidade cidade);	
    List<Cidade> getAll();	
    List<Cidade> getCidadeByNome(String nome);
    Cidade findById(Integer id);
    List<Cidade> getCidadeByIdEstado(Integer idEstado);
    Cidade getCidadeByNomeIdEstado(String nome, Integer idEstado);
    void init();
}
