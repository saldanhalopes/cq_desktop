/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.ColunaConfig;
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
public class ColunaConfigDAO {

    public void createColunaConfig(ColunaConfig config) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_coluna_config "
                    + "(tipo_coluna_config, coluna_config, descricao_coluna_config) "
                    + "VALUES (?,?,?)");
            stmt.setString(1, config.getTipo_coluna_config());
            stmt.setString(2, config.getColuna_config());
            stmt.setString(3, config.getDescricao_coluna_config());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createColunaConfig");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<ColunaConfig> readColunaConfig() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ColunaConfig> configes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_coluna_config");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ColunaConfig config = new ColunaConfig();
                config.setColuna_config_id(rs.getInt("coluna_config_id"));
                config.setTipo_coluna_config(rs.getString("tipo_coluna_config"));
                config.setColuna_config(rs.getString("coluna_config"));
                config.setDescricao_coluna_config(rs.getString("descricao_coluna_config"));
                configes.add(config);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readColunaConfig");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return configes;
    }

    public List<ColunaConfig> readColunaConfig(String tipo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ColunaConfig> configes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_coluna_config "
                    + "WHERE tipo_coluna_config = ? "
                    + "ORDER BY coluna_config ASC");
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ColunaConfig config = new ColunaConfig();
                config.setColuna_config_id(rs.getInt("coluna_config_id"));
                config.setTipo_coluna_config(rs.getString("tipo_coluna_config"));
                config.setColuna_config(rs.getString("coluna_config"));
                config.setDescricao_coluna_config(rs.getString("descricao_coluna_config"));
                configes.add(config);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readColunaConfig");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return configes;
    }

    public void updateColunaConfig(ColunaConfig config) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_coluna_config SET "
                    + "tipo_coluna_config = ?, coluna_config = ?, descricao_coluna_config = ? "
                    + "WHERE coluna_config_id = ?");
            stmt.setString(1, config.getTipo_coluna_config());
            stmt.setString(2, config.getColuna_config());
            stmt.setString(3, config.getDescricao_coluna_config());
            stmt.setInt(4, config.getColuna_config_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateColunaConfig");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectColunaConfig(ColunaConfig config) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_coluna_config WHERE coluna_config_id = ?");
            stmt.setInt(1, config.getColuna_config_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                config.setTipo_coluna_config(rs.getString("tipo_coluna_config"));
                config.setColuna_config(rs.getString("coluna_config"));
                config.setDescricao_coluna_config(rs.getString("descricao_coluna_config"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectColunaConfig");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteColunaConfig(ColunaConfig config) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_coluna_config WHERE coluna_config_id = ?");
            stmt.setInt(1, config.getColuna_config_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteColunaConfig");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
