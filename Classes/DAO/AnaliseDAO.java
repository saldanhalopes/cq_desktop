/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Acompanhamento;
import Classes.Modelo.Analise;
import Classes.Modelo.Metodologia;
import Classes.Modelo.Setor;
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
public class AnaliseDAO {

    //tb_analise
    public void createAnalise(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_analise "
                    + "(analise, sigla_analise, descricao_analise) "
                    + "VALUES (?,?,?)");
            stmt.setString(1, anls.getAnalise());
            stmt.setString(2, anls.getSigla_analise());
            stmt.setString(3, anls.getDescricao_analise());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAnalise");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Analise> readAnalise() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analises = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_analise");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                anls.setSigla_analise(rs.getString("sigla_analise"));
                anls.setDescricao_analise(rs.getString("descricao_analise"));
                analises.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnalise");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analises;
    }

    public void updateAnalise(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_analise SET "
                    + "analise = ?, sigla_analise = ?, descricao_analise = ? "
                    + "WHERE analise_id = ?");
            stmt.setString(1, anls.getAnalise());
            stmt.setString(2, anls.getSigla_analise());
            stmt.setString(3, anls.getDescricao_analise());
            stmt.setInt(4, anls.getAnalise_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAnalise");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAnalise(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_analise WHERE analise_id = ?");
            stmt.setInt(1, anls.getAnalise_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                anls.setAnalise(rs.getString("analise"));
                anls.setSigla_analise(rs.getString("sigla_analise"));
                anls.setDescricao_analise(rs.getString("descricao_analise"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAnalise");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteAnalise(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_analise WHERE analise_id = ?");
            stmt.setInt(1, anls.getAnalise_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAnalise");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    //tb_analise_amostra
    public void createAnaliseAmostra(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_analise_amostra "
                    + "(analise_amostra, sigla_analise_amostra, descricao_analise_amostra) "
                    + "VALUES (?,?,?)");
            stmt.setString(1, anls.getAnalise_amostra());
            stmt.setString(2, anls.getSigla_analise_amostra());
            stmt.setString(3, anls.getDescricao_analise_amostra());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAnaliseAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Analise> readAnaliseAmostra() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analises = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_analise_amostra");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                anls.setAnalise_amostra_id((Integer)rs.getInt("analise_amostra_id"));
                anls.setAnalise_amostra(rs.getString("analise_amostra"));
                anls.setSigla_analise_amostra(rs.getString("sigla_analise_amostra"));
                anls.setDescricao_analise_amostra(rs.getString("descricao_analise_amostra"));
                analises.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analises;
    }

    public List<Analise> readAnaliseAmostraGeral() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analises = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_analise_amostra "
                    + "WHERE tb_analise_amostra.analise_amostra IS NOT NULL ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                anls.setAnalise_amostra_id((Integer)rs.getInt("analise_amostra_id"));
                anls.setAnalise_amostra(rs.getString("analise_amostra"));
                analises.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseAmostraGeral");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analises;
    }

    public void updateAnaliseAmostra(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_analise_amostra SET "
                    + "analise_amostra = ?, sigla_analise_amostra = ?, descricao_analise_amostra = ? "
                    + "WHERE analise_amostra_id = ?");
            stmt.setString(1, anls.getAnalise_amostra());
            stmt.setString(2, anls.getSigla_analise_amostra());
            stmt.setString(3, anls.getDescricao_analise_amostra());
            stmt.setInt(4, anls.getAnalise_amostra_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAnaliseAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAnaliseAmostra(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_analise_amostra WHERE analise_amostra_id = ?");
            stmt.setInt(1, anls.getAnalise_amostra_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                anls.setAnalise_amostra(rs.getString("analise_amostra"));
                anls.setSigla_analise_amostra(rs.getString("sigla_analise_amostra"));
                anls.setDescricao_analise_amostra(rs.getString("descricao_analise_amostra"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAnaliseAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteAnaliseAmostra(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_analise_amostra WHERE analise_amostra_id = ?");
            stmt.setInt(1, anls.getAnalise_amostra_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAnaliseAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    //tb_analise_finalidade
    public void createAnaliseFinalidade(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_analise_finalidade "
                    + "(analise_finalidade, sigla_analise_finalidade, descricao_analise_finalidade) "
                    + "VALUES (?,?,?)");
            stmt.setString(1, anls.getAnalise_finalidade());
            stmt.setString(2, anls.getSigla_analise_finalidade());
            stmt.setString(3, anls.getDescricao_analise_finalidade());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAnaliseFinalidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Analise> readAnaliseFinalidade() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analises = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_analise_finalidade");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                anls.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anls.setAnalise_finalidade(rs.getString("analise_finalidade"));
                anls.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                anls.setDescricao_analise_finalidade(rs.getString("descricao_analise_finalidade"));
                analises.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseFinalidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analises;
    }

    public void updateAnaliseFinalidade(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_analise_finalidade SET "
                    + "analise_finalidade = ?, sigla_analise_finalidade = ?, descricao_analise_finalidade = ? "
                    + "WHERE analise_finalidade_id = ?");
            stmt.setString(1, anls.getAnalise_finalidade());
            stmt.setString(2, anls.getSigla_analise_finalidade());
            stmt.setString(3, anls.getDescricao_analise_finalidade());
            stmt.setInt(4, anls.getAnalise_finalidade_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAnaliseFinalidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAnaliseFinalidade(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_analise_finalidade WHERE analise_finalidade_id = ?");
            stmt.setInt(1, anls.getAnalise_finalidade_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                anls.setAnalise_finalidade(rs.getString("analise_finalidade"));
                anls.setSigla_analise_finalidade(rs.getString("sigla_analise_finalidade"));
                anls.setDescricao_analise_finalidade(rs.getString("descricao_analise_finalidade"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAnaliseFinalidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteAnaliseFinalidade(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_analise_finalidade WHERE analise_finalidade_id = ?");
            stmt.setInt(1, anls.getAnalise_finalidade_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAnaliseFinalidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    //tb_analise_produtividade
    public void createAnaliseProdutividade(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_analise_produtividade "
                    + "(analise_produtividade, sigla_analise_produtividade, "
                    + "descricao_analise_produtividade, cor) "
                    + "VALUES (?,?,?,?)");
            stmt.setString(1, anls.getAnalise_produtividade());
            stmt.setString(2, anls.getSigla_analise_produtividade());
            stmt.setString(3, anls.getDescricao_analise_produtividade());
            stmt.setString(4, anls.getCor());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAnaliseProdutividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Analise> readAnaliseProdutividade() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analises = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_analise_produtividade");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                anls.setAnalise_produtividade_id(rs.getInt("analise_produtividade_id"));
                anls.setAnalise_produtividade(rs.getString("analise_produtividade"));
                anls.setSigla_analise_produtividade(rs.getString("sigla_analise_produtividade"));
                anls.setDescricao_analise_produtividade(rs.getString("descricao_analise_produtividade"));
                anls.setCor(rs.getString("cor"));
                analises.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseProdutividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analises;
    }

    public void updateAnaliseProdutividade(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_analise_produtividade SET "
                    + "analise_produtividade = ?, sigla_analise_produtividade = ?, "
                    + "descricao_analise_produtividade = ?, cor = ?  "
                    + "WHERE analise_produtividade_id = ?");
            stmt.setString(1, anls.getAnalise_produtividade());
            stmt.setString(2, anls.getSigla_analise_produtividade());
            stmt.setString(3, anls.getDescricao_analise_produtividade());
            stmt.setString(4, anls.getCor());
            stmt.setInt(5, anls.getAnalise_produtividade_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAnaliseProdutividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAnaliseProdutividade(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_analise_produtividade WHERE analise_produtividade_id = ?");
            stmt.setInt(1, anls.getAnalise_produtividade_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                anls.setAnalise_produtividade(rs.getString("analise_produtividade"));
                anls.setSigla_analise_produtividade(rs.getString("sigla_analise_produtividade"));
                anls.setDescricao_analise_produtividade(rs.getString("descricao_analise_produtividade"));
                anls.setCor(rs.getString("cor"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAnaliseProdutividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteAnaliseProdutividade(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_analise_produtividade WHERE analise_produtividade_id = ?");
            stmt.setInt(1, anls.getAnalise_produtividade_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAnaliseProdutividade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    //tb_analise_status
    public void createAnaliseStatus(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_analise_status "
                    + "(analise_status, sigla_analise_status, descricao_analise_status, "
                    + "analise_produtividade_id) VALUES (?,?,?,?)");
            stmt.setString(1, anls.getAnalise_status());
            stmt.setString(2, anls.getSigla_analise_status());
            stmt.setString(3, anls.getDescricao_analise_status());
            stmt.setInt(4, anls.getAnalise_produtividade_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAnaliseStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Analise> readAnaliseStatus() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analises = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_analise_status.*, tb_analise_produtividade.analise_produtividade "
                    + "FROM tb_analise_status INNER JOIN tb_analise_produtividade "
                    + "ON tb_analise_status.analise_produtividade_id = tb_analise_produtividade.analise_produtividade_id "
                    + "WHERE tb_analise_status.analise_status_id > 10");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                anls.setAnalise_status_id(rs.getInt("analise_status_id"));
                anls.setAnalise_status(rs.getString("analise_status"));
                anls.setSigla_analise_status(rs.getString("sigla_analise_status"));
                anls.setDescricao_analise_status(rs.getString("descricao_analise_status"));
                anls.setAnalise_produtividade(rs.getString("analise_produtividade"));
                analises.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analises;
    }

    public void updateAnaliseStatus(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_analise_status SET "
                    + "analise_status = ?, sigla_analise_status = ?, "
                    + "descricao_analise_status = ?, analise_produtividade_id = ? "
                    + "WHERE analise_status_id = ?");
            stmt.setString(1, anls.getAnalise_status());
            stmt.setString(2, anls.getSigla_analise_status());
            stmt.setString(3, anls.getDescricao_analise_status());
            stmt.setInt(4, anls.getAnalise_produtividade_id());
            stmt.setInt(5, anls.getAnalise_status_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAnaliseStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAnaliseStatus(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_analise_status.*, tb_analise_produtividade.analise_produtividade "
                    + "FROM tb_analise_status INNER JOIN tb_analise_produtividade "
                    + "ON tb_analise_status.analise_produtividade_id = tb_analise_produtividade.analise_produtividade_id "
                    + "WHERE analise_status_id = ?");
            stmt.setInt(1, anls.getAnalise_status_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                anls.setAnalise_status(rs.getString("analise_status"));
                anls.setSigla_analise_status(rs.getString("sigla_analise_status"));
                anls.setDescricao_analise_status(rs.getString("descricao_analise_status"));
                anls.setAnalise_produtividade(rs.getString("analise_produtividade"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAnaliseStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteAnaliseStatus(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_analise_status WHERE analise_status_id = ?");
            stmt.setInt(1, anls.getAnalise_status_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAnaliseStatus");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    //tb_metodologia_analiserlope
    public void createAnaliseMetodologia(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_metodologia_analise "
                    + "(metodo_id, analise_id, registro_acompanhamento_id, "
                    + "setor_id, metodologia_analise_ativo) "
                    + "VALUES (?,?,?,?,?)");
            stmt.setInt(1, anls.getMetodo_id_metodologia_analise_id());
            stmt.setInt(2, anls.getAnalise_id());
            stmt.setInt(3, anls.getAcompanhamento().getRegistro_acompanhamento_id());
            stmt.setInt(4, anls.getSetor().getSetor_id());
            stmt.setInt(5, (anls.getMetodologia_analise_ativo()) ? 1 : 0);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAnaliseMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Analise> readAnaliseMetodologia(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analiseMetodo = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_analise.analise_id, tb_analise.analise, "
                    + "tb_analise.sigla_analise, tb_metodologia_analise.metodologia_analise_id, "
                    + "tb_setor.sigla_setor, tb_metodologia_analise.metodologia_analise_ativo, "
                    + "tb_registro_acompanhamento.sigla_registro_acompanhamento "
                    + "FROM tb_analise "
                    + "LEFT JOIN tb_metodologia_analise "
                    + "ON tb_analise.analise_id = tb_metodologia_analise.analise_id "
                    + "AND tb_metodologia_analise.metodo_id = ? "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_metodologia_analise.setor_id = tb_setor.setor_id "
                    + "LEFT JOIN tb_registro_acompanhamento "
                    + "ON tb_registro_acompanhamento.registro_acompanhamento_id "
                    + "= tb_metodologia_analise.registro_acompanhamento_id");
            stmt.setInt(1, mtd.getMetodo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                Setor setor = new Setor();
                Acompanhamento acomp = new Acompanhamento();
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                anls.setSigla_analise(rs.getString("sigla_analise"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                anls.setSetor(setor);
                anls.setMetodologia_analise_id(rs.getInt("metodologia_analise_id"));
                anls.setMetodologia_analise_ativo(rs.getBoolean("metodologia_analise_ativo"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                anls.setAcompanhamento(acomp);
                analiseMetodo.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analiseMetodo;
    }

    public List<Analise> readAnaliseMetodologia(int metodo_id, boolean analise_ativo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analiseMetodo = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_analise.analise_id, tb_analise.analise, "
                    + "tb_analise.sigla_analise, tb_metodologia_analise.metodologia_analise_id, "
                    + "tb_setor.setor_id, tb_setor.sigla_setor, "
                    + "tb_metodologia_analise.metodologia_analise_ativo "
                    + "FROM tb_analise "
                    + "LEFT JOIN tb_metodologia_analise "
                    + "ON tb_analise.analise_id = tb_metodologia_analise.analise_id "
                    + "AND tb_metodologia_analise.metodo_id = ? "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_metodologia_analise.setor_id = tb_setor.setor_id "
                    + "WHERE tb_metodologia_analise.metodologia_analise_ativo = ?");
            stmt.setInt(1, metodo_id);
            int ativo = 0;
            if (analise_ativo) {
                ativo = 1;
            }
            stmt.setInt(2, ativo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                Setor setor = new Setor();
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                anls.setSigla_analise(rs.getString("sigla_analise"));
                anls.setMetodologia_analise_id(rs.getInt("metodologia_analise_id"));
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                anls.setSetor(setor);
                anls.setMetodologia_analise_ativo(rs.getBoolean("metodologia_analise_ativo"));
                analiseMetodo.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analiseMetodo;
    }

    public void updateAnaliseMetodologia(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_metodologia_analise SET "
                    + "metodologia_analise_ativo = ? , registro_acompanhamento_id = ?, setor_id = ? "
                    + "WHERE metodologia_analise_id = ?");
            stmt.setInt(1, (anls.getMetodologia_analise_ativo()) ? 1 : 0);
            stmt.setInt(2, anls.getAcompanhamento().getRegistro_acompanhamento_id());
            stmt.setInt(3, anls.getSetor().getSetor_id());
            stmt.setInt(4, anls.getMetodologia_analise_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAnaliseMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    //tb_amostra
    public List<Analise> readAnaliseAmostra(int metodo_id, boolean analise_ativo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analiseMetodo = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_analise.analise_id, tb_analise.analise, "
                    + "tb_analise.sigla_analise, tb_metodologia_analise.metodologia_analise_id, "
                    + "tb_setor.setor_id, tb_setor.sigla_setor, "
                    + "tb_metodologia_analise.metodologia_analise_ativo "
                    + "FROM tb_analise "
                    + "LEFT JOIN tb_metodologia_analise "
                    + "ON tb_analise.analise_id = tb_metodologia_analise.analise_id "
                    + "AND tb_metodologia_analise.metodo_id = ? "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_metodologia_analise.setor_id = tb_setor.setor_id "
                    + "WHERE tb_metodologia_analise.metodologia_analise_ativo = ?");
            stmt.setInt(1, metodo_id);
            int ativo = 0;
            if (analise_ativo) {
                ativo = 1;
            }
            stmt.setInt(2, ativo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                Setor setor = new Setor();
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                anls.setSigla_analise(rs.getString("sigla_analise"));
                anls.setMetodologia_analise_id(rs.getInt("metodologia_analise_id"));
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                anls.setSetor(setor);
                anls.setMetodologia_analise_ativo(rs.getBoolean("metodologia_analise_ativo"));
                analiseMetodo.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseAmostra");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analiseMetodo;
    }

    public List<Analise> readAnaliseEntrada(int metodo_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analiseMetodo = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_analise.analise_id, tb_analise.analise, "
                    + "tb_analise.sigla_analise, tb_metodologia_analise.metodologia_analise_id, "
                    + "tb_setor.sigla_setor, tb_metodologia_analise.metodologia_analise_ativo, "
                    + "tb_registro_acompanhamento.sigla_registro_acompanhamento "
                    + "FROM tb_analise "
                    + "INNER JOIN tb_metodologia_analise "
                    + "ON tb_analise.analise_id = tb_metodologia_analise.analise_id "
                    + "AND tb_metodologia_analise.metodo_id = ? "
                    + "INNER JOIN tb_setor  "
                    + "ON tb_metodologia_analise.setor_id = tb_setor.setor_id "
                    + "INNER JOIN tb_registro_acompanhamento "
                    + "ON tb_registro_acompanhamento.registro_acompanhamento_id "
                    + "= tb_metodologia_analise.registro_acompanhamento_id");
            stmt.setInt(1, metodo_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                Setor setor = new Setor();
                Acompanhamento acomp = new Acompanhamento();
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getString("analise"));
                anls.setSigla_analise(rs.getString("sigla_analise"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                anls.setSetor(setor);
                anls.setMetodologia_analise_id(rs.getInt("metodologia_analise_id"));
                anls.setMetodologia_analise_ativo(rs.getBoolean("metodologia_analise_ativo"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                anls.setAcompanhamento(acomp);
                analiseMetodo.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseEntrada");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analiseMetodo;
    }

    public int selectMaterialPorTipo(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_analise_finalidade.analise_finalidade_id "
                    + "FROM tb_analise_finalidade "
                    + "WHERE tb_analise_finalidade.sigla_analise_finalidade = ?");
            stmt.setString(1, anls.getSigla_analise_finalidade());
            rs = stmt.executeQuery();
            while (rs.next()) {
                anls.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectMaterialPorTipo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return anls.getAnalise_finalidade_id();
    }

    public List<Analise> readAnaliseMetodologiaByLote(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> analiseMetodo = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_analise.analise_id, tb_setor.setor_id, "
                    + "tb_registro_acompanhamento.registro_acompanhamento_id "
                    + "FROM tb_analise "
                    + "LEFT JOIN tb_metodologia_analise "
                    + "ON tb_analise.analise_id = tb_metodologia_analise.analise_id "
                    + "AND tb_metodologia_analise.metodo_id = ? "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_metodologia_analise.setor_id = tb_setor.setor_id "
                    + "LEFT JOIN tb_registro_acompanhamento "
                    + "ON tb_registro_acompanhamento.registro_acompanhamento_id "
                    + "= tb_metodologia_analise.registro_acompanhamento_id "
                    + "WHERE tb_metodologia_analise.metodologia_analise_ativo = 1 "
                    + "ORDER BY tb_analise.analise_id ASC");
            stmt.setInt(1, mtd.getMetodo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                Setor setor = new Setor();
                Acompanhamento acomp = new Acompanhamento();
                anls.setAnalise_id(rs.getInt("analise_id"));
                setor.setSetor_id(rs.getInt("setor_id"));
                anls.setSetor(setor);
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                anls.setAcompanhamento(acomp);
                analiseMetodo.add(anls);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAnaliseMetodologiaByLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analiseMetodo;
    }

    public void createAnaliseByLote(Analise anls) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_analise_lote "
                    + "(analise_id, lote_id, setor_id, registro_acompanhamento_id, "
                    + "status, data_hora_registro, user_registro) "
                    + "VALUES (?,?,?,?,?,?,?)");
            stmt.setInt(1, anls.getAnalise_id());
            stmt.setInt(2, anls.getLote().getLote_id());
            stmt.setInt(3, anls.getSetor().getSetor_id());
            stmt.setInt(4, anls.getAcompanhamento().getRegistro_acompanhamento_id());
            stmt.setString(5, anls.getStatus());
            stmt.setTimestamp(6, anls.getData_hora_registro());
            stmt.setString(7, anls.getUser_registro().getUser());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAnaliseByLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

}
