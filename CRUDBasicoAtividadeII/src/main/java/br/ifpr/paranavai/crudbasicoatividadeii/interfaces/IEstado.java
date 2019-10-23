package br.ifpr.paranavai.crudbasicoatividadeii.interfaces;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Estado;
import java.util.List;

/**
 *
 * @author Sidney
 */
public interface IEstado {
    void save(Estado estado);
    void remove(Estado estado);	
    List<Object[]> getAll();
    List<String> getAllStr();
    List<Estado> getEstadosByNome(String nome);
    Estado getEstadoByNome(String nome);
    Estado findById(Integer id);
    void init();
}
