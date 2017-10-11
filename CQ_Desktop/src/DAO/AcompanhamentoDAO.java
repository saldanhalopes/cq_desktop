/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.ConnectionFactory;
import model.Acompanhamento;
import model.Metodologia;
import model.Setor;
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
public class AcompanhamentoDAO {

    public void createAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_registro_acompanhamento "
                    + "(metodo_id, setor_id, cod_registro_acompanhamento, "
                    + "registro_acompanhamento, sigla_registro_acompanhamento, "
                    + "categoria, versao) "
                    + "VALUES (?,?,?,?,?,?,?)");
            stmt.setInt(1, acomp.getMetodo().getMetodo_id());
            stmt.setInt(2, acomp.getSetor().getSetor_id());
            stmt.setString(3, acomp.getCod_registro_acompanhamento());
            stmt.setString(4, acomp.getRegistro_acompanhamento());
            stmt.setString(5, acomp.getSigla_registro_acompanhamento());
            stmt.setString(6, acomp.getCategoria());
            stmt.setString(7, acomp.getVersao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Acompanhamento> readAcompanhamento() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Acompanhamento> acomps = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_registro_acompanhamento ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Acompanhamento acomp = new Acompanhamento();
                Metodologia mtd = new Metodologia();
                Setor setor = new Setor();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                setor.setSetor_id(rs.getInt("setor_id"));
                acomp.setSetor(setor);
                acomp.setMetodo(mtd);
                acomp.setCod_registro_acompanhamento(rs.getString("cod_registro_acompanhamento"));
                acomp.setRegistro_acompanhamento(rs.getString("registro_acompanhamento"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                acomp.setCategoria(rs.getString("categoria"));
                acomp.setVersao(rs.getString("versao"));
                acomps.add(acomp);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return acomps;
    }

    public List<Acompanhamento> readAcompanhamento(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Acompanhamento> acomps = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * From tb_registro_acompanhamento WHERE metodo_id = ? "
                    + "ORDER BY cod_registro_acompanhamento ");
            stmt.setInt(1, mtd.getMetodo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Acompanhamento acomp = new Acompanhamento();
                Setor setor = new Setor();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                setor.setSetor_id(rs.getInt("setor_id"));
                acomp.setSetor(setor);
                acomp.setCod_registro_acompanhamento(rs.getString("cod_registro_acompanhamento"));
                acomp.setRegistro_acompanhamento(rs.getString("registro_acompanhamento"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                acomp.setCategoria(rs.getString("categoria"));
                acomp.setVersao(rs.getString("versao"));
                acomps.add(acomp);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return acomps;
    }

    public void updateAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_registro_acompanhamento SET "
                    + "metodo_id = ?, setor_id = ?, cod_registro_acompanhamento = ? "
                    + "registro_acompanhamento = ?, sigla_registro_acompanhamento = ? "
                    + "categoria = ?, versao = ? "
                    + "WHERE registro_acompanhamento_id = ?");
            stmt.setInt(1, acomp.getMetodo().getMetodo_id());
            stmt.setInt(2, acomp.getSetor().getSetor_id());
            stmt.setString(3, acomp.getCod_registro_acompanhamento());
            stmt.setString(4, acomp.getRegistro_acompanhamento());
            stmt.setString(5, acomp.getSigla_registro_acompanhamento());
            stmt.setString(6, acomp.getCategoria());
            stmt.setString(7, acomp.getVersao());
            stmt.setInt(8, acomp.getRegistro_acompanhamento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_registro_acompanhamento WHERE registro_acompanhamento_id = ?");
            stmt.setInt(1, acomp.getRegistro_acompanhamento_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Setor setor = new Setor();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                setor.setSetor_id(rs.getInt("setor_id"));
                acomp.setSetor(setor);
                acomp.setMetodo(mtd);
                acomp.setCod_registro_acompanhamento(rs.getString("cod_registro_acompanhamento"));
                acomp.setRegistro_acompanhamento(rs.getString("registro_acompanhamento"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                acomp.setCategoria(rs.getString("categoria"));
                acomp.setVersao(rs.getString("versao"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectAcompanhamento(Acompanhamento acomp, Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_registro_acompanhamento WHERE metodo_id = ? "
                    + "ORDER BY cod_registro_acompanhamento ");
            stmt.setInt(1, mtd.getMetodo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                setor.setSetor_id(rs.getInt("setor_id"));
                acomp.setSetor(setor);
                acomp.setMetodo(mtd);
                acomp.setCod_registro_acompanhamento(rs.getString("cod_registro_acompanhamento"));
                acomp.setRegistro_acompanhamento(rs.getString("registro_acompanhamento"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                acomp.setCategoria(rs.getString("categoria"));
                acomp.setVersao(rs.getString("versao"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_registro_acompanhamento WHERE registro_acompanhamento_id = ?");
            stmt.setInt(1, acomp.getRegistro_acompanhamento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
