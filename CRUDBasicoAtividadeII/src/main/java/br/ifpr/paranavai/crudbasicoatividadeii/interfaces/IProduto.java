package br.ifpr.paranavai.crudbasicoatividadeii.interfaces;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Produto;
import java.util.List;

/**
 *
 * @author Sidney
 */
public interface IProduto {
    void save(Produto produto);
    void remove(Produto produto);	
    List<Object[]> getAll();	
    List<Produto> getProdutosByNome(String nome);
    Produto findById(Integer id);
    void init();
}
