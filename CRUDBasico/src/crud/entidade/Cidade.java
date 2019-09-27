package crud.entidade;

/**
 *
 * @author Sidney
 */
public class Cidade {
    
    private Integer id;
    private String nome;
    private int idEstado;

    public Cidade(Integer id, String nome, int idEstado) {
        this.id = id;
        this.nome = nome;
        this.idEstado = idEstado;
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
     * @return the idEstado
     */
    public int getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado the idEstado to set
     */
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
    
    
    
}
