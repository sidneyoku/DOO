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
@NamedQuery(name="Pedido.getData", query="select p.dataPedido from Pedido p"),
@NamedQuery(name="Pedido.getAll", query="select p.id, p.dataPedido from Pedido p"),
@NamedQuery(name="Pedido.getUltimo", query="select max(p.id) from Pedido p")
})
//@NamedQuery(name="Pedido.getUltimo", query="select max(p.id)p.id, p.dataPedido, p.qtd from Pedido p order by p.id desc")
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String dataPedido;
    
//    @OneToOne(cascade = CascadeType.PERSIST)
//    private Pessoa pessoa;
    
//    @OneToMany(mappedBy = "pedido", cascade = CascadeType.MERGE)
//    private List<Produto> produtos;

    public Pedido() {}

    public Pedido(Integer id, String dataPedido, Integer qtd) {
        this.id = id;
        this.dataPedido = dataPedido;
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
     * @return the dataPedido
     */
    public String getDataPedido() {
        return dataPedido;
    }

    /**
     * @param dataPedido the dataPedido to set
     */
    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

}
