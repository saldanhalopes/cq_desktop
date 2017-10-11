/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.ConnectionFactory;
import model.Material;
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
public class MaterialDAO {

    public void createProduto(Material prod) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_material "
                    + "(cod_material, material, tipo, metodo_id) "
                    + "VALUES (?,?,?,?)");
            stmt.setString(1, prod.getCod_material());
            stmt.setString(2, prod.getMaterial());
            stmt.setString(3, prod.getTipo());
            stmt.setInt(4, prod.getMetodo().getMetodo_id());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Material> readMaterial() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Material> produtos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select tb_material.material_id, tb_material.cod_material, "
                    + "tb_material.material, tb_material.tipo, tb_metodologia.cod_metodo, tb_material.metodo_id "
                    + "from tb_material inner join tb_metodologia "
                    + "on tb_metodologia.metodo_id = tb_material.metodo_id");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                prod.setMaterial_id(rs.getInt("material_id"));
                prod.setCod_material(rs.getString("cod_material"));
                prod.setMaterial(rs.getString("material"));
                prod.setTipo(rs.getString("tipo"));
                prod.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                prod.setMetodo(mtd);
                produtos.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return produtos;
    }
    
    public List<Material> readMaterial(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Material> materiais = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select tb_material.material_id, "
                    + "tb_material.cod_material, tb_material.material, tb_material.tipo "
                    + "from tb_material inner join tb_metodologia "
                    + "on tb_metodologia.metodo_id = tb_material.metodo_id "
                    + "Where tb_material.metodo_id = ?");
            stmt.setInt(1, mtd.getMetodo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Material mat = new Material();
                mat.setMaterial_id(rs.getInt("material_id"));
                mat.setCod_material(rs.getString("cod_material"));
                mat.setMaterial(rs.getString("material"));
                mat.setTipo(rs.getString("tipo"));
                materiais.add(mat);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return materiais;
    }

    public void updateProduto(Material prod) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_material SET "
                    + "cod_material = ?, material = ?, tipo = ?, metodo_id = ? "
                    + "WHERE material_id = ?");
            stmt.setString(1, prod.getCod_material());
            stmt.setString(2, prod.getMaterial());
            stmt.setString(3, prod.getTipo());
            stmt.setInt(4, prod.getMetodo().getMetodo_id());
            stmt.setInt(5, prod.getMaterial_id());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectProduto(Material prod) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select tb_material.material_id, tb_material.cod_material, "
                    + "tb_material.material, tb_material.tipo, tb_metodologia.cod_metodo "
                    + "from tb_material inner join tb_metodologia "
                    + "on tb_metodologia.metodo_id = tb_material.metodo_id "
                    + "WHERE material_id = ?");
            stmt.setInt(1, prod.getMaterial_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                prod.setCod_material(rs.getString("cod_material"));
                prod.setMaterial(rs.getString("material"));
                prod.setTipo(rs.getString("tipo"));
                prod.setCod_metodo(rs.getString("cod_metodo"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteProduto(Material prod) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_material WHERE material_id = ?");
            stmt.setInt(1, prod.getMaterial_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
