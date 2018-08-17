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
import Classes.Modelo.Acesso;
import Classes.Modelo.Usuario;
import Classes.Modelo.Formulario;
import Classes.Modelo.Grupo;
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
public class AcessoDAO {

    public void createAcesso(Acesso acesso) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_regra_acesso "
                    + "(acesso_id, form_id, group_id) "
                    + "VALUES (?,?,?)");
            stmt.setInt(1, acesso.getAcesso_id());
            stmt.setInt(2, acesso.getForm().getForm_id());
            stmt.setInt(3, acesso.getGrupo().getGroup_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAcesso");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Acesso> readAcesso() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Acesso> acessos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_acesso");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Acesso acess = new Acesso();
                acess.setAcesso_id(rs.getInt("acesso_id"));
                acess.setAcesso(rs.getString("acesso"));
                acessos.add(acess);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAcesso");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return acessos;
    }

    public List<Acesso> readAcessobyGrupo(Grupo grup) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Acesso> acessos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_form.*, tb_regra_acesso.* , tb_acesso.acesso "
                    + "FROM tb_form "
                    + "LEFT JOIN tb_regra_acesso "
                    + "ON tb_form.form_id = tb_regra_acesso.form_id "
                    + "AND tb_regra_acesso.group_id = ? "
                    + "LEFT JOIN tb_acesso "
                    + "ON tb_regra_acesso.acesso_id = tb_acesso.acesso_id "
                    + "ORDER BY tb_form.form_name");
            stmt.setInt(1, grup.getGroup_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Acesso acess = new Acesso();
                Formulario form = new Formulario();
                form.setForm_id(rs.getInt("form_id"));
                form.setForm_name(rs.getString("form_name"));
                acess.setForm(form);
                acess.setAcesso(rs.getString("acesso"));
                acess.setRegra_acesso_id(rs.getInt("regra_acesso_id"));
                acessos.add(acess);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAcessobyGrupo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return acessos;
    }

    public List<Acesso> readAcessobyUser(Usuario user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Acesso> acessos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select tb_form.form_id, "
                    + "tb_form.form_name, tb_users.acesso "
                    + "FROM tb_form "
                    + "LEFT JOIN tb_users "
                    + "ON tb_users.user_id = ?");
            stmt.setInt(1, user.getUser_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Acesso acess = new Acesso();
                Formulario form = new Formulario();
                form.setForm_id(rs.getInt("form_id"));
                form.setForm_name(rs.getString("form_name"));
                acess.setForm(form);
                acess.setAcesso(rs.getString("acesso"));
                acessos.add(acess);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAcessobyUser");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return acessos;
    }

    public void updateAcesso(Acesso acesso) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_regra_acesso SET "
                    + "acesso_id = ?, form_id = ?, group_id = ? "
                    + "WHERE regra_acesso_id = ?");
            stmt.setInt(1, acesso.getAcesso_id());
            stmt.setInt(2, acesso.getForm().getForm_id());
            stmt.setInt(3, acesso.getGrupo().getGroup_id());
            stmt.setInt(4, acesso.getRegra_acesso_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAcesso");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

}
