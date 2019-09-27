package crud.dao;

import crud.database.ConnectionManager;
import crud.entidade.Estado;
import crud.exceptions.PersistenceException;
import crud.interfaces.IEstado;
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
public class EstadoDao implements IEstado {
    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ESTADO (ID INT NOT NULL IDENTITY, NOME VARCHAR(255) NOT NULL UNIQUE, SIGLA CHAR(2) NOT NULL UNIQUE, STATUS CHAR(1) DEFAULT 'A' NOT NULL);";
    private final static String INSERT_ESTADO = "INSERT INTO ESTADO (SIGLA, NOME) VALUES (?, ?);";
    private final static String UPDATE_ESTADO = "UPDATE ESTADO SET SIGLA = ?, NOME = ? WHERE ID = ?;";
    private final static String DELETE_ESTADO = "DELETE FROM ESTADO WHERE ID = ?;";
    private final static String GET_ALL_ESTADO = "SELECT * FROM ESTADO;";
    private final static String GET_ESTADO_BY_NOMES = "SELECT * FROM ESTADO WHERE NOME LIKE ?;";
    private final static String GET_ESTADO_BY_ID = "SELECT * FROM ESTADO WHERE ID = ?;";
    private final static String GET_ESTADO_BY_NOME = "SELECT * FROM ESTADO WHERE NOME = ?;";

    private static Logger log = Logger.getLogger(EstadoDao.class);

    @Override
    public void save(Estado estado) {
        if (estado == null) {
            throw new PersistenceException("Informe o Estado para salvar!");
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            
            if (estado.getId() == null) {
                stmt = getStatementInsert(conn, estado);
            } else {
                stmt = getStatementUpdate(conn, estado);
            }
            
            stmt.executeUpdate();
            conn.commit();
            log.debug("Estado foi salva");
        } catch (SQLException e) {
            try { conn.rollback(); } catch (Exception sx) {}
            String errorMsg = "Erro ao salvar Estado!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    @Override
    public void remove(Estado estado) {
        if (estado == null || estado.getId() == null) {
            throw new PersistenceException("Informe o estado para exclusão!");
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, DELETE_ESTADO);
            stmt.setInt(1, estado.getId());
            stmt.executeUpdate();
            conn.commit();
            log.debug("Estado foi excluído");
        } catch (SQLException e) {
            try { conn.rollback(); } catch (Exception sx) {}
            String errorMsg = "Erro ao excluir Estado!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    @Override
    public List<Estado> getAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_ALL_ESTADO);
            rs = stmt.executeQuery();

            return toEstados(rs);
        } catch (SQLException e) {
            String errorMsg = "Erro ao consultar todas as cidades!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    @Override
    public List<Estado> getEstadosByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estado findById(Integer id) {
        if (id == null || id.intValue() <= 0) {
            throw new PersistenceException("Informe o id válido para fazer a busca!");
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Estado estado = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_ESTADO_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String sigla = rs.getString("SIGLA");
                String nome = rs.getString("NOME");
                
                estado = new Estado(id, sigla, nome);
            }
            
            return estado;
        } catch (SQLException e) {
            String errorMsg = "Erro ao consultar Estado por id!";
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
                log.info("Criou a tabela 'estado'");
            }
        } catch (SQLException e) {
            log.error(e);
            throw new PersistenceException("Não foi possivel inicializar o banco de dados: " + CREATE_TABLE, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }
    
    private PreparedStatement getStatementInsert(Connection conn, Estado estado) throws SQLException {
        PreparedStatement stmt = createStatementWithLog(conn, INSERT_ESTADO);
        stmt.setString(1, estado.getSigla());
        stmt.setString(2, estado.getNome());
        return stmt;
    }
	
    private PreparedStatement getStatementUpdate(Connection conn, Estado estado) throws SQLException {
        PreparedStatement stmt = createStatementWithLog(conn, UPDATE_ESTADO);
        stmt.setString(1, estado.getSigla());
        stmt.setString(2, estado.getNome());
        stmt.setInt(3, estado.getId());
        return stmt;
    }
    
    private static PreparedStatement createStatementWithLog(Connection conn, String sql) throws SQLException{
        if (conn == null)
            return null;

        log.debug("SQL: "+sql);
        return conn.prepareStatement(sql);
    }
    
    private List<Estado> toEstados(ResultSet rs) throws SQLException {
        List<Estado> lista = new ArrayList<Estado>();
        
        while (rs.next()) {
            int id = rs.getInt("ID");
            String sigla = rs.getString("SIGLA");
            String nome = rs.getString("NOME");
            
            lista.add(new Estado(id, sigla, nome));
        }
        
        return lista;
    }

    @Override
    public Estado getEstadoByNome(String nome) {
        if (nome == null || nome.isEmpty())
            return null;
		
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_ESTADO_BY_NOME);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            Estado e;
            
            if (rs.next()) {
                int id = rs.getInt("ID");
                String sigla = rs.getString("SIGLA");
                e = new Estado(id, sigla, nome);
                return e;
            }
            
        } catch (SQLException e) {
                String errorMsg = "Erro ao consultar estado por nome!";
                log.error(errorMsg, e);
                throw new PersistenceException(errorMsg, e);
        } finally {
                ConnectionManager.closeAll(conn, stmt, rs);
        }
        
        return null;
    }
    
}
