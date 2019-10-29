package br.ifpr.paranavai.crudbasicoatividadeii.entidade;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Sidney
 */

@Entity
@NamedQueries({
@NamedQuery(name="Produto.getNome", query="select p.nome from Produto p"),
@NamedQuery(name="Produto.getAll", query="select p.id, p.nome, p.descricao, p.qtdEntrada, p.qtdSaida, p.codigoBarras from Produto p")
})
public class Produto implements Serializable {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String descricao;
    private Integer qtdEntrada;
    private Integer qtdSaida;
    private String codigoBarras;

    public Produto() {}

    public Produto(Integer id, String nome, String descricao, Integer qtdEntrada, Integer qtdSaida, String codigoBarras) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.qtdEntrada = qtdEntrada;
        this.qtdSaida = qtdSaida;
        this.codigoBarras = codigoBarras;
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the qtdEntrada
     */
    public Integer getQtdEntrada() {
        return qtdEntrada;
    }

    /**
     * @param qtdEntrada the qtdEntrada to set
     */
    public void setQtdEntrada(Integer qtdEntrada) {
        this.qtdEntrada = qtdEntrada;
    }

    /**
     * @return the qtdSaida
     */
    public Integer getQtdSaida() {
        return qtdSaida;
    }

    /**
     * @param qtdSaida the qtdSaida to set
     */
    public void setQtdSaida(Integer qtdSaida) {
        this.qtdSaida = qtdSaida;
    }

    /**
     * @return the codigoBarras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * @param codigoBarras the codigoBarras to set
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    
}
