/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Analise;
import Classes.Modelo.Material;
import Classes.Modelo.Lote;
import Classes.Modelo.Metodologia;
import Classes.Modelo.Usuario;
import Classes.Util.Reports;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 *
 * @author rafael.lopes
 */
public class LoteDAO {

    public int createLote(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        int generatedKey = -1;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_lote "
                    + "(material_id, lote_produto, analise_finalidade_id, "
                    + "data_entrada, data_prevista, lote_obs,"
                    + "data_registro, user_name) "
                    + "VALUES (?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, lot.getProduto().getMaterial_id());
            stmt.setString(2, lot.getLote_produto());
            stmt.setInt(3, lot.getAnalise().getAnalise_finalidade_id());
            stmt.setTimestamp(4, lot.getData_entrada());
            stmt.setTimestamp(5, lot.getData_prevista());
            stmt.setString(6, lot.getLote_obs());
            stmt.setTimestamp(7, lot.getData_registro());
            stmt.setString(8, lot.getUser().getUser());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedKey = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
        return generatedKey;
    }

    public void createLoteOutros(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_lote "
                    + "(material_id, lote_produto, analise_finalidade_id, "
                    + "data_entrada, data_prevista, lote_obs,"
                    + "data_registro, user_name, metodologia_id, etapa) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, lot.getProduto().getMaterial_id());
            stmt.setString(2, lot.getLote_produto());
            stmt.setInt(3, lot.getAnalise().getAnalise_finalidade_id());
            stmt.setTimestamp(4, lot.getData_entrada());
            stmt.setTimestamp(5, lot.getData_prevista());
            stmt.setString(6, lot.getLote_obs());
            stmt.setTimestamp(7, lot.getData_registro());
            stmt.setString(8, lot.getUser().getUser());
            stmt.setInt(9, lot.getMetodo().getMetodo_id());
            stmt.setString(10, lot.getEtapa());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createLoteOutros");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Lote> readLote() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Lote> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_lote.lote_id, tb_material.material, tb_material.cod_material, "
                    + "tb_analise_finalidade.analise_finalidade_id, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, tb_lote.lote_produto, "
                    + "tb_lote.data_entrada, tb_lote.data_prevista, "
                    + "tb_lote.lote_status, tb_lote.lote_obs, "
                    + "tb_metodologia.metodo_id, tb_metodologia.cod_metodo, "
                    + "tb_metodologia.metodo, tb_lote.tamanho_lote "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "ORDER BY tb_lote.lote_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                Analise anals = new Analise();
                lot.setLote_id(rs.getInt("lote_id"));
                prod.setCod_material(rs.getString("cod_material"));
                prod.setNome_material(rs.getString("material"));
                lot.setProduto(prod);
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                lot.setAnalise(anals);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lotes.add(lot);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotes;
    }

    public List<Lote> readTodosProdutosAtivos() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Lote> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_lote.*, tb_material.material, tb_material.cod_material, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, "
                    + "tb_metodologia.metodo_id, tb_metodologia.cod_metodo, tb_metodologia.metodo "
                    + "FROM tb_lote "
                    + "LEFT JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "LEFT JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "OR tb_lote.metodologia_id = tb_metodologia.metodo_id "
                    + "WHERE tb_lote.lote_status IS NULL "
                    + "OR tb_lote.lote_status <> 'Finalizado' "
                    + "ORDER BY tb_lote.lote_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                Analise anals = new Analise();
                Usuario user = new Usuario();
                lot.setLote_id(rs.getInt("lote_id"));
                prod.setNome_material(rs.getString("material"));
                prod.setCod_material(rs.getString("cod_material"));
                lot.setProduto(prod);
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                lot.setAnalise(anals);
                user.setUser(rs.getString("user_name"));
                lot.setUser(user);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lotes.add(lot);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readTodosProdutos");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotes;
    }

    public List<Lote> readCadastroProduto(Integer limit) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Lote> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(?) tb_lote.*, tb_material.material, tb_material.cod_material, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, "
                    + "tb_metodologia.metodo_id, tb_metodologia.cod_metodo, tb_metodologia.metodo "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "WHERE tb_lote.analise_finalidade_id < 4 "
                    + "AND (tb_lote.lote_status IS NULL OR tb_lote.lote_status <> 'Finalizado') "
                    + "ORDER BY tb_lote.lote_id DESC ");
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                Analise anals = new Analise();
                Usuario user = new Usuario();
                lot.setLote_id(rs.getInt("lote_id"));
                prod.setNome_material(rs.getString("material"));
                prod.setCod_material(rs.getString("cod_material"));
                lot.setProduto(prod);
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                lot.setAnalise(anals);
                user.setUser(rs.getString("user_name"));
                lot.setUser(user);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lotes.add(lot);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readCadastroProduto");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotes;
    }

    public List<Lote> readCadastroValidacaoProcesso(Integer limit) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Lote> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(?) tb_lote.*, tb_material.material, tb_material.cod_material, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, "
                    + "tb_metodologia.metodo_id, tb_metodologia.cod_metodo, tb_metodologia.metodo "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "WHERE tb_lote.analise_finalidade_id = 6 "
                    + "ORDER BY tb_lote.lote_id DESC ");
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                Analise anals = new Analise();
                Usuario user = new Usuario();
                lot.setLote_id(rs.getInt("lote_id"));
                prod.setNome_material(rs.getString("material"));
                prod.setCod_material(rs.getString("cod_material"));
                lot.setProduto(prod);
                mtd.setMetodo_id(rs.getInt("metodologia_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                lot.setAnalise(anals);
                user.setUser(rs.getString("user_name"));
                lot.setUser(user);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lot.setEtapa(rs.getString("etapa"));
                lotes.add(lot);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readCadastroValidacaoProcesso");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotes;
    }

    public List<Lote> readCadastroDevolucao(Integer limit) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Lote> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(?) tb_lote.*, tb_material.material, tb_material.cod_material, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, "
                    + "tb_metodologia.metodo_id, tb_metodologia.cod_metodo, tb_metodologia.metodo, "
                    + "(SELECT tb_metodologia.cod_metodo FROM tb_metodologia "
                    + "WHERE tb_metodologia.metodo_id = tb_lote.metodologia_id) as cod_metodo2 "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "WHERE tb_lote.analise_finalidade_id = 9 "
                    + "ORDER BY tb_lote.lote_id DESC ");
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                Material prod = new Material();
                Metodologia mtd1 = new Metodologia();
                Metodologia mtd2 = new Metodologia();
                Analise anals = new Analise();
                Usuario user = new Usuario();
                lot.setLote_id(rs.getInt("lote_id"));
                prod.setNome_material(rs.getString("material"));
                prod.setCod_material(rs.getString("cod_material"));
                lot.setProduto(prod);
                mtd1.setMetodo_id(rs.getInt("metodologia_id"));
                mtd1.setCod_metodo(rs.getString("cod_metodo"));
                mtd1.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd1);
                mtd2.setCod_metodo(rs.getString("cod_metodo2"));
                lot.setMetodo2(mtd2);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                lot.setAnalise(anals);
                user.setUser(rs.getString("user_name"));
                lot.setUser(user);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lot.setEtapa(rs.getString("etapa"));
                lotes.add(lot);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readCadastroDevolucao");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotes;
    }

    public List<Lote> readCadastroTransferenciaMetodo(Integer limit) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Lote> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(?) tb_lote.*, tb_material.material, tb_material.cod_material, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, "
                    + "tb_metodologia.metodo_id, tb_metodologia.cod_metodo, tb_metodologia.metodo "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "WHERE tb_lote.analise_finalidade_id = 8 "
                    + "ORDER BY tb_lote.lote_id DESC ");
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                Analise anals = new Analise();
                Usuario user = new Usuario();
                lot.setLote_id(rs.getInt("lote_id"));
                prod.setNome_material(rs.getString("material"));
                prod.setCod_material(rs.getString("cod_material"));
                lot.setProduto(prod);
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                lot.setAnalise(anals);
                user.setUser(rs.getString("user_name"));
                lot.setUser(user);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lotes.add(lot);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readCadastroTransferenciaMetodo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotes;
    }

    public List<Lote> readCadastroEEPIG(Integer limit) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Lote> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(?) tb_lote.*, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, "
                    + "tb_metodologia.metodo_id, tb_metodologia.cod_metodo, tb_metodologia.metodo "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_lote.metodologia_id = tb_metodologia.metodo_id "
                    + "WHERE tb_lote.analise_finalidade_id = 5 "
                    + "ORDER BY tb_lote.lote_id DESC ");
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                Metodologia mtd = new Metodologia();
                Analise anals = new Analise();
                Usuario user = new Usuario();
                lot.setLote_id(rs.getInt("lote_id"));
                mtd.setMetodo_id(rs.getInt("metodologia_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                lot.setAnalise(anals);
                user.setUser(rs.getString("user_name"));
                lot.setUser(user);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lot.setEtapa(rs.getString("etapa"));
                lotes.add(lot);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readCadastroEEPIG");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotes;
    }

    public List<Lote> readCadastroValidacaoLimpeza(Integer limit) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Lote> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(?) tb_lote.*, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, "
                    + "tb_metodologia.metodo_id, tb_metodologia.cod_metodo, tb_metodologia.metodo "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_lote.metodologia_id = tb_metodologia.metodo_id "
                    + "WHERE tb_lote.analise_finalidade_id = 7 "
                    + "ORDER BY tb_lote.lote_id DESC ");
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                Metodologia mtd = new Metodologia();
                Analise anals = new Analise();
                Usuario user = new Usuario();
                lot.setLote_id(rs.getInt("lote_id"));
                mtd.setMetodo_id(rs.getInt("metodologia_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                lot.setAnalise(anals);
                user.setUser(rs.getString("user_name"));
                lot.setUser(user);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lot.setEtapa(rs.getString("etapa"));
                lotes.add(lot);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readCadastroValidacaoLimpeza");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotes;
    }

    public List<Lote> readLotesPorMetodo(String sql, String order) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Lote> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_lote.*, tb_material.material, tb_material.cod_material, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, "
                    + "tb_metodologia.metodo_id, tb_metodologia.cod_metodo, tb_metodologia.metodo "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "WHERE 1=1 AND tb_lote.lote_status is NULL  " + sql
                    + " ORDER BY " + order
                    + " tb_lote.data_entrada ASC, tb_lote.tamanho_lote DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lote lot = new Lote();
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                Analise anals = new Analise();
                Usuario user = new Usuario();
                lot.setLote_id(rs.getInt("lote_id"));
                prod.setNome_material(rs.getString("material"));
                prod.setCod_material(rs.getString("cod_material"));
                lot.setProduto(prod);
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                lot.setAnalise(anals);
                user.setUser(rs.getString("user_name"));
                lot.setUser(user);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lotes.add(lot);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readLotesPorMetodo");
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
                    + "material_id = ?, analise_finalidade_id = ?, "
                    + "lote_produto = ?, data_entrada = ?, "
                    + "data_prevista = ?, lote_obs = ? "
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
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateLoteOutros(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_lote SET "
                    + "material_id = ?, metodologia_id = ?, analise_finalidade_id = ?, "
                    + "lote_produto = ?, data_entrada = ?, "
                    + "data_prevista = ?, lote_obs = ?, etapa = ? "
                    + "WHERE lote_id = ?");
            stmt.setInt(1, lot.getProduto().getMaterial_id());
            stmt.setInt(2, lot.getMetodo().getMetodo_id());
            stmt.setInt(3, lot.getAnalise().getAnalise_finalidade_id());
            stmt.setString(4, lot.getLote_produto());
            stmt.setTimestamp(5, lot.getData_entrada());
            stmt.setTimestamp(6, lot.getData_prevista());
            stmt.setString(7, lot.getLote_obs());
            stmt.setString(8, lot.getEtapa());
            stmt.setInt(9, lot.getLote_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateLoteOutros");
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
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateLoteStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectLote(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_lote.*, tb_metodologia.metodo, "
                    + "tb_metodologia.cod_metodo, tb_metodologia.metodo_id, "
                    + "tb_material.cod_material, tb_material.material, tb_material.tipo, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, "
                    + "tb_analise_finalidade.analise_finalidade, "
                    + "(SELECT tb_metodologia.cod_metodo FROM tb_metodologia "
                    + "WHERE tb_metodologia.metodo_id = tb_lote.metodologia_id) as cod_metodo2 "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "WHERE lote_id = ?");
            stmt.setInt(1, lot.getLote_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Material prod = new Material();
                Metodologia mtd1 = new Metodologia();
                Metodologia mtd2 = new Metodologia();
                Analise anals = new Analise();
                lot.setLote_id(rs.getInt("lote_id"));
                prod.setMaterial_id(rs.getInt("material_id"));
                prod.setNome_material(rs.getString("material"));
                prod.setCod_material(rs.getString("cod_material"));
                prod.setTipo(rs.getString("tipo"));
                lot.setProduto(prod);
                mtd1.setMetodo_id(rs.getInt("metodo_id"));
                mtd1.setCod_metodo(rs.getString("cod_metodo"));
                mtd1.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd1);
                mtd2.setCod_metodo(rs.getString("cod_metodo2"));
                lot.setMetodo2(mtd2);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                anals.setAnalise_finalidade(rs.getString("analise_finalidade"));
                lot.setAnalise(anals);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lot.setEtapa(rs.getString("etapa"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectLoteOutros(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_lote.*, tb_metodologia.metodo, "
                    + "tb_metodologia.cod_metodo, "
                    + "tb_analise_finalidade.sigla_analise_finalidade, "
                    + "tb_analise_finalidade.analise_finalidade "
                    + "FROM tb_lote "
                    + "INNER JOIN tb_analise_finalidade "
                    + "ON tb_lote.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_lote.metodologia_id = tb_metodologia.metodo_id "
                    + "WHERE lote_id = ?");
            stmt.setInt(1, lot.getLote_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Analise anals = new Analise();
                lot.setLote_id(rs.getInt("lote_id"));
                mtd.setMetodo_id(rs.getInt("metodologia_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                lot.setMetodo(mtd);
                anals.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anals.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                anals.setAnalise_finalidade(rs.getString("analise_finalidade"));
                lot.setAnalise(anals);
                lot.setLote_produto(rs.getString("lote_produto"));
                lot.setData_entrada(rs.getTimestamp("data_entrada"));
                lot.setData_prevista(rs.getTimestamp("data_prevista"));
                lot.setLote_status(rs.getString("lote_status"));
                lot.setLote_obs(rs.getString("lote_obs"));
                lot.setTamanho_lote(rs.getInt("tamanho_lote"));
                lot.setEtapa(rs.getString("etapa"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectLoteOutros");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public static boolean verificarLote(String lote) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExits = false;
        try {
            stmt = conn.prepareStatement("SELECT lote_produto FROM tb_lote WHERE lote_produto = ?");
            stmt.setString(1, lote);
            rs = stmt.executeQuery();
            while (rs.next()) {
                isExits = true;
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "verificarLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return isExits;
    }

    public void deleteLote(Lote lot) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_lote WHERE lote_id = ?");
            stmt.setInt(1, lot.getLote_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public static void printEntradaLotes(String user, Timestamp data_inicio, Timestamp data_fim) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        String src = Reports.getSrc() + "RecepcaoLotes.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printEntradaLotes");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printEntradaLotes(String user, Timestamp data_inicio, Timestamp data_fim, String valor) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("data_inicio", data_inicio);
        map.put("data_fim", data_fim);
        map.put("finalidade", valor);
        String src = Reports.getSrc() + "RecepcaoFinalidadeLotes.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printEntradaLotes");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }
}
