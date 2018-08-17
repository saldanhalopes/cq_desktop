package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Analise;
import Classes.Modelo.Campanha;
import Classes.Modelo.Cromatografo;
import Classes.Modelo.Setor;
import Classes.Modelo.Usuario;
import Classes.Util.Reports;
import Classes.Util.EMail;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author rafael.lopes
 */
public class CampanhaDAO {

    public void createCampanhas(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_campanha "
                    + "(nome_campanha, metodos, system_name, "
                    + "obs_campanha, ordem, sigla_setor, "
                    + "cor, analise_id, data_previsao)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, camp.getNome_campanha());
            stmt.setString(2, camp.getMetodos());
            stmt.setString(3, camp.getCromatografo().getSystem_name());
            stmt.setString(4, camp.getObs_campanha());
            stmt.setInt(5, camp.getOrdem());
            stmt.setString(6, camp.getSetor().getSigla_setor());
            stmt.setString(7, camp.getCor());
            stmt.setInt(8, camp.getAnalise().getAnalise_id());
            stmt.setTimestamp(9, camp.getPrevisao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createCampanhas");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void createCampanhaEquipamento(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_log_campanha "
                    + "(nome_campanha, metodos, system_name, "
                    + "obs_campanha, sigla_setor, "
                    + "cor, analise_id, "
                    + "data_previsao, user_inicio, data_inicio)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, camp.getNome_campanha());
            stmt.setString(2, camp.getMetodos());
            stmt.setString(3, camp.getCromatografo().getSystem_name());
            stmt.setString(4, camp.getObs_campanha());
            stmt.setString(5, camp.getSetor().getSigla_setor());
            stmt.setString(6, camp.getCor());
            stmt.setInt(7, camp.getAnalise().getAnalise_id());
            stmt.setTimestamp(8, camp.getPrevisao());
            stmt.setString(9, camp.getUser_inicio().getUser());
            stmt.setTimestamp(10, camp.getData_inicio());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createCampanhaEquipamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Campanha> readCampanha() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Campanha> campanhas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_log_campanha");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campanha camp = new Campanha();
                Usuario user_inicio = new Usuario();
                Usuario user_fim = new Usuario();
                Cromatografo equip = new Cromatografo();
                camp.setLog_campanha_id(rs.getInt("log_campanha_id"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                camp.setMetodos(rs.getString("metodos"));
                equip.setSystem_name(rs.getString("system_name"));
                user_inicio.setUser(rs.getString("user_inicio"));
                camp.setUser_inicio(user_inicio);
                camp.setData_inicio(rs.getTimestamp("data_inicio"));
                user_fim.setUser(rs.getString("user_fim"));
                camp.setUser_fim(user_fim);
                camp.setData_fim(rs.getTimestamp("data_fim"));
                camp.setCromatografo(equip);
                camp.setObs_campanha(rs.getString("obs_campanha"));
                campanhas.add(camp);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readCampanha");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return campanhas;
    }

    public void updateCampanhas(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_campanha SET "
                    + "nome_campanha = ?, metodos = ?, system_name = ?, "
                    + "obs_campanha = ?, sigla_setor = ?, cor = ?, "
                    + "analise_id = ?, data_previsao = ? "
                    + "WHERE log_campanha_id = ?");
            stmt.setString(1, camp.getNome_campanha());
            stmt.setString(2, camp.getMetodos());
            stmt.setString(3, camp.getCromatografo().getSystem_name());
            stmt.setString(4, camp.getObs_campanha());
            stmt.setString(5, camp.getSetor().getSigla_setor());
            stmt.setString(6, camp.getCor());
            stmt.setInt(7, camp.getAnalise().getAnalise_id());
            stmt.setTimestamp(8, camp.getPrevisao());
            stmt.setInt(9, camp.getLog_campanha_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createCampanhas");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateCampanha(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_campanha SET "
                    + "nome_campanha = ?, metodos = ?, system_name = ?, "
                    + "user_inicio = ?, data_inicio = ?, obs_campanha "
                    + "WHERE log_campanha_id = ?");
            stmt.setString(1, camp.getNome_campanha());
            stmt.setString(2, camp.getMetodos());
            stmt.setString(3, camp.getCromatografo().getSystem_name());
            stmt.setString(4, camp.getUser_inicio().getUser());
            stmt.setTimestamp(5, camp.getData_inicio());
            stmt.setString(6, camp.getObs_campanha());
            stmt.setInt(7, camp.getLog_campanha_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateCampanha");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateInicioCampanha(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_campanha SET "
                    + "system_name = ?, user_inicio = ?, data_inicio = ? "
                    + "WHERE log_campanha_id = ?");
            stmt.setString(1, camp.getCromatografo().getSystem_name());
            stmt.setString(2, camp.getUser_inicio().getUser());
            stmt.setTimestamp(3, camp.getData_inicio());
            stmt.setInt(4, camp.getLog_campanha_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateCampanha");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateFimCampanha(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_log_campanha SET "
                    + "user_fim = ?, data_fim = ?, cor = ? "
                    + "WHERE log_campanha_id = ?");
            stmt.setString(1, camp.getUser_fim().getUser());
            stmt.setTimestamp(2, camp.getData_fim());
            stmt.setString(3, camp.getCor());
            stmt.setInt(4, camp.getLog_campanha_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateFimCampanha");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateOrdemPrioreidade(List lista) {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            for (Object listas : lista) {
                stmt.addBatch(listas.toString());
            }
            stmt.executeBatch();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateOrdemPrioreidade");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectCampanha(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select tb_log_campanha.* From tb_log_campanha "
                    + "WHERE log_campanha_id = ?");
            stmt.setInt(1, camp.getLog_campanha_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user_inicio = new Usuario();
                Usuario user_fim = new Usuario();
                Cromatografo equip = new Cromatografo();
                camp.setLog_campanha_id(rs.getInt("log_campanha_id"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                camp.setMetodos(rs.getString("metodos"));
                equip.setSystem_name(rs.getString("system_name"));
                user_inicio.setUser(rs.getString("user_inicio"));
                camp.setUser_inicio(user_inicio);
                camp.setData_inicio(rs.getTimestamp("data_inicio"));
                user_fim.setUser(rs.getString("user_fim"));
                camp.setUser_fim(user_fim);
                camp.setData_fim(rs.getTimestamp("data_fim"));
                camp.setCromatografo(equip);
                camp.setObs_campanha(rs.getString("obs_campanha"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectCampanha");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectCampanhaBySystem(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_campanha.*, tb_analise.analise "
                    + "FROM tb_log_campanha "
                    + "LEFT JOIN tb_analise "
                    + "ON tb_log_campanha.analise_id = tb_analise.analise_id "
                    + "WHERE tb_log_campanha.system_name = ? "
                    + "AND tb_log_campanha.user_inicio IS NOT NULL "
                    + "AND tb_log_campanha.user_fim IS NULL "
                    + "ORDER BY tb_log_campanha.log_campanha_id DESC ");
            stmt.setString(1, camp.getCromatografo().getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user_inicio = new Usuario();
                Cromatografo equip = new Cromatografo();
                Analise anls = new Analise();
                camp.setLog_campanha_id(rs.getInt("log_campanha_id"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                camp.setMetodos(rs.getString("metodos"));
                equip.setSystem_name(rs.getString("system_name"));
                camp.setCromatografo(equip);
                user_inicio.setUser(rs.getString("user_inicio"));
                camp.setUser_inicio(user_inicio);
                camp.setData_inicio(rs.getTimestamp("data_inicio"));
                camp.setObs_campanha(rs.getString("obs_campanha"));
                anls.setAnalise(rs.getString("analise"));
                camp.setAnalise(anls);
                camp.setPrevisao(rs.getTimestamp("data_previsao"));
                camp.setCor(rs.getString("cor"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectCampanhaBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public List<Campanha> selectCampanhaProgramacao(String _setor, String campo, String texto) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Campanha> campanhas = new ArrayList<>();
        String where = "";
        try {
            if (!"Todos".equals(_setor)) {
                where += " AND tb_log_campanha.sigla_setor = '" + _setor + "' ";
            }
            if (!texto.isEmpty()) {
                if ("Campanha".equals(campo)) {
                    where += " AND tb_log_campanha.nome_campanha LIKE '%" + texto + "%' ";
                }
                if ("Análise".equals(campo)) {
                    where += " AND tb_analise.analise LIKE '%" + texto + "%' ";
                }
                if ("Equipamento".equals(campo)) {
                    where += " AND tb_log_campanha.system_name LIKE '%" + texto + "%' ";
                }
                if ("Prioridade".equals(campo)) {
                    where += " AND tb_log_campanha.cor LIKE '%" + texto + "%' ";
                }
            }
            stmt = conn.prepareStatement("SELECT tb_log_campanha.*, tb_analise.analise "
                    + "FROM tb_log_campanha "
                    + "LEFT JOIN tb_analise "
                    + "ON tb_log_campanha.analise_id = tb_analise.analise_id "
                    + "WHERE tb_log_campanha.user_inicio IS NULL " + where
                    + "ORDER BY tb_log_campanha.ordem ASC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campanha camp = new Campanha();
                Usuario user_inicio = new Usuario();
                Usuario user_fim = new Usuario();
                Cromatografo equip = new Cromatografo();
                Setor setor = new Setor();
                Analise anls = new Analise();
                camp.setLog_campanha_id(rs.getInt("log_campanha_id"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                camp.setMetodos(rs.getString("metodos"));
                equip.setSystem_name(rs.getString("system_name"));
                user_inicio.setUser(rs.getString("user_inicio"));
                camp.setUser_inicio(user_inicio);
                camp.setData_inicio(rs.getTimestamp("data_inicio"));
                user_fim.setUser(rs.getString("user_fim"));
                camp.setUser_fim(user_fim);
                camp.setData_fim(rs.getTimestamp("data_fim"));
                camp.setCromatografo(equip);
                camp.setObs_campanha(rs.getString("obs_campanha"));
                camp.setOrdem(rs.getInt("ordem"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                camp.setSetor(setor);
                anls.setAnalise(rs.getString("analise"));
                camp.setAnalise(anls);
                camp.setPrevisao(rs.getTimestamp("data_previsao"));
                camp.setCor(rs.getString("cor"));
                campanhas.add(camp);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectCampanhaProgramacao");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return campanhas;
    }

    public List<Campanha> selectAllCampanha(String status, String campo, String texto, String _setor) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Campanha> campanhas = new ArrayList<>();
        String where = "";
        try {
            if ("Em Análise".equals(status)) {
                where = " AND tb_log_campanha.user_fim IS NULL ";
            }
            if ("Finalizados".equals(status)) {
                where += " AND tb_log_campanha.user_fim IS NOT NULL ";
            }
            if (!"Todos".equals(_setor)) {
                where += " AND tb_log_campanha.sigla_setor = '" + _setor + "' ";
            }
            if (!texto.isEmpty()) {
                if ("Campanha".equals(campo)) {
                    where += " AND tb_log_campanha.nome_campanha LIKE '%" + texto + "%' ";
                }
                if ("Análise".equals(campo)) {
                    where += " AND tb_analise.analise LIKE '%" + texto + "%' ";
                }
                if ("Equipamento".equals(campo)) {
                    where += " AND tb_log_campanha.system_name LIKE '%" + texto + "%' ";
                }
                if ("Prioridade".equals(campo)) {
                    where += " AND tb_log_campanha.cor LIKE '%" + texto + "%' ";
                }
            }
            stmt = conn.prepareStatement("SELECT TOP(1000) tb_log_campanha.*, tb_analise.analise "
                    + "FROM tb_log_campanha "
                    + "LEFT JOIN tb_analise "
                    + "ON tb_log_campanha.analise_id = tb_analise.analise_id "
                    + "WHERE tb_log_campanha.user_inicio IS NOT NULL " + where
                    + "ORDER BY tb_log_campanha.log_campanha_id DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campanha camp = new Campanha();
                Usuario user_inicio = new Usuario();
                Usuario user_fim = new Usuario();
                Cromatografo equip = new Cromatografo();
                Analise anls = new Analise();
                Setor setor = new Setor();
                camp.setLog_campanha_id(rs.getInt("log_campanha_id"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                camp.setMetodos(rs.getString("metodos"));
                equip.setSystem_name(rs.getString("system_name"));
                user_inicio.setUser(rs.getString("user_inicio"));
                camp.setUser_inicio(user_inicio);
                camp.setData_inicio(rs.getTimestamp("data_inicio"));
                user_fim.setUser(rs.getString("user_fim"));
                camp.setUser_fim(user_fim);
                camp.setData_fim(rs.getTimestamp("data_fim"));
                camp.setCromatografo(equip);
                setor.setSigla_setor(rs.getString("sigla_setor"));
                camp.setSetor(setor);
                camp.setObs_campanha(rs.getString("obs_campanha"));
                anls.setAnalise(rs.getString("analise"));
                camp.setAnalise(anls);
                camp.setPrevisao(rs.getTimestamp("data_previsao"));
                camp.setCor(rs.getString("cor"));
                campanhas.add(camp);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAllCampanha");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return campanhas;
    }

    public List<Campanha> selectAllCampanhaBySystem(String system_name) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Campanha> campanhas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(100) tb_log_campanha.* "
                    + "FROM tb_log_campanha "
                    + "WHERE tb_log_campanha.system_name = ? AND tb_log_campanha.user_inicio IS NOT NULL "
                    + "ORDER BY tb_log_campanha.log_campanha_id DESC ");
            stmt.setString(1, system_name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campanha camp = new Campanha();
                Usuario user_inicio = new Usuario();
                Usuario user_fim = new Usuario();
                Cromatografo equip = new Cromatografo();
                camp.setLog_campanha_id(rs.getInt("log_campanha_id"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                camp.setMetodos(rs.getString("metodos"));
                equip.setSystem_name(rs.getString("system_name"));
                user_inicio.setUser(rs.getString("user_inicio"));
                camp.setUser_inicio(user_inicio);
                camp.setData_inicio(rs.getTimestamp("data_inicio"));
                user_fim.setUser(rs.getString("user_fim"));
                camp.setUser_fim(user_fim);
                camp.setData_fim(rs.getTimestamp("data_fim"));
                camp.setCromatografo(equip);
                camp.setObs_campanha(rs.getString("obs_campanha"));
                campanhas.add(camp);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAllCampanhaBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return campanhas;
    }

    public void selectCampanhaFimBySystem(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_campanha.* "
                    + "FROM tb_log_campanha "
                    + "WHERE tb_log_campanha.system_name = ? "
                    + "AND tb_log_campanha.user_inicio IS NOT NULL "
                    + "AND tb_log_campanha.user_fim IS NULL "
                    + "ORDER BY tb_log_campanha.log_campanha_id DESC ");
            stmt.setString(1, camp.getCromatografo().getSystem_name());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user_inicio = new Usuario();
                Usuario user_fim = new Usuario();
                Cromatografo equip = new Cromatografo();
                camp.setLog_campanha_id(rs.getInt("log_campanha_id"));
                camp.setNome_campanha(rs.getString("nome_campanha"));
                camp.setMetodos(rs.getString("metodos"));
                equip.setSystem_name(rs.getString("system_name"));
                camp.setCromatografo(equip);
                user_inicio.setUser(rs.getString("user_inicio"));
                camp.setUser_inicio(user_inicio);
                user_fim.setUser(rs.getString("user_fim"));
                camp.setUser_fim(user_fim);
                camp.setData_inicio(rs.getTimestamp("data_inicio"));
                camp.setData_fim(rs.getTimestamp("data_fim"));
                camp.setObs_campanha(rs.getString("obs_campanha"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectCampanhaFimBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public int selectUltimaCampanhaByOrdem() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int ultimo = 0;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) tb_log_campanha.ordem FROM tb_log_campanha "
                    + "WHERE tb_log_campanha.ordem IS NOT NULL "
                    + "AND tb_log_campanha.user_inicio IS NULL "
                    + "ORDER BY tb_log_campanha.ordem DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ultimo = rs.getInt("ordem");
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectCampanhaFimBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return ultimo;
    }

    public void deleteCampanha(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_log_campanha WHERE log_campanha_id = ?");
            stmt.setInt(1, camp.getLog_campanha_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteCampanha");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public static void sendToStatusEquipamento(Usuario user, String setor) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("setor", setor);
        String src = Reports.getSrc() + "StatusEquipamento.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            String arquivo = "C:\\CQ_Desktop\\StatusEquipamento.pdf";
            JasperExportManager.exportReportToPdfFile(jpt, arquivo);
            //Runtime.getRuntime().exec("cmd /c start M:\\controledequalidade_compartilhada\\CQ_Desktop\\Relatorios\\StatusEquipamento.pdf");
            File file = new File(arquivo);
            EMail email = new EMail();
            email.enviaEmailComAnexo(arquivo, "StatusEquipamento.pdf", "CQ_Desktop - Status dos Equipamento",
                    user.getEmail(), user.getName(), "Segue em anexo Status dos Equipamentos");
            file.deleteOnExit();
        } catch (JRException | EmailException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void sendToProximasCampanhas(Usuario user, String setor) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("setor", setor);
        String src = Reports.getSrc() + "ProximasCampanha.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            String arquivo = "C:\\CQ_Desktop\\ProximasCampanhas.pdf";
            JasperExportManager.exportReportToPdfFile(jpt, arquivo);
            //Runtime.getRuntime().exec("cmd /c start M:\\controledequalidade_compartilhada\\CQ_Desktop\\Relatorios\\StatusEquipamento.pdf");
            File file = new File(arquivo);
            EMail email = new EMail();
            email.enviaEmailComAnexo(arquivo, "ProximasCampanhas.pdf", "CQ_Desktop - Próximas Campanhas",
                    user.getEmail(), user.getName(), "Segue em anexo as Próximas Campanhas");
            file.deleteOnExit();
        } catch (JRException | EmailException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printStatusCampanhas(String user, String where) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("where", where);
        String src = Reports.getSrc() + "StatusCampanha.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printStatusCampanhas");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printStatusEquipamento(String user, String setor) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("setor", setor);
        String src = Reports.getSrc() + "StatusEquipamento.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printStatusEquipamento");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printProximasCampanhas(String user, String setor) {
        Connection conn = ConnectionFactory.getConnection();
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("setor", setor);
        String src = Reports.getSrc() + "ProximasCampanha.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printProximasCampanhas");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public void selectCampanhaById(Campanha camp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_log_campanha.*, tb_analise.analise "
                    + "FROM tb_log_campanha "
                    + "LEFT JOIN tb_analise "
                    + "ON tb_log_campanha.analise_id = tb_analise.analise_id "
                    + "WHERE tb_log_campanha.log_campanha_id = ?");
            stmt.setInt(1, camp.getLog_campanha_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                Cromatografo equip = new Cromatografo();
                Analise anls = new Analise();
                camp.setNome_campanha(rs.getString("nome_campanha"));
                camp.setMetodos(rs.getString("metodos"));
                equip.setSystem_name(rs.getString("system_name"));
                camp.setCromatografo(equip);
                camp.setObs_campanha(rs.getString("obs_campanha"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                anls.setAnalise(rs.getString("analise"));
                camp.setAnalise(anls);
                camp.setPrevisao(rs.getTimestamp("data_previsao"));
                camp.setCor(rs.getString("cor"));
                camp.setSetor(setor);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectCampanhaBySystem");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

}
