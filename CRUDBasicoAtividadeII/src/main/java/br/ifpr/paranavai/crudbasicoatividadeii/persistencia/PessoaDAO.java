package br.ifpr.paranavai.crudbasicoatividadeii.persistencia;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Pessoa;
import br.ifpr.paranavai.crudbasicoatividadeii.exceptions.PersistenceException;
import br.ifpr.paranavai.crudbasicoatividadeii.interfaces.IPessoa;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sidney
 */
public class PessoaDAO implements IPessoa {    
    private static EntityManager em = EntityManagerProvider.getInstancia();
    
    @Override
    public void save(Pessoa pessoa) {
        try {
            em.getTransaction().begin();
            em.merge(pessoa);
            em.getTransaction().commit();
        } catch(PersistenceException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public void remove(Pessoa pessoa) {
        try {
            Pessoa pessoaRemover = findById(pessoa.getId());
            em.getTransaction().begin();
            em.remove(pessoaRemover);
            em.flush();
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public List<Object[]> getAll() {
        return em.createNamedQuery("Pessoa.getAll").getResultList();
    }

    @Override
    public List<Pessoa> getEstadosByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa findById(Integer id) {
        return em.find(Pessoa.class, id);
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
