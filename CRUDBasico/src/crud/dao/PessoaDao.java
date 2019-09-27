package crud.dao;

import crud.database.ConnectionManager;
import crud.entidade.Pessoa;
import crud.exceptions.PersistenceException;
import crud.interfaces.IPessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Sidney
 */
public class PessoaDao implements IPessoa {
    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS PESSOA (ID INT NOT NULL IDENTITY, NOME VARCHAR(255) NOT NULL UNIQUE, IDCIDADE INTEGER NOT NULL, IDESTADO INTEGER NOT NULL);";
    private final static String INSERT_PESSOA = "INSERT INTO PESSOA (NOME, IDESTADO, IDCIDADE) VALUES (?, ?, ?);";
    private final static String UPDATE_PESSOA = "UPDATE PESSOA SET NOME = ?, IDESTADO = ?, IDCIDADE = ? WHERE ID = ?;";
    private final static String DELETE_PESSOA = "DELETE FROM PESSOA WHERE ID = ?;";
    private final static String GET_ALL_PESSOA = "SELECT * FROM PESSOA;";
    private final static String GET_PESSOA_BY_NOME = "SELECT * FROM PESSOA WHERE NOME LIKE ?;";
    private final static String GET_PESSOA_BY_ID = "SELECT * FROM PESSOA WHERE ID = ?;";

    private static Logger log = Logger.getLogger(PessoaDao.class);

    @Override
    public void save(Pessoa pessoa) {
        if (pessoa == null)
            throw new PersistenceException("Informe a Pessoa para salvar!");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            
            if (pessoa.getId() == null) {
                stmt = getStatementInsert(conn, pessoa);
            } else {
                stmt = getStatementUpdate(conn, pessoa);
            }
            
            stmt.executeUpdate();
            conn.commit();
            log.debug("Pessoa foi salva.");
        } catch (SQLException e) {
            try { conn.rollback(); } catch (Exception sx) {}
            String errorMsg = "Erro ao salvar Pessoa!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    @Override
    public void remove(Pessoa pessoa) {
        if (pessoa == null || pessoa.getId() == null)
            throw new PersistenceException("Informe a pessoa para exclusão!");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, DELETE_PESSOA);
            stmt.setInt(1, pessoa.getId());
            stmt.executeUpdate();
            conn.commit();
            log.debug("Pessoa foi excluída.");
        } catch (SQLException e) {
            try { conn.rollback(); } catch (Exception sx) {}
            String errorMsg = "Erro ao excluir Pessoa!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    @Override
    public List<Pessoa> getAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_ALL_PESSOA);
            rs = stmt.executeQuery();

            return toPessoas(rs);
        } catch (SQLException e) {
            String errorMsg = "Erro ao consultar todas as pessoas!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    @Override
    public List<Pessoa> getEstadosByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa findById(Integer id) {
        if (id == null || id.intValue() <= 0) {
            throw new PersistenceException("Informe o id válido para fazer a busca!");
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pessoa pessoa = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_PESSOA_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NOME");
                int idEstado = rs.getInt("IDESTADO");
                int idCidade = rs.getInt("IDCIDADE");
                
                pessoa = new Pessoa(id, nome, idEstado, idCidade);
            }
            
            return pessoa;
        } catch (SQLException e) {
            String errorMsg = "Erro ao consultar Pessoa por id!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    @Override
    public void init() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            int r = stmt.executeUpdate(CREATE_TABLE);

            if (r > 0) {
                log.info("Criou a tabela 'pessoa'");
            }
        } catch (SQLException e) {
            log.error(e);
            throw new PersistenceException("Não foi possivel inicializar o banco de dados: " + CREATE_TABLE, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }
    
    private PreparedStatement getStatementInsert(Connection conn, Pessoa pessoa) throws SQLException {
        PreparedStatement stmt = createStatementWithLog(conn, INSERT_PESSOA);
        stmt.setString(1, pessoa.getNome());
        stmt.setInt(2, pessoa.getIdEstado());
        stmt.setInt(3, pessoa.getIdCidade());
        return stmt;
    }
	
    private PreparedStatement getStatementUpdate(Connection conn, Pessoa pessoa) throws SQLException {
        PreparedStatement stmt = createStatementWithLog(conn, UPDATE_PESSOA);
        stmt.setString(1, pessoa.getNome());
        stmt.setInt(2, pessoa.getIdEstado());
        stmt.setInt(3, pessoa.getIdCidade());
        stmt.setInt(4, pessoa.getId());
        return stmt;
    }
    
    private static PreparedStatement createStatementWithLog(Connection conn, String sql) throws SQLException{
        if (conn == null)
            return null;

        log.debug("SQL: "+sql);
        return conn.prepareStatement(sql);
    }
    
    private List<Pessoa> toPessoas(ResultSet rs) throws SQLException {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        
        while (rs.next()) {
            int id = rs.getInt("ID");
            String nome = rs.getString("NOME");
            int idEstado = rs.getInt("IDESTADO");
            int idCidade = rs.getInt("IDCIDADE");
            
            lista.add(new Pessoa(id, nome, idEstado, idCidade));
        }
        
        return lista;
    }
    
}
