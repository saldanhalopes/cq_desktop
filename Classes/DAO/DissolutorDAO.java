/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Dissolutor;
import Classes.Modelo.Setor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @since JDK8
 * @author rafael.lopes
 */
public class DissolutorDAO {

    public void createDissolutor(Dissolutor diss) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_dissolutor "
                    + "(dissolutor_name, setor_id, modelo, tipo, marca, "
                    + "hastes, timer_1, timer_2, termometro, rotor) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, diss.getDissolutor_name());
            stmt.setInt(2, diss.getSetor().getSetor_id());
            stmt.setString(3, diss.getModelo());
            stmt.setString(4, diss.getTipo());
            stmt.setString(5, diss.getMarca());
            stmt.setInt(6, diss.getHastes());
            stmt.setString(7, diss.getTimer_1());
            stmt.setString(8, diss.getTimer_2());
            stmt.setString(9, diss.getTermometro());
            stmt.setString(10, diss.getRotor());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createEquipamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Dissolutor> readDissolutor() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Dissolutor> dissolutores = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_dissolutor.*, tb_setor.sigla_setor "
                    + "FROM tb_dissolutor "
                    + "INNER JOIN tb_setor "
                    + "ON tb_dissolutor.setor_id = tb_setor.setor_id "
                    + "ORDER BY tb_dissolutor.dissolutor_name ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Dissolutor diss = new Dissolutor();
                Setor setor = new Setor();
                diss.setDissolutor_id(rs.getInt("dissolutor_id"));
                diss.setDissolutor_name(rs.getString("dissolutor_name"));
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                diss.setSetor(setor);
                diss.setModelo(rs.getString("modelo"));
                diss.setMarca(rs.getString("marca"));
                diss.setTipo(rs.getString("tipo"));
                diss.setHastes(rs.getInt("hastes"));
                diss.setTermometro(rs.getString("termometro"));
                diss.setTimer_1(rs.getString("timer_1"));
                diss.setTimer_2(rs.getString("timer_2"));
                diss.setRotor(rs.getString("rotor"));
                dissolutores.add(diss);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readDissolutor");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return dissolutores;
    }

    public void updateDissolutor(Dissolutor diss) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_dissolutor SET "
                    + "dissolutor_name = ?, setor_id = ?, modelo = ?, "
                    + "marca = ?, tipo = ?, hastes = ?, termometro = ?, "
                    + "timer_1 = ?, timer_2 = ?, rotor = ? "
                    + "WHERE dissolutor_id = ?");
            stmt.setString(1, diss.getDissolutor_name());
            stmt.setInt(2, diss.getSetor().getSetor_id());
            stmt.setString(3, diss.getModelo());
            stmt.setString(4, diss.getMarca());
            stmt.setString(5, diss.getTipo());
            stmt.setInt(6, diss.getHastes());
            stmt.setString(7, diss.getTermometro());
            stmt.setString(8, diss.getTimer_1());
            stmt.setString(9, diss.getTimer_2());
            stmt.setString(10, diss.getRotor());
            stmt.setInt(11, diss.getDissolutor_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateDissolutor");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectDissolutor(Dissolutor diss) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_dissolutor.*, tb_setor.sigla_setor "
                    + "FROM tb_dissolutor "
                    + "INNER JOIN tb_setor "
                    + "ON tb_dissolutor.setor_id = tb_setor.setor_id  "
                    + "WHERE dissolutor_id = ?");
            stmt.setInt(1, diss.getDissolutor_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                diss.setDissolutor_name(rs.getString("dissolutor_name"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                diss.setSetor(setor);
                diss.setModelo(rs.getString("modelo"));
                diss.setMarca(rs.getString("marca"));
                diss.setTipo(rs.getString("tipo"));
                diss.setHastes(rs.getInt("hastes"));
                diss.setTermometro(rs.getString("termometro"));
                diss.setTimer_1(rs.getString("timer_1"));
                diss.setTimer_2(rs.getString("timer_2"));
                diss.setRotor(rs.getString("rotor"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectDissolutor");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectDissolutorByName(Dissolutor diss) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
             stmt = conn.prepareStatement("SELECT tb_dissolutor.*, tb_setor.sigla_setor "
                    + "FROM tb_dissolutor "
                    + "INNER JOIN tb_setor "
                    + "ON tb_dissolutor.setor_id = tb_setor.setor_id  "
                    + "WHERE dissolutor_name = ?");
            stmt.setString(1, diss.getDissolutor_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                diss.setDissolutor_id(rs.getInt("dissolutor_id"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                diss.setSetor(setor);
                diss.setModelo(rs.getString("modelo"));
                diss.setMarca(rs.getString("marca"));
                diss.setTipo(rs.getString("tipo"));
                diss.setHastes(rs.getInt("hastes"));
                diss.setTermometro(rs.getString("termometro"));
                diss.setTimer_1(rs.getString("timer_1"));
                diss.setTimer_2(rs.getString("timer_2"));
                diss.setRotor(rs.getString("rotor"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectDissolutorByName");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteDissolutor(Dissolutor diss) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_dissolutor "
                    + "WHERE dissolutor_id = ?");
            stmt.setInt(1, diss.getDissolutor_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteDissolutor");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

//    //tb_log_cromatografo
//    public void createLogCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("INSERT INTO tb_log_cromatografo "
//                    + "(analise_status_id, log_system_name, coluna_id, fase_movel_id, "
//                    + "data_inicio, descricao, user_name, data_registro, "
//                    + "log_campanha_id, obs_log) "
//                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");
//            stmt.setInt(1, equip.getAnalise().getAnalise_status_id());
//            stmt.setString(2, equip.getSystem_name());
//            stmt.setInt(3, equip.getColuna().getColuna_id());
//            stmt.setInt(4, equip.getFasemovel().getFase_movel_id());
//            stmt.setTimestamp(5, equip.getData_inicio() == null ? equip.getData_registro() : equip.getData_inicio());
//            stmt.setString(6, equip.getDescricao());
//            stmt.setString(7, equip.getUsuario().getUser());
//            stmt.setTimestamp(8, equip.getData_registro());
//            stmt.setInt(9, equip.getCampanha().getLog_campanha_id());
//            stmt.setString(10, equip.getObs_log());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "createLogCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public void createLogFimCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("INSERT INTO tb_log_cromatografo "
//                    + "(analise_status_id, log_system_name, coluna_id, fase_movel_id, "
//                    + "data_inicio, descricao, user_name, data_registro, "
//                    + "log_campanha_id, metodos, obs_log) "
//                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
//            stmt.setInt(1, equip.getAnalise().getAnalise_status_id());
//            stmt.setString(2, equip.getSystem_name());
//            stmt.setInt(3, equip.getColuna().getColuna_id());
//            stmt.setInt(4, equip.getFasemovel().getFase_movel_id());
//            stmt.setTimestamp(5, equip.getData_inicio());
//            stmt.setString(6, equip.getDescricao());
//            stmt.setString(7, equip.getUsuario().getUser());
//            stmt.setTimestamp(8, equip.getData_registro());
//            stmt.setInt(9, equip.getCampanha().getLog_campanha_id());
//            stmt.setString(10, equip.getMetodos());
//            stmt.setString(11, equip.getObs_log());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "createLogFimCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public List<Cromatografo> readLogCromatografo(String system_name) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<Cromatografo> equipamentos = new ArrayList<>();
//        try {
//            stmt = conn.prepareStatement("SELECT TOP(500) tb_log_cromatografo.log_cromatografo_id, "
//                    + "tb_log_cromatografo.data_inicio, tb_log_cromatografo.data_fim, "
//                    + "tb_analise_status.analise_status, tb_analise_status.analise_produtividade_id, "
//                    + "tb_log_cromatografo.data_registro, tb_analise_produtividade.cor, "
//                    + "tb_log_cromatografo.descricao, tb_log_cromatografo.user_name "
//                    + "FROM tb_log_cromatografo "
//                    + "LEFT JOIN tb_analise_status "
//                    + "ON tb_log_cromatografo.analise_status_id = tb_analise_status.analise_status_id "
//                    + "LEFT JOIN tb_cromatografo "
//                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name  "
//                    + "LEFT JOIN tb_analise_produtividade "
//                    + "ON tb_analise_status.analise_produtividade_id = tb_analise_produtividade.analise_produtividade_id "
//                    + "WHERE tb_cromatografo.system_name = ? "
//                    + "AND (tb_log_cromatografo.log_ativo IS NULL OR tb_log_cromatografo.log_ativo = 1) "
//                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC ");
//            stmt.setString(1, system_name);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Cromatografo equip = new Cromatografo();
//                Analise anls = new Analise();
//                Usuario user = new Usuario();
//                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
//                equip.setData_inicio(rs.getTimestamp("data_inicio"));
//                equip.setData_fim(rs.getTimestamp("data_fim"));
//                anls.setAnalise_status(rs.getString("analise_status"));
//                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
//                anls.setCor(rs.getString("cor"));
//                equip.setAnalise(anls);
//                equip.setData_registro(rs.getTimestamp("data_registro"));
//                equip.setDescricao(rs.getString("descricao"));
//                user.setUser(rs.getString("user_name"));
//                equip.setUsuario(user);
//                equipamentos.add(equip);
//            }
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "readLogCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return equipamentos;
//    }
//
//    public List<Cromatografo> readLogCromatografoAuditoria(String system_name) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<Cromatografo> equipamentos = new ArrayList<>();
//        try {
//            stmt = conn.prepareStatement("SELECT TOP(5000) tb_log_cromatografo.log_cromatografo_id, "
//                    + "tb_log_cromatografo.data_inicio, tb_log_cromatografo.data_fim, "
//                    + "tb_analise_status.analise_status, tb_analise_status.analise_produtividade_id, "
//                    + "tb_log_cromatografo.data_registro, tb_analise_produtividade.cor, "
//                    + "tb_log_cromatografo.descricao, tb_log_cromatografo.user_name, tb_log_cromatografo.obs_log "
//                    + "FROM tb_log_cromatografo "
//                    + "LEFT JOIN tb_analise_status "
//                    + "ON tb_log_cromatografo.analise_status_id = tb_analise_status.analise_status_id "
//                    + "LEFT JOIN tb_cromatografo "
//                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name  "
//                    + "LEFT JOIN tb_analise_produtividade "
//                    + "ON tb_analise_status.analise_produtividade_id = tb_analise_produtividade.analise_produtividade_id "
//                    + "WHERE tb_cromatografo.system_name = ? "
//                    + "AND tb_log_cromatografo.log_ativo = 0 "
//                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC ");
//            stmt.setString(1, system_name);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Cromatografo equip = new Cromatografo();
//                Analise anls = new Analise();
//                Usuario user = new Usuario();
//                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
//                equip.setData_inicio(rs.getTimestamp("data_inicio"));
//                equip.setData_fim(rs.getTimestamp("data_fim"));
//                anls.setAnalise_status(rs.getString("analise_status"));
//                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
//                anls.setCor(rs.getString("cor"));
//                equip.setAnalise(anls);
//                equip.setData_registro(rs.getTimestamp("data_registro"));
//                equip.setDescricao(rs.getString("descricao"));
//                equip.setObs_log(rs.getString("obs_log"));
//                user.setUser(rs.getString("user_name"));
//                equip.setUsuario(user);
//                equipamentos.add(equip);
//            }
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "readLogCromatografoAuditoria");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return equipamentos;
//    }
//
//    public void updateLogCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
//                    + "data_fim = ?, data_registro = ?, user_name = ?, "
//                    + "coluna_id = ?, fase_movel_id = ? "
//                    + "WHERE log_cromatografo_id = ?");
//            stmt.setTimestamp(1, equip.getData_fim());
//            stmt.setTimestamp(2, equip.getData_registro());
//            stmt.setString(3, System.getProperty("user"));
//            stmt.setInt(4, equip.getColuna().getColuna_id());
//            stmt.setInt(5, equip.getFasemovel().getFase_movel_id());
//            stmt.setInt(6, equip.getLog_cromatografo_id());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "updateLogCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public void updateColunaLogCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
//                    + "coluna_id = ? WHERE log_cromatografo_id = ?");
//            stmt.setInt(1, equip.getColuna().getColuna_id());
//            stmt.setInt(2, equip.getLog_cromatografo_id());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "updateColunaLogCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public void updateFaseMovelLogCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
//                    + "fase_movel_id = ? WHERE log_cromatografo_id = ?");
//            stmt.setInt(1, equip.getFasemovel().getFase_movel_id());
//            stmt.setInt(2, equip.getLog_cromatografo_id());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "updateFaseMovelLogCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public void updateDeleteLogCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
//                    + "data_registro = ?, obs_log = ?, "
//                    + "log_ativo = ?, user_name = ? "
//                    + "WHERE log_cromatografo_id = ?");
//            stmt.setTimestamp(1, equip.getData_registro());
//            stmt.setString(2, equip.getObs_log());
//            stmt.setInt(3, (equip.isLog_ativo()) ? 1 : 0);
//            stmt.setString(4, equip.getUsuario().getUser());
//            stmt.setInt(5, equip.getLog_cromatografo_id());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "updateDeleteLogCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public void updateUltimoFimLogCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
//                    + "data_registro = ?,  user_name = ?, "
//                    + "data_fim = null "
//                    + "WHERE log_cromatografo_id = ?");
//            stmt.setTimestamp(1, equip.getData_registro());
//            stmt.setString(2, equip.getUsuario().getUser());
//            stmt.setInt(3, equip.getLog_cromatografo_id());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "updateUltimoFimLogCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public void selectLogCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_cromatografo.log_cromatografo_id, "
//                    + "tb_log_cromatografo.data_inicio, tb_log_cromatografo.data_fim, "
//                    + "tb_analise_status.analise_status, tb_analise_status.analise_produtividade_id, "
//                    + "tb_log_cromatografo.analise_status_id, tb_log_cromatografo.data_registro, "
//                    + "tb_analise_produtividade.cor, tb_log_cromatografo.coluna_id, tb_log_cromatografo.fase_movel_id, "
//                    + "tb_log_cromatografo.descricao, tb_log_cromatografo.user_name "
//                    + "FROM tb_log_cromatografo "
//                    + "LEFT JOIN tb_analise_status "
//                    + "ON tb_log_cromatografo.analise_status_id = tb_analise_status.analise_status_id "
//                    + "LEFT JOIN tb_cromatografo "
//                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name "
//                    + "LEFT JOIN tb_analise_produtividade "
//                    + "ON tb_analise_status.analise_produtividade_id = tb_analise_produtividade.analise_produtividade_id "
//                    + "WHERE tb_cromatografo.system_name = ? "
//                    + "AND (tb_log_cromatografo.log_ativo IS NULL OR tb_log_cromatografo.log_ativo = 1) "
//                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC  ");
//            stmt.setString(1, equip.getSystem_name());
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Analise anls = new Analise();
//                Usuario user = new Usuario();
//                Coluna col = new Coluna();
//                FaseMovel fase = new FaseMovel();
//                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
//                equip.setData_inicio(rs.getTimestamp("data_inicio"));
//                equip.setData_fim(rs.getTimestamp("data_fim"));
//                anls.setAnalise_status(rs.getString("analise_status"));
//                anls.setAnalise_status_id(rs.getInt("analise_status_id"));
//                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
//                anls.setCor(rs.getString("cor"));
//                equip.setAnalise(anls);
//                col.setColuna_id(rs.getInt("coluna_id"));
//                equip.setColuna(col);
//                fase.setFase_movel_id(rs.getInt("fase_movel_id"));
//                equip.setFasemovel(fase);
//                equip.setData_registro(rs.getTimestamp("data_registro"));
//                equip.setDescricao(rs.getString("descricao"));
//                user.setUser(rs.getString("user_name"));
//                equip.setUsuario(user);
//            }
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "selectLogCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//    }
//
//    public void selectLogCampanhaCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_cromatografo.log_cromatografo_id, tb_log_campanha.nome_campanha, "
//                    + "tb_log_cromatografo.campanha, tb_log_cromatografo.metodos, tb_log_cromatografo.obs_log "
//                    + "FROM tb_log_cromatografo "
//                    + "LEFT JOIN tb_cromatografo "
//                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name "
//                    + "INNER JOIN tb_log_campanha "
//                    + "ON tb_log_cromatografo.log_campanha_id = tb_log_campanha.log_campanha_id "
//                    + "WHERE tb_cromatografo.system_name = ? AND tb_log_cromatografo.analise_status_id = 1 "
//                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC  ");
//            stmt.setString(1, equip.getSystem_name());
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Campanha camp = new Campanha();
//                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
//                camp.setNome_campanha(rs.getString("nome_campanha"));
//                equip.setCampanha(camp);
//                equip.setMetodos(rs.getString("metodos"));
//                equip.setObs_log(rs.getString("obs_log"));
//            }
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "selectLogCampanhaCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//    }
//
//    public void selectLogCorridasCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_cromatografo.log_cromatografo_id, tb_log_campanha.nome_campanha, "
//                    + "tb_log_cromatografo.campanha, tb_log_cromatografo.metodos, tb_log_cromatografo.obs_log "
//                    + "FROM tb_log_cromatografo "
//                    + "LEFT JOIN tb_cromatografo "
//                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name "
//                    + "INNER JOIN tb_log_campanha "
//                    + "ON tb_log_cromatografo.log_campanha_id = tb_log_campanha.log_campanha_id "
//                    + "WHERE tb_cromatografo.system_name = ? AND tb_log_cromatografo.analise_status_id = 1 "
//                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC  ");
//            stmt.setString(1, equip.getSystem_name());
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Campanha camp = new Campanha();
//                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
//                camp.setNome_campanha(rs.getString("nome_campanha"));
//                equip.setCampanha(camp);
//                equip.setMetodos(rs.getString("metodos"));
//                equip.setObs_log(rs.getString("obs_log"));
//            }
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "selectLogCorridasCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//    }
//
//    public Timestamp selectDataUltimoRegistroCromatografo(String equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        Timestamp data = null;
//        try {
//            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_cromatografo.data_registro "
//                    + "FROM tb_log_cromatografo "
//                    + "WHERE tb_log_cromatografo.log_system_name = ? "
//                    + "AND tb_log_cromatografo.log_ativo IS NULL "
//                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC ");
//            stmt.setString(1, equip);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                data = rs.getTimestamp("data_registro");
//            }
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "selectDataUltimoRegistroCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return data;
//    }
//
//    public void deleteLogCromatografo(Cromatografo equip) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = conn.prepareStatement("DELETE FROM tb_log_cromatografo "
//                    + "WHERE log_cromatografo_id = ?");
//            stmt.setInt(1, equip.getLog_cromatografo_id());
//            stmt.executeUpdate();
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "deleteLogCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt);
//        }
//    }
//
//    public List<Cromatografo> readProgramacaoCromatografo() {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<Cromatografo> equipamentos = new ArrayList<>();
//        try {
//            stmt = conn.prepareStatement("SELECT * FROM vw_ProgramacaoCromatografo "
//                    + "ORDER BY log_system_name ASC ");
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Cromatografo equip = new Cromatografo();
//                Analise anls = new Analise();
//                Usuario user = new Usuario();
//                Setor setor = new Setor();
//                Coluna col = new Coluna();
//                Campanha camp = new Campanha();
//                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
//                equip.setSystem_name(rs.getString("log_system_name"));
//                camp.setNome_campanha(rs.getString("nome_campanha"));
//                equip.setCampanha(camp);
//                anls.setAnalise_status(rs.getString("analise_status"));
//                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
//                anls.setCor(rs.getString("cor"));
//                equip.setAnalise(anls);
//                equip.setData_registro(rs.getTimestamp("data_registro"));
//                equip.setDescricao(rs.getString("descricao"));
//                user.setUser(rs.getString("user_name"));
//                equip.setUsuario(user);
//                setor.setSigla_setor(rs.getString("sigla_setor"));
//                equip.setSetor(setor);
//                col.setColuna_id(rs.getInt("coluna_id"));
//                equip.setColuna(col);
//                equipamentos.add(equip);
//            }
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "readProgramacaoCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return equipamentos;
//    }
//
//    /**
//     *
//     * Gera uma lista utilizando o ojeto <code>Cromatografo</code>
//     *
//     * @author Rafael Saldanha Lopes
//     * @param setores String - Nome do Usuario solicitante
//     * @return Cromatografo - List - Lista com o objeto Cromatografo
//     * @throws logSQLException Gera registro no log de erro
//     *
//     */
//    public List<Cromatografo> readProgramacaoCromatografo(String setores) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<Cromatografo> equipamentos = new ArrayList<>();
//        try {
//            String where = "";
//            if (!"Todos".equals(setores)) {
//                where = " AND sigla_setor = '" + setores + "' ";
//            }
//            stmt = conn.prepareStatement("SELECT * FROM vw_ProgramacaoCromatografo "
//                    + "WHERE 1 = 1 " + where
//                    + "ORDER BY log_system_name ASC");
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Cromatografo equip = new Cromatografo();
//                Analise anls = new Analise();
//                Usuario user = new Usuario();
//                Setor setor = new Setor();
//                Coluna col = new Coluna();
//                Campanha camp = new Campanha();
//                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
//                equip.setSystem_name(rs.getString("log_system_name"));
//                camp.setNome_campanha(rs.getString("nome_campanha"));
//                equip.setCampanha(camp);
//                anls.setAnalise_status(rs.getString("analise_status"));
//                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
//                anls.setCor(rs.getString("cor"));
//                equip.setAnalise(anls);
//                equip.setData_registro(rs.getTimestamp("data_registro"));
//                equip.setDescricao(rs.getString("descricao"));
//                user.setUser(rs.getString("user_name"));
//                equip.setUsuario(user);
//                setor.setSigla_setor(rs.getString("sigla_setor"));
//                equip.setSetor(setor);
//                col.setColuna_id(rs.getInt("coluna_id"));
//                equip.setColuna(col);
//                equipamentos.add(equip);
//            }
//        } catch (SQLException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logSQLException(ex, "readProgramacaoCromatografo");
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return equipamentos;
//    }
//
//    /**
//     *
//     * Imprime relatório de Ocupação Individual dos Cromatógrafos Utiliza o
//     * <code>JasperPrint</code> para gerar o relatório.
//     *
//     * @author Rafael Saldanha Lopes
//     * @param user String - Nome do Usuario solicitante
//     * @param system_name String - Nome do Sistema Cromatográfico
//     * @param data_inicio Timestamp - Data do início do relatório
//     * @param data_fim Timestamp - Data do final do relatório
//     * @throws logJasperPrint Gera registro no log de erro
//     * @throws showMessageDialog Gera mensagem de erro na tela
//     */
//    public static void printOcupacaoIndividual(String user, String system_name,
//            Timestamp data_inicio, Timestamp data_fim) {
//        Connection conn = ConnectionFactory.getConnection();
//        FrmCarregando frm = new FrmCarregando();
//        frm.setVisible(true);
//        HashMap map = new HashMap();
//        map.put("User_name", user);
//        map.put("system_name", system_name);
//        map.put("data_inicio", data_inicio);
//        map.put("data_fim", data_fim);
//        String src = Reports.getSrc() + "CromatografoOcupacaoIndividual.jasper";
//        try {
//            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
//            frm.dispose();
//            JasperViewer jv = new JasperViewer(jpt, false);
//            jv.setVisible(true);
//        } catch (JRException ex) {
//            LogDAO logDAO = new LogDAO();
//            logDAO.logJasperPrint(ex, "printOcupacaoIndividual");
//            frm.dispose();
//            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
//        }
//    }
}
