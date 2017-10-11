/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.ConnectionFactory;
import model.Cromatografo;
import model.FaseMovel;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class FaseMovelDAO {

    public void createFaseMovelDAO(FaseMovel fase) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_fase_movel "
                    + "(fase_movel_a, fase_movel_a_qt, fase_movel_a_lote, fase_movel_a_validade, "
                    + "fase_movel_b, fase_movel_b_qt, fase_movel_b_lote, fase_movel_b_validade, "
                    + "fase_movel_c, fase_movel_c_qt, fase_movel_c_lote, fase_movel_c_validade, "
                    + "fase_movel_d, fase_movel_d_qt, fase_movel_d_lote, fase_movel_d_validade, "
                    + "system_name, user_name, data_registro) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, fase.getFase_movel_a());
            stmt.setInt(2, fase.getFase_movel_a_qt());
            stmt.setString(3, fase.getFase_movel_a_lote());
            stmt.setTimestamp(4, fase.getFase_movel_a_validade());
            stmt.setString(5, fase.getFase_movel_b());
            stmt.setInt(6, fase.getFase_movel_b_qt());
            stmt.setString(7, fase.getFase_movel_b_lote());
            stmt.setTimestamp(8, fase.getFase_movel_b_validade());
            stmt.setString(9, fase.getFase_movel_c());
            stmt.setInt(10, fase.getFase_movel_c_qt());
            stmt.setString(11, fase.getFase_movel_c_lote());
            stmt.setTimestamp(12, fase.getFase_movel_c_validade());
            stmt.setString(13, fase.getFase_movel_d());
            stmt.setInt(14, fase.getFase_movel_d_qt());
            stmt.setString(15, fase.getFase_movel_d_lote());
            stmt.setTimestamp(16, fase.getFase_movel_d_validade());
            stmt.setString(17, fase.getCromatografo().getSystem_name());
            stmt.setString(18, fase.getUser().getUser());
            stmt.setTimestamp(19, fase.getData_registro());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<FaseMovel> readFaseMovelDAO() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<FaseMovel> fasemovel = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_log_fase_movel");
            rs = stmt.executeQuery();
            while (rs.next()) {
                FaseMovel fase = new FaseMovel();
                Cromatografo equip = new Cromatografo();
                fase.setFase_movel_id(rs.getInt("log_fase_movel_id"));
                fase.setFase_movel_a(rs.getString("fase_movel_a"));
                fase.setFase_movel_a_qt(rs.getInt("fase_movel_a_qt"));
                fase.setFase_movel_a_lote(rs.getString("fase_movel_a_lote"));
                fase.setFase_movel_a_validade(rs.getTimestamp("fase_movel_a_validade"));
                fase.setFase_movel_b(rs.getString("fase_movel_b"));
                fase.setFase_movel_b_qt(rs.getInt("fase_movel_b_qt"));
                fase.setFase_movel_b_lote(rs.getString("fase_movel_b_lote"));
                fase.setFase_movel_b_validade(rs.getTimestamp("fase_movel_b_validade"));
                fase.setFase_movel_c(rs.getString("fase_movel_c"));
                fase.setFase_movel_c_qt(rs.getInt("fase_movel_c_qt"));
                fase.setFase_movel_c_lote(rs.getString("fase_movel_c_lote"));
                fase.setFase_movel_c_validade(rs.getTimestamp("fase_movel_c_validade"));
                fase.setFase_movel_d(rs.getString("fase_movel_d"));
                fase.setFase_movel_d_qt(rs.getInt("fase_movel_d_qt"));
                fase.setFase_movel_d_lote(rs.getString("fase_movel_d_lote"));
                fase.setFase_movel_d_validade(rs.getTimestamp("fase_movel_d_validade"));
                equip.setSystem_name(rs.getString("system_name"));
                fase.setCromatografo(equip);
                fasemovel.add(fase);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return fasemovel;
    }

    public void selectFaseMovel(FaseMovel fase) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_log_fase_movel WHERE log_fase_movel_id = ?");
            stmt.setInt(1, fase.getFase_movel_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cromatografo equip = new Cromatografo();
                Usuario user = new Usuario();
                fase.setFase_movel_a(rs.getString("fase_movel_a"));
                fase.setFase_movel_a_qt(rs.getInt("fase_movel_a_qt"));
                fase.setFase_movel_a_lote(rs.getString("fase_movel_a_lote"));
                fase.setFase_movel_a_validade(rs.getTimestamp("fase_movel_a_validade"));
                fase.setFase_movel_b(rs.getString("fase_movel_b"));
                fase.setFase_movel_b_qt(rs.getInt("fase_movel_b_qt"));
                fase.setFase_movel_b_lote(rs.getString("fase_movel_b_lote"));
                fase.setFase_movel_b_validade(rs.getTimestamp("fase_movel_b_validade"));
                fase.setFase_movel_c(rs.getString("fase_movel_c"));
                fase.setFase_movel_c_qt(rs.getInt("fase_movel_c_qt"));
                fase.setFase_movel_c_lote(rs.getString("fase_movel_c_lote"));
                fase.setFase_movel_c_validade(rs.getTimestamp("fase_movel_c_validade"));
                fase.setFase_movel_d(rs.getString("fase_movel_d"));
                fase.setFase_movel_d_qt(rs.getInt("fase_movel_d_qt"));
                fase.setFase_movel_d_lote(rs.getString("fase_movel_d_lote"));
                fase.setFase_movel_d_validade(rs.getTimestamp("fase_movel_d_validade"));
                equip.setSystem_name(rs.getString("system_name"));
                fase.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                fase.setUser(user);
                fase.setCromatografo(equip);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public List<FaseMovel> readFaseMovelBySystem(String system_name) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<FaseMovel> fasemovel = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * From tb_log_fase_movel WHERE system_name = ? "
                    + "ORDER BY log_fase_movel_id DESC LIMIT 20 ");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                FaseMovel fase = new FaseMovel();
                Usuario user = new Usuario();
                fase.setFase_movel_a(rs.getString("fase_movel_a"));
                fase.setFase_movel_a_qt(rs.getInt("fase_movel_a_qt"));
                fase.setFase_movel_a_lote(rs.getString("fase_movel_a_lote"));
                fase.setFase_movel_a_validade(rs.getTimestamp("fase_movel_a_validade"));
                fase.setFase_movel_b(rs.getString("fase_movel_b"));
                fase.setFase_movel_b_qt(rs.getInt("fase_movel_b_qt"));
                fase.setFase_movel_b_lote(rs.getString("fase_movel_b_lote"));
                fase.setFase_movel_b_validade(rs.getTimestamp("fase_movel_b_validade"));
                fase.setFase_movel_c(rs.getString("fase_movel_c"));
                fase.setFase_movel_c_qt(rs.getInt("fase_movel_c_qt"));
                fase.setFase_movel_c_lote(rs.getString("fase_movel_c_lote"));
                fase.setFase_movel_c_validade(rs.getTimestamp("fase_movel_c_validade"));
                fase.setFase_movel_d(rs.getString("fase_movel_d"));
                fase.setFase_movel_d_qt(rs.getInt("fase_movel_d_qt"));
                fase.setFase_movel_d_lote(rs.getString("fase_movel_d_lote"));
                fase.setFase_movel_d_validade(rs.getTimestamp("fase_movel_d_validade"));
                fase.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                fase.setUser(user);
                fasemovel.add(fase);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return fasemovel;
    }

    public void selectUltimaFaseMovelBySystem(FaseMovel fase) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_log_fase_movel WHERE system_name = ? "
                    + "ORDER BY log_fase_movel_id DESC LIMIT 1 ");
            stmt.setString(1, fase.getCromatografo().getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                fase.setFase_movel_id(rs.getInt("log_fase_movel_id"));
                fase.setFase_movel_a(rs.getString("fase_movel_a"));
                fase.setFase_movel_a_qt(rs.getInt("fase_movel_a_qt"));
                fase.setFase_movel_a_lote(rs.getString("fase_movel_a_lote"));
                fase.setFase_movel_a_validade(rs.getTimestamp("fase_movel_a_validade"));
                fase.setFase_movel_b(rs.getString("fase_movel_b"));
                fase.setFase_movel_b_qt(rs.getInt("fase_movel_b_qt"));
                fase.setFase_movel_b_lote(rs.getString("fase_movel_b_lote"));
                fase.setFase_movel_b_validade(rs.getTimestamp("fase_movel_b_validade"));
                fase.setFase_movel_c(rs.getString("fase_movel_c"));
                fase.setFase_movel_c_qt(rs.getInt("fase_movel_c_qt"));
                fase.setFase_movel_c_lote(rs.getString("fase_movel_c_lote"));
                fase.setFase_movel_c_validade(rs.getTimestamp("fase_movel_c_validade"));
                fase.setFase_movel_d(rs.getString("fase_movel_d"));
                fase.setFase_movel_d_qt(rs.getInt("fase_movel_d_qt"));
                fase.setFase_movel_d_lote(rs.getString("fase_movel_d_lote"));
                fase.setFase_movel_d_validade(rs.getTimestamp("fase_movel_d_validade"));
                fase.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                fase.setUser(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteFaseMovelDAO(FaseMovel fase) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_log_fase_movel WHERE log_fase_movel_id = ?");
            stmt.setInt(1, fase.getFase_movel_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
