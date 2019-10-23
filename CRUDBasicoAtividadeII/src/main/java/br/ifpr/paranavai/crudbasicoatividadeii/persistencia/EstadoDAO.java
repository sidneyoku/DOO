package br.ifpr.paranavai.crudbasicoatividadeii.persistencia;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Estado;
import br.ifpr.paranavai.crudbasicoatividadeii.exceptions.PersistenceException;
import br.ifpr.paranavai.crudbasicoatividadeii.interfaces.IEstado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Sidney
 */
public class EstadoDAO implements IEstado {
    private static EntityManager em = EntityManagerProvider.getInstancia();

    @Override
    public void save(Estado estado) {
        try {
            em.getTransaction().begin();
            em.merge(estado);
            em.getTransaction().commit();
        } catch(PersistenceException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public void remove(Estado estado) {
        try {
            Estado estadoRemover = findById(estado.getId());
            em.getTransaction().begin();
            em.remove(estadoRemover);
            em.flush();
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public List<Object[]> getAll() {
        return em.createNamedQuery("Estado.getAll").getResultList();
    }

    @Override
    public List<Estado> getEstadosByNome(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estado getEstadoByNome(String nome) {
        String queryStr = "SELECT e.id, e.sigla, e.nome FROM Estado e WHERE e.nome = :pNome";
        Query query = em.createQuery(queryStr);
        query.setParameter("pNome", nome);
        List<Object[]> rows = query.getResultList();
        List<Estado> result = new ArrayList<>(rows.size());
        
        for (Object[] row : rows) {
            result.add(new Estado((int)row[0], (String)row[1], (String)row[2]));
        }
        
        return result.get(0);
//        return em.find(Estado.class, nome);
    }

    @Override
    public Estado findById(Integer id) {
        return em.find(Estado.class, id);
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getAllStr() {
        return em.createNamedQuery("Estado.getAllStr").getResultList();
    }
    
}
