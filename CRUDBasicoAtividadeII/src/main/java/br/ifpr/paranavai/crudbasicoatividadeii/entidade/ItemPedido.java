package br.ifpr.paranavai.crudbasicoatividadeii.entidade;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Sidney
 */

@Entity
@NamedQueries({
    @NamedQuery(name="ItemPedido.getAll", query="select ip.id, ip.pedido, ip.produtos, ip.qtd from ItemPedido ip"),
    @NamedQuery(name="ItemPedido.getUltimo", query="select max(ip.id) from ItemPedido ip")
})
public class ItemPedido implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Pedido pedido;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private Produto produtos;
    
    private Integer qtd;

    public ItemPedido() {}

    public ItemPedido(Integer id, Pedido pedido, Produto produtos, Integer qtd) {
        this.id = id;
        this.pedido = pedido;
        this.produtos = produtos;
        this.qtd = qtd;
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
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the produtos
     */
    public Produto getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(Produto produtos) {
        this.produtos = produtos;
    }

    /**
     * @return the qtd
     */
    public Integer getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }
    
    
    
}
