/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpr.paranavai.crudbasicoatividadeii.persistencia;

import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Cidade;
import br.ifpr.paranavai.crudbasicoatividadeii.entidade.Estado;
import br.ifpr.paranavai.crudbasicoatividadeii.exceptions.PersistenceException;
import br.ifpr.paranavai.crudbasicoatividadeii.interfaces.ICidade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Sidnei
 */
public class CidadeDAO implements ICidade {
    private static EntityManager em = EntityManagerProvider.getInstancia();

    @Override
    public void save(Cidade cidade) {
        Estado estado = em.getReference(Estado.class, cidade.getIdEstado().getId());
        cidade.setIdEstado(estado);
        em.getTransaction().begin();
//        em.persist(cidade);
        em.merge(cidade);
        em.getTransaction().commit();
    }

    @Override
    public void remove(Cidade cidade) {
        try {
            Cidade cidadeRemover = findById(cidade.getId());
            em.getTransaction().begin();
            em.remove(cidadeRemover);
            em.flush();
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public List<Object[]> getAll() {
        return em.createNamedQuery("Cidade.getAll").getResultList();
    }

    @Override
    public List<Cidade> getCidadeByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cidade findById(Integer id) {
        return em.find(Cidade.class, id);
    }

    @Override
    public List<Cidade> getCidadeByIdEstado(Estado estado) {
//        String queryStr = "SELECT c.id, c.estado, c.nome FROM Cidade c WHERE c.estado = :pIdEstado";
        String queryStr = "SELECT c.id, c.nome FROM Cidade c WHERE c.estado = :pIdEstado";
        Query query = em.createQuery(queryStr);
        query.setParameter("pIdEstado", estado);
        List<Object[]> rows = query.getResultList();
        List<Cidade> result = new ArrayList<>(rows.size());
        
        for (Object[] row : rows) {
            result.add(new Cidade((Integer)row[0], (String)row[1], null));
        }
        
        return result;
//        return em.find(Estado.class, nome);
    }

    @Override
    public Cidade getCidadeByNomeIdEstado(String nome, Estado estado) {
//        private final static String GET_CIDADE_BY_NOME_IDESTADO = "SELECT * FROM CIDADE WHERE NOME = ? AND IDESTADO = ?;";
        String queryStr = "SELECT c.id, c.nome FROM Cidade c WHERE c.nome = :pNome AND c.estado = :pIdEstado";
        Query query = em.createQuery(queryStr);
        query.setParameter("pNome", nome);
        query.setParameter("pIdEstado", estado);
        List<Object[]> rows = query.getResultList();
        List<Cidade> result = new ArrayList<>(rows.size());
        
        for (Object[] row : rows) {
            result.add(new Cidade((Integer)row[0], (String)row[1], null));
        }
        
        return result.get(0);
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
