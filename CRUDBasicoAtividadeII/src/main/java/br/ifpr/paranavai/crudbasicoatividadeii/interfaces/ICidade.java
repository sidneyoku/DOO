package br.ifpr.paranavai.crudbasicoatividadeii.interfaces;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Cidade;
import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Estado;
import java.util.List;

/**
 *
 * @author Sidney
 */
public interface ICidade {
    void save(Cidade cidade);
    void remove(Cidade cidade);	
    List<Object[]> getAll();	
    List<Cidade> getCidadeByNome(String nome);
    Cidade findById(Integer id);
    List<Cidade> getCidadeByIdEstado(Estado estado);
    Cidade getCidadeByNomeIdEstado(String nome, Estado estado);
    void init();
    
}
