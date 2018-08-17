/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
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
public class MetodologiaDAO {

    public void createMetodologia(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_metodologia "
                    + "(cod_metodo, metodo, versao, categoria, link) VALUES (?,?,?,?,?)");
            stmt.setString(1, mtd.getCod_metodo());
            stmt.setString(2, mtd.getMetodo());
            stmt.setInt(3, mtd.getVersao());
            stmt.setString(4, mtd.getCategoria());
            stmt.setString(5, mtd.getLink());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Metodologia> readMetodologia() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Metodologia> metodologias = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_metodologia "
                    + "ORDER BY cod_metodo");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                mtd.setVersao(rs.getInt("versao"));
                mtd.setCategoria(rs.getString("categoria"));
                mtd.setLink(rs.getString("link"));
                metodologias.add(mtd);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readMetodologia");
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
            stmt = conn.prepareStatement("SELECT Distinct cod_metodo "
                    + "FROM tb_metodologia");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                metodos.add(mtd);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readMetodo");
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
                    + "cod_metodo = ?, metodo = ?, versao = ?, "
                    + "categoria = ?, link = ? "
                    + "WHERE metodo_id = ?");
            stmt.setString(1, mtd.getCod_metodo());
            stmt.setString(2, mtd.getMetodo());
            stmt.setInt(3, mtd.getVersao());
            stmt.setString(4, mtd.getCategoria());
            stmt.setString(5, mtd.getLink());
            stmt.setInt(6, mtd.getMetodo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectMetodologia(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_metodologia "
                    + "WHERE metodo_id = ?");
            stmt.setInt(1, mtd.getMetodo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                mtd.setVersao(rs.getInt("versao"));
                mtd.setCategoria(rs.getString("categoria"));
                mtd.setLink(rs.getString("link"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public String selectMetodologiaById(Integer metodo_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String metodo = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_metodologia.metodo "
                    + "FROM tb_metodologia "
                    + "WHERE metodo_id = ?");
            stmt.setInt(1, metodo_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                metodo = rs.getString("metodo");
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectMetodologiaById");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return metodo;
    }

    public String selectCodMetodologiaById(Integer metodo_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String metodo = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_metodologia.cod_metodo "
                    + "FROM tb_metodologia "
                    + "WHERE metodo_id = ?");
            stmt.setInt(1, metodo_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                metodo = rs.getString("cod_metodo");
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectCodMetodologiaById");
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
            stmt = conn.prepareStatement("SELECT tb_metodologia.link "
                    + "FROM tb_metodologia "
                    + "WHERE metodo_id = ?");
            stmt.setString(1, metodo_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                link = rs.getString("link");
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectMetodologiaLinkById");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return link;
    }

    public int selectMetodologiaIdByCodMetodo(String cod_metodo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int metodo_id = 0;
        try {
            stmt = conn.prepareStatement("SELECT tb_metodologia.metodo_id "
                    + "FROM tb_metodologia "
                    + "WHERE cod_metodo = ?");
            stmt.setString(1, cod_metodo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                metodo_id = (Integer) rs.getInt("metodo_id");
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectMetodologiaIdByCodMetodo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return metodo_id;
    }

    public void deleteMetodologia(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_metodologia "
                    + "WHERE metodo_id = ?");
            stmt.setInt(1, mtd.getMetodo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Metodologia> readInfoMetodo() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Metodologia> metodos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT DISTINCT(tb_metodologia.metodo_id), "
                    + "tb_metodologia.cod_metodo, tb_metodologia.metodo, tb_metodologia.link, "
                    + "tb_historico_metodologia.metodo_id as mtd_id "
                    + "FROM tb_metodologia "
                    + "LEFT JOIN tb_historico_metodologia "
                    + "ON tb_metodologia.metodo_id = tb_historico_metodologia.metodo_id "
                    + "ORDER BY tb_metodologia.cod_metodo ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setAjuda(rs.getInt("mtd_id") > 0);
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                mtd.setLink(rs.getString("link"));
                metodos.add(mtd);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readInfoMetodo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return metodos;
    }

}
