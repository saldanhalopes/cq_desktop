/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.ConnectionFactory;
import model.Analise;
import model.Material;
import model.Lote;
import model.Metodologia;
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
public class LoteDAO {

    public void createLote(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_lote "
                    + "(material_id, lote_produto, analise_finalidade_id, "
                    + "data_entrada, data_prevista, lote_obs) "
                    + "VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, lot.getProduto().getMaterial_id());
            stmt.setString(2, lot.getLote_produto());
            stmt.setInt(3, lot.getAnalise().getAnalise_finalidade_id());
            stmt.setTimestamp(4, lot.getData_entrada());
            stmt.setTimestamp(5, lot.getData_prevista());
            stmt.setString(6, lot.getLote_obs());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Lote> readCadastroProduto(String limit) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Lote> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_lote.lote_id, tb_material.material, "
                    + "tb_analise_finalidade.analise_finalidade_id, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, tb_lote.lote_produto, "
                    + "tb_lote.data_entrada, tb_lote.data_prevista, "
                    + "tb_lote.lote_status, tb_lote.lote_obs "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "ORDER BY tb_lote.lote_id DESC LIMIT ?;");
            stmt.setString(1, limit);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                lot.setLote_id(rs.getInt("lote_id"));
                Material prod = new Material();
                prod.setMaterial(rs.getString("material"));
                lot.setProduto(prod);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                Analise anals = new Analise();
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                lot.setAnalise(anals);
                lotes.add(lot);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotes;
    }

    public void updateLote(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_lote SET "
                    + "material_id = ?, analise_finalidade_id = ?, lote_produto = ?, "
                    + "data_entrada = ?, data_prevista = ?, lote_obs = ? "
                    + "WHERE lote_id = ?");
            stmt.setInt(1, lot.getProduto().getMaterial_id());
            stmt.setInt(2, lot.getAnalise().getAnalise_finalidade_id());
            stmt.setString(3, lot.getLote_produto());
            stmt.setTimestamp(4, lot.getData_entrada());
            stmt.setTimestamp(5, lot.getData_prevista());
            stmt.setString(6, lot.getLote_obs());
            stmt.setInt(7, lot.getLote_id());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateLoteStatus(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_lote SET "
                    + "lote_status = ? "
                    + "WHERE lote_id = ?");
            stmt.setString(1, lot.getLote_status());
            stmt.setInt(2, lot.getLote_id());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectLote(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_lote.lote_id, tb_lote.lote_produto, "
                    + "tb_metodologia.cod_metodo, tb_metodologia.metodo_id, "
                    + "tb_material.cod_material, tb_material.material, "
                    + "tb_material.tipo, tb_analise_finalidade.analise_finalidade, tb_lote.data_entrada, "
                    + "tb_lote.lote_status, tb_lote.lote_obs FROM tb_lote "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "WHERE lote_id = ?");
            stmt.setInt(1, lot.getLote_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                lot.setLote_id(rs.getInt("lote_id"));
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                Metodologia mtd = new Metodologia();
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                lot.setMetodo(mtd);
                Material prod = new Material();
                prod.setCod_material(rs.getString("cod_material"));
                prod.setMaterial(rs.getString("material"));
                prod.setTipo(rs.getString("tipo"));
                lot.setProduto(prod);
                Analise anals = new Analise();
                anals.setAnalise_finalidade(rs.getString("analise_finalidade"));
                lot.setAnalise(anals);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteLote(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_lote WHERE lote_id = ?");
            stmt.setInt(1, lot.getLote_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
