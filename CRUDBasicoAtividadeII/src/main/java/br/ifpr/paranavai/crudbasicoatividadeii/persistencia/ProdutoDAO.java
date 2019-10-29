package br.ifpr.paranavai.crudbasicoatividadeii.persistencia;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Produto;
import br.ifpr.paranavai.crudbasicoatividadeii.exceptions.PersistenceException;
import br.ifpr.paranavai.crudbasicoatividadeii.interfaces.IProduto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sidney
 */
public class ProdutoDAO implements IProduto {
    private static EntityManager em = EntityManagerProvider.getInstancia();

    @Override
    public void save(Produto produto) {
        em.getTransaction().begin();
//        em.persist(cidade);
        em.merge(produto);
        em.getTransaction().commit();
    }

    @Override
    public void remove(Produto produto) {
        try {
            Produto produtoRemover = findById(produto.getId());
            em.getTransaction().begin();
            em.remove(produtoRemover);
            em.flush();
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public List<Object[]> getAll() {
        return em.createNamedQuery("Produto.getAll").getResultList();
    }

    @Override
    public List<Produto> getProdutosByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produto findById(Integer id) {
        return em.find(Produto.class, id);
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
