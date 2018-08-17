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
import Classes.Modelo.Campanha;
import Classes.Modelo.Cromatografo;
import Classes.Modelo.SystemSuitability;
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
public class SystemSuitabilityDAO {

    public void createSystemSuitability(SystemSuitability system) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_system_suitability (system_name, "
                    + "solucao_1, numero_solucao_1, descricao_solucao_1, data_solucao_1, user_solucao_1, "
                    + "solucao_2, numero_solucao_2, descricao_solucao_2, data_solucao_2, user_solucao_2, "
                    + "solucao_3, numero_solucao_3, descricao_solucao_3, data_solucao_3, user_solucao_3, "
                    + "solucao_4, numero_solucao_4, descricao_solucao_4, data_solucao_4, user_solucao_4, "
                    + "solucao_5, numero_solucao_5, descricao_solucao_5, data_solucao_5, user_solucao_5, "
                    + "solucao_6, numero_solucao_6, descricao_solucao_6, data_solucao_6, user_solucao_6, "
                    + "solucao_7, numero_solucao_7, descricao_solucao_7, data_solucao_7, user_solucao_7, "
                    + "solucao_8, numero_solucao_8, descricao_solucao_8, data_solucao_8, user_solucao_8, "
                    + "solucao_9, numero_solucao_9, descricao_solucao_9, data_solucao_9, user_solucao_9, "
                    + "data_registro, data_inicio, user_name, log_campanha_id) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, "
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, system.getCromatografo().getSystem_name());
            stmt.setString(2, system.getSolucao_1());
            stmt.setInt(3, system.getNumero_solucao_1());
            stmt.setString(4, system.getDescricao_solucao_1());
            stmt.setTimestamp(5, system.getData_solucao_1());
            stmt.setString(6, system.getUser_solucao_1().getUser());
            stmt.setString(7, system.getSolucao_2());
            stmt.setInt(8, system.getNumero_solucao_2());
            stmt.setString(9, system.getDescricao_solucao_2());
            stmt.setTimestamp(10, system.getData_solucao_2());
            stmt.setString(11, system.getUser_solucao_2().getUser());
            stmt.setString(12, system.getSolucao_3());
            stmt.setInt(13, system.getNumero_solucao_3());
            stmt.setString(14, system.getDescricao_solucao_3());
            stmt.setTimestamp(15, system.getData_solucao_3());
            stmt.setString(16, system.getUser_solucao_3().getUser());
            stmt.setString(17, system.getSolucao_4());
            stmt.setInt(18, system.getNumero_solucao_4());
            stmt.setString(19, system.getDescricao_solucao_4());
            stmt.setTimestamp(20, system.getData_solucao_4());
            stmt.setString(21, system.getUser_solucao_4().getUser());
            stmt.setString(22, system.getSolucao_5());
            stmt.setInt(23, system.getNumero_solucao_5());
            stmt.setString(24, system.getDescricao_solucao_5());
            stmt.setTimestamp(25, system.getData_solucao_5());
            stmt.setString(26, system.getUser_solucao_5().getUser());
            stmt.setString(27, system.getSolucao_6());
            stmt.setInt(28, system.getNumero_solucao_6());
            stmt.setString(29, system.getDescricao_solucao_6());
            stmt.setTimestamp(30, system.getData_solucao_6());
            stmt.setString(31, system.getUser_solucao_6().getUser());
            stmt.setString(32, system.getSolucao_7());
            stmt.setInt(33, system.getNumero_solucao_7());
            stmt.setString(34, system.getDescricao_solucao_7());
            stmt.setTimestamp(35, system.getData_solucao_7());
            stmt.setString(36, system.getUser_solucao_7().getUser());
            stmt.setString(37, system.getSolucao_8());
            stmt.setInt(38, system.getNumero_solucao_8());
            stmt.setString(39, system.getDescricao_solucao_8());
            stmt.setTimestamp(40, system.getData_solucao_8());
            stmt.setString(41, system.getUser_solucao_8().getUser());
            stmt.setString(42, system.getSolucao_9());
            stmt.setInt(43, system.getNumero_solucao_9());
            stmt.setString(44, system.getDescricao_solucao_9());
            stmt.setTimestamp(45, system.getData_solucao_9());
            stmt.setString(46, system.getUser_solucao_9().getUser());
            stmt.setTimestamp(47, system.getData_registro());
            stmt.setTimestamp(48, system.getData_inicio());
            stmt.setString(49, system.getUser_name().getUser());
            stmt.setInt(50, system.getCampanha().getLog_campanha_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createSystemSuitability");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<SystemSuitability> readSystemSuitability() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<SystemSuitability> systems = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_system_suitability");
            rs = stmt.executeQuery();
            while (rs.next()) {
                SystemSuitability system = new SystemSuitability();
                Cromatografo equip = new Cromatografo();
                Usuario user = new Usuario();
                equip.setSystem_name(rs.getString("system_name"));
                system.setCromatografo(equip);
                system.setSolucao_1(rs.getString("solucao_1"));
                system.setNumero_solucao_1(rs.getInt("numero_solucao_1"));
                system.setDescricao_solucao_1(rs.getString("descricao_solucao_1"));
                system.setData_solucao_1(rs.getTimestamp("data_solucao_1"));
                user.setUser(rs.getString("user_solucao_1"));
                system.setUser_solucao_1(user);
                system.setSolucao_2(rs.getString("solucao_2"));
                system.setNumero_solucao_2(rs.getInt("numero_solucao_2"));
                system.setDescricao_solucao_2(rs.getString("descricao_solucao_2"));
                system.setData_solucao_2(rs.getTimestamp("data_solucao_2"));
                user.setUser(rs.getString("user_solucao_2"));
                system.setUser_solucao_2(user);
                system.setSolucao_3(rs.getString("solucao_3"));
                system.setNumero_solucao_3(rs.getInt("numero_solucao_3"));
                system.setDescricao_solucao_3(rs.getString("descricao_solucao_3"));
                system.setData_solucao_3(rs.getTimestamp("data_solucao_3"));
                user.setUser(rs.getString("user_solucao_3"));
                system.setUser_solucao_3(user);
                system.setSolucao_4(rs.getString("solucao_4"));
                system.setNumero_solucao_4(rs.getInt("numero_solucao_4"));
                system.setDescricao_solucao_4(rs.getString("descricao_solucao_4"));
                system.setData_solucao_4(rs.getTimestamp("data_solucao_4"));
                user.setUser(rs.getString("user_solucao_4"));
                system.setUser_solucao_4(user);
                system.setSolucao_5(rs.getString("solucao_5"));
                system.setNumero_solucao_5(rs.getInt("numero_solucao_5"));
                system.setDescricao_solucao_5(rs.getString("descricao_solucao_5"));
                system.setData_solucao_5(rs.getTimestamp("data_solucao_5"));
                user.setUser(rs.getString("user_solucao_5"));
                system.setUser_solucao_5(user);
                system.setSolucao_6(rs.getString("solucao_6"));
                system.setNumero_solucao_6(rs.getInt("numero_solucao_6"));
                system.setDescricao_solucao_6(rs.getString("descricao_solucao_6"));
                system.setData_solucao_6(rs.getTimestamp("data_solucao_6"));
                user.setUser(rs.getString("user_solucao_6"));
                system.setUser_solucao_6(user);
                system.setSolucao_7(rs.getString("solucao_7"));
                system.setNumero_solucao_7(rs.getInt("numero_solucao_7"));
                system.setDescricao_solucao_7(rs.getString("descricao_solucao_7"));
                system.setData_solucao_7(rs.getTimestamp("data_solucao_7"));
                user.setUser(rs.getString("user_solucao_7"));
                system.setUser_solucao_7(user);
                system.setSolucao_8(rs.getString("solucao_8"));
                system.setNumero_solucao_8(rs.getInt("numero_solucao_8"));
                system.setDescricao_solucao_8(rs.getString("descricao_solucao_8"));
                system.setData_solucao_8(rs.getTimestamp("data_solucao_8"));
                user.setUser(rs.getString("user_solucao_8"));
                system.setUser_solucao_8(user);
                system.setSolucao_9(rs.getString("solucao_9"));
                system.setNumero_solucao_9(rs.getInt("numero_solucao_9"));
                system.setDescricao_solucao_9(rs.getString("descricao_solucao_9"));
                system.setData_solucao_9(rs.getTimestamp("data_solucao_9"));
                user.setUser(rs.getString("user_solucao_9"));
                system.setUser_solucao_9(user);
                system.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                system.setUser_name(user);
                system.setCheck_system_suitability(rs.getBoolean("check_system_suitability"));
                user.setUser(rs.getString("user_check"));
                system.setUser_check(user);
                systems.add(system);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readSystemSuitability");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return systems;
    }

    public List<SystemSuitability> readSystemSuitabilityBySystem(String system_name) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<SystemSuitability> systems = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select TOP(50) tb_system_suitability.*, tb_log_campanha.nome_campanha "
                    + "FROM tb_system_suitability "
                    + "LEFT JOIN tb_log_campanha "
                    + "ON tb_system_suitability.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "WHERE tb_system_suitability.system_name = ?  "
                    + "ORDER BY tb_system_suitability.system_suitability_id DESC ");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                SystemSuitability system = new SystemSuitability();
                Cromatografo equip = new Cromatografo();
                Usuario user1 = new Usuario();
                Usuario user2 = new Usuario();
                Usuario user3 = new Usuario();
                Usuario user4 = new Usuario();
                Usuario user5 = new Usuario();
                Usuario user6 = new Usuario();
                Usuario user7 = new Usuario();
                Usuario user8 = new Usuario();
                Usuario user9 = new Usuario();
                Usuario usercheck = new Usuario();
                Usuario user = new Usuario();
                Campanha camp = new Campanha();
                camp.setNome_campanha(rs.getString("nome_campanha"));
                system.setCampanha(camp);
                system.setSystem_suitability_id(rs.getInt("system_suitability_id"));
                equip.setSystem_name(rs.getString("system_name"));
                system.setCromatografo(equip);
                system.setSolucao_1(rs.getString("solucao_1"));
                system.setNumero_solucao_1(rs.getInt("numero_solucao_1"));
                system.setDescricao_solucao_1(rs.getString("descricao_solucao_1"));
                system.setData_solucao_1(rs.getTimestamp("data_solucao_1"));
                user1.setUser(rs.getString("user_solucao_1"));
                system.setUser_solucao_1(user1);
                system.setSolucao_2(rs.getString("solucao_2"));
                system.setNumero_solucao_2(rs.getInt("numero_solucao_2"));
                system.setDescricao_solucao_2(rs.getString("descricao_solucao_2"));
                system.setData_solucao_2(rs.getTimestamp("data_solucao_2"));
                user2.setUser(rs.getString("user_solucao_2"));
                system.setUser_solucao_2(user2);
                system.setSolucao_3(rs.getString("solucao_3"));
                system.setNumero_solucao_3(rs.getInt("numero_solucao_3"));
                system.setDescricao_solucao_3(rs.getString("descricao_solucao_3"));
                system.setData_solucao_3(rs.getTimestamp("data_solucao_3"));
                user3.setUser(rs.getString("user_solucao_3"));
                system.setUser_solucao_3(user3);
                system.setSolucao_4(rs.getString("solucao_4"));
                system.setNumero_solucao_4(rs.getInt("numero_solucao_4"));
                system.setDescricao_solucao_4(rs.getString("descricao_solucao_4"));
                system.setData_solucao_4(rs.getTimestamp("data_solucao_4"));
                user4.setUser(rs.getString("user_solucao_4"));
                system.setUser_solucao_4(user4);
                system.setSolucao_5(rs.getString("solucao_5"));
                system.setNumero_solucao_5(rs.getInt("numero_solucao_5"));
                system.setDescricao_solucao_5(rs.getString("descricao_solucao_5"));
                system.setData_solucao_5(rs.getTimestamp("data_solucao_5"));
                user5.setUser(rs.getString("user_solucao_5"));
                system.setUser_solucao_5(user5);
                system.setSolucao_6(rs.getString("solucao_6"));
                system.setNumero_solucao_6(rs.getInt("numero_solucao_6"));
                system.setDescricao_solucao_6(rs.getString("descricao_solucao_6"));
                system.setData_solucao_6(rs.getTimestamp("data_solucao_6"));
                user6.setUser(rs.getString("user_solucao_6"));
                system.setUser_solucao_6(user6);
                system.setSolucao_7(rs.getString("solucao_7"));
                system.setNumero_solucao_7(rs.getInt("numero_solucao_7"));
                system.setDescricao_solucao_7(rs.getString("descricao_solucao_7"));
                system.setData_solucao_7(rs.getTimestamp("data_solucao_7"));
                user7.setUser(rs.getString("user_solucao_7"));
                system.setUser_solucao_7(user7);
                system.setSolucao_8(rs.getString("solucao_8"));
                system.setNumero_solucao_8(rs.getInt("numero_solucao_8"));
                system.setDescricao_solucao_8(rs.getString("descricao_solucao_8"));
                system.setData_solucao_8(rs.getTimestamp("data_solucao_8"));
                user8.setUser(rs.getString("user_solucao_8"));
                system.setUser_solucao_8(user8);
                system.setSolucao_9(rs.getString("solucao_9"));
                system.setNumero_solucao_9(rs.getInt("numero_solucao_9"));
                system.setDescricao_solucao_9(rs.getString("descricao_solucao_9"));
                system.setData_solucao_9(rs.getTimestamp("data_solucao_9"));
                user9.setUser(rs.getString("user_solucao_9"));
                system.setUser_solucao_9(user9);
                system.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                system.setUser_name(user);
                system.setCheck_system_suitability(rs.getBoolean("check_system_suitability"));
                usercheck.setUser(rs.getString("user_check"));
                system.setUser_check(usercheck);
                systems.add(system);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readSystemSuitabilityBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return systems;
    }

    public void updateSystemSuitability(SystemSuitability system) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_system_suitability SET system_name = ?, "
                    + "solucao_1 = ?, numero_solucao_1 = ?, descricao_solucao_1 = ?, data_solucao_1 = ?, user_solucao_1 = ?, "
                    + "solucao_2 = ?, numero_solucao_2 = ?, descricao_solucao_2 = ?, data_solucao_2 = ?, user_solucao_2 = ?, "
                    + "solucao_3 = ?, numero_solucao_3 = ?, descricao_solucao_3 = ?, data_solucao_3 = ?, user_solucao_3 = ?, "
                    + "solucao_4 = ?, numero_solucao_4 = ?, descricao_solucao_4 = ?, data_solucao_4 = ?, user_solucao_4 = ?, "
                    + "solucao_5 = ?, numero_solucao_5 = ?, descricao_solucao_5 = ?, data_solucao_5 = ?, user_solucao_5 = ?, "
                    + "solucao_6 = ?, numero_solucao_6 = ?, descricao_solucao_6 = ?, data_solucao_6 = ?, user_solucao_6 = ?, "
                    + "solucao_7 = ?, numero_solucao_7 = ?, descricao_solucao_7 = ?, data_solucao_7 = ?, user_solucao_7 = ?, "
                    + "solucao_8 = ?, numero_solucao_8 = ?, descricao_solucao_8 = ?, data_solucao_8 = ?, user_solucao_8 = ?, "
                    + "solucao_9 = ?, numero_solucao_9 = ?, descricao_solucao_9 = ?, data_solucao_9 = ?, user_solucao_9 = ?, "
                    + "data_registro = ?, user_name = ? "
                    + "WHERE system_suitability_id = ?");
            stmt.setString(1, system.getCromatografo().getSystem_name());
            stmt.setString(2, system.getSolucao_1());
            stmt.setInt(3, system.getNumero_solucao_1());
            stmt.setString(4, system.getDescricao_solucao_1());
            stmt.setTimestamp(5, system.getData_solucao_1());
            stmt.setString(6, system.getUser_solucao_1().getUser());
            stmt.setString(7, system.getSolucao_2());
            stmt.setInt(8, system.getNumero_solucao_2());
            stmt.setString(9, system.getDescricao_solucao_2());
            stmt.setTimestamp(10, system.getData_solucao_2());
            stmt.setString(11, system.getUser_solucao_2().getUser());
            stmt.setString(12, system.getSolucao_3());
            stmt.setInt(13, system.getNumero_solucao_3());
            stmt.setString(14, system.getDescricao_solucao_3());
            stmt.setTimestamp(15, system.getData_solucao_3());
            stmt.setString(16, system.getUser_solucao_3().getUser());
            stmt.setString(17, system.getSolucao_4());
            stmt.setInt(18, system.getNumero_solucao_4());
            stmt.setString(19, system.getDescricao_solucao_4());
            stmt.setTimestamp(20, system.getData_solucao_4());
            stmt.setString(21, system.getUser_solucao_4().getUser());
            stmt.setString(22, system.getSolucao_5());
            stmt.setInt(23, system.getNumero_solucao_5());
            stmt.setString(24, system.getDescricao_solucao_5());
            stmt.setTimestamp(25, system.getData_solucao_5());
            stmt.setString(26, system.getUser_solucao_5().getUser());
            stmt.setString(27, system.getSolucao_6());
            stmt.setInt(28, system.getNumero_solucao_6());
            stmt.setString(29, system.getDescricao_solucao_6());
            stmt.setTimestamp(30, system.getData_solucao_6());
            stmt.setString(31, system.getUser_solucao_6().getUser());
            stmt.setString(32, system.getSolucao_7());
            stmt.setInt(33, system.getNumero_solucao_7());
            stmt.setString(34, system.getDescricao_solucao_7());
            stmt.setTimestamp(35, system.getData_solucao_7());
            stmt.setString(36, system.getUser_solucao_7().getUser());
            stmt.setString(37, system.getSolucao_8());
            stmt.setInt(38, system.getNumero_solucao_8());
            stmt.setString(39, system.getDescricao_solucao_8());
            stmt.setTimestamp(40, system.getData_solucao_8());
            stmt.setString(41, system.getUser_solucao_8().getUser());
            stmt.setString(42, system.getSolucao_9());
            stmt.setInt(43, system.getNumero_solucao_9());
            stmt.setString(44, system.getDescricao_solucao_9());
            stmt.setTimestamp(45, system.getData_solucao_9());
            stmt.setString(46, system.getUser_solucao_9().getUser());
            stmt.setTimestamp(47, system.getData_registro());
            stmt.setString(48, system.getUser_name().getUser());
            stmt.setInt(49, system.getSystem_suitability_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateSystemSuitability");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateValidarSystemSuitability(SystemSuitability system) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_system_suitability SET "
                    + "check_system_suitability = ?, user_check = ?, data_registro = ? "
                    + "WHERE system_suitability_id = ?");
            stmt.setInt(1, (system.getCheck_system_suitability()) ? 1 : 0);
            stmt.setString(2, system.getUser_check().getUser());
            stmt.setTimestamp(3, system.getData_registro());
            stmt.setInt(4, system.getSystem_suitability_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateValidarSystemSuitability");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectSystemSuitabilityBySystem(SystemSuitability system) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select tb_system_suitability.* "
                    + "FROM tb_system_suitability "
                    + "WHERE system_name = ?");
            stmt.setString(1, system.getCromatografo().getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user1 = new Usuario();
                Usuario user2 = new Usuario();
                Usuario user3 = new Usuario();
                Usuario user4 = new Usuario();
                Usuario user5 = new Usuario();
                Usuario user6 = new Usuario();
                Usuario user7 = new Usuario();
                Usuario user8 = new Usuario();
                Usuario user9 = new Usuario();
                Usuario user = new Usuario();
                system.setSystem_suitability_id(rs.getInt("system_suitability_id"));
                system.setSolucao_1(rs.getString("solucao_1"));
                system.setNumero_solucao_1(rs.getInt("numero_solucao_1"));
                system.setDescricao_solucao_1(rs.getString("descricao_solucao_1"));
                system.setData_solucao_1(rs.getTimestamp("data_solucao_1"));
                user1.setUser(rs.getString("user_solucao_1"));
                system.setUser_solucao_1(user1);
                system.setSolucao_2(rs.getString("solucao_2"));
                system.setNumero_solucao_2(rs.getInt("numero_solucao_2"));
                system.setDescricao_solucao_2(rs.getString("descricao_solucao_2"));
                system.setData_solucao_2(rs.getTimestamp("data_solucao_2"));
                user2.setUser(rs.getString("user_solucao_2"));
                system.setUser_solucao_2(user2);
                system.setSolucao_3(rs.getString("solucao_3"));
                system.setNumero_solucao_3(rs.getInt("numero_solucao_3"));
                system.setDescricao_solucao_3(rs.getString("descricao_solucao_3"));
                system.setData_solucao_3(rs.getTimestamp("data_solucao_3"));
                user3.setUser(rs.getString("user_solucao_3"));
                system.setUser_solucao_3(user3);
                system.setSolucao_4(rs.getString("solucao_4"));
                system.setNumero_solucao_4(rs.getInt("numero_solucao_4"));
                system.setDescricao_solucao_4(rs.getString("descricao_solucao_4"));
                system.setData_solucao_4(rs.getTimestamp("data_solucao_4"));
                user4.setUser(rs.getString("user_solucao_4"));
                system.setUser_solucao_4(user4);
                system.setSolucao_5(rs.getString("solucao_5"));
                system.setNumero_solucao_5(rs.getInt("numero_solucao_5"));
                system.setDescricao_solucao_5(rs.getString("descricao_solucao_5"));
                system.setData_solucao_5(rs.getTimestamp("data_solucao_5"));
                user5.setUser(rs.getString("user_solucao_5"));
                system.setUser_solucao_5(user5);
                system.setSolucao_6(rs.getString("solucao_6"));
                system.setNumero_solucao_6(rs.getInt("numero_solucao_6"));
                system.setDescricao_solucao_6(rs.getString("descricao_solucao_6"));
                system.setData_solucao_6(rs.getTimestamp("data_solucao_6"));
                user6.setUser(rs.getString("user_solucao_6"));
                system.setUser_solucao_6(user6);
                system.setSolucao_7(rs.getString("solucao_7"));
                system.setNumero_solucao_7(rs.getInt("numero_solucao_7"));
                system.setDescricao_solucao_7(rs.getString("descricao_solucao_7"));
                system.setData_solucao_7(rs.getTimestamp("data_solucao_7"));
                user7.setUser(rs.getString("user_solucao_7"));
                system.setUser_solucao_7(user7);
                system.setSolucao_8(rs.getString("solucao_8"));
                system.setNumero_solucao_8(rs.getInt("numero_solucao_8"));
                system.setDescricao_solucao_8(rs.getString("descricao_solucao_8"));
                system.setData_solucao_8(rs.getTimestamp("data_solucao_8"));
                user8.setUser(rs.getString("user_solucao_8"));
                system.setUser_solucao_8(user8);
                system.setSolucao_9(rs.getString("solucao_9"));
                system.setNumero_solucao_9(rs.getInt("numero_solucao_9"));
                system.setDescricao_solucao_9(rs.getString("descricao_solucao_9"));
                system.setData_solucao_9(rs.getTimestamp("data_solucao_9"));
                user8.setUser(rs.getString("user_solucao_9"));
                system.setUser_solucao_9(user8);
                system.setData_registro(rs.getTimestamp("data_registro"));
                system.setData_fim(rs.getTimestamp("data_fim"));
                user9.setUser(rs.getString("user_name"));
                system.setUser_name(user9);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectSystemSuitabilityBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectSystemSuitability(SystemSuitability system) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select tb_system_suitability.* "
                    + "FROM tb_system_suitability "
                    + "WHERE tb_system_suitability.system_suitability_id = ?");
            stmt.setInt(1, system.getSystem_suitability_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user1 = new Usuario();
                Usuario user2 = new Usuario();
                Usuario user3 = new Usuario();
                Usuario user4 = new Usuario();
                Usuario user5 = new Usuario();
                Usuario user6 = new Usuario();
                Usuario user7 = new Usuario();
                Usuario user8 = new Usuario();
                Usuario user9 = new Usuario();
                Usuario usercheck = new Usuario();
                Usuario user = new Usuario();
                system.setSolucao_1(rs.getString("solucao_1"));
                system.setNumero_solucao_1(rs.getInt("numero_solucao_1"));
                system.setDescricao_solucao_1(rs.getString("descricao_solucao_1"));
                system.setData_solucao_1(rs.getTimestamp("data_solucao_1"));
                user1.setUser(rs.getString("user_solucao_1"));
                system.setUser_solucao_1(user1);
                system.setSolucao_2(rs.getString("solucao_2"));
                system.setNumero_solucao_2(rs.getInt("numero_solucao_2"));
                system.setDescricao_solucao_2(rs.getString("descricao_solucao_2"));
                system.setData_solucao_2(rs.getTimestamp("data_solucao_2"));
                user2.setUser(rs.getString("user_solucao_2"));
                system.setUser_solucao_2(user2);
                system.setSolucao_3(rs.getString("solucao_3"));
                system.setNumero_solucao_3(rs.getInt("numero_solucao_3"));
                system.setDescricao_solucao_3(rs.getString("descricao_solucao_3"));
                system.setData_solucao_3(rs.getTimestamp("data_solucao_3"));
                user3.setUser(rs.getString("user_solucao_3"));
                system.setUser_solucao_3(user3);
                system.setSolucao_4(rs.getString("solucao_4"));
                system.setNumero_solucao_4(rs.getInt("numero_solucao_4"));
                system.setDescricao_solucao_4(rs.getString("descricao_solucao_4"));
                system.setData_solucao_4(rs.getTimestamp("data_solucao_4"));
                user4.setUser(rs.getString("user_solucao_4"));
                system.setUser_solucao_4(user4);
                system.setSolucao_5(rs.getString("solucao_5"));
                system.setNumero_solucao_5(rs.getInt("numero_solucao_5"));
                system.setDescricao_solucao_5(rs.getString("descricao_solucao_5"));
                system.setData_solucao_5(rs.getTimestamp("data_solucao_5"));
                user5.setUser(rs.getString("user_solucao_5"));
                system.setUser_solucao_5(user5);
                system.setSolucao_6(rs.getString("solucao_6"));
                system.setNumero_solucao_6(rs.getInt("numero_solucao_6"));
                system.setDescricao_solucao_6(rs.getString("descricao_solucao_6"));
                system.setData_solucao_6(rs.getTimestamp("data_solucao_6"));
                user6.setUser(rs.getString("user_solucao_6"));
                system.setUser_solucao_6(user6);
                system.setSolucao_7(rs.getString("solucao_7"));
                system.setNumero_solucao_7(rs.getInt("numero_solucao_7"));
                system.setDescricao_solucao_7(rs.getString("descricao_solucao_7"));
                system.setData_solucao_7(rs.getTimestamp("data_solucao_7"));
                user7.setUser(rs.getString("user_solucao_7"));
                system.setUser_solucao_7(user7);
                system.setSolucao_8(rs.getString("solucao_8"));
                system.setNumero_solucao_8(rs.getInt("numero_solucao_8"));
                system.setDescricao_solucao_8(rs.getString("descricao_solucao_8"));
                system.setData_solucao_8(rs.getTimestamp("data_solucao_8"));
                user8.setUser(rs.getString("user_solucao_8"));
                system.setUser_solucao_8(user8);
                system.setSolucao_9(rs.getString("solucao_9"));
                system.setNumero_solucao_9(rs.getInt("numero_solucao_9"));
                system.setDescricao_solucao_9(rs.getString("descricao_solucao_9"));
                system.setData_solucao_9(rs.getTimestamp("data_solucao_9"));
                user9.setUser(rs.getString("user_solucao_9"));
                system.setUser_solucao_9(user9);
                system.setData_registro(rs.getTimestamp("data_registro"));
                system.setData_fim(rs.getTimestamp("data_fim"));
                user.setUser(rs.getString("user_name"));
                system.setUser_name(user);
                system.setCheck_system_suitability(rs.getBoolean("check_system_suitability"));
                usercheck.setUser(rs.getString("user_check"));
                system.setUser_check(usercheck);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectSystemSuitability");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectUltimoSystemSuitabilityBySystem(SystemSuitability system) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_system_suitability.* "
                    + "FROM tb_system_suitability "
                    + "WHERE tb_system_suitability.system_name = ?  "
                    + "ORDER BY tb_system_suitability.system_suitability_id DESC ");
            stmt.setString(1, system.getCromatografo().getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                system.setSystem_suitability_id(rs.getInt("system_suitability_id"));
                system.setSolucao_1(rs.getString("solucao_1"));
                system.setNumero_solucao_1(rs.getInt("numero_solucao_1"));
                system.setDescricao_solucao_1(rs.getString("descricao_solucao_1"));
                system.setData_solucao_1(rs.getTimestamp("data_solucao_1"));
                user.setUser(rs.getString("user_solucao_1"));
                system.setUser_solucao_1(user);
                system.setSolucao_2(rs.getString("solucao_2"));
                system.setNumero_solucao_2(rs.getInt("numero_solucao_2"));
                system.setDescricao_solucao_2(rs.getString("descricao_solucao_2"));
                system.setData_solucao_2(rs.getTimestamp("data_solucao_2"));
                user.setUser(rs.getString("user_solucao_2"));
                system.setUser_solucao_2(user);
                system.setSolucao_3(rs.getString("solucao_3"));
                system.setNumero_solucao_3(rs.getInt("numero_solucao_3"));
                system.setDescricao_solucao_3(rs.getString("descricao_solucao_3"));
                system.setData_solucao_3(rs.getTimestamp("data_solucao_3"));
                user.setUser(rs.getString("user_solucao_3"));
                system.setUser_solucao_3(user);
                system.setSolucao_4(rs.getString("solucao_4"));
                system.setNumero_solucao_4(rs.getInt("numero_solucao_4"));
                system.setDescricao_solucao_4(rs.getString("descricao_solucao_4"));
                system.setData_solucao_4(rs.getTimestamp("data_solucao_4"));
                user.setUser(rs.getString("user_solucao_4"));
                system.setUser_solucao_4(user);
                system.setSolucao_5(rs.getString("solucao_5"));
                system.setNumero_solucao_5(rs.getInt("numero_solucao_5"));
                system.setDescricao_solucao_5(rs.getString("descricao_solucao_5"));
                system.setData_solucao_5(rs.getTimestamp("data_solucao_5"));
                user.setUser(rs.getString("user_solucao_5"));
                system.setUser_solucao_5(user);
                system.setSolucao_6(rs.getString("solucao_6"));
                system.setNumero_solucao_6(rs.getInt("numero_solucao_6"));
                system.setDescricao_solucao_6(rs.getString("descricao_solucao_6"));
                system.setData_solucao_6(rs.getTimestamp("data_solucao_6"));
                user.setUser(rs.getString("user_solucao_6"));
                system.setUser_solucao_6(user);
                system.setSolucao_7(rs.getString("solucao_7"));
                system.setNumero_solucao_7(rs.getInt("numero_solucao_7"));
                system.setDescricao_solucao_7(rs.getString("descricao_solucao_7"));
                system.setData_solucao_7(rs.getTimestamp("data_solucao_7"));
                user.setUser(rs.getString("user_solucao_7"));
                system.setUser_solucao_7(user);
                system.setSolucao_8(rs.getString("solucao_8"));
                system.setNumero_solucao_8(rs.getInt("numero_solucao_8"));
                system.setDescricao_solucao_8(rs.getString("descricao_solucao_8"));
                system.setData_solucao_8(rs.getTimestamp("data_solucao_8"));
                user.setUser(rs.getString("user_solucao_8"));
                system.setUser_solucao_8(user);
                system.setSolucao_9(rs.getString("solucao_9"));
                system.setNumero_solucao_9(rs.getInt("numero_solucao_9"));
                system.setDescricao_solucao_9(rs.getString("descricao_solucao_9"));
                system.setData_solucao_9(rs.getTimestamp("data_solucao_9"));
                user.setUser(rs.getString("user_solucao_9"));
                system.setUser_solucao_9(user);
                system.setData_registro(rs.getTimestamp("data_registro"));
                system.setData_fim(rs.getTimestamp("data_fim"));
                user.setUser(rs.getString("user_name"));
                system.setUser_name(user);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectUltimoSystemSuitabilityBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public int selectIDUltimoSystemSuitabilityBySystem(SystemSuitability system) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_system_suitability.system_suitability_id "
                    + "FROM tb_system_suitability "
                    + "WHERE tb_system_suitability.system_name = ?  "
                    + "ORDER BY tb_system_suitability.system_suitability_id DESC ");
            stmt.setString(1, system.getCromatografo().getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("system_suitability_id");
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectIDUltimoSystemSuitabilityBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return id;
    }

    public void updateFimLogSystemSuitability(SystemSuitability system) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_system_suitability SET "
                    + "data_registro = ?, data_fim = ? "
                    + "WHERE system_suitability_id = ?");
            stmt.setTimestamp(1, system.getData_registro());
            stmt.setTimestamp(2, system.getData_fim());
            stmt.setInt(3, system.getSystem_suitability_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateFimLogSystemSuitability");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void deleteSystemSuitability(SystemSuitability system) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_system_suitability WHERE system_suitability_id = ?");
            stmt.setInt(1, system.getSystem_suitability_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteSystemSuitability");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
