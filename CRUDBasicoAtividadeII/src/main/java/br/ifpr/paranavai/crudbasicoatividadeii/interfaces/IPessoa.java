package br.ifpr.paranavai.crudbasicoatividadeii.interfaces;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Pessoa;

import java.util.List;

/**
 *
 * @author Sidney
 */
public interface IPessoa {
    void save(Pessoa pessoa);
    void remove(Pessoa pessoa);	
    List<Object[]> getAll();	
    List<Pessoa> getEstadosByNome(String nome);
    Pessoa findById(Integer id);
    void init();   
}
