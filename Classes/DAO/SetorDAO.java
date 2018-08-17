package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Setor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael.lopes
 */
public class SetorDAO {

    public void createSetor(Setor setor) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_setor "
                    + "(setor, sigla_setor, descricao_setor) "
                    + "VALUES (?,?,?)");
            stmt.setString(1, setor.getSetor());
            stmt.setString(2, setor.getSigla_setor());
            stmt.setString(3, setor.getDescricao_setor());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createSetor");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Setor> readSetor() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Setor> setores = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_setor");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSetor(rs.getString("setor"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                setor.setDescricao_setor(rs.getString("descricao_setor"));
                setores.add(setor);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readSetor");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return setores;
    }

    public void updateSetor(Setor setor) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_setor SET "
                    + "setor = ?, sigla_setor = ?, descricao_setor = ? "
                    + "WHERE setor_id = ?");
            stmt.setString(1, setor.getSetor());
            stmt.setString(2, setor.getSigla_setor());
            stmt.setString(3, setor.getDescricao_setor());
            stmt.setInt(4, setor.getSetor_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateSetor");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectSetor(Setor setor) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_setor WHERE setor_id = ?");
            stmt.setInt(1, setor.getSetor_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                setor.setSetor(rs.getString("setor"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                setor.setDescricao_setor(rs.getString("descricao_setor"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectSetor");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteSetor(Setor setor) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_setor WHERE setor_id = ?");
            stmt.setInt(1, setor.getSetor_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteSetor");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
