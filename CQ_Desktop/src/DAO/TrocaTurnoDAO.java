package DAO;

import data.ConnectionFactory;
import model.Cromatografo;
import model.TrocaTurno;
import model.Usuario;
import util.Frames;
import view.logbook.FrmLogCromatografo;
import view.principal.FrmCarregando;
import view.principal.FrmPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class TrocaTurnoDAO {

    public void createColunaConfig(TrocaTurno turno) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_troca_turno "
                    + "(system_name, turno, descricao_turno, "
                    + "data_registro, user_name, campanha) "
                    + "VALUES (?,?,?,?,?,?)");
            stmt.setString(1, turno.getCromatografo().getSystem_name());
            stmt.setString(2, turno.getTurno());
            stmt.setString(3, turno.getDescricao_turno());
            stmt.setTimestamp(4, turno.getData_registro());
            stmt.setString(5, turno.getUser_name().getUser());
            stmt.setString(6, turno.getCampanha());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<TrocaTurno> readTrocaTurno(String system_name) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TrocaTurno> turnos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_troca_turno "
                    + "WHERE system_name = ? "
                    + "ORDER BY troca_turno_id DESC LIMIT 100");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TrocaTurno turno = new TrocaTurno();
                Usuario user = new Usuario();
                Cromatografo equip = new Cromatografo();
                turno.setTroca_turno_id(rs.getInt("troca_turno_id"));
                equip.setSystem_name(rs.getString("system_name"));
                turno.setCromatografo(equip);
                turno.setTurno(rs.getString("turno"));
                turno.setDescricao_turno(rs.getString("descricao_turno"));
                turno.setCampanha(rs.getString("campanha"));
                turno.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                turno.setUser_name(user);
                turnos.add(turno);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
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
            stmt = conn.prepareStatement("Select * FROM tb_troca_turno "
                    + "WHERE troca_turno_id = ?");
            stmt.setInt(1, turno.getTroca_turno_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                Cromatografo equip = new Cromatografo();
                equip.setSystem_name(rs.getString("system_name"));
                turno.setCromatografo(equip);
                turno.setTurno(rs.getString("turno"));
                turno.setDescricao_turno(rs.getString("descricao_turno"));
                turno.setCampanha(rs.getString("campanha"));
                turno.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                turno.setUser_name(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
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
                            + "data_registro = ?, user_name = ?, campanha = ? "
                            + "WHERE troca_turno_id = ?");
                    stmt.setString(1, turno.getTurno());
                    stmt.setString(2, turno.getDescricao_turno());
                    stmt.setTimestamp(3, turno.getData_registro());
                    stmt.setString(4, turno.getUser_name().getUser());
                    stmt.setString(5, turno.getCampanha());
                    stmt.setInt(6, turno.getTroca_turno_id());
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex);
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
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
