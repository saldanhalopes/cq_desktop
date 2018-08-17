/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Autonomia;
import Classes.Modelo.Material;
import Classes.Modelo.Metodologia;
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
public class MaterialDAO {

    public void createMaterial(Material prod) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_material "
                    + "(cod_material, material, tipo, metodo_id) "
                    + "VALUES (?,?,?,?)");
            stmt.setString(1, prod.getCod_material());
            stmt.setString(2, prod.getNome_material());
            stmt.setString(3, prod.getTipo());
            stmt.setInt(4, prod.getMetodo().getMetodo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createMaterial");
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
            stmt = conn.prepareStatement("SELECT tb_material.material_id, tb_material.cod_material, "
                    + "tb_material.material, tb_material.tipo, "
                    + "tb_metodologia.metodo, tb_metodologia.cod_metodo, tb_material.metodo_id "
                    + "FROM tb_material "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_metodologia.metodo_id = tb_material.metodo_id");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                prod.setMaterial_id(rs.getInt("material_id"));
                prod.setCod_material(rs.getString("cod_material"));
                prod.setNome_material(rs.getString("material"));
                prod.setTipo(rs.getString("tipo"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setMetodo(rs.getString("metodo"));
                prod.setMetodo(mtd);
                produtos.add(prod);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readMaterial");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return produtos;
    }

    public List<Material> readMaterial(String sql) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Material> produtos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_material.material_id, tb_material.cod_material, "
                    + "tb_material.material, tb_material.tipo, "
                    + "tb_metodologia.metodo, tb_metodologia.cod_metodo, "
                    + "tb_material.metodo_id, tb_autonomia.autonomia, tb_autonomia.estoque_expedicao "
                    + "FROM tb_material "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_metodologia.metodo_id = tb_material.metodo_id "
                    + "LEFT JOIN tb_autonomia "
                    + "ON tb_autonomia.cod_material = tb_material.cod_material "
                    + "WHERE 1=1 " + sql
                    + "ORDER BY tb_autonomia.autonomia IS NULL ASC, tb_autonomia.autonomia ASC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                Autonomia auto = new Autonomia();
                prod.setMaterial_id(rs.getInt("material_id"));
                prod.setCod_material(rs.getString("cod_material"));
                prod.setNome_material(rs.getString("material"));
                prod.setTipo(rs.getString("tipo"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setMetodo(rs.getString("metodo"));
                prod.setMetodo(mtd);
                auto.setAutonomia(rs.getDouble("autonomia"));
                auto.setEstoque_expedicao(rs.getInt("estoque_expedicao"));
                prod.setAutonomia(auto);
                produtos.add(prod);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readMaterial");
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
            stmt = conn.prepareStatement("Select tb_material.*, tb_metodologia.metodo_id "
                    + "FROM tb_material "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_metodologia.metodo_id = tb_material.metodo_id "
                    + "Where tb_material.metodo_id = ?");
            stmt.setInt(1, mtd.getMetodo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Material mat = new Material();
                mat.setMaterial_id(rs.getInt("material_id"));
                mat.setCod_material(rs.getString("cod_material"));
                mat.setNome_material(rs.getString("material"));
                mat.setTipo(rs.getString("tipo"));
                materiais.add(mat);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readMaterial");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return materiais;
    }

    public void updateMaterial(Material prod) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_material SET "
                    + "cod_material = ?, material = ?, tipo = ?, metodo_id = ? "
                    + "WHERE material_id = ?");
            stmt.setString(1, prod.getCod_material());
            stmt.setString(2, prod.getNome_material());
            stmt.setString(3, prod.getTipo());
            stmt.setInt(4, prod.getMetodo().getMetodo_id());
            stmt.setInt(5, prod.getMaterial_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateMaterial");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectMaterial(Material prod) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select tb_material.*, "
                    + "tb_metodologia.cod_metodo, tb_metodologia.metodo "
                    + "FROM tb_material "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_metodologia.metodo_id = tb_material.metodo_id "
                    + "WHERE material_id = ?");
            stmt.setInt(1, prod.getMaterial_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                prod.setCod_material(rs.getString("cod_material"));
                prod.setNome_material(rs.getString("material"));
                prod.setTipo(rs.getString("tipo"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setMetodo(rs.getString("metodo"));
                prod.setMetodo(mtd);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectMaterial");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteMaterial(Material prod) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_material "
                    + "WHERE material_id = ?");
            stmt.setInt(1, prod.getMaterial_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteMaterial");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
