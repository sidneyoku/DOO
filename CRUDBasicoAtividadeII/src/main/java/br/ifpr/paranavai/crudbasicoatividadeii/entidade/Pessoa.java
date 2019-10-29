package br.ifpr.paranavai.crudbasicoatividadeii.entidade;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Sidney
 */

@Entity
@NamedQueries({
@NamedQuery(name="Pessoa.getNome", query="select p.nome from Pessoa p"),
@NamedQuery(name="Pessoa.getAll", query="select p.id, p.nome, p.estado, p.cidade from Pessoa p"),
})
public class Pessoa implements Serializable {
    
    @Id 
    @GeneratedValue
    private Integer id;
    private String nome;
    @OneToOne(cascade=CascadeType.PERSIST)
    private Estado estado;
    @OneToOne(cascade=CascadeType.PERSIST)
    private Cidade cidade;
    private String rg;
    private String cpf;

    public Pessoa() {}
    
    public Pessoa(Integer id, String nome, Estado estado, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.cidade = cidade;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * @return the cidade
     */
    public Cidade getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
}
