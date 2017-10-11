/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.ConnectionFactory;
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
public class MetodologiaDAO {

    public void createMetodologia(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_metodologia "
                    + "(cod_metodo, metodo, versao, categoria, link) VALUES (?,?,?,?,?)");
            stmt.setString(1, mtd.getCod_metodo());
            stmt.setString(2, mtd.getMetodo());
            stmt.setString(3, mtd.getVersao());
            stmt.setString(4, mtd.getCategoria());
            stmt.setString(5, mtd.getLink());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Metodologia> read() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Metodologia> metodologias = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_metodologia");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                mtd.setVersao(rs.getString("versao"));
                mtd.setCategoria(rs.getString("categoria"));
                mtd.setLink(rs.getString("link"));
                metodologias.add(mtd);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return metodologias;
    }

    public List<Metodologia> readMetodo() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Metodologia> metodos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT Distinct cod_metodo FROM tb_metodologia");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                metodos.add(mtd);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return metodos;
    }

    public void updateMetodologia(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_metodologia SET "
                    + "cod_metodo = ?, metodo = ?, versao = ?, categoria = ?, link = ? "
                    + "WHERE metodo_id = ?");
            stmt.setString(1, mtd.getCod_metodo());
            stmt.setString(2, mtd.getMetodo());
            stmt.setString(3, mtd.getVersao());
            stmt.setString(4, mtd.getCategoria());
            stmt.setString(5, mtd.getLink());
            stmt.setInt(6, mtd.getMetodo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectMetodologia(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_metodologia WHERE metodo_id = ?");
            stmt.setInt(1, mtd.getMetodo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                mtd.setVersao(rs.getString("versao"));
                mtd.setCategoria(rs.getString("categoria"));
                mtd.setLink(rs.getString("link"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public String selectMetodologiaById(String metodo_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String metodo = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_metodologia.metodo FROM tb_metodologia WHERE metodo_id = ?");
            stmt.setString(1, metodo_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                metodo = rs.getString("metodo");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return metodo;
    }
    
    public String selectMetodologiaLinkById(String metodo_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String link = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_metodologia.link FROM tb_metodologia WHERE metodo_id = ?");
            stmt.setString(1, metodo_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                link = rs.getString("link");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return link;
    }

    public void deleteMetodologia(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_metodologia WHERE metodo_id = ?");
            stmt.setInt(1, mtd.getMetodo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
