package br.ifpr.paranavai.crudbasicoatividadeii.persistencia;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Pedido;
import br.ifpr.paranavai.crudbasicoatividadeii.exceptions.PersistenceException;
import br.ifpr.paranavai.crudbasicoatividadeii.interfaces.IPedido;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sidney
 */
public class PedidoDAO implements IPedido {
    private static EntityManager em = EntityManagerProvider.getInstancia();
    
    @Override
    public void save(Pedido pedido) {
        em.getTransaction().begin();
        em.merge(pedido);
        em.getTransaction().commit();
    }

    @Override
    public void remove(Pedido pedido) {
        try {
            Pedido pedidoRemover = findById(pedido.getId());
            em.getTransaction().begin();
            em.remove(pedidoRemover);
            em.flush();
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public List<Object[]> getAll() {
        return em.createNamedQuery("Pedido.getAll").getResultList();
    }

    @Override
    public List<Pedido> getPedidossByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido findById(Integer id) {
        return em.find(Pedido.class, id);
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int findUltimoId() {
//        return em.createNamedQuery("Pedido.getUltimo").getFirstResult();
        Object o = em.createNamedQuery("Pedido.getUltimo").getSingleResult();
        return o.hashCode();
    }
    
}
