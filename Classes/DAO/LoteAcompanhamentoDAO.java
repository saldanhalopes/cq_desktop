/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Acompanhamento;
import Classes.Modelo.Material;
import Classes.Modelo.Lote;
import Classes.Modelo.LoteAcompanhamento;
import Classes.Modelo.Metodologia;
import Classes.Modelo.Usuario;
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
public class LoteAcompanhamentoDAO {

    public void createLoteAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_lote_acompanhamento "
                    + "(lote_id, registro_acompanhamento_id, "
                    + "data_registro, user_registro, codigo_barras) "
                    + "VALUES (?,?,?,?,?)");
            stmt.setInt(1, acomp.getLote().getLote_id());
            stmt.setInt(2, acomp.getRegistro_acompanhamento_id());
            stmt.setTimestamp(3, acomp.getData_registro());
            stmt.setString(4, acomp.getUser_registro().getUser());
            stmt.setString(5, acomp.getCodigo_barras());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createLoteAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<LoteAcompanhamento> readLoteAcompanhamento() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<LoteAcompanhamento> lotacomps = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_lote_acompanhamento.lote_acompanhamento_id, "
                    + "tb_lote_acompanhamento.lote_id, tb_lote_acompanhamento.data_registro, "
                    + "tb_lote_acompanhamento.user_registro, tb_lote_acompanhamento.codigo_barras, "
                    + "tb_lote_acompanhamento.data_retirada, tb_lote_acompanhamento.user_retirada, "
                    + "tb_material.material, tb_material.cod_material, tb_metodologia.metodo_id, "
                    + "tb_metodologia.cod_metodo, tb_metodologia.metodo, tb_lote.data_entrada, "
                    + "tb_registro_acompanhamento.sigla_registro_acompanhamento, tb_lote.lote_produto "
                    + "FROM tb_lote_acompanhamento "
                    + "INNER JOIN tb_registro_acompanhamento "
                    + "ON tb_lote_acompanhamento.registro_acompanhamento_id = tb_registro_acompanhamento.registro_acompanhamento_id "
                    + "INNER JOIN tb_lote "
                    + "ON tb_lote_acompanhamento.lote_id = tb_lote.lote_id "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "WHERE tb_lote_acompanhamento.status IS NULL "
                    + "ORDER BY tb_lote_acompanhamento.lote_acompanhamento_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                LoteAcompanhamento lotacomp = new LoteAcompanhamento();
                Lote lot = new Lote();
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                Usuario user_registro = new Usuario();
                Usuario user_retirada = new Usuario();
                Acompanhamento acomp = new Acompanhamento();
                lotacomp.setLote_acompanhamento_id(rs.getInt("lote_acompanhamento_id"));
                lot.setLote_id(rs.getInt("lote_id"));
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lotacomp.setLote(lot);
                prod.setNome_material(rs.getString("material"));
                prod.setCod_material(rs.getString("cod_material"));
                lotacomp.setMaterial(prod);
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                lotacomp.setMetodologia(mtd);
                user_registro.setUser(rs.getString("user_registro"));
                lotacomp.setUser_registro(user_registro);
                user_retirada.setUser(rs.getString("user_retirada"));
                lotacomp.setUser_retirada(user_retirada);
                lotacomp.setData_registro(rs.getTimestamp("data_registro"));
                lotacomp.setData_retirada(rs.getTimestamp("data_retirada"));
                lotacomp.setCodigo_barras(rs.getString("codigo_barras"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                lotacomp.setAcompanhamento(acomp);
                lotacomps.add(lotacomp);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readLoteAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotacomps;
    }

    public void updateRetiradaLoteAcompanhamento(LoteAcompanhamento lotacomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_lote_acompanhamento SET "
                    + "data_retirada = ?, user_retirada = ? "
                    + "WHERE lote_acompanhamento_id = ?");
            stmt.setTimestamp(1, lotacomp.getData_retirada());
            stmt.setString(2, lotacomp.getUser_retirada().getUser());
            stmt.setInt(3, lotacomp.getLote_acompanhamento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateRetiradaLoteAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
    
    public void updateDevolverLoteAcompanhamento(LoteAcompanhamento lotacomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_lote_acompanhamento SET "
                    + "data_retirada = NULL, user_retirada = NULL "
                    + "WHERE lote_acompanhamento_id = ?");
            stmt.setInt(1, lotacomp.getLote_acompanhamento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateDevolverLoteAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

}
