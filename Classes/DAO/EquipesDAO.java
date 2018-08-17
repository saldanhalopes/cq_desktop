/*
 * Copyright (C) 2018 rafael.lopes
 *
 * Este programa é um software livre: você pode redistribuí-lo e / ou modificar
 * sob os termos da GNU General Public License, conforme publicado pela
 * a Free Software Foundation, seja a versão 3 da Licença, quanto
 * qualquer versão posterior.
 *
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem a garantia implícita de
 * COMERCIALIZAÇÃO OU APTIDÃO PARA UM PROPÓSITO PARTICULAR. Veja o
 * GNU General Public License para obter mais detalhes.
 *
 * Você deve ter recebido uma cópia da GNU General Public License
 *  juntamente com este programa. Caso contrário, veja <http://www.gnu.org/licenses/>.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Cromatografo;
import Classes.Modelo.Equipes;
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
public class EquipesDAO {
    
    public void createEquipes(Equipes equipe) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_equipes "
                    + "(system_name, nome_equipe, turno, "
                    + "user_equipamento, user_preparo, "
                    + "data_registro, user_registro) "
                    + "VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, equipe.getCromatografo().getSystem_name());
            stmt.setString(2, equipe.getNome_equipe());
            stmt.setString(3, equipe.getTurno());
            stmt.setString(4, equipe.getUser_equipamento().getUser());
            stmt.setString(5, equipe.getUser_preparo().getUser());
            stmt.setTimestamp(6, equipe.getData_registro());
            stmt.setString(7, equipe.getUser_registro().getUser());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createEquipes");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Equipes> readEquipes() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Equipes> equipes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_equipes");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Equipes equipe = new Equipes();
                Cromatografo equip = new Cromatografo();
                Usuario user_equipamento = new Usuario();
                Usuario user_preparo = new Usuario();
                Usuario user_registro = new Usuario();
                equipe.setEquipe_id(rs.getInt("equipe_id"));
                equipe.setNome_equipe(rs.getString("nome_equipe"));
                equipe.setTurno(rs.getString("turno"));
                equip.setSystem_name(rs.getString("system_name"));
                equipe.setCromatografo(equip);
                equipe.setData_registro(rs.getTimestamp("data_registro"));
                user_equipamento.setUser(rs.getString("user_equipamento"));
                equipe.setUser_equipamento(user_equipamento);
                user_preparo.setUser(rs.getString("user_preparo"));
                equipe.setUser_preparo(user_preparo);
                user_registro.setUser(rs.getString("user_registro"));
                equipe.setUser_registro(user_registro);
                equipes.add(equipe);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readEquipes");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return equipes;
    }

    public void updateEquipes(Equipes equipe) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_equipes SET "
                    + "nome_equipe = ?, turno = ?, system_name = ?, "
                    + "data_registro = ?, user_equipamento = ?, "
                    + "user_preparo = ?, user_registro = ? "
                    + "WHERE equipe_id = ?");
            stmt.setString(1, equipe.getNome_equipe());
            stmt.setString(2, equipe.getTurno());
            stmt.setString(3, equipe.getCromatografo().getSystem_name());
            stmt.setTimestamp(4, equipe.getData_registro());
            stmt.setString(5, equipe.getUser_equipamento().getUser());
            stmt.setString(6, equipe.getUser_preparo().getUser());
            stmt.setString(7, equipe.getUser_registro().getUser());
            stmt.setInt(8, equipe.getEquipe_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateEquipes");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectEquipes(Equipes equipe) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_equipes WHERE equipe_id = ?");
            stmt.setInt(1, equipe.getEquipe_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cromatografo equip = new Cromatografo();
                Usuario user_equipamento = new Usuario();
                Usuario user_preparo = new Usuario();
                Usuario user_registro = new Usuario();
                equipe.setEquipe_id(rs.getInt("equipe_id"));
                equipe.setNome_equipe(rs.getString("nome_equipe"));
                equipe.setTurno(rs.getString("turno"));
                equip.setSystem_name(rs.getString("system_name"));
                equipe.setCromatografo(equip);
                equipe.setData_registro(rs.getTimestamp("data_registro"));
                user_equipamento.setUser(rs.getString("user_equipamento"));
                equipe.setUser_equipamento(user_equipamento);
                user_preparo.setUser(rs.getString("user_preparo"));
                equipe.setUser_preparo(user_preparo);
                user_registro.setUser(rs.getString("user_registro"));
                equipe.setUser_registro(user_registro);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectEquipes");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteEquipes(Equipes equipe) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_equipes WHERE equipe_id = ?");
            stmt.setInt(1, equipe.getEquipe_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteEquipes");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
