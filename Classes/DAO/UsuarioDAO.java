/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Grupo;
import Classes.Modelo.Setor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Classes.Modelo.Usuario;
import Classes.Util.DataHora;
import java.util.ArrayList;
import java.util.Date;
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
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "getIdUserByName");
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
            stmt = conn.prepareStatement("SELECT tb_users.*, "
                    + "tb_groups.group_name, tb_setor.sigla_setor "
                    + "FROM tb_users "
                    + "LEFT JOIN tb_groups "
                    + "ON tb_users.group_id = tb_groups.group_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_users.setor_id = tb_setor.setor_id "
                    + "WHERE user_id = ?");
            stmt.setInt(1, user.getUser_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Grupo grup = new Grupo();
                Setor setor = new Setor();
                user.setUser(rs.getString("user_name"));
                user.setName(rs.getString("name"));
                grup.setGroup_name(rs.getString("group_name"));
                user.setGrupo(grup);
                user.setEmail(rs.getString("email"));
                user.setCracha(rs.getString("cracha"));
                user.setLock(rs.getBoolean("locked"));
                user.setAcesso(rs.getString("acesso"));
                user.setTurno(rs.getString("turno"));
                user.setChange_pass(rs.getBoolean("change_password"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                user.setSetor(setor);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectUser");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public static boolean checkUserIsExits(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExits = false;
        try {
            stmt = conn.prepareStatement("SELECT user_name FROM tb_users WHERE user_name = ?");
            stmt.setString(1, user.getUser());
            rs = stmt.executeQuery();
            while (rs.next()) {
                isExits = true;
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "checkUserIsExits");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return isExits;
    }

    public void createUser(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_users "
                    + "(user_name, password, name, email, "
                    + "locked, group_id, cracha, change_password, "
                    + "created_date, turno, setor_id) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, user.getUser());
            stmt.setString(2, user.getPass());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getEmail());
            stmt.setInt(5, user.getLock() ? 1 : 0);
            stmt.setInt(6, user.getGrupo().getGroup_id());
            stmt.setString(7, user.getCracha());
            stmt.setInt(8, user.getChange_pass() ? 1 : 0);
            stmt.setTimestamp(9, DataHora.getTimestampDate(new Date()));
            stmt.setString(10, user.getTurno());
            stmt.setInt(11, user.getSetor().getSetor_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createUser");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Usuario> readUsuario() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_users.*, "
                    + "tb_groups.group_name, tb_setor.sigla_setor "
                    + "FROM tb_users "
                    + "LEFT JOIN tb_groups "
                    + "ON tb_users.group_id = tb_groups.group_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_users.setor_id = tb_setor.setor_id "
                    + "ORDER BY tb_users.name ASC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                Grupo grup = new Grupo();
                Setor setor = new Setor();
                user.setUser_id(rs.getInt("user_id"));
                user.setUser(rs.getString("user_name"));
                user.setName(rs.getString("name"));
                grup.setGroup_name(rs.getString("group_name"));
                user.setGrupo(grup);
                user.setFailed_access_count(rs.getInt("failed_access_count"));
                user.setLock(rs.getBoolean("locked"));
                user.setLastlogin(rs.getTimestamp("last_login"));
                user.setLastlogout(rs.getTimestamp("last_logout"));
                user.setTurno(rs.getString("turno"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                user.setSetor(setor);
                usuarios.add(user);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readUsuario");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return usuarios;
    }

    public List<Usuario> selectUsuarios() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_users.*, "
                    + "tb_groups.group_name, tb_setor.sigla_setor "
                    + "FROM tb_users "
                    + "INNER JOIN tb_groups "
                    + "ON tb_users.group_id = tb_groups.group_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_users.setor_id = tb_setor.setor_id "
                    + "WHERE user_id <> 1 "
                    + "ORDER BY tb_users.name ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                Grupo grup = new Grupo();
                Setor setor = new Setor();
                user.setUser_id(rs.getInt("user_id"));
                user.setUser(rs.getString("user_name"));
                grup.setGroup_name(rs.getString("group_name"));
                user.setGrupo(grup);
                user.setName(rs.getString("name"));
                user.setCracha(rs.getString("cracha"));
                user.setEmail(rs.getString("email"));
                user.setLock(rs.getBoolean("locked"));
                user.setTurno(rs.getString("turno"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                user.setSetor(setor);
                usuarios.add(user);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectUsuario");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return usuarios;
    }

    public void updateUsuario(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_users SET "
                    + "name = ?, email = ?, locked = ?, "
                    + "group_id = ?, cracha = ?, "
                    + "change_password = ?, turno = ?, setor_id = ? "
                    + "WHERE user_id = ?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getLock() ? 1 : 0);
            stmt.setInt(4, user.getGrupo().getGroup_id());
            stmt.setString(5, user.getCracha());
            stmt.setInt(6, user.getChange_pass() ? 1 : 0);
            stmt.setString(7, user.getTurno());
            stmt.setInt(8, user.getSetor().getSetor_id());
            stmt.setInt(9, user.getUser_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateUsuario");
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
            stmt.setInt(2, user.getUser_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateUserName");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateUserPassword(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_users "
                    + "SET password = ? "
                    + "WHERE user_id = ?");
            stmt.setString(1, user.getPass());
            stmt.setInt(2, user.getUser_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateUserPassword");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateUserPassword(Usuario user, int ChangePass) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_users SET password = ?, "
                    + "change_password = ? WHERE user_id = ?");
            stmt.setString(1, user.getPass());
            stmt.setInt(2, ChangePass);
            stmt.setInt(3, user.getUser_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateUserPassword");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void deleteUser(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_users "
                    + "WHERE user_id = ?");
            stmt.setInt(1, user.getUser_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteUser");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

}
