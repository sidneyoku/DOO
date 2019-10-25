package br.ifpr.paranavai.crudbasicoatividadeii.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Sidney
 */

@Entity
@NamedQueries({
    @NamedQuery(name="Cidade.getEstado", query="select c.estado from Cidade c"),
    @NamedQuery(name="Cidade.getNomes", query="select c.nome from Cidade c"),
    @NamedQuery(name="Cidade.getAll", query="select c.id, c.estado, c.nome from Cidade c")
})
public class Cidade {
    
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Estado estado;
    
    public Cidade(){}

    public Cidade(Integer id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
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

    /**
     * @return the Estado
     */
    public Estado getIdEstado() {
        return estado;
    }

    /**
     * @param Estado the idEstado to set
     */
    public void setIdEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    
}
