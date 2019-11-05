package br.ifpr.paranavai.crudbasicoatividadeii.persistencia;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.ItemPedido;
import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Pedido;
import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Produto;
import br.ifpr.paranavai.crudbasicoatividadeii.exceptions.PersistenceException;
import br.ifpr.paranavai.crudbasicoatividadeii.interfaces.IItemPedido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Sidney
 */
public class ItemPedidoDAO implements IItemPedido {
    private static EntityManager em = EntityManagerProvider.getInstancia();

    @Override
    public void save(ItemPedido itemPedido) {
        em.getTransaction().begin();
        em.merge(itemPedido);
        em.getTransaction().commit();
    }

    @Override
    public void remove(ItemPedido itemPedido) {
        try {
//            ItemPedido itemPedidoRemover = findById(itemPedido.getId());
            em.getTransaction().begin();
            ItemPedido copy = em.merge(itemPedido);
            em.remove(copy);
            em.flush();
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public List<Object[]> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemPedido> getItemPedidosByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemPedido findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemPedido findProduto(Produto produto, Pedido pedido) {
        String queryStr = "SELECT ip.id, ip.pedido, ip.produtos, ip.qtd FROM ItemPedido ip "
                + "WHERE ip.produtos = :pProduto AND ip.pedido = :pPedido";
        Query query = em.createQuery(queryStr);
        query.setParameter("pProduto", produto);
        query.setParameter("pPedido", pedido);
        List<Object[]> rows = query.getResultList();
        List<ItemPedido> result = new ArrayList<>(rows.size());
        
        for (Object[] row : rows) {
            result.add(new ItemPedido((int)row[0], (Pedido)row[1], (Produto)row[2], (int)row[3]));
//            result.add(new ItemPedido((int)row[0], null, null, null));
        }
        
        return result.get(0);
//        return em.find(ItemPedido.class, produto.getId());
    }

    @Override
    public List<ItemPedido> getByPedidos(Pedido pedido) {
        String queryStr = "SELECT ip.id, ip.pedido, ip.produtos, ip.qtd FROM ItemPedido ip WHERE ip.pedido = :pPedido";
        Query query = em.createQuery(queryStr);
        query.setParameter("pPedido", pedido);
        List<Object[]> rows = query.getResultList();
        List<ItemPedido> result = new ArrayList<>(rows.size());
        
        for (Object[] row : rows) {
            result.add(new ItemPedido((int)row[0], (Pedido)row[1], (Produto)row[2], (int)row[3]));
        }
        
        return result;
    }
    
    @Override
    public int findUltimoId() {
        Object o = em.createNamedQuery("ItemPedido.getUltimo").getSingleResult();
        return o.hashCode();
    }
    
}
