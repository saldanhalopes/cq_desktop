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
import Classes.Modelo.Analise;
import Classes.Modelo.AnaliseCromatografo;
import Classes.Modelo.Campanha;
import Classes.Modelo.Cromatografo;
import Classes.Modelo.Lote;
import Classes.Modelo.Material;
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
public class AnaliseCromatografoDAO {

    public void createAnaliseCromatografo(AnaliseCromatografo anlscro) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_analise_lote "
                    + "(analise_id, lote_id, system_name, "
                    + "data_hora_inicio, user_inicio,"
                    + "status, obs, data_hora_registro, user_registro, "
                    + "retrabalho_lote, log_campanha_id) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, anlscro.getAnalise().getAnalise_id());
            stmt.setInt(2, anlscro.getLote().getLote_id());
            stmt.setString(3, anlscro.getCromatografo().getSystem_name());
            stmt.setTimestamp(4, anlscro.getData_hora_inicio());
            stmt.setString(5, anlscro.getUser_inicio().getUser());
            stmt.setString(6, anlscro.getStatus());
            stmt.setString(7, anlscro.getObs());
            stmt.setTimestamp(8, anlscro.getData_hora_registro());
            stmt.setString(9, anlscro.getUser_registro().getUser());
            stmt.setInt(10, (anlscro.getRetrabalho_lote()) ? 1 : 0);
            stmt.setInt(11, anlscro.getCampanha().getLog_campanha_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAnaliseCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
    
    public void createLoteCampanha(AnaliseCromatografo anlscro) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_analise_lote "
                    + "(analise_id, lote_id, obs, "
                    + "data_hora_registro, user_registro) "
                    + "VALUES (?,?,?,?,?)");
            stmt.setInt(1, anlscro.getAnalise().getAnalise_id());
            stmt.setInt(2, anlscro.getLote().getLote_id());
            stmt.setString(3, anlscro.getLote().getLote_obs());
            stmt.setTimestamp(4, anlscro.getData_hora_registro());
            stmt.setString(5, anlscro.getUser_registro().getUser());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAnaliseCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
    
    public List<AnaliseCromatografo> readAnaliseCromatografo() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AnaliseCromatografo> anlscros = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(10000) tb_log_analise_lote.*, tb_cromatografo.system_name as sys_name, "
                    + "tb_analise.analise, tb_lote.lote_produto, tb_material.material, tb_log_campanha.nome_campanha "
                    + "FROM tb_log_analise_lote "
                    + "INNER JOIN tb_cromatografo "
                    + "ON tb_log_analise_lote.system_name = tb_cromatografo.system_name "
                    + "INNER JOIN tb_analise "
                    + "ON tb_log_analise_lote.analise_id = tb_analise.analise_id "
                    + "INNER JOIN tb_lote "
                    + "ON tb_log_analise_lote.lote_id = tb_lote.lote_id "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "LEFT JOIN tb_log_campanha "
                    + "ON tb_log_analise_lote.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "ORDER BY log_lote_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                AnaliseCromatografo anlscro = new AnaliseCromatografo();
                Analise anls = new Analise();
                Cromatografo equip = new Cromatografo();
                Lote lote = new Lote();
                Material prod = new Material();
                Usuario user_inicio = new Usuario();
                Usuario user_fim = new Usuario();
                Usuario user_registro = new Usuario();
                Campanha camp = new Campanha();
                camp.setNome_campanha(rs.getString("nome_campanha"));
                anlscro.setCampanha(camp);
                anlscro.setLog_lote_id(rs.getInt("log_lote_id"));
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                anlscro.setAnalise(anls);
                equip.setSystem_name(rs.getString("sys_name"));
                anlscro.setCromatografo(equip);
                lote.setLote_id(rs.getInt("lote_id"));
                lote.setLote_produto(rs.getString("lote_produto"));
                anlscro.setLote(lote);
                prod.setNome_material(rs.getString("material"));
                anlscro.setMaterial(prod);
                anlscro.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
                anlscro.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
                anlscro.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
                user_inicio.setUser(rs.getString("user_inicio"));
                anlscro.setUser_inicio(user_inicio);
                user_fim.setUser(rs.getString("user_fim"));
                anlscro.setUser_fim(user_fim);
                user_registro.setUser(rs.getString("user_registro"));
                anlscro.setUser_registro(user_registro);
                anlscro.setStatus(rs.getString("status"));
                anlscro.setObs(rs.getString("obs"));
                anlscro.setRetrabalho_lote(rs.getBoolean("retrabalho_lote"));
                anlscros.add(anlscro);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return anlscros;
    }

    public List<AnaliseCromatografo> readAnaliseCromatografo(Cromatografo equip_name) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AnaliseCromatografo> anlscros = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(500) tb_log_analise_lote.*, tb_cromatografo.system_name as sys_name, "
                    + "tb_analise.analise, tb_lote.lote_produto, tb_material.material, tb_log_campanha.nome_campanha "
                    + "FROM tb_log_analise_lote "
                    + "INNER JOIN tb_cromatografo "
                    + "ON tb_log_analise_lote.system_name = tb_cromatografo.system_name "
                    + "INNER JOIN tb_analise "
                    + "ON tb_log_analise_lote.analise_id = tb_analise.analise_id "
                    + "INNER JOIN tb_lote "
                    + "ON tb_log_analise_lote.lote_id = tb_lote.lote_id "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "LEFT JOIN tb_log_campanha "
                    + "ON tb_log_analise_lote.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "WHERE tb_log_analise_lote.system_name = ? "
                    + "ORDER BY tb_log_analise_lote.log_lote_id DESC ");
            stmt.setString(1, equip_name.getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                AnaliseCromatografo anlscro = new AnaliseCromatografo();
                Analise anls = new Analise();
                Cromatografo equip = new Cromatografo();
                Lote lote = new Lote();
                Material prod = new Material();
                Usuario user_inicio = new Usuario();
                Usuario user_fim = new Usuario();
                Usuario user_registro = new Usuario();
                Campanha camp = new Campanha();
                camp.setNome_campanha(rs.getString("nome_campanha"));
                anlscro.setCampanha(camp);
                anlscro.setLog_lote_id(rs.getInt("log_lote_id"));
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                anlscro.setAnalise(anls);
                equip.setSystem_name(rs.getString("sys_name"));
                anlscro.setCromatografo(equip);
                lote.setLote_id(rs.getInt("lote_id"));
                lote.setLote_produto(rs.getString("lote_produto"));
                anlscro.setLote(lote);
                prod.setNome_material(rs.getString("material"));
                anlscro.setMaterial(prod);
                anlscro.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
                anlscro.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
                anlscro.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
                user_inicio.setUser(rs.getString("user_inicio"));
                anlscro.setUser_inicio(user_inicio);
                user_fim.setUser(rs.getString("user_fim"));
                anlscro.setUser_fim(user_fim);
                user_registro.setUser(rs.getString("user_registro"));
                anlscro.setUser_registro(user_registro);
                anlscro.setStatus(rs.getString("status"));
                anlscro.setObs(rs.getString("obs"));
                anlscro.setRetrabalho_lote(rs.getBoolean("retrabalho_lote"));
                anlscros.add(anlscro);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return anlscros;
    }

    public List<AnaliseCromatografo> readAnaliseCromatografoAivo(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AnaliseCromatografo> anlscros = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_log_analise_lote.log_lote_id "
                    + "FROM tb_log_analise_lote "
                    + "INNER JOIN tb_cromatografo "
                    + "ON tb_log_analise_lote.system_name = tb_cromatografo.system_name "
                    + "WHERE tb_log_analise_lote.system_name = ? "
                    + "AND tb_log_analise_lote.status = 'Em Análise' "
                    + "ORDER BY log_lote_id ");
            stmt.setString(1, equip.getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                AnaliseCromatografo anlscro = new AnaliseCromatografo();
                anlscro.setLog_lote_id(rs.getInt("log_lote_id"));
                anlscros.add(anlscro);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseCromatografoAivo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return anlscros;
    }
    
    public void updateFimLote(AnaliseCromatografo anlscro) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_analise_lote SET "
                    + "status = ?, data_hora_fim = ?, user_fim = ?,"
                    + "data_hora_registro = ?, user_registro = ? "
                    + "WHERE tb_log_analise_lote.log_lote_id = ?");
            stmt.setString(1, anlscro.getStatus());
            stmt.setTimestamp(2, anlscro.getData_hora_fim());
            stmt.setString(3, anlscro.getUser_fim().getUser());
            stmt.setTimestamp(4, anlscro.getData_hora_registro());
            stmt.setString(5, anlscro.getUser_registro().getUser());
            stmt.setInt(6, anlscro.getLog_lote_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateFimLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void deleteAnaliseCromatografo(AnaliseCromatografo anlscro) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_log_analise_lote "
                    + "WHERE tb_log_analise_lote.log_lote_id = ?");
            stmt.setInt(1, anlscro.getLog_lote_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAnaliseCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
