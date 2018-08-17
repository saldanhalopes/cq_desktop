/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Amostra;
import Classes.Modelo.Analise;
import Classes.Modelo.Lote;
import Classes.Modelo.Metodologia;
import Classes.Modelo.Material;
import Classes.Modelo.Setor;
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
public class AmostraDAO {

    //tb_amostra
    public void createAmostra(Amostra ams) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_amostra "
                    + "(lote_id, metodologia_analise_id, amostra_status_id) "
                    + "VALUES (?,?,?)");
            stmt.setInt(1, ams.getLote().getLote_id());
            stmt.setInt(2, ams.getAnalise().getMetodologia_analise_id());
            stmt.setInt(3, ams.getAmostra_status_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Amostra> readAmostra() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Amostra> amostras = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select tb_amostra.amostra_id, tb_lote.lote_produto, "
                    + "tb_lote.data_entrada, tb_metodologia.cod_metodo, tb_material.cod_material, "
                    + "tb_material.material, tb_analise.analise, tb_setor.sigla_setor, "
                    + "tb_amostra_status.amostra_status "
                    + "From tb_amostra "
                    + "Inner JOIN tb_lote "
                    + "ON tb_amostra.lote_id = tb_lote.lote_id "
                    + "Inner JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "Inner JOIN tb_metodologia_analise "
                    + "ON tb_amostra.metodologia_analise_id = tb_metodologia_analise.metodologia_analise_id "
                    + "Inner JOIN tb_metodologia "
                    + "ON tb_metodologia_analise.metodo_id = tb_metodologia.metodo_id "
                    + "Inner JOIN tb_analise "
                    + "ON tb_metodologia_analise.analise_id = tb_analise.analise_id "
                    + "Inner JOIN tb_setor "
                    + "ON tb_metodologia_analise.setor_id = tb_setor.setor_id "
                    + "Inner JOIN tb_amostra_status "
                    + "ON tb_amostra.amostra_status_id = tb_amostra_status.amostra_status_id");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Amostra ams = new Amostra();
                Analise anse = new Analise();
                Lote lot = new Lote();
                Metodologia mtd = new Metodologia();
                Material prod = new Material();
                Setor setor = new Setor();
                ams.setAmostra_id(rs.getInt("amostra_id"));
                ams.setQuantidade_amostra(rs.getString("quantidade_amostra"));
                ams.setObs_amostra(rs.getString("obs_amostra"));
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                ams.setLote(lot);
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                ams.setMetodo(mtd);
                prod.setCod_material(rs.getString("cod_material"));
                prod.setNome_material(rs.getString("material"));
                ams.setProduto(prod);
                setor.setSigla_setor(rs.getString("sigla_setor"));
                ams.setSetor(setor);
                anse.setAnalise(rs.getString("amostra"));
                ams.setAnalise(anse);
                ams.setAmostra_status(rs.getString("amostra_status"));
                amostras.add(ams);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return amostras;
    }

    public void updateAmostra(Amostra ams) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_amostra SET lote_id = ?, "
                    + "metodologia_analise_id = ?, amostra_status_id = ? "
                    + "WHERE amostra_id = ?");
            stmt.setInt(1, ams.getLote().getLote_id());
            stmt.setInt(2, ams.getAnalise().getMetodologia_analise_id());
            stmt.setInt(3, ams.getAmostra_status_id());
            stmt.setInt(4, ams.getAmostra_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAmostra(Amostra ams) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select tb_amostra.amostra_id, tb_amostra.lote_id, "
                    + "tb_amostra_status.amostra_status "
                    + "FROM tb_amostra "
                    + "Inner JOIN tb_amostra_status "
                    + "ON tb_amostra.amostra_status_id = tb_amostra_status.amostra_status_id "
                    + "WHERE tb_amostra.amostra_id = ?");
            stmt.setInt(1, ams.getAmostra_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                ams.setAmostra_id(rs.getInt("amostra_id"));
                lot.setLote_id(rs.getInt("lote_id"));
                ams.setLote(lot);
                ams.setAmostra_status(rs.getString("amostra_status"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteAmostra(Amostra ams) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_amostra WHERE amostra_id = ?");
            stmt.setInt(1, ams.getAmostra_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    //tb_amostra_status
    public void createAmostraStatus(Amostra anse) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_amostra_status "
                    + "(amostra_status, sigla_amostra_status, descricao_amostra_status) VALUES (?,?,?)");
            stmt.setString(1, anse.getAmostra_status());
            stmt.setString(2, anse.getSigla_amostra_status());
            stmt.setString(3, anse.getDescricao_amostra_status());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAmostraStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Amostra> readAmostraStatus() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Amostra> anseamentos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_amostra_status");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Amostra anse = new Amostra();
                anse.setAmostra_status_id(rs.getInt("amostra_status_id"));
                anse.setAmostra_status(rs.getString("amostra_status"));
                anse.setSigla_amostra_status(rs.getString("sigla_amostra_status"));
                anse.setDescricao_amostra_status(rs.getString("descricao_amostra_status"));
                anseamentos.add(anse);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAmostraStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return anseamentos;
    }

    public void updateAmostraStatus(Amostra anse) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_amostra_status SET "
                    + "amostra_status = ?, sigla_amostra_status = ?, descricao_amostra_status = ? "
                    + "WHERE amostra_status_id = ?");
            stmt.setString(1, anse.getAmostra_status());
            stmt.setString(2, anse.getSigla_amostra_status());
            stmt.setString(3, anse.getDescricao_amostra_status());
            stmt.setInt(4, anse.getAmostra_status_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAmostraStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAmostraStatus(Amostra ams) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_amostra_status WHERE amostra_status_id = ?");
            stmt.setInt(1, ams.getAmostra_status_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                ams.setAmostra_status(rs.getString("amostra_status"));
                ams.setSigla_amostra_status(rs.getString("sigla_amostra_status"));
                ams.setDescricao_amostra_status(rs.getString("descricao_amostra_status"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAmostraStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteAmostraStatus(Amostra anse) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_amostra_status WHERE amostra_status_id = ?");
            stmt.setInt(1, anse.getAmostra_status_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAmostraStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

}
