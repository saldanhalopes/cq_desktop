package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Analise;
import Classes.Modelo.Campanha;
import Classes.Modelo.Setor;
import Classes.Modelo.Coluna;
import Classes.Modelo.Cromatografo;
import Classes.Modelo.Metodologia;
import Classes.Modelo.Usuario;
import Classes.Util.Reports;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ColunaDAO {

    public void createColuna(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_coluna "
                    + "(cod_coluna, codigo_sap_coluna, setor_id, metodo_id, analise_id, "
                    + "tipo_coluna, fabricante_coluna, marca_coluna, fase_coluna, "
                    + "tamanho_coluna, diametro_coluna, particula_coluna, part_number_coluna, "
                    + "serial_number_coluna, data_ativacao_coluna, user_name_ativacao, "
                    + "vaga_coluna_id, obs_coluna, analise_finalidade_id, "
                    + "data_performance, user_name_performance, metodo_versao) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, col.getCodigo_coluna());
            stmt.setString(2, col.getCodigo_sap_coluna());
            stmt.setInt(3, col.getSetor().getSetor_id());
            stmt.setInt(4, col.getMetodologia().getMetodo_id());
            stmt.setInt(5, col.getAnalise().getAnalise_id());
            stmt.setString(6, col.getTipo_coluna());
            stmt.setString(7, col.getFabricante_coluna());
            stmt.setString(8, col.getMarca_coluna());
            stmt.setString(9, col.getFase_coluna());
            stmt.setString(10, col.getTamanho_coluna());
            stmt.setString(11, col.getDiametro_coluna());
            stmt.setString(12, col.getParticula_coluna());
            stmt.setString(13, col.getPart_number_coluna());
            stmt.setString(14, col.getSerial_number_coluna());
            stmt.setTimestamp(15, col.getData_ativacao_coluna());
            stmt.setString(16, col.getUser_ativacao().getUser());
            stmt.setInt(17, col.getVaga_coluna());
            stmt.setString(18, col.getObs_coluna());
            stmt.setInt(19, col.getAnalise().getAnalise_finalidade_id());
            stmt.setTimestamp(20, col.getData_performance());
            stmt.setString(21, col.getUser_performance().getUser());
            stmt.setInt(22, col.getMetodologia().getVersao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Coluna> readColuna(String _setor, int status) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coluna> colunas = new ArrayList<>();
        try {
            String where = "";
            if ((!"Todos".equals(_setor) && status != 0)) {
                where = "AND tb_setor.sigla_setor = '" + _setor + "' ";
                if (status == 1) {
                    where = where + "AND tb_coluna.data_descarte_coluna is null ";
                } else if (status == 2) {
                    where = where + "AND tb_coluna.data_descarte_coluna is not null ";
                } else if (status == 3) {
                    where = where + "AND tb_coluna.data_performance is null ";
                }
            } else {
                if ("Todos".equals(_setor)) {
                    if (status != 0) {
                        if (status == 1) {
                            where = "AND tb_coluna.data_descarte_coluna is null ";
                        } else if (status == 2) {
                            where = "AND tb_coluna.data_descarte_coluna is not null ";
                        } else if (status == 3) {
                            where = "AND tb_coluna.data_performance is null ";
                        }
                    }
                } else {
                    where = "AND tb_setor.sigla_setor = '" + _setor + "' ";
                }
            }
            stmt = conn.prepareStatement("SELECT TOP(10000) tb_coluna.*, "
                    + "tb_metodologia.cod_metodo, tb_metodologia.metodo, tb_analise.analise, "
                    + "tb_setor.sigla_setor, tb_analise_finalidade.analise_finalidade "
                    + "FROM tb_coluna "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_coluna.metodo_id = tb_metodologia.metodo_id "
                    + "LEFT JOIN tb_analise "
                    + "ON tb_coluna.analise_id = tb_analise.analise_id "
                    + "LEFT JOIN tb_analise_finalidade "
                    + "ON tb_coluna.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_coluna.setor_id = tb_setor.setor_id "
                    + "WHERE 1 = 1 " + where
                    + "ORDER BY tb_coluna.coluna_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Coluna col = new Coluna();
                Analise anls = new Analise();
                Setor setor = new Setor();
                Usuario user_ativacao = new Usuario();
                Usuario user_descarte = new Usuario();
                Usuario user_performance = new Usuario();
                Usuario user_verificacao = new Usuario();
                Metodologia mtd = new Metodologia();
                col.setColuna_id(rs.getInt("coluna_id"));
                col.setCodigo_coluna(rs.getInt("cod_coluna"));
                col.setCodigo_sap_coluna(rs.getString("codigo_sap_coluna"));
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                col.setSetor(setor);
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setMetodo(rs.getString("metodo"));
                mtd.setVersao(rs.getInt("metodo_versao"));
                mtd.setCod_metodo(rs.getInt("metodo_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("cod_metodo"));
                col.setMetodologia(mtd);
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getInt("analise_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("analise"));
                anls.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anls.setAnalise_finalidade(rs.getInt("analise_finalidade_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("analise_finalidade"));
                col.setAnalise(anls);
                col.setTipo_coluna(rs.getString("tipo_coluna"));
                col.setFabricante_coluna(rs.getString("fabricante_coluna"));
                col.setMarca_coluna(rs.getString("marca_coluna"));
                col.setFase_coluna(rs.getString("fase_coluna"));
                col.setTamanho_coluna(rs.getString("tamanho_coluna"));
                col.setDiametro_coluna(rs.getString("diametro_coluna"));
                col.setParticula_coluna(rs.getString("particula_coluna"));
                col.setPart_number_coluna(rs.getString("part_number_coluna"));
                col.setSerial_number_coluna(rs.getString("serial_number_coluna"));
                col.setData_ativacao_coluna(rs.getTimestamp("data_ativacao_coluna"));
                col.setData_descarte_coluna(rs.getTimestamp("data_descarte_coluna"));
                col.setData_performance(rs.getTimestamp("data_performance"));
                user_ativacao.setUser(rs.getString("user_name_ativacao"));
                col.setUser_ativacao(user_ativacao);
                user_descarte.setUser(rs.getString("user_name_descarte"));
                col.setUser_descarte(user_descarte);
                user_performance.setUser(rs.getString("user_name_performance"));
                col.setUser_performance(user_performance);
                col.setVaga_coluna(rs.getInt("vaga_coluna_id"));
                col.setObs_coluna(rs.getString("obs_coluna"));
                col.setData_verificacao(rs.getTimestamp("data_verificacao"));
                user_verificacao.setUser(rs.getString("user_verificacao"));
                col.setUser_verificacao(user_verificacao);
                col.setObs_verificacao(rs.getString("obs_verificacao"));
                colunas.add(col);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return colunas;
    }

    public List<Coluna> readAllColuna() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coluna> colunas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(10000) tb_coluna.*, "
                    + "tb_metodologia.cod_metodo, tb_metodologia.metodo, tb_analise.analise, "
                    + "tb_setor.sigla_setor, tb_analise_finalidade.analise_finalidade "
                    + "FROM tb_coluna "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_coluna.metodo_id = tb_metodologia.metodo_id "
                    + "LEFT JOIN tb_analise "
                    + "ON tb_coluna.analise_id = tb_analise.analise_id "
                    + "LEFT JOIN tb_analise_finalidade "
                    + "ON tb_coluna.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_coluna.setor_id = tb_setor.setor_id "
                    + "ORDER BY tb_coluna.coluna_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Coluna col = new Coluna();
                Analise anls = new Analise();
                Setor setor = new Setor();
                Usuario user_ativacao = new Usuario();
                Usuario user_descarte = new Usuario();
                Usuario user_performance = new Usuario();
                Usuario user_verificacao = new Usuario();
                Metodologia mtd = new Metodologia();
                col.setColuna_id(rs.getInt("coluna_id"));
                col.setCodigo_coluna(rs.getInt("cod_coluna"));
                col.setCodigo_sap_coluna(rs.getString("codigo_sap_coluna"));
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                col.setSetor(setor);
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setMetodo(rs.getString("metodo"));
                mtd.setVersao(rs.getInt("metodo_versao"));
                mtd.setCod_metodo(rs.getInt("metodo_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("cod_metodo"));
                col.setMetodologia(mtd);
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getInt("analise_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("analise"));
                anls.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anls.setAnalise_finalidade(rs.getInt("analise_finalidade_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("analise_finalidade"));
                col.setAnalise(anls);
                col.setTipo_coluna(rs.getString("tipo_coluna"));
                col.setFabricante_coluna(rs.getString("fabricante_coluna"));
                col.setMarca_coluna(rs.getString("marca_coluna"));
                col.setFase_coluna(rs.getString("fase_coluna"));
                col.setTamanho_coluna(rs.getString("tamanho_coluna"));
                col.setDiametro_coluna(rs.getString("diametro_coluna"));
                col.setParticula_coluna(rs.getString("particula_coluna"));
                col.setPart_number_coluna(rs.getString("part_number_coluna"));
                col.setSerial_number_coluna(rs.getString("serial_number_coluna"));
                col.setData_ativacao_coluna(rs.getTimestamp("data_ativacao_coluna"));
                col.setData_descarte_coluna(rs.getTimestamp("data_descarte_coluna"));
                col.setData_performance(rs.getTimestamp("data_performance"));
                user_ativacao.setUser(rs.getString("user_name_ativacao"));
                col.setUser_ativacao(user_ativacao);
                user_descarte.setUser(rs.getString("user_name_descarte"));
                col.setUser_descarte(user_descarte);
                user_performance.setUser(rs.getString("user_name_performance"));
                col.setUser_performance(user_performance);
                col.setVaga_coluna(rs.getInt("vaga_coluna_id"));
                col.setObs_coluna(rs.getString("obs_coluna"));
                col.setData_verificacao(rs.getTimestamp("data_verificacao"));
                user_verificacao.setUser(rs.getString("user_verificacao"));
                col.setUser_verificacao(user_verificacao);
                col.setObs_verificacao(rs.getString("obs_verificacao"));
                colunas.add(col);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return colunas;
    }

    public void updateColuna(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_coluna SET "
                    + "codigo_sap_coluna = ?, setor_id = ?, metodo_id = ?, analise_id = ?, "
                    + "tipo_coluna = ?, fabricante_coluna = ?, marca_coluna = ?, fase_coluna = ?, "
                    + "tamanho_coluna = ?, diametro_coluna = ?, particula_coluna = ?, part_number_coluna = ?, "
                    + "serial_number_coluna = ?, vaga_coluna_id = ?, obs_coluna = ?, analise_finalidade_id = ?, "
                    + "data_performance = ?, user_name_performance = ?, metodo_versao = ?, "
                    + "data_verificacao = ?, user_verificacao = ?, obs_verificacao = ? "
                    + "WHERE coluna_id = ?");
            stmt.setString(1, col.getCodigo_sap_coluna());
            stmt.setInt(2, col.getSetor().getSetor_id());
            stmt.setInt(3, col.getMetodologia().getMetodo_id());
            stmt.setInt(4, col.getAnalise().getAnalise_id());
            stmt.setString(5, col.getTipo_coluna());
            stmt.setString(6, col.getFabricante_coluna());
            stmt.setString(7, col.getMarca_coluna());
            stmt.setString(8, col.getFase_coluna());
            stmt.setString(9, col.getTamanho_coluna());
            stmt.setString(10, col.getDiametro_coluna());
            stmt.setString(11, col.getParticula_coluna());
            stmt.setString(12, col.getPart_number_coluna());
            stmt.setString(13, col.getSerial_number_coluna());
            stmt.setInt(14, col.getVaga_coluna());
            stmt.setString(15, col.getObs_coluna());
            stmt.setInt(16, col.getAnalise().getAnalise_finalidade_id());
            stmt.setTimestamp(17, col.getData_performance());
            stmt.setString(18, col.getUser_performance().getUser());
            stmt.setInt(19, col.getMetodologia().getVersao());
            stmt.setTimestamp(20, col.getData_verificacao());
            stmt.setString(21, col.getUser_verificacao().getUser());
            stmt.setString(22, col.getObs_verificacao());
            stmt.setInt(23, col.getColuna_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateDescarteColuna(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_coluna SET user_name_descarte = ?, "
                    + "data_descarte_coluna = ?, vaga_coluna_id = ? "
                    + "WHERE coluna_id = ?");
            stmt.setString(1, col.getUser_descarte().getUser());
            stmt.setTimestamp(2, col.getData_descarte_coluna());
            stmt.setInt(3, col.getVaga_coluna());
            stmt.setInt(4, col.getColuna_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateDescarteColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateRealocarColuna(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_coluna SET user_name_descarte = NULL, "
                    + "data_descarte_coluna = NULL, vaga_coluna_id = ? "
                    + "WHERE coluna_id = ?");
            stmt.setInt(1, col.getVaga_coluna());
            stmt.setInt(2, col.getColuna_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateRealocarColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectColuna(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_coluna.*, "
                    + "tb_metodologia.cod_metodo, tb_analise.analise, "
                    + "tb_setor.sigla_setor, tb_analise_finalidade.analise_finalidade "
                    + "FROM tb_coluna "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_coluna.metodo_id = tb_metodologia.metodo_id "
                    + "LEFT JOIN tb_analise "
                    + "ON tb_coluna.analise_id = tb_analise.analise_id "
                    + "LEFT JOIN tb_analise_finalidade "
                    + "ON tb_coluna.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_coluna.setor_id = tb_setor.setor_id  "
                    + "WHERE coluna_id = ?");
            stmt.setInt(1, col.getColuna_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise anls = new Analise();
                Setor setor = new Setor();
                Metodologia mtd = new Metodologia();
                Usuario userAtivacao = new Usuario();
                Usuario userPerformance = new Usuario();
                Usuario userDescarte = new Usuario();
                Usuario userVerificacao = new Usuario();
                col.setColuna_id(rs.getInt("coluna_id"));
                col.setCodigo_coluna(rs.getInt("cod_coluna"));
                col.setCodigo_sap_coluna(rs.getString("codigo_sap_coluna"));
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                col.setSetor(setor);
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setVersao(rs.getInt("metodo_versao"));
                mtd.setCod_metodo(rs.getInt("metodo_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("cod_metodo"));
                col.setMetodologia(mtd);
                anls.setAnalise_id(rs.getInt("analise_id"));
                anls.setAnalise(rs.getInt("analise_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("analise"));
                anls.setAnalise_finalidade_id(rs.getInt("analise_finalidade_id"));
                anls.setAnalise_finalidade(rs.getInt("analise_finalidade_id") == 0 ? "MULTIPROPÓSITO" : rs.getString("analise_finalidade"));
                col.setAnalise(anls);
                col.setTipo_coluna(rs.getString("tipo_coluna"));
                col.setFabricante_coluna(rs.getString("fabricante_coluna"));
                col.setMarca_coluna(rs.getString("marca_coluna"));
                col.setFase_coluna(rs.getString("fase_coluna"));
                col.setTamanho_coluna(rs.getString("tamanho_coluna"));
                col.setDiametro_coluna(rs.getString("diametro_coluna"));
                col.setParticula_coluna(rs.getString("particula_coluna"));
                col.setPart_number_coluna(rs.getString("part_number_coluna"));
                col.setSerial_number_coluna(rs.getString("serial_number_coluna"));
                col.setData_ativacao_coluna(rs.getTimestamp("data_ativacao_coluna"));
                col.setData_descarte_coluna(rs.getTimestamp("data_descarte_coluna"));
                col.setData_performance(rs.getTimestamp("data_performance"));
                userAtivacao.setUser(rs.getString("user_name_ativacao"));
                col.setUser_ativacao(userAtivacao);
                userDescarte.setUser(rs.getString("user_name_descarte"));
                col.setUser_descarte(userDescarte);
                userPerformance.setUser(rs.getString("user_name_performance"));
                col.setUser_performance(userPerformance);
                col.setVaga_coluna(rs.getInt("vaga_coluna_id"));
                col.setObs_coluna(rs.getString("obs_coluna"));
                col.setData_verificacao(rs.getTimestamp("data_verificacao"));
                userVerificacao.setUser(rs.getString("user_verificacao"));
                col.setUser_verificacao(userVerificacao);
                col.setObs_verificacao(rs.getString("obs_verificacao"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectUltimaColuna(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_coluna.cod_coluna "
                    + "FROM tb_coluna WHERE tb_coluna.tipo_coluna = ? "
                    + "ORDER BY tb_coluna.cod_coluna DESC ");
            stmt.setString(1, col.getTipo_coluna());
            rs = stmt.executeQuery();
            if (rs.next()) {
                col.setCodigo_coluna(rs.getInt("cod_coluna") + 1);
            } else {
                col.setCodigo_coluna(1);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectUltimaColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteColuna(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_coluna WHERE coluna_id = ?");
            stmt.setInt(1, col.getColuna_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public static void printColuna(String user, String setor, int status) {
        Connection conn = ConnectionFactory.getConnection();
        String where = "";
        String src = Reports.getSrc() + "Colunas.jasper";
        if ((!"Todos".equals(setor) && status != 0)) {
            where = "AND tb_setor.sigla_setor = '" + setor + "' ";
            if (status == 1) {
                where = where + "AND tb_coluna.data_descarte_coluna is null ";
            } else if (status == 2) {
                where = where + "AND tb_coluna.data_descarte_coluna is not null ";
                src = Reports.getSrc() + "ColunasDescartadas.jasper";
            } else if (status == 3) {
                where = where + "AND tb_coluna.data_performance is null ";
            }
        } else {
            if ("Todos".equals(setor)) {
                if (status != 0) {
                    if (status == 1) {
                        where = "AND tb_coluna.data_descarte_coluna is null ";
                    } else if (status == 2) {
                        where = "AND tb_coluna.data_descarte_coluna is not null ";
                        src = Reports.getSrc() + "ColunasDescartadas.jasper";
                    } else if (status == 3) {
                        where = "AND tb_coluna.data_performance is null ";
                    }
                }
            } else {
                where = "AND tb_setor.sigla_setor = '" + setor + "' ";
            }
        }
        String stmt = "SELECT tb_coluna.*, tb_metodologia.cod_metodo, "
                + "tb_metodologia.metodo, tb_coluna.metodo_versao as versao, tb_analise.analise, tb_analise.sigla_analise, "
                + "tb_setor.*, tb_analise_finalidade.analise_finalidade, "
                + "CAST(round(CAST(tb_coluna.vaga_coluna_id AS FLOAT)/19 + 0.45,0) AS INTEGER) as gaveta "
                + "FROM tb_coluna "
                + "LEFT JOIN tb_metodologia "
                + "ON tb_coluna.metodo_id = tb_metodologia.metodo_id "
                + "LEFT JOIN tb_analise "
                + "ON tb_coluna.analise_id = tb_analise.analise_id "
                + "LEFT JOIN tb_analise_finalidade "
                + "ON tb_coluna.analise_finalidade_id = tb_analise_finalidade.analise_finalidade_id "
                + "LEFT JOIN tb_setor "
                + "ON tb_coluna.setor_id = tb_setor.setor_id "
                + "WHERE 1 = 1 " + where
                + " ORDER BY tb_coluna.vaga_coluna_id ASC ";
        HashMap map = new HashMap();
        map.put("sql", stmt);
        map.put("User_name", user);
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printColuna");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printEtiquetaColuna(String id, String vaga) {
        Connection conn = ConnectionFactory.getConnection();

        HashMap map = new HashMap();
        map.put("id_coluna", id);
        map.put("vaga", vaga);
        String src = Reports.getSrc() + "EtiquetaColuna.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printEtiquetaColuna");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    //tb_log_coluna
    public void createLogColuna(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_coluna "
                    + "(coluna_id, system_name, user_name, "
                    + "data_registro, data_inicio, "
                    + "sentido, precoluna, prefiltro, log_campanha_id) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, col.getColuna_id());
            stmt.setString(2, col.getCromatografo().getSystem_name());
            stmt.setString(3, col.getUser_log_coluna().getUser());
            stmt.setTimestamp(4, col.getData_registro_log());
            stmt.setTimestamp(5, col.getData_inicio_log());
            stmt.setInt(6, (col.getSentido()) ? 1 : 0);
            stmt.setInt(7, (col.getPrefiltro()) ? 1 : 0);
            stmt.setInt(8, (col.getPrecoluna()) ? 1 : 0);
            stmt.setInt(9, col.getCampanha().getLog_campanha_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createLogColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateFimLogColuna(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_coluna SET "
                    + "data_registro = ?, data_fim = ? "
                    + "WHERE log_coluna_id = ?");
            stmt.setTimestamp(1, col.getData_registro_log());
            stmt.setTimestamp(2, col.getData_fim_log());
            stmt.setInt(3, col.getLog_coluna_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateFimLogColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Coluna> readColunaBySystem(String system_name) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coluna> colunas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(100) tb_log_coluna.log_coluna_id, tb_coluna.*, tb_log_campanha.nome_campanha, "
                    + "tb_metodologia.metodo, tb_metodologia.cod_metodo, tb_analise.analise, tb_setor.setor, "
                    + "tb_log_coluna.user_name, tb_log_coluna.system_name, tb_log_coluna.data_registro  "
                    + "FROM tb_log_coluna "
                    + "LEFT JOIN tb_coluna "
                    + "ON tb_log_coluna.coluna_id = tb_coluna.coluna_id "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_coluna.metodo_id = tb_metodologia.metodo_id "
                    + "LEFT JOIN tb_analise "
                    + "ON tb_coluna.analise_id = tb_analise.analise_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_coluna.setor_id = tb_setor.setor_id "
                    + "LEFT JOIN tb_log_campanha "
                    + "ON tb_log_coluna.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "WHERE tb_log_coluna.system_name = ?  "
                    + "ORDER BY tb_log_coluna.log_coluna_id DESC ");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Coluna col = new Coluna();
                Usuario user = new Usuario();
                Analise anls = new Analise();
                Setor setor = new Setor();
                Metodologia mtd = new Metodologia();
                Cromatografo equip = new Cromatografo();
                Campanha camp = new Campanha();
                camp.setNome_campanha(rs.getString("nome_campanha"));
                col.setCampanha(camp);
                col.setColuna_id(rs.getInt("coluna_id"));
                col.setCodigo_coluna(rs.getInt("cod_coluna"));
                col.setCodigo_sap_coluna(rs.getString("codigo_sap_coluna"));
                setor.setSetor(rs.getString("setor"));
                col.setSetor(setor);
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                col.setMetodologia(mtd);
                anls.setAnalise(rs.getString("analise"));
                col.setAnalise(anls);
                col.setTipo_coluna(rs.getString("tipo_coluna"));
                col.setFabricante_coluna(rs.getString("fabricante_coluna"));
                col.setMarca_coluna(rs.getString("marca_coluna"));
                col.setFase_coluna(rs.getString("fase_coluna"));
                col.setTamanho_coluna(rs.getString("tamanho_coluna"));
                col.setDiametro_coluna(rs.getString("diametro_coluna"));
                col.setParticula_coluna(rs.getString("particula_coluna"));
                col.setPart_number_coluna(rs.getString("part_number_coluna"));
                col.setSerial_number_coluna(rs.getString("serial_number_coluna"));
                col.setData_ativacao_coluna(rs.getTimestamp("data_ativacao_coluna"));
                col.setData_descarte_coluna(rs.getTimestamp("data_descarte_coluna"));
                col.setVaga_coluna(rs.getInt("vaga_coluna_id"));
                col.setObs_coluna(rs.getString("obs_coluna"));
                col.setData_registro_log(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_name"));
                equip.setSystem_name(rs.getString("system_name"));
                col.setCromatografo(equip);
                col.setUser_log_coluna(user);
                colunas.add(col);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readColunaBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return colunas;
    }

    public List<Coluna> readColunaByHistorico(Integer coluna_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coluna> colunas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_coluna.coluna_id, tb_log_campanha.nome_campanha, "
                    + "tb_log_campanha.data_inicio, tb_log_campanha.data_fim, "
                    + "tb_log_coluna.user_name, tb_log_coluna.system_name "
                    + "FROM tb_log_coluna "
                    + "LEFT JOIN tb_coluna "
                    + "ON tb_log_coluna.coluna_id = tb_coluna.coluna_id "
                    + "LEFT JOIN tb_log_campanha "
                    + "ON tb_log_coluna.log_campanha_id = tb_log_campanha.log_campanha_id "
                    + "WHERE tb_coluna.coluna_id = ?  "
                    + "ORDER BY tb_log_coluna.log_coluna_id DESC ");
            stmt.setInt(1, coluna_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Coluna col = new Coluna();
                Usuario user = new Usuario();
                Cromatografo equip = new Cromatografo();
                Campanha camp = new Campanha();
                col.setColuna_id(rs.getInt("coluna_id"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                camp.setData_inicio(rs.getTimestamp("data_inicio"));
                camp.setData_fim(rs.getTimestamp("data_fim"));
                col.setCampanha(camp);
                user.setUser(rs.getString("user_name"));
                col.setUser_log_coluna(user);
                equip.setSystem_name(rs.getString("system_name"));
                col.setCromatografo(equip);
                colunas.add(col);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readColunaByHistorico");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return colunas;
    }

    public void selectUltimaColunaBySystem(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_coluna.log_coluna_id, tb_coluna.*, "
                    + "tb_metodologia.metodo, tb_analise.analise, tb_setor.setor, "
                    + "tb_log_coluna.user_name, tb_log_coluna.system_name, "
                    + "tb_log_coluna.data_registro, tb_log_coluna.data_fim, "
                    + "tb_log_coluna.sentido, tb_log_coluna.precoluna, tb_log_coluna.prefiltro "
                    + "FROM tb_log_coluna "
                    + "LEFT JOIN tb_coluna "
                    + "ON tb_log_coluna.coluna_id = tb_coluna.coluna_id "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_coluna.metodo_id = tb_metodologia.metodo_id "
                    + "LEFT JOIN tb_analise "
                    + "ON tb_coluna.analise_id = tb_analise.analise_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_coluna.setor_id = tb_setor.setor_id  "
                    + "WHERE tb_log_coluna.system_name = ?  "
                    + "ORDER BY tb_log_coluna.log_coluna_id DESC ");
            stmt.setString(1, col.getCromatografo().getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                Analise anls = new Analise();
                Setor setor = new Setor();
                Metodologia mtd = new Metodologia();
                Cromatografo equip = new Cromatografo();
                col.setLog_coluna_id(rs.getInt("log_coluna_id"));
                col.setColuna_id(rs.getInt("coluna_id"));
                col.setCodigo_coluna(rs.getInt("cod_coluna"));
                col.setCodigo_sap_coluna(rs.getString("codigo_sap_coluna"));
                setor.setSetor(rs.getString("setor"));
                col.setSetor(setor);
                mtd.setMetodo(rs.getString("metodo"));
                col.setMetodologia(mtd);
                anls.setAnalise(rs.getString("analise"));
                col.setAnalise(anls);
                col.setTipo_coluna(rs.getString("tipo_coluna"));
                col.setFabricante_coluna(rs.getString("fabricante_coluna"));
                col.setMarca_coluna(rs.getString("marca_coluna"));
                col.setFase_coluna(rs.getString("fase_coluna"));
                col.setTamanho_coluna(rs.getString("tamanho_coluna"));
                col.setDiametro_coluna(rs.getString("diametro_coluna"));
                col.setParticula_coluna(rs.getString("particula_coluna"));
                col.setPart_number_coluna(rs.getString("part_number_coluna"));
                col.setSerial_number_coluna(rs.getString("serial_number_coluna"));
                col.setData_ativacao_coluna(rs.getTimestamp("data_ativacao_coluna"));
                col.setData_descarte_coluna(rs.getTimestamp("data_descarte_coluna"));
                col.setVaga_coluna(rs.getInt("vaga_coluna_id"));
                col.setObs_coluna(rs.getString("obs_coluna"));
                col.setData_registro_log(rs.getTimestamp("data_registro"));
                col.setData_fim_log(rs.getTimestamp("data_fim"));
                user.setUser(rs.getString("user_name"));
                equip.setSystem_name(rs.getString("system_name"));
                col.setCromatografo(equip);
                col.setSentido(rs.getBoolean("sentido"));
                col.setPrefiltro(rs.getBoolean("prefiltro"));
                col.setPrecoluna(rs.getBoolean("precoluna"));
                col.setUser_log_coluna(user);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectUltimaColunaBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public int selectIDUltimaColunaBySystem(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_coluna.log_coluna_id "
                    + "FROM tb_log_coluna "
                    + "WHERE tb_log_coluna.system_name = ?  "
                    + "ORDER BY tb_log_coluna.log_coluna_id DESC ");
            stmt.setString(1, col.getCromatografo().getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("log_coluna_id");
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectIDUltimaColunaBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return id;
    }

    //tb_vaga_coluna
    public List<Coluna> readVagaColuna() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coluna> colunas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_vaga_coluna.*, "
                    + "tb_coluna.cod_coluna, tb_coluna.tipo_coluna, tb_coluna.coluna_id "
                    + "FROM tb_vaga_coluna "
                    + "LEFT JOIN tb_coluna "
                    + "ON tb_vaga_coluna.vaga_coluna_id = tb_coluna.vaga_coluna_id "
                    + " ");
            // + "WHERE tb_coluna.cod_coluna IS NULL ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Coluna col = new Coluna();
                col.setVaga_coluna(rs.getInt("vaga_coluna_id"));
                col.setColuna_id(rs.getInt("coluna_id"));
                col.setCodigo_coluna(rs.getInt("cod_coluna"));
                col.setTipo_coluna(rs.getString("tipo_coluna"));
                colunas.add(col);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readVagaColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return colunas;
    }

    public void selectVagaColuna(Coluna col) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_vaga_coluna.*, "
                    + "tb_coluna.cod_coluna, tb_coluna.tipo_coluna, tb_coluna.coluna_id "
                    + "FROM tb_vaga_coluna "
                    + "LEFT JOIN tb_coluna "
                    + "ON tb_vaga_coluna.vaga_coluna_id = tb_coluna.vaga_coluna_id "
                    + "WHERE tb_vaga_coluna.vaga_coluna_id = ?  ");
            stmt.setInt(1, col.getVaga_coluna());
            rs = stmt.executeQuery();
            while (rs.next()) {
                col.setColuna_id(rs.getInt("coluna_id"));
                col.setCodigo_coluna(rs.getInt("cod_coluna"));
                col.setTipo_coluna(rs.getString("tipo_coluna"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectVagaColuna");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }
}
