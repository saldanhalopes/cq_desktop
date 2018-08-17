/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Analise;
import Classes.Modelo.Start;
import Classes.Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @since JDK8
 * @author rafael.lopes
 */
public class StartDAO {

    public void createStart(Start start) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_start "
                    + "(analise_status_id, system_name, "
                    + "descricao, user_name, data_registro) "
                    + "VALUES (?,?,?,?,?)");
            stmt.setInt(1, start.getAnalise().getAnalise_status_id());
            stmt.setString(2, start.getSystem_name());
            stmt.setString(3, start.getDescricao());
            stmt.setString(4, start.getUsuario().getUser());
            stmt.setTimestamp(5, start.getData_registro());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createStart");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Start> readStart(String system_name) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Start> starts = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(500) tb_log_start.log_start_id, "
                    + "tb_analise_status.analise_status, tb_analise_status.analise_produtividade_id, "
                    + "tb_log_start.data_registro, tb_log_start.descricao, "
                    + "tb_log_start.user_name, tb_users.turno "
                    + "FROM tb_log_start "
                    + "LEFT JOIN tb_analise_status "
                    + "ON tb_log_start.analise_status_id = tb_analise_status.analise_status_id "
                    + "LEFT JOIN tb_cromatografo "
                    + "ON tb_log_start.system_name = tb_cromatografo.system_name  "
                    + "LEFT JOIN tb_analise_produtividade "
                    + "ON tb_analise_status.analise_produtividade_id = "
                    + "tb_analise_produtividade.analise_produtividade_id "
                    + "LEFT JOIN tb_users "
                    + "ON tb_log_start.user_name = tb_users.user_name "
                    + "WHERE tb_log_start.system_name = ? "
                    + "AND tb_analise_status.analise_status = 'START' "
                    + "ORDER BY tb_log_start.log_start_id DESC ");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Start start = new Start();
                Analise anls = new Analise();
                Usuario user = new Usuario();
                start.setLog_start_id(rs.getInt("log_start_id"));
                anls.setAnalise_status(rs.getString("analise_status"));
                start.setAnalise(anls);
                start.setData_registro(rs.getTimestamp("data_registro"));
                start.setDescricao(rs.getString("descricao"));
                user.setUser(rs.getString("user_name"));
                user.setTurno(rs.getString("turno"));
                start.setUsuario(user);
                starts.add(start);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readStart");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return starts;
    }

    
    public void deleteStart(Start start) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_log_start "
                    + "WHERE log_start_id = ?");
            stmt.setInt(1, start.getLog_start_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteStart");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    
}
