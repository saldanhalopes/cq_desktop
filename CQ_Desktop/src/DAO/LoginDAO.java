/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.ConnectionFactory;
import model.Usuario;
import util.DataHora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class LoginDAO {

    public int checkLogin(final Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int check = 0;
        try {
            stmt = conn.prepareStatement("SELECT user_id, user_name, password, locked, "
                    + "change_password, acesso FROM tb_users Where user_name = ? AND password = ?");
            stmt.setString(1, user.getUser());
            stmt.setString(2, user.getPass());
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    System.setProperty("user_id", Integer.toString(rs.getInt("user_id")));
                    System.setProperty("user", rs.getString("user_name"));
                    System.setProperty("acesso", rs.getString("acesso"));
                    if (rs.getInt("locked") == 0) {
                        if (rs.getInt("change_password") == 0) {
                            check = 1;
                        } else {
                            check = 2;
                        }
                        user.setId(rs.getInt("user_id"));
                        stmt = conn.prepareStatement("UPDATE tb_users SET last_login = ? WHERE user_id = ?");
                        stmt.setTimestamp(1, DataHora.getTimestampDate(new Date()));
                        stmt.setInt(2, user.getId());
                        stmt.executeUpdate();
                    } else {
                        check = 3;
                    }
                }
            } else {
                check = 4;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return check;
    }

    public boolean selectUser(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean usuario = false;
        try {
            stmt = conn.prepareStatement("SELECT user_name, password FROM tb_users "
                    + "Where user_name = ? AND password = ?");
            stmt.setString(1, user.getUser());
            stmt.setString(2, user.getPass());
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    usuario = user.getUser().equals(rs.getString("user_name")) 
                            && user.getPass().equals(rs.getString("password"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return usuario;
    }

}
