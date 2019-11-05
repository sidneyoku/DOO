package br.ifpr.paranavai.crudbasicoatividadeii.interfaces;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.ItemPedido;
import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Pedido;
import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Produto;
import java.util.List;

/**
 *
 * @author Sidney
 */
public interface IItemPedido {
    void save(ItemPedido itemPedido);
    void remove(ItemPedido itemPedido);	
    List<Object[]> getAll();	
    List<ItemPedido> getItemPedidosByNome(String nome);
    List<ItemPedido> getByPedidos(Pedido pedido);
    ItemPedido findById(Integer id);
    ItemPedido findProduto(Produto produto, Pedido pedido);
    int findUltimoId();
    void init();  
    
}
