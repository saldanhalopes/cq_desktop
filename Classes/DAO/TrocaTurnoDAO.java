package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Cromatografo;
import Classes.Modelo.TrocaTurno;
import Classes.Modelo.Usuario;
import Frm.Principal.FrmCarregando;
import Classes.Modelo.Campanha;
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
public class TrocaTurnoDAO {

    public void createTrocaTurno(TrocaTurno turno) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_troca_turno "
                    + "(system_name, turno, descricao_turno, "
                    + "data_registro, user_name, log_campanha_id) "
                    + "VALUES (?,?,?,?,?,?)");
            stmt.setString(1, turno.getCromatografo().getSystem_name());
            stmt.setString(2, turno.getTurno());
            stmt.setString(3, turno.getDescricao_turno());
            stmt.setTimestamp(4, turno.getData_registro());
            stmt.setString(5, turno.getUser_name().getUser());
            stmt.setInt(6, turno.getCampanha().getLog_campanha_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createTrocaTurno");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<TrocaTurno> readTrocaTurno() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TrocaTurno> turnos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select TOP(300) tb_troca_turno.*, tb_log_campanha.nome_campanha "
                    + "FROM tb_troca_turno "
                    + "LEFT JOIN tb_log_campanha "
                    + "ON tb_troca_turno.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "ORDER BY tb_troca_turno.troca_turno_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                TrocaTurno turno = new TrocaTurno();
                Usuario user = new Usuario();
                Cromatografo equip = new Cromatografo();
                Campanha camp = new Campanha();
                camp.setNome_campanha(rs.getString("nome_campanha"));
                turno.setCampanha(camp);
                turno.setTroca_turno_id(rs.getInt("troca_turno_id"));
                equip.setSystem_name(rs.getString("system_name"));
                turno.setCromatografo(equip);
                turno.setTurno(rs.getString("turno"));
                turno.setDescricao_turno(rs.getString("descricao_turno"));
                turno.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                turno.setUser_name(user);
                turnos.add(turno);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readTrocaTurno");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return turnos;
    }

    public List<TrocaTurno> readTrocaTurno(String system_name) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TrocaTurno> turnos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select TOP(300) tb_troca_turno.*, tb_log_campanha.nome_campanha "
                    + "FROM tb_troca_turno "
                    + "LEFT JOIN tb_log_campanha "
                    + "ON tb_troca_turno.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "WHERE tb_troca_turno.system_name = ? "
                    + "ORDER BY tb_troca_turno.troca_turno_id DESC ");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TrocaTurno turno = new TrocaTurno();
                Usuario user = new Usuario();
                Cromatografo equip = new Cromatografo();
                Campanha camp = new Campanha();
                camp.setNome_campanha(rs.getString("nome_campanha"));
                turno.setCampanha(camp);
                turno.setTroca_turno_id(rs.getInt("troca_turno_id"));
                equip.setSystem_name(rs.getString("system_name"));
                turno.setCromatografo(equip);
                turno.setTurno(rs.getString("turno"));
                turno.setDescricao_turno(rs.getString("descricao_turno"));
                turno.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                turno.setUser_name(user);
                turnos.add(turno);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readTrocaTurno");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return turnos;
    }

    public void selectTrocaTurno(TrocaTurno turno) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select TOP(300) tb_troca_turno.*, tb_log_campanha.nome_campanha "
                    + "FROM tb_troca_turno "
                    + "LEFT JOIN tb_log_campanha "
                    + "ON tb_troca_turno.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "WHERE tb_troca_turno.troca_turno_id = ? "
                    + "ORDER BY tb_troca_turno.troca_turno_id DESC ");
            stmt.setInt(1, turno.getTroca_turno_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                Cromatografo equip = new Cromatografo();
                Campanha camp = new Campanha();
                camp.setNome_campanha(rs.getString("nome_campanha"));
                turno.setCampanha(camp);
                equip.setSystem_name(rs.getString("system_name"));
                turno.setCromatografo(equip);
                turno.setTurno(rs.getString("turno"));
                turno.setDescricao_turno(rs.getString("descricao_turno"));
                turno.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                turno.setUser_name(user);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectTrocaTurno");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void updateTrocaTurno(final TrocaTurno turno) {
        final FrmCarregando frm = new FrmCarregando();
        frm.setVisible(true);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = null;
                try {
                    stmt = conn.prepareStatement("UPDATE tb_troca_turno SET "
                            + "turno = ?, descricao_turno = ?, "
                            + "data_registro = ?, user_name = ? "
                            + "WHERE troca_turno_id = ?");
                    stmt.setString(1, turno.getTurno());
                    stmt.setString(2, turno.getDescricao_turno());
                    stmt.setTimestamp(3, turno.getData_registro());
                    stmt.setString(4, turno.getUser_name().getUser());
                    stmt.setInt(5, turno.getTroca_turno_id());
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    LogDAO logDAO = new LogDAO();
                    logDAO.logSQLException(ex, "updateTrocaTurno");
                } finally {
                    ConnectionFactory.closeConection(conn, stmt);
                }
                frm.dispose();
            }
        };
        t1.start();
    }

    public void deleteTrocaTurno(TrocaTurno turno) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_troca_turno WHERE troca_turno_id = ?");
            stmt.setInt(1, turno.getTroca_turno_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteTrocaTurno");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
