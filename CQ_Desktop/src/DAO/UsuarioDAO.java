/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class UsuarioDAO {

    public int getIdUserByName(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int user_id = 0;
        try {
            stmt = conn.prepareStatement("SELECT user_id FROM tb_users WHERE user_name = ?");
            stmt.setString(1, user.getUser());
            rs = stmt.executeQuery();
            while (rs.next()) {
                user_id = (rs.getInt("user_id"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return user_id;
    }

    public void selectUser(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_users WHERE user_id = ?");
            stmt.setInt(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                user.setUser(rs.getString("user_name"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setCracha(rs.getString("cracha"));
                user.setLock(rs.getBoolean("locked"));
                user.setAcesso(rs.getString("acesso"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void createUser(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_users "
                    + "(user_name, password, name, email, "
                    + "locked, acesso, cracha, change_password) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            stmt.setString(1, user.getUser());
            stmt.setString(2, user.getPass());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getEmail());
            stmt.setInt(5, user.isLock() ? 1 : 0);
            stmt.setString(6, user.getAcesso());
            stmt.setString(7, user.getCracha());
            stmt.setInt(8, user.getChange_pass());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Usuario> read() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_users");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("user_id"));
                user.setUser(rs.getString("user_name"));
                user.setName(rs.getString("name"));
                user.setLock(rs.getBoolean("locked"));
                usuarios.add(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return usuarios;
    }
    
    public List<Usuario> readSelect() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_users WHERE user_id <> 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("user_id"));
                user.setUser(rs.getString("user_name"));
                user.setName(rs.getString("name"));
                user.setLock(rs.getBoolean("locked"));
                usuarios.add(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return usuarios;
    }

    public void updateUser(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_users SET name = ?, email = ?, locked = ?, "
                    + "acesso = ?, cracha = ?, change_password = ? WHERE user_id = ?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.isLock() ? 1 : 0);
            stmt.setString(4, user.getAcesso());
            stmt.setString(5, user.getCracha());
            stmt.setInt(6, user.getChange_pass());
            stmt.setInt(7, user.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateUserName(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_users SET user_name = ? WHERE user_id = ?");
            stmt.setString(1, user.getUser());
            stmt.setInt(2, user.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateUserPassword(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_users SET password = ? WHERE user_id = ?");
            stmt.setString(1, user.getPass());
            stmt.setInt(2, user.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateUserPassword(Usuario user, int ChangePass) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_users SET password = ?, change_password = ? WHERE user_id = ?");
            stmt.setString(1, user.getPass());
            stmt.setInt(2, ChangePass);
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateUserLocked(Usuario user, boolean lock) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_users SET last_login = ? WHERE user_id = ?");
            stmt.setBoolean(1, lock);
            stmt.setInt(2, user.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void deleteUser(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_users WHERE user_id = ?");
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

}
