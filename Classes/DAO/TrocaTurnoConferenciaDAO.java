package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.TrocaTurnoConferencia;
import Classes.Modelo.Usuario;
import Frm.Principal.FrmCarregando;
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
public class TrocaTurnoConferenciaDAO {

    public void createTrocaTurnoConferencia(TrocaTurnoConferencia turno) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_troca_turno_conferencia "
                    + "(turno, descricao_turno, "
                    + "data_registro, user_name) "
                    + "VALUES (?,?,?,?)");
            stmt.setString(1, turno.getTurno());
            stmt.setString(2, turno.getDescricao_turno());
            stmt.setTimestamp(3, turno.getData_registro());
            stmt.setString(4, turno.getUser_name().getUser());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createTrocaTurnoConferencia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<TrocaTurnoConferencia> readTrocaTurnoConferencia() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TrocaTurnoConferencia> turnos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select TOP(500) tb_troca_turno_conferencia.* "
                    + "FROM tb_troca_turno_conferencia "
                    + "ORDER BY tb_troca_turno_conferencia.troca_turno_conferencia_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                TrocaTurnoConferencia turno = new TrocaTurnoConferencia();
                Usuario user = new Usuario();
                turno.setTroca_turno_conferencia_id(rs.getInt("troca_turno_conferencia_id"));
                turno.setTurno(rs.getString("turno"));
                turno.setDescricao_turno(rs.getString("descricao_turno"));
                turno.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                turno.setUser_name(user);
                turnos.add(turno);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readTrocaTurnoConferencia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return turnos;
    }

    public void selectTrocaTurnoConferencia(TrocaTurnoConferencia turno) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select tb_troca_turno_conferencia.* "
                    + "FROM tb_troca_turno_conferencia "
                    + "WHERE tb_troca_turno_conferencia.troca_turno_conferencia_id = ? "
                    + "ORDER BY tb_troca_turno_conferencia.troca_turno_conferencia_id DESC ");
            stmt.setInt(1, turno.getTroca_turno_conferencia_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                turno.setTurno(rs.getString("turno"));
                turno.setDescricao_turno(rs.getString("descricao_turno"));
                turno.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                turno.setUser_name(user);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectTrocaTurnoConferencia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void updateTrocaTurnoConferencia(final TrocaTurnoConferencia turno) {
        final FrmCarregando frm = new FrmCarregando();
        frm.setVisible(true);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = null;
                try {
                    stmt = conn.prepareStatement("UPDATE tb_troca_turno_conferencia SET "
                            + "turno = ?, descricao_turno = ?, "
                            + "data_registro = ?, user_name = ? "
                            + "WHERE troca_turno_conferencia_id = ?");
                    stmt.setString(1, turno.getTurno());
                    stmt.setString(2, turno.getDescricao_turno());
                    stmt.setTimestamp(3, turno.getData_registro());
                    stmt.setString(4, turno.getUser_name().getUser());
                    stmt.setInt(5, turno.getTroca_turno_conferencia_id());
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    LogDAO logDAO = new LogDAO();
                    logDAO.logSQLException(ex, "updateTrocaTurnoConferencia");
                } finally {
                    ConnectionFactory.closeConection(conn, stmt);
                }
                frm.dispose();
            }
        };
        t1.start();
    }

    public void deleteTrocaTurnoConferencia(TrocaTurnoConferencia turno) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_troca_turno_conferencia "
                    + "WHERE troca_turno_conferencia_id = ?");
            stmt.setInt(1, turno.getTroca_turno_conferencia_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteTrocaTurnoConferencia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
