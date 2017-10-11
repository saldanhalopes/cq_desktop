/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.ConnectionFactory;
import model.Analise;
import model.Coluna;
import model.Cromatografo;
import model.FaseMovel;
import model.Setor;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class CromatografoDAO {

    public void createEquipamento(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_cromatografo "
                    + "(system_name, node, setor_id, modelo, tipo, controladora, "
                    + "degaseificador, bomba, injetor, forno, detector) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, equip.getSystem_name());
            stmt.setString(2, equip.getNode());
            stmt.setInt(3, equip.getSetor().getSetor_id());
            stmt.setString(4, equip.getModelo());
            stmt.setString(5, equip.getTipo());
            stmt.setString(6, equip.getControladora());
            stmt.setString(7, equip.getDegaseificador());
            stmt.setString(8, equip.getBomba());
            stmt.setString(9, equip.getInjetor());
            stmt.setString(10, equip.getForno());
            stmt.setString(11, equip.getDetector());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Cromatografo> read() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cromatografo> equipamentos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_cromatografo.*, tb_setor.sigla_setor "
                    + "FROM tb_cromatografo "
                    + "INNER JOIN tb_setor "
                    + "ON tb_cromatografo.setor_id = tb_setor.setor_id  ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cromatografo equip = new Cromatografo();
                Setor setor = new Setor();
                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
                equip.setSystem_name(rs.getString("system_name"));
                equip.setNode(rs.getString("node"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                equip.setSetor(setor);
                equip.setTipo(rs.getString("tipo"));
                equipamentos.add(equip);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return equipamentos;
    }

    public void updateEquipamento(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_cromatografo SET "
                    + "system_name = ?, node = ?, setor_id = ?, modelo = ?, "
                    + "tipo = ?, controladora = ?, degaseificador = ?, bomba = ?, "
                    + "injetor = ?, forno = ?, detector = ? "
                    + "WHERE cromatografo_id = ?");
            stmt.setString(1, equip.getSystem_name());
            stmt.setString(2, equip.getNode());
            stmt.setInt(3, equip.getSetor().getSetor_id());
            stmt.setString(4, equip.getModelo());
            stmt.setString(5, equip.getTipo());
            stmt.setString(6, equip.getControladora());
            stmt.setString(7, equip.getDegaseificador());
            stmt.setString(8, equip.getBomba());
            stmt.setString(9, equip.getInjetor());
            stmt.setString(10, equip.getForno());
            stmt.setString(11, equip.getDetector());
            stmt.setInt(12, equip.getCromatografo_id());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectEquipamento(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_cromatografo.*, tb_setor.sigla_setor "
                    + "FROM tb_cromatografo "
                    + "INNER JOIN tb_setor "
                    + "ON tb_cromatografo.setor_id = tb_setor.setor_id  "
                    + "WHERE cromatografo_id = ?");
            stmt.setInt(1, equip.getCromatografo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                equip.setSystem_name(rs.getString("system_name"));
                equip.setNode(rs.getString("node"));
                equip.setModelo(rs.getString("modelo"));
                equip.setTipo(rs.getString("tipo"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                equip.setSetor(setor);
                equip.setControladora(rs.getString("controladora"));
                equip.setDegaseificador(rs.getString("degaseificador"));
                equip.setBomba(rs.getString("bomba"));
                equip.setInjetor(rs.getString("injetor"));
                equip.setForno(rs.getString("forno"));
                equip.setDetector(rs.getString("detector"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectEquipamentoByName(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_cromatografo.*, tb_setor.setor "
                    + "FROM tb_cromatografo "
                    + "INNER JOIN tb_setor "
                    + "ON tb_cromatografo.setor_id = tb_setor.setor_id  "
                    + "WHERE system_name = ?");
            stmt.setString(1, equip.getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                equip.setCromatografo_id(rs.getInt("cromatografo_id"));
                equip.setNode(rs.getString("node"));
                equip.setModelo(rs.getString("modelo"));
                equip.setTipo(rs.getString("tipo"));
                setor.setSetor(rs.getString("setor"));
                equip.setSetor(setor);
                equip.setControladora(rs.getString("controladora"));
                equip.setDegaseificador(rs.getString("degaseificador"));
                equip.setBomba(rs.getString("bomba"));
                equip.setInjetor(rs.getString("injetor"));
                equip.setForno(rs.getString("forno"));
                equip.setDetector(rs.getString("detector"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteEquipamento(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_cromatografo WHERE cromatografo_id = ?");
            stmt.setInt(1, equip.getCromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    //tb_log_cromatografo
    public void createLogCromatografo(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_cromatografo "
                    + "(analise_status_id, log_system_name, coluna_id, fase_movel_id, "
                    + "data_inicio, descricao, user_name, data_registro, "
                    + "campanha, metodos, obs_log) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, equip.getAnalise().getAnalise_status_id());
            stmt.setString(2, equip.getSystem_name());
            stmt.setInt(3, equip.getColuna().getColuna_id());
            stmt.setInt(4, equip.getFasemovel().getFase_movel_id());
            stmt.setTimestamp(5, equip.getData_inicio());
            stmt.setString(6, equip.getDescricao());
            stmt.setString(7, equip.getUsuario().getUser());
            stmt.setTimestamp(8, equip.getData_registro());
            stmt.setString(9, equip.getCampanha());
            stmt.setString(10, equip.getMetodos());
            stmt.setString(11, equip.getObs_log());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
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
            stmt = conn.prepareStatement("SELECT tb_log_cromatografo.log_cromatografo_id, "
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
                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC LIMIT 500 ");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cromatografo equip = new Cromatografo();
                Analise anls = new Analise();
                Usuario user = new Usuario();
                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                equip.setData_inicio(rs.getTimestamp("data_inicio"));
                equip.setData_fim(rs.getTimestamp("data_fim"));
                anls.setAnalise_status(rs.getString("analise_status"));
                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
                anls.setCor(rs.getString("cor"));
                equip.setAnalise(anls);
                equip.setData_registro(rs.getTimestamp("data_registro"));
                equip.setDescricao(rs.getString("descricao"));
                user.setUser(rs.getString("user_name"));
                equip.setUsuario(user);
                equipamentos.add(equip);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return equipamentos;
    }

    public void updateLogCromatografo(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_cromatografo SET "
                    + "data_fim = ?, data_registro = ?, coluna_id = ?, fase_movel_id = ? "
                    + "WHERE log_cromatografo_id = ?");
            stmt.setTimestamp(1, equip.getData_fim());
            stmt.setTimestamp(2, equip.getData_registro());
            stmt.setInt(3, equip.getColuna().getColuna_id());
            stmt.setInt(4, equip.getFasemovel().getFase_movel_id());
            stmt.setInt(5, equip.getLog_cromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectLogCromatografo(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_log_cromatografo.log_cromatografo_id, "
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
                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC LIMIT 1 ");
            stmt.setString(1, equip.getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                Usuario user = new Usuario();
                Coluna col = new Coluna();
                FaseMovel fase = new FaseMovel();
                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                equip.setData_inicio(rs.getTimestamp("data_inicio"));
                equip.setData_fim(rs.getTimestamp("data_fim"));
                anls.setAnalise_status(rs.getString("analise_status"));
                anls.setAnalise_status_id(rs.getInt("analise_status_id"));
                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
                anls.setCor(rs.getString("cor"));
                equip.setAnalise(anls);
                col.setColuna_id(rs.getInt("coluna_id"));
                equip.setColuna(col);
                fase.setFase_movel_id(rs.getInt("fase_movel_id"));
                equip.setFasemovel(fase);
                equip.setData_registro(rs.getTimestamp("data_registro"));
                equip.setDescricao(rs.getString("descricao"));
                user.setUser(rs.getString("user_name"));
                equip.setUsuario(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectLogCampamhaCromatografo(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_log_cromatografo.log_cromatografo_id, "
                    + "tb_log_cromatografo.campanha, tb_log_cromatografo.metodos, tb_log_cromatografo.obs_log "
                    + "FROM tb_log_cromatografo "
                    + "LEFT JOIN tb_cromatografo "
                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name "
                    + "WHERE tb_cromatografo.system_name = ? AND tb_log_cromatografo.analise_status_id = 1 "
                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC LIMIT 1 ");
            stmt.setString(1, equip.getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                equip.setCampanha(rs.getString("campanha"));
                equip.setMetodos(rs.getString("metodos"));
                equip.setObs_log(rs.getString("obs_log"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }
    
    public void selectLogCorridasCromatografo(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_log_cromatografo.log_cromatografo_id, "
                    + "tb_log_cromatografo.campanha, tb_log_cromatografo.metodos, tb_log_cromatografo.obs_log "
                    + "FROM tb_log_cromatografo "
                    + "LEFT JOIN tb_cromatografo "
                    + "ON tb_log_cromatografo.log_system_name = tb_cromatografo.system_name "
                    + "WHERE tb_cromatografo.system_name = ? AND tb_log_cromatografo.analise_status_id = 1 "
                    + "ORDER BY tb_log_cromatografo.log_cromatografo_id DESC LIMIT 1 ");
            stmt.setString(1, equip.getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                equip.setLog_cromatografo_id(rs.getInt("log_cromatografo_id"));
                equip.setCampanha(rs.getString("campanha"));
                equip.setMetodos(rs.getString("metodos"));
                equip.setObs_log(rs.getString("obs_log"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteLogCromatografo(Cromatografo equip) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_log_cromatografo WHERE log_cromatografo_id = ?");
            stmt.setInt(1, equip.getLog_cromatografo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

}
