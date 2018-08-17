/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Frm.Principal.FrmCarregando;
import Classes.Modelo.Analise;
import Classes.Modelo.Campanha;
import Classes.Modelo.Coluna;
import Classes.Modelo.Cromatografo;
import Classes.Modelo.FaseMovel;
import Classes.Modelo.Setor;
import Classes.Modelo.Usuario;
import Classes.Util.Reports;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @since JDK8
 * @author rafael.lopes
 */
public class CromatografoDAO {

    public void createCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_cromatografo "
                    + "(system_name, node, setor_id, modelo, tipo, controladora, "
                    + "degaseificador, bomba, injetor, forno, detector) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, crom.getSystem_name());
            stmt.setString(2, crom.getNode());
            stmt.setInt(3, crom.getSetor().getSetor_id());
            stmt.setString(4, crom.getModelo());
            stmt.setString(5, crom.getTipo());
            stmt.setString(6, crom.getControladora());
            stmt.setString(7, crom.getDegaseificador());
            stmt.setString(8, crom.getBomba());
            stmt.setString(9, crom.getInjetor());
            stmt.setString(10, crom.getForno());
            stmt.setString(11, crom.getDetector());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Cromatografo> readCromatografo() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cromatografo> equipamentos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_cromatografo.*, tb_setor.sigla_setor "
                    + "FROM tb_cromatografo "
                    + "INNER JOIN tb_setor "
                    + "ON tb_cromatografo.setor_id = tb_setor.setor_id "
                    + "ORDER BY tb_cromatografo.system_name ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cromatografo crom = new Cromatografo();
                Setor setor = new Setor();
                crom.setCromatografo_id(rs.getInt("cromatografo_id"));
                crom.setSystem_name(rs.getString("system_name"));
                crom.setNode(rs.getString("node"));
                setor.setSetor_id(1);
                setor.setSigla_setor(rs.getString("sigla_setor"));
                crom.setSetor(setor);
                crom.setTipo(rs.getString("tipo"));
                equipamentos.add(crom);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return equipamentos;
    }

    public void updateCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_cromatografo SET "
                    + "system_name = ?, node = ?, setor_id = ?, modelo = ?, "
                    + "tipo = ?, controladora = ?, degaseificador = ?, bomba = ?, "
                    + "injetor = ?, forno = ?, detector = ? "
                    + "WHERE cromatografo_id = ?");
            stmt.setString(1, crom.getSystem_name());
            stmt.setString(2, crom.getNode());
            stmt.setInt(3, crom.getSetor().getSetor_id());
            stmt.setString(4, crom.getModelo());
            stmt.setString(5, crom.getTipo());
            stmt.setString(6, crom.getControladora());
            stmt.setString(7, crom.getDegaseificador());
            stmt.setString(8, crom.getBomba());
            stmt.setString(9, crom.getInjetor());
            stmt.setString(10, crom.getForno());
            stmt.setString(11, crom.getDetector());
            stmt.setInt(12, crom.getCromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_cromatografo.*, tb_setor.sigla_setor "
                    + "FROM tb_cromatografo "
                    + "INNER JOIN tb_setor "
                    + "ON tb_cromatografo.setor_id = tb_setor.setor_id  "
                    + "WHERE cromatografo_id = ?");
            stmt.setInt(1, crom.getCromatografo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                crom.setSystem_name(rs.getString("system_name"));
                crom.setNode(rs.getString("node"));
                crom.setModelo(rs.getString("modelo"));
                crom.setTipo(rs.getString("tipo"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                crom.setSetor(setor);
                crom.setControladora(rs.getString("controladora"));
                crom.setDegaseificador(rs.getString("degaseificador"));
                crom.setBomba(rs.getString("bomba"));
                crom.setInjetor(rs.getString("injetor"));
                crom.setForno(rs.getString("forno"));
                crom.setDetector(rs.getString("detector"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectCromatografoByName(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_cromatografo.*, tb_setor.setor "
                    + "FROM tb_cromatografo "
                    + "INNER JOIN tb_setor "
                    + "ON tb_cromatografo.setor_id = tb_setor.setor_id  "
                    + "WHERE system_name = ?");
            stmt.setString(1, crom.getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                crom.setCromatografo_id(rs.getInt("cromatografo_id"));
                crom.setNode(rs.getString("node"));
                crom.setModelo(rs.getString("modelo"));
                crom.setTipo(rs.getString("tipo"));
                setor.setSetor(rs.getString("setor"));
                crom.setSetor(setor);
                crom.setControladora(rs.getString("controladora"));
                crom.setDegaseificador(rs.getString("degaseificador"));
                crom.setBomba(rs.getString("bomba"));
                crom.setInjetor(rs.getString("injetor"));
                crom.setForno(rs.getString("forno"));
                crom.setDetector(rs.getString("detector"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectCromatografoByName");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_cromatografo WHERE cromatografo_id = ?");
            stmt.setInt(1, crom.getCromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    //tb_log_cromatografo
    public void createLogCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_cromatografo "
                    + "(analise_status_id, log_system_name, coluna_id, fase_movel_id, "
                    + "data_inicio, descricao, user_name, data_registro, "
                    + "log_campanha_id, obs_log) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, crom.getAnalise().getAnalise_status_id());
            stmt.setString(2, crom.getSystem_name());
            stmt.setInt(3, crom.getColuna().getColuna_id());
            stmt.setInt(4, crom.getFasemovel().getFase_movel_id());
            stmt.setTimestamp(5, crom.getData_inicio() == null ? crom.getData_registro() : crom.getData_inicio());
            stmt.setString(6, crom.getDescricao());
            stmt.setString(7, crom.getUsuario().getUser());
            stmt.setTimestamp(8, crom.getData_registro());
            stmt.setInt(9, crom.getCampanha().getLog_campanha_id());
            stmt.setString(10, crom.getObs_log());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createLogCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void createLogFimCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_cromatografo "
                    + "(analise_status_id, log_system_name, coluna_id, fase_movel_id, "
                    + "data_inicio, descricao, user_name, data_registro, "
                    + "log_campanha_id, metodos, obs_log) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, crom.getAnalise().getAnalise_status_id());
            stmt.setString(2, crom.getSystem_name());
            stmt.setInt(3, crom.getColuna().getColuna_id());
            stmt.setInt(4, crom.getFasemovel().getFase_movel_id());
            stmt.setTimestamp(5, crom.getData_inicio());
            stmt.setString(6, crom.getDescricao());
            stmt.setString(7, crom.getUsuario().getUser());
            stmt.setTimestamp(8, crom.getData_registro());
            stmt.setInt(9, crom.getCampanha().getLog_campanha_id());
            stmt.setString(10, crom.getMetodos());
            stmt.setString(11, crom.getObs_log());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createLogFimCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Cromatografo> readLogCromatografo(String system_name) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cromatografo> equipamentos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(500) tb_log_cromatografo.log_cromatografo_id, "
                    + "tb_log_cromatografo.data_inicio, tb_log_cromatografo.data_fim, "
                    + "tb_analise_status.analise_status, tb_analise_status.analise_produtividade_id, "
                    + "tb_log_cromatografo.data_registro, tb_analise_produtividade.cor, "
                    + "tb_log_cromatografo.descricao, tb_log_cromatografo.user_name "
                    + "FROM tb_log_cromatografo "
                    + "LEFT JOIN tb_analise_status "
                    + "ON tb_log_cromatografo.analise_status_id = tb_analise_status.analise_status_id "
                    + "LEFT JOIN tb_cromatografo "
                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name  "
                    + "LEFT JOIN tb_analise_produtividade "
                    + "ON tb_analise_status.analise_produtividade_id = tb_analise_produtividade.analise_produtividade_id "
                    + "WHERE tb_cromatografo.system_name = ? "
                    + "AND (tb_log_cromatografo.log_ativo IS NULL OR tb_log_cromatografo.log_ativo = 1) "
                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC ");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cromatografo crom = new Cromatografo();
                Analise anls = new Analise();
                Usuario user = new Usuario();
                crom.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                crom.setData_inicio(rs.getTimestamp("data_inicio"));
                crom.setData_fim(rs.getTimestamp("data_fim"));
                anls.setAnalise_status(rs.getString("analise_status"));
                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
                anls.setCor(rs.getString("cor"));
                crom.setAnalise(anls);
                crom.setData_registro(rs.getTimestamp("data_registro"));
                crom.setDescricao(rs.getString("descricao"));
                user.setUser(rs.getString("user_name"));
                crom.setUsuario(user);
                equipamentos.add(crom);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readLogCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return equipamentos;
    }

    public List<Cromatografo> readLogCromatografoAuditoria(String system_name) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cromatografo> equipamentos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(5000) tb_log_cromatografo.log_cromatografo_id, "
                    + "tb_log_cromatografo.data_inicio, tb_log_cromatografo.data_fim, "
                    + "tb_analise_status.analise_status, tb_analise_status.analise_produtividade_id, "
                    + "tb_log_cromatografo.data_registro, tb_analise_produtividade.cor, "
                    + "tb_log_cromatografo.descricao, tb_log_cromatografo.user_name, tb_log_cromatografo.obs_log "
                    + "FROM tb_log_cromatografo "
                    + "LEFT JOIN tb_analise_status "
                    + "ON tb_log_cromatografo.analise_status_id = tb_analise_status.analise_status_id "
                    + "LEFT JOIN tb_cromatografo "
                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name  "
                    + "LEFT JOIN tb_analise_produtividade "
                    + "ON tb_analise_status.analise_produtividade_id = tb_analise_produtividade.analise_produtividade_id "
                    + "WHERE tb_cromatografo.system_name = ? "
                    + "AND tb_log_cromatografo.log_ativo = 0 "
                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC ");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cromatografo crom = new Cromatografo();
                Analise anls = new Analise();
                Usuario user = new Usuario();
                crom.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                crom.setData_inicio(rs.getTimestamp("data_inicio"));
                crom.setData_fim(rs.getTimestamp("data_fim"));
                anls.setAnalise_status(rs.getString("analise_status"));
                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
                anls.setCor(rs.getString("cor"));
                crom.setAnalise(anls);
                crom.setData_registro(rs.getTimestamp("data_registro"));
                crom.setDescricao(rs.getString("descricao"));
                crom.setObs_log(rs.getString("obs_log"));
                user.setUser(rs.getString("user_name"));
                crom.setUsuario(user);
                equipamentos.add(crom);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readLogCromatografoAuditoria");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return equipamentos;
    }

    public void updateLogCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
                    + "data_fim = ?, data_registro = ?, user_name = ?, "
                    + "coluna_id = ?, fase_movel_id = ? "
                    + "WHERE log_cromatografo_id = ?");
            stmt.setTimestamp(1, crom.getData_fim());
            stmt.setTimestamp(2, crom.getData_registro());
            stmt.setString(3, System.getProperty("user"));
            stmt.setInt(4, crom.getColuna().getColuna_id());
            stmt.setInt(5, crom.getFasemovel().getFase_movel_id());
            stmt.setInt(6, crom.getLog_cromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateLogCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateColunaLogCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
                    + "coluna_id = ? WHERE log_cromatografo_id = ?");
            stmt.setInt(1, crom.getColuna().getColuna_id());
            stmt.setInt(2, crom.getLog_cromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateColunaLogCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateFaseMovelLogCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
                    + "fase_movel_id = ? WHERE log_cromatografo_id = ?");
            stmt.setInt(1, crom.getFasemovel().getFase_movel_id());
            stmt.setInt(2, crom.getLog_cromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateFaseMovelLogCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateDeleteLogCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
                    + "data_registro = ?, obs_log = ?, "
                    + "log_ativo = ?, user_name = ? "
                    + "WHERE log_cromatografo_id = ?");
            stmt.setTimestamp(1, crom.getData_registro());
            stmt.setString(2, crom.getObs_log());
            stmt.setInt(3, (crom.getLog_ativo()) ? 1 : 0);
            stmt.setString(4, crom.getUsuario().getUser());
            stmt.setInt(5, crom.getLog_cromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateDeleteLogCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateUltimoFimLogCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
                    + "data_registro = ?,  user_name = ?, "
                    + "data_fim = null "
                    + "WHERE log_cromatografo_id = ?");
            stmt.setTimestamp(1, crom.getData_registro());
            stmt.setString(2, crom.getUsuario().getUser());
            stmt.setInt(3, crom.getLog_cromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateUltimoFimLogCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectLogCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_cromatografo.log_cromatografo_id, "
                    + "tb_log_cromatografo.data_inicio, tb_log_cromatografo.data_fim, "
                    + "tb_analise_status.analise_status, tb_analise_status.analise_produtividade_id, "
                    + "tb_log_cromatografo.analise_status_id, tb_log_cromatografo.data_registro, "
                    + "tb_analise_produtividade.cor, tb_log_cromatografo.coluna_id, tb_log_cromatografo.fase_movel_id, "
                    + "tb_log_cromatografo.descricao, tb_log_cromatografo.user_name "
                    + "FROM tb_log_cromatografo "
                    + "LEFT JOIN tb_analise_status "
                    + "ON tb_log_cromatografo.analise_status_id = tb_analise_status.analise_status_id "
                    + "LEFT JOIN tb_cromatografo "
                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name "
                    + "LEFT JOIN tb_analise_produtividade "
                    + "ON tb_analise_status.analise_produtividade_id = tb_analise_produtividade.analise_produtividade_id "
                    + "WHERE tb_cromatografo.system_name = ? "
                    + "AND (tb_log_cromatografo.log_ativo IS NULL OR tb_log_cromatografo.log_ativo = 1) "
                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC  ");
            stmt.setString(1, crom.getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                Usuario user = new Usuario();
                Coluna col = new Coluna();
                FaseMovel fase = new FaseMovel();
                crom.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                crom.setData_inicio(rs.getTimestamp("data_inicio"));
                crom.setData_fim(rs.getTimestamp("data_fim"));
                anls.setAnalise_status(rs.getString("analise_status"));
                anls.setAnalise_status_id(rs.getInt("analise_status_id"));
                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
                anls.setCor(rs.getString("cor"));
                crom.setAnalise(anls);
                col.setColuna_id(rs.getInt("coluna_id"));
                crom.setColuna(col);
                fase.setFase_movel_id(rs.getInt("fase_movel_id"));
                crom.setFasemovel(fase);
                crom.setData_registro(rs.getTimestamp("data_registro"));
                crom.setDescricao(rs.getString("descricao"));
                user.setUser(rs.getString("user_name"));
                crom.setUsuario(user);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectLogCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectLogCampanhaCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_cromatografo.log_cromatografo_id, tb_log_campanha.nome_campanha, "
                    + "tb_log_cromatografo.campanha, tb_log_cromatografo.metodos, tb_log_cromatografo.obs_log "
                    + "FROM tb_log_cromatografo "
                    + "LEFT JOIN tb_cromatografo "
                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name "
                    + "INNER JOIN tb_log_campanha "
                    + "ON tb_log_cromatografo.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "WHERE tb_cromatografo.system_name = ? AND tb_log_cromatografo.analise_status_id = 1 "
                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC  ");
            stmt.setString(1, crom.getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campanha camp = new Campanha();
                crom.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                crom.setCampanha(camp);
                crom.setMetodos(rs.getString("metodos"));
                crom.setObs_log(rs.getString("obs_log"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectLogCampanhaCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectLogCorridasCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_cromatografo.log_cromatografo_id, tb_log_campanha.nome_campanha, "
                    + "tb_log_cromatografo.campanha, tb_log_cromatografo.metodos, tb_log_cromatografo.obs_log "
                    + "FROM tb_log_cromatografo "
                    + "LEFT JOIN tb_cromatografo "
                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name "
                    + "INNER JOIN tb_log_campanha "
                    + "ON tb_log_cromatografo.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "WHERE tb_cromatografo.system_name = ? AND tb_log_cromatografo.analise_status_id = 1 "
                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC  ");
            stmt.setString(1, crom.getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campanha camp = new Campanha();
                crom.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                crom.setCampanha(camp);
                crom.setMetodos(rs.getString("metodos"));
                crom.setObs_log(rs.getString("obs_log"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectLogCorridasCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public Timestamp selectDataUltimoRegistroCromatografo(String equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Timestamp data = null;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_cromatografo.data_registro "
                    + "FROM tb_log_cromatografo "
                    + "WHERE tb_log_cromatografo.log_system_name = ? "
                    + "AND tb_log_cromatografo.log_ativo IS NULL "
                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC ");
            stmt.setString(1, equip);
            rs = stmt.executeQuery();
            while (rs.next()) {
                data = rs.getTimestamp("data_registro");
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectDataUltimoRegistroCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return data;
    }

    public void deleteLogCromatografo(Cromatografo crom) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_log_cromatografo "
                    + "WHERE log_cromatografo_id = ?");
            stmt.setInt(1, crom.getLog_cromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteLogCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Cromatografo> readProgramacaoCromatografo() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cromatografo> equipamentos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM vw_ProgramacaoCromatografo "
                    + "ORDER BY log_system_name ASC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cromatografo crom = new Cromatografo();
                Analise anls = new Analise();
                Usuario user = new Usuario();
                Setor setor = new Setor();
                Coluna col = new Coluna();
                Campanha camp = new Campanha();
                crom.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                crom.setSystem_name(rs.getString("log_system_name"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                crom.setCampanha(camp);
                anls.setAnalise_status(rs.getString("analise_status"));
                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
                anls.setCor(rs.getString("cor"));
                crom.setAnalise(anls);
                crom.setData_registro(rs.getTimestamp("data_registro"));
                crom.setDescricao(rs.getString("descricao"));
                user.setUser(rs.getString("user_name"));
                crom.setUsuario(user);
                setor.setSigla_setor(rs.getString("sigla_setor"));
                crom.setSetor(setor);
                col.setColuna_id(rs.getInt("coluna_id"));
                crom.setColuna(col);
                equipamentos.add(crom);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readProgramacaoCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return equipamentos;
    }

    /**
     *
     * Gera uma lista utilizando o ojeto <code>Cromatografo</code>
     *
     * @author Rafael Saldanha Lopes
     * @param setores String - Nome do Usuario solicitante
     * @return Cromatografo - List - Lista com o objeto Cromatografo
     * @throws logSQLException Gera registro no log de erro
     *
     */
    public List<Cromatografo> readProgramacaoCromatografo(String setores) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cromatografo> equipamentos = new ArrayList<>();
        try {
            String where = "";
            if (!"Todos".equals(setores)) {
                where = " AND sigla_setor = '" + setores + "' ";
            }
            stmt = conn.prepareStatement("SELECT * FROM vw_ProgramacaoCromatografo "
                    + "WHERE 1 = 1 " + where
                    + "ORDER BY log_system_name ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cromatografo crom = new Cromatografo();
                Analise anls = new Analise();
                Usuario user = new Usuario();
                Setor setor = new Setor();
                Coluna col = new Coluna();
                Campanha camp = new Campanha();
                crom.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                crom.setSystem_name(rs.getString("log_system_name"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                crom.setCampanha(camp);
                anls.setAnalise_status(rs.getString("analise_status"));
                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
                anls.setCor(rs.getString("cor"));
                crom.setAnalise(anls);
                crom.setData_registro(rs.getTimestamp("data_registro"));
                crom.setDescricao(rs.getString("descricao"));
                user.setUser(rs.getString("user_name"));
                crom.setUsuario(user);
                setor.setSigla_setor(rs.getString("sigla_setor"));
                crom.setSetor(setor);
                col.setColuna_id(rs.getInt("coluna_id"));
                crom.setColuna(col);
                equipamentos.add(crom);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readProgramacaoCromatografo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return equipamentos;
    }

    /**
     *
     * Imprime relatório de Ocupação Individual dos Cromatógrafos Utiliza o
     * <code>JasperPrint</code> para gerar o relatório.
     *
     * @author Rafael Saldanha Lopes
     * @param user String - Nome do Usuario solicitante
     * @param system_name String - Nome do Sistema Cromatográfico
     * @param data_inicio Timestamp - Data do início do relatório
     * @param data_fim Timestamp - Data do final do relatório
     * @throws logJasperPrint Gera registro no log de erro
     * @throws showMessageDialog Gera mensagem de erro na tela
     */
    public static void printOcupacaoIndividual(String user, String system_name,
            Timestamp data_inicio, Timestamp data_fim) {
        Connection conn = ConnectionFactory.getConnection();
        FrmCarregando frm = new FrmCarregando();
        frm.setVisible(true);
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("system_name", system_name);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        String src = Reports.getSrc() + "CromatografoOcupacaoIndividual.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            frm.dispose();
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printOcupacaoIndividual");
            frm.dispose();
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

}
