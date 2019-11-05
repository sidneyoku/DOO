package br.ifpr.paranavai.crudbasicoatividadeii.interfaces;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Pedido;
import java.util.List;

/**
 *
 * @author Sidney
 */
public interface IPedido {
    void save(Pedido pedido);
    void remove(Pedido pedido);	
    List<Object[]> getAll();	
    List<Pedido> getPedidossByNome(String nome);
    Pedido findById(Integer id);
    int findUltimoId();
    void init();    
}
