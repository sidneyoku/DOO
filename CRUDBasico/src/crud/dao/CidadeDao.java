package crud.dao;

import crud.database.ConnectionManager;
import crud.entidade.Cidade;
import crud.exceptions.PersistenceException;
import crud.interfaces.ICidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Sidney
 */
public class CidadeDao implements ICidade {
    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS CIDADE (ID INT NOT NULL IDENTITY, NOME VARCHAR(255) NOT NULL UNIQUE, IDESTADO INTEGER NOT NULL);";
    private final static String INSERT_CIDADE = "INSERT INTO CIDADE (NOME, IDESTADO) values(?, ?);";
    private final static String UPDATE_CIDADE = "UPDATE CIDADE SET NOME = ?, IDESTADO = ? WHERE ID = ?;";
    private final static String DELETE_CIDADE = "DELETE FROM CIDADE WHERE ID = ?;";
    private final static String GET_ALL_CIDADE = "SELECT * FROM CIDADE;";
    private final static String GET_CIDADES_BY_NOME = "SELECT * FROM CIDADE WHERE NOME LIKE ?;";
    private final static String GET_CIDADE_BY_ID = "SELECT * FROM CIDADE WHERE ID = ?;";
    private final static String GET_CIDADE_BY_IDESTADO = "SELECT * FROM CIDADE WHERE IDESTADO = ?;";
    private final static String GET_CIDADE_BY_NOME_IDESTADO = "SELECT * FROM CIDADE WHERE NOME = ? AND IDESTADO = ?;";

    private static Logger log = Logger.getLogger(CidadeDao.class);

    @Override
    public void save(Cidade cidade) {
        if (cidade == null)
            throw new PersistenceException("Informe a Cidade para salvar!");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionManager.getConnection();
            
            if (cidade.getId() == null) {
                stmt = getStatementInsert(conn, cidade);
            } else {
                stmt = getStatementUpdate(conn, cidade);
            }
            
            stmt.executeUpdate();
            conn.commit();
            log.debug("Cidade foi salva");
        } catch (SQLException e) {
            try { conn.rollback(); } catch (Exception sx) {}
            String errorMsg = "Erro ao salvar Cidade!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    @Override
    public void remove(Cidade cidade) {
        if (cidade == null || cidade.getId() == null)
            throw new PersistenceException("Informe a cidade para exclusão!");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, DELETE_CIDADE);
            stmt.setInt(1, cidade.getId());
            stmt.executeUpdate();
            conn.commit();
            log.debug("Cidade foi excluído");
        } catch (SQLException e) {
            try { conn.rollback(); } catch (Exception sx) {}
            String errorMsg = "Erro ao excluir Cidade!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    @Override
    public List<Cidade> getAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_ALL_CIDADE);
            rs = stmt.executeQuery();

            return toCidades(rs);
        } catch (SQLException e) {
            String errorMsg = "Erro ao consultar todas as cidades!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    @Override
    public List<Cidade> getCidadeByNome(String nome) {
        if (nome == null || nome.isEmpty())
            return Collections.EMPTY_LIST;
		
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_CIDADES_BY_NOME);
            stmt.setString(1, nome + "%");
            rs = stmt.executeQuery();

            return toCidades(rs);
        } catch (SQLException e) {
                String errorMsg = "Erro ao consultar cidade(s) por nome!";
                log.error(errorMsg, e);
                throw new PersistenceException(errorMsg, e);
        } finally {
                ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    @Override
    public Cidade findById(Integer id) {
        if (id == null || id.intValue() <= 0)
            throw new PersistenceException("Informe o id válido para fazer a busca!");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cidade cidade = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_CIDADE_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NOME");
                int idEstado = rs.getInt("IDESTADO");
                
                cidade = new Cidade(id, nome, idEstado);
            }
            
            return cidade;
        } catch (SQLException e) {
            String errorMsg = "Erro ao consultar Cidade por id!";
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
                log.info("Criou a tabela 'cidade'");
            }
        } catch (SQLException e) {
            log.error(e);
            throw new PersistenceException("Não foi possivel inicializar o banco de dados: " + CREATE_TABLE, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }
    
    private PreparedStatement getStatementInsert(Connection conn, Cidade cidade) throws SQLException {
        PreparedStatement stmt = createStatementWithLog(conn, INSERT_CIDADE);
        stmt.setString(1, cidade.getNome());
        stmt.setInt(2, cidade.getIdEstado());
        return stmt;
    }
	
    private PreparedStatement getStatementUpdate(Connection conn, Cidade cidade) throws SQLException {
        PreparedStatement stmt = createStatementWithLog(conn, UPDATE_CIDADE);
        stmt.setString(1, cidade.getNome());
        stmt.setInt(2, cidade.getIdEstado());
        stmt.setInt(3, cidade.getId());
        return stmt;
    }
    
    private static PreparedStatement createStatementWithLog(Connection conn, String sql) throws SQLException{
        if (conn == null)
            return null;

        log.debug("SQL: "+sql);
        return conn.prepareStatement(sql);
    }
    
    private List<Cidade> toCidades(ResultSet rs) throws SQLException {
        List<Cidade> lista = new ArrayList<Cidade>();
        
        while (rs.next()) {
            int id = rs.getInt("ID");
            String nome = rs.getString("NOME");
            int idEstado = rs.getInt("IDESTADO");

            lista.add(new Cidade(id, nome, idEstado));
        }
        
        return lista;
    }

    @Override
    public List<Cidade> getCidadeByIdEstado(Integer idEstado) {
        if (idEstado == null || idEstado.intValue() <= 0)
            throw new PersistenceException("Informe o id válido para fazer a busca!");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_CIDADE_BY_IDESTADO);
            stmt.setInt(1, idEstado);
            rs = stmt.executeQuery();
            
            return toCidades(rs);
        } catch (SQLException e) {
            String errorMsg = "Erro ao consultar Cidade por id!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    @Override
    public Cidade getCidadeByNomeIdEstado(String nome, Integer idEstado) {
        if (nome == null || nome.isEmpty())
            return null;
		
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_CIDADE_BY_NOME_IDESTADO);
            stmt.setString(1, nome);
            stmt.setInt(2, idEstado);
            rs = stmt.executeQuery();
            Cidade c;
            
            if (rs.next()) {
                int id = rs.getInt("ID");
                c = new Cidade(id, nome, idEstado);
                return c;
            }
            
        } catch (SQLException e) {
                String errorMsg = "Erro ao consultar cidade(s) por nome!";
                log.error(errorMsg, e);
                throw new PersistenceException(errorMsg, e);
        } finally {
                ConnectionManager.closeAll(conn, stmt, rs);
        }
        
        return null;
    }
    
}
