package br.ifpr.paranavai.crudbasicoatividadeii.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Sidney
 */

@Entity
@NamedQueries({
@NamedQuery(name="Estado.getSiglas", query="select e.sigla from Estado e"),
@NamedQuery(name="Estado.getNomes", query="select e.nome from Estado e"),
@NamedQuery(name="Estado.getAll", query="select e.sigla, e.nome from Estado e"),
})
public class Estado {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String sigla;
    
    @OneToMany(mappedBy="estado", cascade=CascadeType.MERGE, orphanRemoval=true)
    private List<Cidade> cidades;
    
    public Estado() {}

    public Estado(Integer id, String sigla, String nome) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
    
}
