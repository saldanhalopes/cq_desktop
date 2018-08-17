/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Campanha;
import Classes.Modelo.Cromatografo;
import Classes.Modelo.FaseMovel;
import Classes.Modelo.Usuario;
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
                    + "fase_movel_e, fase_movel_e_lote, fase_movel_e_validade, "
                    + "fase_movel_f, fase_movel_f_lote, fase_movel_f_validade, "
                    + "system_name, user_name, data_registro, data_inicio, log_campanha_id) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
            stmt.setString(17, fase.getFase_movel_e());
            stmt.setString(18, fase.getFase_movel_e_lote());
            stmt.setTimestamp(19, fase.getFase_movel_e_validade());
            stmt.setString(20, fase.getFase_movel_f());
            stmt.setString(21, fase.getFase_movel_f_lote());
            stmt.setTimestamp(22, fase.getFase_movel_f_validade());
            stmt.setString(23, fase.getCromatografo().getSystem_name());
            stmt.setString(24, fase.getUser().getUser());
            stmt.setTimestamp(25, fase.getData_registro());
            stmt.setTimestamp(26, fase.getData_inicio());
            stmt.setInt(27, fase.getCampanha().getLog_campanha_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createFaseMovelDAO");
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
                fase.setFase_movel_e(rs.getString("fase_movel_e"));
                fase.setFase_movel_e_lote(rs.getString("fase_movel_e_lote"));
                fase.setFase_movel_e_validade(rs.getTimestamp("fase_movel_e_validade"));
                fase.setFase_movel_f(rs.getString("fase_movel_f"));
                fase.setFase_movel_f_lote(rs.getString("fase_movel_f_lote"));
                fase.setFase_movel_f_validade(rs.getTimestamp("fase_movel_f_validade"));
                equip.setSystem_name(rs.getString("system_name"));
                fase.setCromatografo(equip);
                fasemovel.add(fase);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readFaseMovelDAO");
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
                fase.setFase_movel_e(rs.getString("fase_movel_e"));
                fase.setFase_movel_e_lote(rs.getString("fase_movel_e_lote"));
                fase.setFase_movel_e_validade(rs.getTimestamp("fase_movel_e_validade"));
                fase.setFase_movel_f(rs.getString("fase_movel_f"));
                fase.setFase_movel_f_lote(rs.getString("fase_movel_f_lote"));
                fase.setFase_movel_f_validade(rs.getTimestamp("fase_movel_f_validade"));
                equip.setSystem_name(rs.getString("system_name"));
                fase.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                fase.setUser(user);
                fase.setCromatografo(equip);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectFaseMovel");
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
            stmt = conn.prepareStatement("Select TOP(100) tb_log_fase_movel.*, tb_log_campanha.nome_campanha "
                    + "FROM tb_log_fase_movel "
                    + "LEFT JOIN tb_log_campanha "
                    + "ON tb_log_fase_movel.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "WHERE tb_log_fase_movel.system_name = ? "
                    + "ORDER BY tb_log_fase_movel.log_fase_movel_id DESC ");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                FaseMovel fase = new FaseMovel();
                Usuario user = new Usuario();
                Campanha camp = new Campanha();
                camp.setNome_campanha(rs.getString("nome_campanha"));
                fase.setCampanha(camp);
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
                fase.setFase_movel_e(rs.getString("fase_movel_e"));
                fase.setFase_movel_e_lote(rs.getString("fase_movel_e_lote"));
                fase.setFase_movel_e_validade(rs.getTimestamp("fase_movel_e_validade"));
                fase.setFase_movel_f(rs.getString("fase_movel_f"));
                fase.setFase_movel_f_lote(rs.getString("fase_movel_f_lote"));
                fase.setFase_movel_f_validade(rs.getTimestamp("fase_movel_f_validade"));
                fase.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                fase.setUser(user);
                fasemovel.add(fase);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readFaseMovelBySystem");
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
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_fase_movel.* "
                    + "FROM tb_log_fase_movel "
                    + "WHERE system_name = ? "
                    + "ORDER BY log_fase_movel_id DESC ");
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
                fase.setFase_movel_e(rs.getString("fase_movel_e"));
                fase.setFase_movel_e_lote(rs.getString("fase_movel_e_lote"));
                fase.setFase_movel_e_validade(rs.getTimestamp("fase_movel_e_validade"));
                fase.setFase_movel_f(rs.getString("fase_movel_f"));
                fase.setFase_movel_f_lote(rs.getString("fase_movel_f_lote"));
                fase.setFase_movel_f_validade(rs.getTimestamp("fase_movel_f_validade"));
                fase.setData_registro(rs.getTimestamp("data_registro"));
                fase.setData_fim(rs.getTimestamp("data_fim"));
                user.setUser(rs.getString("user_name"));
                fase.setUser(user);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectUltimaFaseMovelBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public int selectIDUltimaFaseMovelBySystem(FaseMovel fase) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_fase_movel.log_fase_movel_id "
                    + "FROM tb_log_fase_movel "
                    + "WHERE tb_log_fase_movel.system_name = ?  "
                    + "ORDER BY tb_log_fase_movel.log_fase_movel_id DESC ");
            stmt.setString(1, fase.getCromatografo().getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("log_fase_movel_id");
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectIDUltimaFaseMovelBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return id;
    }

    public void updateFimLogFaseMovel(FaseMovel fase) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_fase_movel SET "
                    + "data_registro = ?, data_fim = ? "
                    + "WHERE log_fase_movel_id = ?");
            stmt.setTimestamp(1, fase.getData_registro());
            stmt.setTimestamp(2, fase.getData_fim());
            stmt.setInt(3, fase.getFase_movel_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateFimLogFaseMovel");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
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
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteFaseMovelDAO");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
    
    public void updateFaseMovelStartDAO(FaseMovel fase) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_fase_movel SET "
                    + "fase_movel_e = ?, fase_movel_e_lote = ?, fase_movel_e_validade = ?, "
                    + "fase_movel_f = ?, fase_movel_f_lote = ?, fase_movel_f_validade = ?, "
                    + "user_name = ?, data_registro = ? "
                    + "WHERE log_fase_movel_id = ?");
            stmt.setString(1, fase.getFase_movel_e());
            stmt.setString(2, fase.getFase_movel_e_lote());
            stmt.setTimestamp(3, fase.getFase_movel_e_validade());
            stmt.setString(4, fase.getFase_movel_f());
            stmt.setString(5, fase.getFase_movel_f_lote());
            stmt.setTimestamp(6, fase.getFase_movel_f_validade());
            stmt.setString(7, fase.getUser().getUser());
            stmt.setTimestamp(8, fase.getData_registro());
            stmt.setInt(9, fase.getFase_movel_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateFaseMovelStartDAO");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
    
}
