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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Classes.Modelo.AtividadePreparo;
import Classes.Modelo.Cromatografo;
import Classes.Modelo.Metodologia;
import Classes.Modelo.Usuario;
import Classes.Util.Reports;
import Classes.Util.DataHora;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author rafael.lopes
 */
public class AtividadePreparoDAO {

    public void createAtividade(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_atividade_preparo "
                    + "(metodo_id, analise_id, cromatografo_id, "
                    + "categoria_preparo, tipo_preparo, descricao_preparo, "
                    + "lote_preparo, qtd_preparo, "
                    + "data_hora_inicio, user_inicio, "
                    + "data_hora_fim, user_fim, "
                    + "data_hora_registro, user_registro, "
                    + "obs_preparo, retrabalho_preparo) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, atvprep.getMetodologia().getMetodo_id());
            stmt.setInt(2, atvprep.getAnalise().getAnalise_id());
            stmt.setInt(3, atvprep.getCromatografo().getCromatografo_id());
            stmt.setString(4, atvprep.getCategoria_preparo());
            stmt.setString(5, atvprep.getTipo_preparo());
            stmt.setString(6, atvprep.getDescricao_preparo());
            stmt.setString(7, atvprep.getLote_preparo());
            stmt.setDouble(8, atvprep.getQtd_preparo());
            stmt.setTimestamp(9, atvprep.getData_hora_inicio());
            stmt.setString(10, atvprep.getUser_inicio().getUser());
            stmt.setTimestamp(11, atvprep.getData_hora_fim());
            stmt.setString(12, atvprep.getUser_fim().getUser());
            stmt.setTimestamp(13, atvprep.getData_hora_registro());
            stmt.setString(14, atvprep.getUser_registro().getUser());
            stmt.setString(15, atvprep.getObs_preparo());
            stmt.setInt(16, (atvprep.getRetrabalho_preparo()) ? 1 : 0);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAtividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void createPedido(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_atividade_preparo "
                    + "(metodo_id, analise_id, cromatografo_id, "
                    + "categoria_preparo, tipo_preparo, descricao_preparo, "
                    + "lote_preparo, qtd_preparo, "
                    + "data_hora_registro, user_registro, "
                    + "obs_preparo, retrabalho_preparo) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, atvprep.getMetodologia().getMetodo_id());
            stmt.setInt(2, atvprep.getAnalise().getAnalise_id());
            stmt.setInt(3, atvprep.getCromatografo().getCromatografo_id());
            stmt.setString(4, atvprep.getCategoria_preparo());
            stmt.setString(5, atvprep.getTipo_preparo());
            stmt.setString(6, atvprep.getDescricao_preparo());
            stmt.setString(7, atvprep.getLote_preparo());
            stmt.setDouble(8, atvprep.getQtd_preparo());
            stmt.setTimestamp(9, atvprep.getData_hora_registro());
            stmt.setString(10, atvprep.getUser_registro().getUser());
            stmt.setString(11, atvprep.getObs_preparo());
            stmt.setInt(12, (atvprep.getRetrabalho_preparo()) ? 1 : 0);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createPedido");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<AtividadePreparo> readPreparo() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AtividadePreparo> atividades = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(1000) tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
                    + "tb_metodologia.metodo, tb_analise.analise, tb_cromatografo.system_name "
                    + "FROM tb_atividade_preparo "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
                    + "LEFT JOIN  tb_analise "
                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
                    + "LEFT JOIN  tb_cromatografo "
                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
                    + "WHERE tb_atividade_preparo.data_hora_inicio is NOT NULL "
                    + "AND tb_atividade_preparo.categoria_preparo = 'Análise' "
                    + " ORDER BY tb_atividade_preparo.atividade_preparo_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Analise anls = new Analise();
                AtividadePreparo atvprep = new AtividadePreparo();
                Cromatografo equip = new Cromatografo();
                atvprep.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                atvprep.setMetodologia(mtd);
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                atvprep.setAnalise(anls);
                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
                equip.setSystem_name(rs.getString("system_name"));
                atvprep.setCromatografo(equip);
                atvprep.setCategoria_preparo(rs.getString("categoria_preparo"));
                atvprep.setTipo_preparo(rs.getString("tipo_preparo"));
                atvprep.setDescricao_preparo(rs.getString("descricao_preparo"));
                atvprep.setLote_preparo(rs.getString("lote_preparo"));
                atvprep.setQtd_preparo(rs.getDouble("qtd_preparo"));
                atvprep.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
                Usuario user_inicio = new Usuario();
                user_inicio.setUser(rs.getString("user_inicio"));
                atvprep.setUser_inicio(user_inicio);
                atvprep.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
                Usuario user_fim = new Usuario();
                user_fim.setUser(rs.getString("user_fim"));
                atvprep.setUser_fim(user_fim);
                atvprep.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
                Usuario user_registro = new Usuario();
                user_registro.setUser(rs.getString("user_registro"));
                atvprep.setUser_registro(user_registro);
                atvprep.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
                atvprep.setObs_preparo(rs.getString("obs_preparo"));
                atividades.add(atvprep);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readPreparo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return atividades;
    }

    public List<AtividadePreparo> readAtividade(Integer status, Integer limit) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AtividadePreparo> atividades = new ArrayList<>();
        try {
            String where = "";
            if (status == 1) {
                where = "AND tb_atividade_preparo.data_hora_fim is NULL ";
            } else if (status == 2) {
                where = "AND tb_atividade_preparo.data_hora_fim is NOT NULL ";
            }
            stmt = conn.prepareStatement("SELECT TOP(?) tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
                    + "tb_metodologia.metodo, tb_analise.analise, tb_cromatografo.system_name "
                    + "FROM tb_atividade_preparo "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
                    + "LEFT JOIN  tb_analise "
                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
                    + "LEFT JOIN  tb_cromatografo "
                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
                    + "WHERE tb_atividade_preparo.data_hora_inicio is NOT NULL " + where
                    + " ORDER BY tb_atividade_preparo.atividade_preparo_id DESC ");
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Analise anls = new Analise();
                AtividadePreparo atvprep = new AtividadePreparo();
                Cromatografo equip = new Cromatografo();
                atvprep.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                atvprep.setMetodologia(mtd);
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                atvprep.setAnalise(anls);
                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
                equip.setSystem_name(rs.getString("system_name"));
                atvprep.setCromatografo(equip);
                atvprep.setCategoria_preparo(rs.getString("categoria_preparo"));
                atvprep.setTipo_preparo(rs.getString("tipo_preparo"));
                atvprep.setDescricao_preparo(rs.getString("descricao_preparo"));
                atvprep.setLote_preparo(rs.getString("lote_preparo"));
                atvprep.setQtd_preparo(rs.getDouble("qtd_preparo"));
                atvprep.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
                Usuario user_inicio = new Usuario();
                user_inicio.setUser(rs.getString("user_inicio"));
                atvprep.setUser_inicio(user_inicio);
                atvprep.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
                Usuario user_fim = new Usuario();
                user_fim.setUser(rs.getString("user_fim"));
                atvprep.setUser_fim(user_fim);
                atvprep.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
                Usuario user_registro = new Usuario();
                user_registro.setUser(rs.getString("user_registro"));
                atvprep.setUser_registro(user_registro);
                atvprep.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
                atvprep.setObs_preparo(rs.getString("obs_preparo"));
                atividades.add(atvprep);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAtividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return atividades;
    }

    public List<AtividadePreparo> readPedidos() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AtividadePreparo> atividades = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(5000) tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
                    + "tb_metodologia.metodo, tb_analise.analise, tb_cromatografo.system_name "
                    + "FROM tb_atividade_preparo "
                    + "LEFT JOIN  tb_metodologia "
                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
                    + "LEFT JOIN  tb_analise "
                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
                    + "LEFT JOIN  tb_cromatografo "
                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
                    + "WHERE tb_atividade_preparo.data_hora_inicio is NULL "
                    + "AND tb_atividade_preparo.data_hora_registro > ? "
                    + "ORDER BY tb_atividade_preparo.atividade_preparo_id ASC ");
            Date currentDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.DATE, -2);
            stmt.setTimestamp(1, DataHora.getTimestampDate(cal.getTime()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Analise anls = new Analise();
                AtividadePreparo atvprep = new AtividadePreparo();
                Cromatografo equip = new Cromatografo();
                atvprep.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                atvprep.setMetodologia(mtd);
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                atvprep.setAnalise(anls);
                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
                equip.setSystem_name(rs.getString("system_name"));
                atvprep.setCromatografo(equip);
                atvprep.setCategoria_preparo(rs.getString("categoria_preparo"));
                atvprep.setTipo_preparo(rs.getString("tipo_preparo"));
                atvprep.setDescricao_preparo(rs.getString("descricao_preparo"));
                atvprep.setLote_preparo(rs.getString("lote_preparo"));
                atvprep.setQtd_preparo(rs.getDouble("qtd_preparo"));
                atvprep.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
                Usuario user_inicio = new Usuario();
                user_inicio.setUser(rs.getString("user_inicio"));
                atvprep.setUser_inicio(user_inicio);
                atvprep.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
                Usuario user_fim = new Usuario();
                user_fim.setUser(rs.getString("user_fim"));
                atvprep.setUser_fim(user_fim);
                atvprep.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
                Usuario user_registro = new Usuario();
                user_registro.setUser(rs.getString("user_registro"));
                atvprep.setUser_registro(user_registro);
                atvprep.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
                atvprep.setObs_preparo(rs.getString("obs_preparo"));
                atividades.add(atvprep);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readPedidos");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return atividades;
    }

    public List<AtividadePreparo> readRetrabalho(int status) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AtividadePreparo> atividades = new ArrayList<>();
        try {
            String where = "";
            if (status == 1) {
                where = "AND tb_atividade_preparo.motivo_retrabalho is NULL ";
            } else if (status == 2) {
                where = "AND tb_atividade_preparo.motivo_retrabalho is NOT NULL ";
            }
            stmt = conn.prepareStatement("SELECT TOP(5000) tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
                    + "tb_metodologia.metodo, tb_analise.analise, tb_cromatografo.system_name "
                    + "FROM tb_atividade_preparo "
                    + "LEFT JOIN  tb_metodologia "
                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
                    + "LEFT JOIN  tb_analise "
                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
                    + "LEFT JOIN  tb_cromatografo "
                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
                    + "WHERE tb_atividade_preparo.data_hora_fim is NOT NULL "
                    + "AND tb_atividade_preparo.retrabalho_preparo = 1 " + where
                    + "ORDER BY tb_atividade_preparo.atividade_preparo_id ASC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Analise anls = new Analise();
                AtividadePreparo atvprep = new AtividadePreparo();
                Cromatografo equip = new Cromatografo();
                atvprep.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                atvprep.setMetodologia(mtd);
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                atvprep.setAnalise(anls);
                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
                equip.setSystem_name(rs.getString("system_name"));
                atvprep.setCromatografo(equip);
                atvprep.setCategoria_preparo(rs.getString("categoria_preparo"));
                atvprep.setTipo_preparo(rs.getString("tipo_preparo"));
                atvprep.setDescricao_preparo(rs.getString("descricao_preparo"));
                atvprep.setLote_preparo(rs.getString("lote_preparo"));
                atvprep.setQtd_preparo(rs.getDouble("qtd_preparo"));
                atvprep.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
                Usuario user_inicio = new Usuario();
                user_inicio.setUser(rs.getString("user_inicio"));
                atvprep.setUser_inicio(user_inicio);
                atvprep.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
                Usuario user_fim = new Usuario();
                user_fim.setUser(rs.getString("user_fim"));
                atvprep.setUser_fim(user_fim);
                atvprep.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
                Usuario user_registro = new Usuario();
                user_registro.setUser(rs.getString("user_registro"));
                atvprep.setUser_registro(user_registro);
                atvprep.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
                atvprep.setObs_preparo(rs.getString("obs_preparo"));
                atvprep.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
                atividades.add(atvprep);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readRetrabalho");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return atividades;
    }

    public void updateAtividade(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_atividade_preparo SET "
                    + "data_hora_inicio = ?, user_inicio = ?, "
                    + "data_hora_fim = ?, user_fim = ?, "
                    + "data_hora_registro = ?, user_registro  = ?, "
                    + "cromatografo_id  = ?,  retrabalho_preparo  = ?, "
                    + "obs_preparo = ?, metodo_id = ?, lote_preparo = ?, "
                    + "tipo_preparo = ?, descricao_preparo = ?, qtd_preparo = ? "
                    + "WHERE atividade_preparo_id = ?");
            stmt.setTimestamp(1, atvprep.getData_hora_inicio());
            stmt.setString(2, atvprep.getUser_inicio().getUser());
            stmt.setTimestamp(3, atvprep.getData_hora_fim());
            stmt.setString(4, atvprep.getUser_fim().getUser());
            stmt.setTimestamp(5, atvprep.getData_hora_registro());
            stmt.setString(6, atvprep.getUser_registro().getUser());
            stmt.setInt(7, atvprep.getCromatografo().getCromatografo_id());
            stmt.setInt(8, (atvprep.getRetrabalho_preparo()) ? 1 : 0);
            stmt.setString(9, atvprep.getObs_preparo());
            stmt.setInt(10, atvprep.getMetodologia().getMetodo_id());
            stmt.setString(11, atvprep.getLote_preparo());
            stmt.setString(12, atvprep.getTipo_preparo());
            stmt.setString(13, atvprep.getDescricao_preparo());
            stmt.setDouble(14, atvprep.getQtd_preparo());
            stmt.setInt(15, atvprep.getAtividade_preparo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAtividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateRetrabalho(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_atividade_preparo SET "
                    + "motivo_retrabalho = ?, categoria_retrabalho = ?, "
                    + "tipo_retrabalho = ?, gerador_retrabalho = ?, "
                    + "user_retrabalho = ?, "
                    + "data_hora_registro_retrabalho = ?, user_registro_retrabalho = ? "
                    + "WHERE atividade_preparo_id = ?");
            stmt.setString(1, atvprep.getMotivo_retrabalho());
            stmt.setString(2, atvprep.getCategoria_retrabalho());
            stmt.setString(3, atvprep.getTipo_retrabalho());
            stmt.setString(4, atvprep.getGerador_retrabalho());
            stmt.setString(5, atvprep.getUser_retrabalho().getUser());
            stmt.setTimestamp(6, atvprep.getData_hora_registro_retrabalho());
            stmt.setString(7, atvprep.getUser_registro_retrabalho().getUser());
            stmt.setInt(8, atvprep.getAtividade_preparo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateRetrabalho");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void deleteAtividade(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_atividade_preparo WHERE atividade_preparo_id = ?");
            stmt.setInt(1, atvprep.getAtividade_preparo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAtividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAtividade(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
                    + "tb_analise.analise, tb_cromatografo.system_name "
                    + "FROM tb_atividade_preparo "
                    + "LEFT JOIN  tb_metodologia "
                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
                    + "LEFT JOIN  tb_analise "
                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
                    + "LEFT JOIN  tb_cromatografo "
                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
                    + "WHERE atividade_preparo_id = ?");
            stmt.setInt(1, atvprep.getAtividade_preparo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Analise anls = new Analise();
                Cromatografo equip = new Cromatografo();
                atvprep.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getInt("metodo_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("cod_metodo"));
                atvprep.setMetodologia(mtd);
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                atvprep.setAnalise(anls);
                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
                equip.setSystem_name(rs.getString("system_name"));
                atvprep.setCromatografo(equip);
                atvprep.setCategoria_preparo(rs.getString("categoria_preparo"));
                atvprep.setTipo_preparo(rs.getString("tipo_preparo"));
                atvprep.setDescricao_preparo(rs.getString("descricao_preparo"));
                atvprep.setLote_preparo(rs.getString("lote_preparo"));
                atvprep.setQtd_preparo(rs.getDouble("qtd_preparo"));
                atvprep.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
                Usuario user_inicio = new Usuario();
                user_inicio.setUser(rs.getString("user_inicio"));
                atvprep.setUser_inicio(user_inicio);
                atvprep.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
                Usuario user_fim = new Usuario();
                user_fim.setUser(rs.getString("user_fim"));
                atvprep.setUser_fim(user_fim);
                atvprep.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
                Usuario user_registro = new Usuario();
                user_registro.setUser(rs.getString("user_registro"));
                atvprep.setUser_registro(user_registro);
                atvprep.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
                atvprep.setObs_preparo(rs.getString("obs_preparo"));
                atvprep.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAtividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public static void printPedidosAnalise(String user) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        String src = Reports.getSrc() + "PedidosAnalise.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printPedidostAnalise");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printPedidosSolucoesReagente(String user) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        String src = Reports.getSrc() + "PedidosSolucoesReagente.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printPedidosSolucoesReagente");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printProdutividade(String user, Timestamp data_inicio, Timestamp data_fim) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        String src = Reports.getSrc() + "ProdutividadePessoa.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printProdutividade");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printProdutividadeDetalhada(String user, Timestamp data_inicio, Timestamp data_fim) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        String src = Reports.getSrc() + "ProdutividadeGeralDetalhada.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printProdutividadeDetalhada");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printProdutividadeColaborador(String user, Timestamp data_inicio, Timestamp data_fim) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        String src = Reports.getSrc() + "ProdutividadeGeralColaborador.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printProdutividadeColaborador");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printTratativaRetrabalho(String user, Timestamp data_inicio, Timestamp data_fim) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        String src = Reports.getSrc() + "RetrabalhoMotivo.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printTratativaRetrabalho");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printRetrabalhoProduto(String user, Timestamp data_inicio, Timestamp data_fim) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        String src = Reports.getSrc() + "RetrabalhoProduto.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printRetrabalhoProduto");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printRetrabalhoDetalhado(String user, Timestamp data_inicio, Timestamp data_fim) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        String src = Reports.getSrc() + "RetrabalhoDetalhado.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printRetrabalhoDetalhado");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printProdutividadeGeral(String user, Timestamp data_inicio, Timestamp data_fim) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        String src = Reports.getSrc() + "ProdutividadeGeral.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printProdutividadeGeral");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printRetrabalho(String user, Timestamp data_inicio, Timestamp data_fim) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        String src = Reports.getSrc() + "Retrabalhos.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printRetrabalho");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printPedidos(String user) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        String src = Reports.getSrc() + "PedidosPreparo.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printPedidos");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public void createMotivoRetrabalho(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_motivo_retrabalho "
                    + "(tipo_motivo_retrabalho, motivo_retrabalho, descricao_motivo_retrabalho) "
                    + "VALUES (?,?,?)");
            stmt.setString(1, atvprep.getTipo_motivo_retrabalho());
            stmt.setString(2, atvprep.getMotivo_retrabalho());
            stmt.setString(3, atvprep.getDescricao_motivo_retrabalho());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createMotivoRetrabalho");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<AtividadePreparo> readMotivoRetrabalho() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AtividadePreparo> atividades = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_motivo_retrabalho ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                AtividadePreparo atvprep = new AtividadePreparo();
                atvprep.setMotivo_retrabalho_id(rs.getInt("motivo_retrabalho_id"));
                atvprep.setTipo_motivo_retrabalho(rs.getString("tipo_motivo_retrabalho"));
                atvprep.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
                atvprep.setDescricao_motivo_retrabalho(rs.getString("descricao_motivo_retrabalho"));
                atividades.add(atvprep);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readMotivoRetrabalho");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return atividades;
    }

    public List<AtividadePreparo> readMotivoRetrabalho(String tipo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AtividadePreparo> atividades = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_motivo_retrabalho "
                    + "WHERE tipo_motivo_retrabalho = ? "
                    + "ORDER BY motivo_retrabalho ASC");
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                AtividadePreparo atvprep = new AtividadePreparo();
                atvprep.setMotivo_retrabalho_id(rs.getInt("motivo_retrabalho_id"));
                atvprep.setTipo_motivo_retrabalho(rs.getString("tipo_motivo_retrabalho"));
                atvprep.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
                atvprep.setDescricao_motivo_retrabalho(rs.getString("descricao_motivo_retrabalho"));
                atividades.add(atvprep);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readMotivoRetrabalho");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return atividades;
    }

    public void selectMotivoRetrabalho(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * FROM tb_motivo_retrabalho "
                    + "WHERE motivo_retrabalho_id = ? ");
            stmt.setInt(1, atvprep.getMotivo_retrabalho_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                atvprep.setMotivo_retrabalho_id(rs.getInt("motivo_retrabalho_id"));
                atvprep.setTipo_motivo_retrabalho(rs.getString("tipo_motivo_retrabalho"));
                atvprep.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
                atvprep.setDescricao_motivo_retrabalho(rs.getString("descricao_motivo_retrabalho"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectMotivoRetrabalho");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectRetrabalho(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
                    + "tb_analise.analise, tb_cromatografo.system_name "
                    + "FROM tb_atividade_preparo "
                    + "LEFT JOIN  tb_metodologia "
                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
                    + "LEFT JOIN  tb_analise "
                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
                    + "LEFT JOIN  tb_cromatografo "
                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
                    + "WHERE atividade_preparo_id = ?");
            stmt.setInt(1, atvprep.getAtividade_preparo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Analise anls = new Analise();
                Cromatografo equip = new Cromatografo();
                atvprep.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getInt("metodo_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("cod_metodo"));
                atvprep.setMetodologia(mtd);
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                atvprep.setAnalise(anls);
                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
                equip.setSystem_name(rs.getString("system_name"));
                atvprep.setCromatografo(equip);
                atvprep.setCategoria_preparo(rs.getString("categoria_preparo"));
                atvprep.setTipo_preparo(rs.getString("tipo_preparo"));
                atvprep.setDescricao_preparo(rs.getString("descricao_preparo"));
                atvprep.setLote_preparo(rs.getString("lote_preparo"));
                atvprep.setQtd_preparo(rs.getDouble("qtd_preparo"));
                atvprep.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
                Usuario user_inicio = new Usuario();
                user_inicio.setUser(rs.getString("user_inicio"));
                atvprep.setUser_inicio(user_inicio);
                atvprep.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
                Usuario user_fim = new Usuario();
                user_fim.setUser(rs.getString("user_fim"));
                atvprep.setUser_fim(user_fim);
                atvprep.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
                Usuario user_registro = new Usuario();
                user_registro.setUser(rs.getString("user_registro"));
                atvprep.setUser_registro(user_registro);
                atvprep.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
                atvprep.setObs_preparo(rs.getString("obs_preparo"));
                atvprep.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
                atvprep.setCategoria_retrabalho(rs.getString("categoria_retrabalho"));
                atvprep.setTipo_retrabalho(rs.getString("tipo_retrabalho"));
                atvprep.setGerador_retrabalho(rs.getString("gerador_retrabalho"));
                Usuario user_retrabalho = new Usuario();
                user_retrabalho.setUser(rs.getString("user_retrabalho"));
                atvprep.setUser_retrabalho(user_retrabalho);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectRetrabalho");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void updateMotivoRetrabalho(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_motivo_retrabalho SET "
                    + "tipo_motivo_retrabalho = ?, motivo_retrabalho = ?, "
                    + "descricao_motivo_retrabalho = ? "
                    + "WHERE motivo_retrabalho_id = ?");
            stmt.setString(1, atvprep.getTipo_motivo_retrabalho());
            stmt.setString(2, atvprep.getMotivo_retrabalho());
            stmt.setString(3, atvprep.getDescricao_motivo_retrabalho());
            stmt.setInt(4, atvprep.getMotivo_retrabalho_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateMotivoRetrabalho");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void deleteMotivoRetrabalho(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_motivo_retrabalho WHERE motivo_retrabalho_id = ?");
            stmt.setInt(1, atvprep.getMotivo_retrabalho_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteMotivoRetrabalho");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateCancelarRetrabalho(AtividadePreparo atvprep) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_atividade_preparo SET "
                    + "retrabalho_preparo  = 0 "
                    + "WHERE atividade_preparo_id = ?");
            stmt.setInt(1, atvprep.getAtividade_preparo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateCancelarRetrabalho");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public Boolean selectUltimoRegistroSolucaoReagente(String equipamento) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean isExist = false;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_atividade_preparo.atividade_preparo_id "
                    + "FROM tb_atividade_preparo "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
                    + "LEFT JOIN tb_analise "
                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
                    + "LEFT JOIN  tb_cromatografo "
                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
                    + "WHERE tb_cromatografo.system_name = ? "
                    + "AND tb_atividade_preparo.data_hora_registro > CURRENT_TIMESTAMP - 0.08 "
                    + "AND tb_atividade_preparo.categoria_preparo = 'Soluções Reagentes' "
                    + "ORDER BY tb_atividade_preparo.atividade_preparo_id DESC ");
            stmt.setString(1, equipamento);
            rs = stmt.executeQuery();
            while (rs.next()) {
                isExist = true;
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectUltimoRegistroSolucaoReagente");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return isExist;
    }

}
