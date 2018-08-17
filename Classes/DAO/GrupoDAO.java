package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Grupo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael.lopes
 */
public class GrupoDAO {

    public int createGrupo(Grupo grup) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        int generatedKey = -1;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_groups "
                    + "(group_name, group_type) "
                    + "VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, grup.getGroup_name());
            stmt.setString(2, grup.getGroup_type());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedKey = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createGrupo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
        return generatedKey;
    }

    public List<Grupo> readGrupo() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Grupo> grupos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_groups ORDER BY group_name");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Grupo grup = new Grupo();
                grup.setGroup_id(rs.getInt("group_id"));
                grup.setGroup_name(rs.getString("group_name"));
                grup.setGroup_type(rs.getString("group_type"));
                grupos.add(grup);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readGrupo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return grupos;
    }

    public void updateGrupo(Grupo grup) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_groups SET "
                    + "group_name = ?, group_type = ? "
                    + "WHERE group_id = ?");
            stmt.setString(1, grup.getGroup_name());
            stmt.setString(2, grup.getGroup_type());
            stmt.setInt(3, grup.getGroup_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateGrupo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectGrupo(Grupo grup) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_groups WHERE group_id = ?");
            stmt.setInt(1, grup.getGroup_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                grup.setGroup_name(rs.getString("group_name"));
                grup.setGroup_type(rs.getString("group_type"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectGrupo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteGrupo(Grupo grup) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_groups WHERE group_id = ?");
            stmt.setInt(1, grup.getGroup_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteGrupo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
