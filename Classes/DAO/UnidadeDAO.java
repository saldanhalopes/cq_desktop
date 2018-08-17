/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Unidade;
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
public class UnidadeDAO {

    public void createUnidade(Unidade unidade) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_unidade "
                    + "(unidade, sigla_unidade) "
                    + "VALUES (?,?)");
            stmt.setString(1, unidade.getUnidade());
            stmt.setString(2, unidade.getSigla_unidade());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createUnidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Unidade> readUnidade() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Unidade> unidadees = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_unidade");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Unidade unidade = new Unidade();
                unidade.setUnidade_id(rs.getInt("unidade_id"));
                unidade.setUnidade(rs.getString("unidade"));
                unidade.setSigla_unidade(rs.getString("sigla_unidade"));
                unidadees.add(unidade);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readUnidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return unidadees;
    }

    public void updateUnidade(Unidade unidade) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_unidade SET "
                    + "unidade = ?, sigla_unidade = ? "
                    + "WHERE unidade_id = ?");
            stmt.setString(1, unidade.getUnidade());
            stmt.setString(2, unidade.getSigla_unidade());
            stmt.setInt(3, unidade.getUnidade_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateUnidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectUnidade(Unidade unidade) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_unidade WHERE unidade_id = ?");
            stmt.setInt(1, unidade.getUnidade_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                unidade.setUnidade(rs.getString("unidade"));
                unidade.setSigla_unidade(rs.getString("sigla_unidade"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectUnidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteUnidade(Unidade unidade) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_unidade WHERE unidade_id = ?");
            stmt.setInt(1, unidade.getUnidade_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteUnidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
}
