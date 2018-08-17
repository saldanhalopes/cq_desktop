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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Classes.Modelo.AtividadeEquipamento;

/**
 *
 * @author rafael.lopes
 */
public class AtividadeEquipamentoDAO {

    public void createAtividade(AtividadeEquipamento atvequip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_atividade_equipamento "
                    + "(lote_id, metodo_id, cromatografo_id, analise_id, "
                    + "atividade_preparo, tipo_preparo, descricao_preparo, "
                    + "lote_preparo, qtd_preparo, "
                    + "data_hora_inicio, user_inicio, "
                    + "data_hora_fim, user_fim, "
                    + "data_hora_registro, user_registro, "
                    + "obs_preparo, retrabalho_preparo) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(7, atvequip.getLote().getLote_id());
            stmt.setInt(1, atvequip.getMetodologia().getMetodo_id());
            stmt.setInt(3, atvequip.getCromatografo().getCromatografo_id());
            stmt.setInt(2, atvequip.getAnalise().getAnalise_id());
            stmt.setString(4, atvequip.getAtividade_equipamento());
            stmt.setString(6, atvequip.getDescricao_atividade());
            stmt.setInt(16, (atvequip.getRetrabalho_atividade()) ? 1 : 0);
            stmt.setTimestamp(9, atvequip.getData_hora_inicio());
            stmt.setString(10, atvequip.getUser_inicio().getUser());
            stmt.setTimestamp(11, atvequip.getData_hora_fim());
            stmt.setString(12, atvequip.getUser_fim().getUser());
            stmt.setTimestamp(13, atvequip.getData_hora_registro());
            stmt.setString(14, atvequip.getUser_registro().getUser());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAtividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
//
//    public void createPedido(AtividadeEquipamento atvequip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("INSERT INTO tb_atividade_preparo "
//                    + "(metodo_id, analise_id, cromatografo_id, "
//                    + "categoria_preparo, tipo_preparo, descricao_preparo, "
//                    + "lote_preparo, qtd_preparo, "
//                    + "data_hora_registro, user_registro, "
//                    + "obs_preparo, retrabalho_preparo) "
//                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
//            stmt.setInt(1, atvequip.getMetodologia().getMetodo_id());
//            stmt.setInt(2, atvequip.getAnalise().getAnalise_id());
//            stmt.setInt(3, atvequip.getCromatografo().getCromatografo_id());
//            stmt.setString(4, atvequip.getCategoria_preparo());
//            stmt.setString(5, atvequip.getTipo_preparo());
//            stmt.setString(6, atvequip.getDescricao_preparo());
//            stmt.setString(7, atvequip.getLote_preparo());
//            stmt.setDouble(8, atvequip.getQtd_preparo());
//            stmt.setTimestamp(9, atvequip.getData_hora_registro());
//            stmt.setString(10, atvequip.getUser_registro().getUser());
//            stmt.setString(11, atvequip.getObs_preparo());
//            stmt.setInt(12, (atvequip.isRetrabalho_preparo()) ? 1 : 0);
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//    
//    public List<AtividadeEquipamento> readPreparo() {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<AtividadeEquipamento> atividades = new ArrayList<>();
//        try {
//            stmt = conn.prepareStatement("SELECT tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
//                    + "tb_metodologia.metodo, tb_analise.analise, tb_cromatografo.system_name "
//                    + "FROM tb_atividade_preparo "
//                    + "LEFT JOIN tb_metodologia "
//                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
//                    + "LEFT JOIN  tb_analise "
//                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
//                    + "LEFT JOIN  tb_cromatografo "
//                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
//                    + "WHERE tb_atividade_preparo.data_hora_inicio is NOT NULL "
//                    + "AND tb_atividade_preparo.categoria_preparo = 'Análise' "
//                    + " ORDER BY tb_atividade_preparo.atividade_preparo_id DESC LIMIT 1000 ");
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Metodologia mtd = new Metodologia();
//                Analise anls = new Analise();
//                AtividadeEquipamento atvequip = new AtividadeEquipamento();
//                Cromatografo equip = new Cromatografo();
//                atvequip.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
//                mtd.setMetodo_id(rs.getInt("metodo_id"));
//                mtd.setCod_metodo(rs.getString("cod_metodo"));
//                mtd.setMetodo(rs.getString("metodo"));
//                atvequip.setMetodologia(mtd);
//                anls.setAnalise_id(rs.getInt("analise_id"));
//                anls.setAnalise(rs.getString("analise"));
//                atvequip.setAnalise(anls);
//                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
//                equip.setSystem_name(rs.getString("system_name"));
//                atvequip.setCromatografo(equip);
//                atvequip.setCategoria_preparo(rs.getString("categoria_preparo"));
//                atvequip.setTipo_preparo(rs.getString("tipo_preparo"));
//                atvequip.setDescricao_preparo(rs.getString("descricao_preparo"));
//                atvequip.setLote_preparo(rs.getString("lote_preparo"));
//                atvequip.setQtd_preparo(rs.getDouble("qtd_preparo"));
//                atvequip.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
//                Usuario user_inicio = new Usuario();
//                user_inicio.setUser(rs.getString("user_inicio"));
//                atvequip.setUser_inicio(user_inicio);
//                atvequip.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
//                Usuario user_fim = new Usuario();
//                user_fim.setUser(rs.getString("user_fim"));
//                atvequip.setUser_fim(user_fim);
//                atvequip.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
//                Usuario user_registro = new Usuario();
//                user_registro.setUser(rs.getString("user_registro"));
//                atvequip.setUser_registro(user_registro);
//                atvequip.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
//                atvequip.setObs_preparo(rs.getString("obs_preparo"));
//                atividades.add(atvequip);
//            }
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return atividades;
//    }
//
//    public List<AtividadeEquipamento> readAtividade(int status, String limit) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<AtividadeEquipamento> atividades = new ArrayList<>();
//        try {
//            String where = "";
//            if (status == 1) {
//                where = "AND tb_atividade_preparo.data_hora_fim is NULL ";
//            } else if (status == 2) {
//                where = "AND tb_atividade_preparo.data_hora_fim is NOT NULL ";
//            }
//            stmt = conn.prepareStatement("SELECT tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
//                    + "tb_metodologia.metodo, tb_analise.analise, tb_cromatografo.system_name "
//                    + "FROM tb_atividade_preparo "
//                    + "LEFT JOIN tb_metodologia "
//                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
//                    + "LEFT JOIN  tb_analise "
//                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
//                    + "LEFT JOIN  tb_cromatografo "
//                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
//                    + "WHERE tb_atividade_preparo.data_hora_inicio is NOT NULL " + where
//                    + " ORDER BY tb_atividade_preparo.atividade_preparo_id DESC LIMIT ? ");
//            stmt.setString(1, limit);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Metodologia mtd = new Metodologia();
//                Analise anls = new Analise();
//                AtividadeEquipamento atvequip = new AtividadeEquipamento();
//                Cromatografo equip = new Cromatografo();
//                atvequip.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
//                mtd.setMetodo_id(rs.getInt("metodo_id"));
//                mtd.setCod_metodo(rs.getString("cod_metodo"));
//                mtd.setMetodo(rs.getString("metodo"));
//                atvequip.setMetodologia(mtd);
//                anls.setAnalise_id(rs.getInt("analise_id"));
//                anls.setAnalise(rs.getString("analise"));
//                atvequip.setAnalise(anls);
//                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
//                equip.setSystem_name(rs.getString("system_name"));
//                atvequip.setCromatografo(equip);
//                atvequip.setCategoria_preparo(rs.getString("categoria_preparo"));
//                atvequip.setTipo_preparo(rs.getString("tipo_preparo"));
//                atvequip.setDescricao_preparo(rs.getString("descricao_preparo"));
//                atvequip.setLote_preparo(rs.getString("lote_preparo"));
//                atvequip.setQtd_preparo(rs.getDouble("qtd_preparo"));
//                atvequip.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
//                Usuario user_inicio = new Usuario();
//                user_inicio.setUser(rs.getString("user_inicio"));
//                atvequip.setUser_inicio(user_inicio);
//                atvequip.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
//                Usuario user_fim = new Usuario();
//                user_fim.setUser(rs.getString("user_fim"));
//                atvequip.setUser_fim(user_fim);
//                atvequip.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
//                Usuario user_registro = new Usuario();
//                user_registro.setUser(rs.getString("user_registro"));
//                atvequip.setUser_registro(user_registro);
//                atvequip.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
//                atvequip.setObs_preparo(rs.getString("obs_preparo"));
//                atividades.add(atvequip);
//            }
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return atividades;
//    }
//
//    public List<AtividadeEquipamento> readPedidos() {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<AtividadeEquipamento> atividades = new ArrayList<>();
//        try {
//            stmt = conn.prepareStatement("SELECT tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
//                    + "tb_metodologia.metodo, tb_analise.analise, tb_cromatografo.system_name "
//                    + "FROM tb_atividade_preparo "
//                    + "LEFT JOIN  tb_metodologia "
//                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
//                    + "LEFT JOIN  tb_analise "
//                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
//                    + "LEFT JOIN  tb_cromatografo "
//                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
//                    + "WHERE tb_atividade_preparo.data_hora_inicio is NULL "
//                    + "AND tb_atividade_preparo.data_hora_registro > ? "
//                    + "ORDER BY tb_atividade_preparo.atividade_preparo_id ASC LIMIT 5000 ");
//            Date currentDate = new Date();
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(currentDate);
//            cal.add(Calendar.DATE, -2);
//            stmt.setTimestamp(1, DataHora.getTimestampDate(cal.getTime()));
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Metodologia mtd = new Metodologia();
//                Analise anls = new Analise();
//                AtividadeEquipamento atvequip = new AtividadeEquipamento();
//                Cromatografo equip = new Cromatografo();
//                atvequip.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
//                mtd.setMetodo_id(rs.getInt("metodo_id"));
//                mtd.setCod_metodo(rs.getString("cod_metodo"));
//                mtd.setMetodo(rs.getString("metodo"));
//                atvequip.setMetodologia(mtd);
//                anls.setAnalise_id(rs.getInt("analise_id"));
//                anls.setAnalise(rs.getString("analise"));
//                atvequip.setAnalise(anls);
//                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
//                equip.setSystem_name(rs.getString("system_name"));
//                atvequip.setCromatografo(equip);
//                atvequip.setCategoria_preparo(rs.getString("categoria_preparo"));
//                atvequip.setTipo_preparo(rs.getString("tipo_preparo"));
//                atvequip.setDescricao_preparo(rs.getString("descricao_preparo"));
//                atvequip.setLote_preparo(rs.getString("lote_preparo"));
//                atvequip.setQtd_preparo(rs.getDouble("qtd_preparo"));
//                atvequip.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
//                Usuario user_inicio = new Usuario();
//                user_inicio.setUser(rs.getString("user_inicio"));
//                atvequip.setUser_inicio(user_inicio);
//                atvequip.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
//                Usuario user_fim = new Usuario();
//                user_fim.setUser(rs.getString("user_fim"));
//                atvequip.setUser_fim(user_fim);
//                atvequip.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
//                Usuario user_registro = new Usuario();
//                user_registro.setUser(rs.getString("user_registro"));
//                atvequip.setUser_registro(user_registro);
//                atvequip.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
//                atvequip.setObs_preparo(rs.getString("obs_preparo"));
//                atividades.add(atvequip);
//            }
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return atividades;
//    }
//
//    public List<AtividadeEquipamento> readRetrabalho(int status) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<AtividadeEquipamento> atividades = new ArrayList<>();
//        try {
//            String where = "";
//            if (status == 1) {
//                where = "AND tb_atividade_preparo.motivo_retrabalho is NULL ";
//            } else if (status == 2) {
//                where = "AND tb_atividade_preparo.motivo_retrabalho is NOT NULL ";
//            }
//            stmt = conn.prepareStatement("SELECT tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
//                    + "tb_metodologia.metodo, tb_analise.analise, tb_cromatografo.system_name "
//                    + "FROM tb_atividade_preparo "
//                    + "LEFT JOIN  tb_metodologia "
//                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
//                    + "LEFT JOIN  tb_analise "
//                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
//                    + "LEFT JOIN  tb_cromatografo "
//                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
//                    + "WHERE tb_atividade_preparo.data_hora_fim is NOT NULL "
//                    + "AND tb_atividade_preparo.retrabalho_preparo = 1 " + where
//                    + "ORDER BY tb_atividade_preparo.atividade_preparo_id ASC LIMIT 5000 ");
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Metodologia mtd = new Metodologia();
//                Analise anls = new Analise();
//                AtividadeEquipamento atvequip = new AtividadeEquipamento();
//                Cromatografo equip = new Cromatografo();
//                atvequip.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
//                mtd.setMetodo_id(rs.getInt("metodo_id"));
//                mtd.setCod_metodo(rs.getString("cod_metodo"));
//                mtd.setMetodo(rs.getString("metodo"));
//                atvequip.setMetodologia(mtd);
//                anls.setAnalise_id(rs.getInt("analise_id"));
//                anls.setAnalise(rs.getString("analise"));
//                atvequip.setAnalise(anls);
//                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
//                equip.setSystem_name(rs.getString("system_name"));
//                atvequip.setCromatografo(equip);
//                atvequip.setCategoria_preparo(rs.getString("categoria_preparo"));
//                atvequip.setTipo_preparo(rs.getString("tipo_preparo"));
//                atvequip.setDescricao_preparo(rs.getString("descricao_preparo"));
//                atvequip.setLote_preparo(rs.getString("lote_preparo"));
//                atvequip.setQtd_preparo(rs.getDouble("qtd_preparo"));
//                atvequip.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
//                Usuario user_inicio = new Usuario();
//                user_inicio.setUser(rs.getString("user_inicio"));
//                atvequip.setUser_inicio(user_inicio);
//                atvequip.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
//                Usuario user_fim = new Usuario();
//                user_fim.setUser(rs.getString("user_fim"));
//                atvequip.setUser_fim(user_fim);
//                atvequip.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
//                Usuario user_registro = new Usuario();
//                user_registro.setUser(rs.getString("user_registro"));
//                atvequip.setUser_registro(user_registro);
//                atvequip.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
//                atvequip.setObs_preparo(rs.getString("obs_preparo"));
//                atvequip.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
//                atividades.add(atvequip);
//            }
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return atividades;
//    }
//
//    public void updateAtividade(AtividadeEquipamento atvequip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("UPDATE tb_atividade_preparo SET "
//                    + "data_hora_inicio = ?, user_inicio = ?, "
//                    + "data_hora_fim = ?, user_fim = ?, "
//                    + "data_hora_registro = ?, user_registro  = ?, "
//                    + "cromatografo_id  = ?,  retrabalho_preparo  = ?, "
//                    + "obs_preparo = ?, metodo_id = ?, lote_preparo = ?, "
//                    + "tipo_preparo = ?, descricao_preparo = ?, qtd_preparo = ? "
//                    + "WHERE atividade_preparo_id = ?");
//            stmt.setTimestamp(1, atvequip.getData_hora_inicio());
//            stmt.setString(2, atvequip.getUser_inicio().getUser());
//            stmt.setTimestamp(3, atvequip.getData_hora_fim());
//            stmt.setString(4, atvequip.getUser_fim().getUser());
//            stmt.setTimestamp(5, atvequip.getData_hora_registro());
//            stmt.setString(6, atvequip.getUser_registro().getUser());
//            stmt.setInt(7, atvequip.getCromatografo().getCromatografo_id());
//            stmt.setInt(8, (atvequip.isRetrabalho_preparo()) ? 1 : 0);
//            stmt.setString(9, atvequip.getObs_preparo());
//            stmt.setInt(10, atvequip.getMetodologia().getMetodo_id());
//            stmt.setString(11, atvequip.getLote_preparo());
//            stmt.setString(12, atvequip.getTipo_preparo());
//            stmt.setString(13, atvequip.getDescricao_preparo());
//            stmt.setDouble(14, atvequip.getQtd_preparo());
//            stmt.setInt(15, atvequip.getAtividade_preparo_id());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public void updateRetrabalho(AtividadeEquipamento atvequip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("UPDATE tb_atividade_preparo SET "
//                    + "motivo_retrabalho = ?, categoria_retrabalho = ?, "
//                    + "tipo_retrabalho = ?, gerador_retrabalho = ?, "
//                    + "user_retrabalho = ?, "
//                    + "data_hora_registro_retrabalho = ?, user_registro_retrabalho = ? "
//                    + "WHERE atividade_preparo_id = ?");
//            stmt.setString(1, atvequip.getMotivo_retrabalho());
//            stmt.setString(2, atvequip.getCategoria_retrabalho());
//            stmt.setString(3, atvequip.getTipo_retrabalho());
//            stmt.setString(4, atvequip.getGerador_retrabalho());
//            stmt.setString(5, atvequip.getUser_retrabalho().getUser());
//            stmt.setTimestamp(6, atvequip.getData_hora_registro_retrabalho());
//            stmt.setString(7, atvequip.getUser_registro_retrabalho().getUser());
//            stmt.setInt(8, atvequip.getAtividade_preparo_id());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public void deleteAtividade(AtividadeEquipamento atvequip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("DELETE FROM tb_atividade_preparo WHERE atividade_preparo_id = ?");
//            stmt.setInt(1, atvequip.getAtividade_preparo_id());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public void selectAtividade(AtividadeEquipamento atvequip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            stmt = conn.prepareStatement("SELECT tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
//                    + "tb_analise.analise, tb_cromatografo.system_name "
//                    + "FROM tb_atividade_preparo "
//                    + "LEFT JOIN  tb_metodologia "
//                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
//                    + "LEFT JOIN  tb_analise "
//                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
//                    + "LEFT JOIN  tb_cromatografo "
//                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
//                    + "WHERE atividade_preparo_id = ?");
//            stmt.setInt(1, atvequip.getAtividade_preparo_id());
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Metodologia mtd = new Metodologia();
//                Analise anls = new Analise();
//                Cromatografo equip = new Cromatografo();
//                atvequip.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
//                mtd.setMetodo_id(rs.getInt("metodo_id"));
//                mtd.setCod_metodo(rs.getInt("metodo_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("cod_metodo"));
//                atvequip.setMetodologia(mtd);
//                anls.setAnalise_id(rs.getInt("analise_id"));
//                anls.setAnalise(rs.getString("analise"));
//                atvequip.setAnalise(anls);
//                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
//                equip.setSystem_name(rs.getString("system_name"));
//                atvequip.setCromatografo(equip);
//                atvequip.setCategoria_preparo(rs.getString("categoria_preparo"));
//                atvequip.setTipo_preparo(rs.getString("tipo_preparo"));
//                atvequip.setDescricao_preparo(rs.getString("descricao_preparo"));
//                atvequip.setLote_preparo(rs.getString("lote_preparo"));
//                atvequip.setQtd_preparo(rs.getDouble("qtd_preparo"));
//                atvequip.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
//                Usuario user_inicio = new Usuario();
//                user_inicio.setUser(rs.getString("user_inicio"));
//                atvequip.setUser_inicio(user_inicio);
//                atvequip.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
//                Usuario user_fim = new Usuario();
//                user_fim.setUser(rs.getString("user_fim"));
//                atvequip.setUser_fim(user_fim);
//                atvequip.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
//                Usuario user_registro = new Usuario();
//                user_registro.setUser(rs.getString("user_registro"));
//                atvequip.setUser_registro(user_registro);
//                atvequip.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
//                atvequip.setObs_preparo(rs.getString("obs_preparo"));
//                atvequip.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
//            }
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//    }
//
//    public static void printPedidostAnalise(String user) {
//        Connection conn = ConnectionFactory.getConnection();
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        String src = Reports.getSrc() + "PedidosAnalise.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
//
//    public static void printProdutividade(String user, Timestamp data_inicio, Timestamp data_fim) {
//        Connection conn = ConnectionFactory.getConnection();
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        map.put("data_inicio", data_inicio);
//        map.put("data_fim", data_fim);
//        String src = Reports.getSrc() + "ProdutividadePessoa.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
//
//    public static void printProdutividadeDetalhada(String user, Timestamp data_inicio, Timestamp data_fim) {
//        Connection conn = ConnectionFactory.getConnection();
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        map.put("data_inicio", data_inicio);
//        map.put("data_fim", data_fim);
//        String src = Reports.getSrc() + "ProdutividadeGeralDetalhada.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
//
//    public static void printProdutividadeColaborador(String user, Timestamp data_inicio, Timestamp data_fim) {
//        Connection conn = ConnectionFactory.getConnection();
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        map.put("data_inicio", data_inicio);
//        map.put("data_fim", data_fim);
//        String src = Reports.getSrc() + "ProdutividadeGeralColaborador.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
//
//    public static void printTratativaRetrabalho(String user, Timestamp data_inicio, Timestamp data_fim) {
//        Connection conn = ConnectionFactory.getConnection();
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        map.put("data_inicio", data_inicio);
//        map.put("data_fim", data_fim);
//        String src = Reports.getSrc() + "RetrabalhoMotivo.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
//
//    public static void printRetrabalhoProduto(String user, Timestamp data_inicio, Timestamp data_fim) {
//        Connection conn = ConnectionFactory.getConnection();
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        map.put("data_inicio", data_inicio);
//        map.put("data_fim", data_fim);
//        String src = Reports.getSrc() + "RetrabalhoProduto.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
//
//    public static void printRetrabalhoDetalhado(String user, Timestamp data_inicio, Timestamp data_fim) {
//        Connection conn = ConnectionFactory.getConnection();
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        map.put("data_inicio", data_inicio);
//        map.put("data_fim", data_fim);
//        String src = Reports.getSrc() + "RetrabalhoDetalhado.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
//
//    public static void printProdutividadeGeral(String user, Timestamp data_inicio, Timestamp data_fim) {
//        Connection conn = ConnectionFactory.getConnection();
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        map.put("data_inicio", data_inicio);
//        map.put("data_fim", data_fim);
//        String src = Reports.getSrc() + "ProdutividadeGeral.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
//
//    public static void printRetrabalho(String user, Timestamp data_inicio, Timestamp data_fim) {
//        Connection conn = ConnectionFactory.getConnection();
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        map.put("data_inicio", data_inicio);
//        map.put("data_fim", data_fim);
//        String src = Reports.getSrc() + "Retrabalhos.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
//
//    public static void printPedidos(String user) {
//        Connection conn = ConnectionFactory.getConnection();
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        String src = Reports.getSrc() + "PedidosPreparo.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
//
//    public void createMotivoRetrabalho(AtividadeEquipamento atvequip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("INSERT INTO tb_motivo_retrabalho "
//                    + "(tipo_motivo_retrabalho, motivo_retrabalho, descricao_motivo_retrabalho) "
//                    + "VALUES (?,?,?)");
//            stmt.setString(1, atvequip.getTipo_motivo_retrabalho());
//            stmt.setString(2, atvequip.getMotivo_retrabalho());
//            stmt.setString(3, atvequip.getDescricao_motivo_retrabalho());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public List<AtividadeEquipamento> readMotivoRetrabalho() {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<AtividadeEquipamento> atividades = new ArrayList<>();
//        try {
//            stmt = conn.prepareStatement("Select * FROM tb_motivo_retrabalho ");
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                AtividadeEquipamento atvequip = new AtividadeEquipamento();
//                atvequip.setMotivo_retrabalho_id(rs.getInt("motivo_retrabalho_id"));
//                atvequip.setTipo_motivo_retrabalho(rs.getString("tipo_motivo_retrabalho"));
//                atvequip.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
//                atvequip.setDescricao_motivo_retrabalho(rs.getString("descricao_motivo_retrabalho"));
//                atividades.add(atvequip);
//            }
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return atividades;
//    }
//
//    public List<AtividadeEquipamento> readMotivoRetrabalho(String tipo) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<AtividadeEquipamento> atividades = new ArrayList<>();
//        try {
//            stmt = conn.prepareStatement("Select * FROM tb_motivo_retrabalho "
//                    + "WHERE tipo_motivo_retrabalho = ? "
//                    + "ORDER BY motivo_retrabalho ASC");
//            stmt.setString(1, tipo);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                AtividadeEquipamento atvequip = new AtividadeEquipamento();
//                atvequip.setMotivo_retrabalho_id(rs.getInt("motivo_retrabalho_id"));
//                atvequip.setTipo_motivo_retrabalho(rs.getString("tipo_motivo_retrabalho"));
//                atvequip.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
//                atvequip.setDescricao_motivo_retrabalho(rs.getString("descricao_motivo_retrabalho"));
//                atividades.add(atvequip);
//            }
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return atividades;
//    }
//
//    public void selectMotivoRetrabalho(AtividadeEquipamento atvequip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            stmt = conn.prepareStatement("Select * FROM tb_motivo_retrabalho "
//                    + "WHERE motivo_retrabalho_id = ? ");
//            stmt.setInt(1, atvequip.getMotivo_retrabalho_id());
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                atvequip.setMotivo_retrabalho_id(rs.getInt("motivo_retrabalho_id"));
//                atvequip.setTipo_motivo_retrabalho(rs.getString("tipo_motivo_retrabalho"));
//                atvequip.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
//                atvequip.setDescricao_motivo_retrabalho(rs.getString("descricao_motivo_retrabalho"));
//            }
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//    }
//
//    public void selectRetrabalho(AtividadeEquipamento atvequip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            stmt = conn.prepareStatement("SELECT tb_atividade_preparo.*, tb_metodologia.cod_metodo, "
//                    + "tb_analise.analise, tb_cromatografo.system_name "
//                    + "FROM tb_atividade_preparo "
//                    + "LEFT JOIN  tb_metodologia "
//                    + "ON tb_atividade_preparo.metodo_id =  tb_metodologia.metodo_id "
//                    + "LEFT JOIN  tb_analise "
//                    + "ON tb_atividade_preparo.analise_id =  tb_analise.analise_id "
//                    + "LEFT JOIN  tb_cromatografo "
//                    + "ON tb_atividade_preparo.cromatografo_id =  tb_cromatografo.cromatografo_id "
//                    + "WHERE atividade_preparo_id = ?");
//            stmt.setInt(1, atvequip.getAtividade_preparo_id());
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Metodologia mtd = new Metodologia();
//                Analise anls = new Analise();
//                Cromatografo equip = new Cromatografo();
//                atvequip.setAtividade_preparo_id(rs.getInt("atividade_preparo_id"));
//                mtd.setMetodo_id(rs.getInt("metodo_id"));
//                mtd.setCod_metodo(rs.getInt("metodo_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("cod_metodo"));
//                atvequip.setMetodologia(mtd);
//                anls.setAnalise_id(rs.getInt("analise_id"));
//                anls.setAnalise(rs.getString("analise"));
//                atvequip.setAnalise(anls);
//                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
//                equip.setSystem_name(rs.getString("system_name"));
//                atvequip.setCromatografo(equip);
//                atvequip.setCategoria_preparo(rs.getString("categoria_preparo"));
//                atvequip.setTipo_preparo(rs.getString("tipo_preparo"));
//                atvequip.setDescricao_preparo(rs.getString("descricao_preparo"));
//                atvequip.setLote_preparo(rs.getString("lote_preparo"));
//                atvequip.setQtd_preparo(rs.getDouble("qtd_preparo"));
//                atvequip.setData_hora_inicio(rs.getTimestamp("data_hora_inicio"));
//                Usuario user_inicio = new Usuario();
//                user_inicio.setUser(rs.getString("user_inicio"));
//                atvequip.setUser_inicio(user_inicio);
//                atvequip.setData_hora_fim(rs.getTimestamp("data_hora_fim"));
//                Usuario user_fim = new Usuario();
//                user_fim.setUser(rs.getString("user_fim"));
//                atvequip.setUser_fim(user_fim);
//                atvequip.setData_hora_registro(rs.getTimestamp("data_hora_registro"));
//                Usuario user_registro = new Usuario();
//                user_registro.setUser(rs.getString("user_registro"));
//                atvequip.setUser_registro(user_registro);
//                atvequip.setRetrabalho_preparo(rs.getBoolean("retrabalho_preparo"));
//                atvequip.setObs_preparo(rs.getString("obs_preparo"));
//                atvequip.setMotivo_retrabalho(rs.getString("motivo_retrabalho"));
//                atvequip.setCategoria_retrabalho(rs.getString("categoria_retrabalho"));
//                atvequip.setTipo_retrabalho(rs.getString("tipo_retrabalho"));
//                atvequip.setGerador_retrabalho(rs.getString("gerador_retrabalho"));
//                Usuario user_retrabalho = new Usuario();
//                user_retrabalho.setUser(rs.getString("user_retrabalho"));
//                atvequip.setUser_retrabalho(user_retrabalho);
//            }
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//    }
//
//    public void updateMotivoRetrabalho(AtividadeEquipamento atvequip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("UPDATE tb_motivo_retrabalho SET "
//                    + "tipo_motivo_retrabalho = ?, motivo_retrabalho = ?, "
//                    + "descricao_motivo_retrabalho = ? "
//                    + "WHERE motivo_retrabalho_id = ?");
//            stmt.setString(1, atvequip.getTipo_motivo_retrabalho());
//            stmt.setString(2, atvequip.getMotivo_retrabalho());
//            stmt.setString(3, atvequip.getDescricao_motivo_retrabalho());
//            stmt.setInt(4, atvequip.getMotivo_retrabalho_id());
//            stmt.executeUpdate();
//
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public void deleteMotivoRetrabalho(AtividadeEquipamento atvequip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("DELETE FROM tb_motivo_retrabalho WHERE motivo_retrabalho_id = ?");
//            stmt.setInt(1, atvequip.getMotivo_retrabalho_id());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            //JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }

}
