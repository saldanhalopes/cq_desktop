package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Ajuste;
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
public class AjusteDAO {

    public void createAjuste(Ajuste ajst) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_tipo_ajuste "
                    + "(tipo_ajuste, descricao_tipo_ajuste) "
                    + "VALUES (?,?)");
            stmt.setString(1, ajst.getTipo_ajuste());
            stmt.setString(2, ajst.getDescricao_tipo_ajuste());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAjuste");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Ajuste> readAjuste() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Ajuste> ajsts = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_tipo_ajuste");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Ajuste ajst = new Ajuste();
                ajst.setTipo_ajuste_id(rs.getInt("tipo_ajuste_id"));
                ajst.setTipo_ajuste(rs.getString("tipo_ajuste"));
                ajst.setDescricao_tipo_ajuste(rs.getString("descricao_tipo_ajuste"));
                ajsts.add(ajst);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAjuste");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return ajsts;
    }

    public void updateAjuste(Ajuste ajst) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_tipo_ajuste SET "
                    + "tipo_ajuste = ?, descricao_tipo_ajuste = ? "
                    + "WHERE tipo_ajuste_id = ?");
            stmt.setString(1, ajst.getTipo_ajuste());
            stmt.setString(2, ajst.getDescricao_tipo_ajuste());
            stmt.setInt(3, ajst.getTipo_ajuste_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAjuste");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAjuste(Ajuste ajst) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_tipo_ajuste "
                    + "WHERE tipo_ajuste_id = ?");
            stmt.setInt(1, ajst.getTipo_ajuste_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                ajst.setTipo_ajuste(rs.getString("tipo_ajuste"));
                ajst.setDescricao_tipo_ajuste(rs.getString("descricao_tipo_ajuste"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAjuste");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteAjuste(Ajuste ajst) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_tipo_ajuste "
                    + "WHERE tipo_ajuste_id = ?");
            stmt.setInt(1, ajst.getTipo_ajuste_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAjuste");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
