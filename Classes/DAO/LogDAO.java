/*
 * Copyright (C) 2017 rafael.lopes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Log;
import Classes.Util.DataHora;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author rafael.lopes
 */
public class LogDAO {

    public void createLogErro(Log log) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_erro "
                    + "(tipo, classe, erro, "
                    + "data, user_name, computador, user_computador) "
                    + "VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, log.getTipo());
            stmt.setString(2, log.getClasse());
            stmt.setString(3, log.getErro());
            stmt.setTimestamp(4, log.getData());
            stmt.setString(5, log.getUser());
            stmt.setString(6, log.getComputador());
            stmt.setString(7, log.getUser_computador());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Log> readLogErro() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Log> logs = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_log_erro "
                    + "ORDER BY tb_log_erro.log_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Log log = new Log();
                log.setLog_id(rs.getInt("log_id"));
                log.setTipo(rs.getString("tipo"));
                log.setClasse(rs.getString("classe"));
                log.setErro(rs.getString("erro"));
                log.setData(rs.getTimestamp("data"));
                log.setUser(rs.getString("user_name"));
                log.setComputador(rs.getString("computador"));
                log.setUser_computador(rs.getString("user_computador"));
                logs.add(log);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return logs;
    }

    public void logSQLException(SQLException ex, String classe) {
        Log log = new Log();
        log.setTipo("SQLException");
        log.setClasse(classe);
        log.setErro(ex.toString());
        log.setData(DataHora.getTimestampDate(new Date()));
        log.setUser(System.getProperty("user"));
        try {
            log.setComputador(java.net.InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException ex1) {
            log.setComputador(null);
        }
        log.setUser_computador(System.getProperty("user.name"));
        createLogErro(log);
    }

    public void logJasperPrint(JRException ex, String classe) {
        Log log = new Log();
        log.setTipo("JRException");
        log.setClasse(classe);
        log.setErro(ex.toString());
        log.setData(DataHora.getTimestampDate(new Date()));
        log.setUser(System.getProperty("user"));
        try {
            log.setComputador(java.net.InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException ex1) {
            log.setComputador(null);
        }
        log.setUser_computador(System.getProperty("user.name"));
        createLogErro(log);
    }

}
