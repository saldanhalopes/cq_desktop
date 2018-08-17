package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Frm.Principal.FrmCarregando;
import Classes.Modelo.Analise;
import Classes.Modelo.Cromatografo;
import Classes.Modelo.HistoricoMetodologia;
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
public class HistoricoMetodologiaDAO {

    public void createHistoricoMetodologia(HistoricoMetodologia histmtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_historico_metodologia "
                    + "(metodo_id, tipo_ajuste, system_name,"
                    + "analise, coluna, fase_movel, codicao_inicial, "
                    + "ajuste, obs, user_registro, data_registro, padronizar) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, histmtd.getMetodologia().getMetodo_id());
            stmt.setString(2, histmtd.getTipo_ajuste());
            stmt.setString(3, histmtd.getCromatografo().getSystem_name());
            stmt.setString(4, histmtd.getAnalise().getAnalise());
            stmt.setString(5, histmtd.getColuna());
            stmt.setString(6, histmtd.getFase_movel());
            stmt.setString(7, histmtd.getCodicao_inicial());
            stmt.setString(8, histmtd.getAjuste());
            stmt.setString(9, histmtd.getObs());
            stmt.setString(10, histmtd.getUser_registro().getUser());
            stmt.setTimestamp(11, histmtd.getData_registro());
            stmt.setInt(12, (histmtd.getPadronizar()) ? 1 : 0);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createHistoricoMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<HistoricoMetodologia> readHistoricoMetodologia() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<HistoricoMetodologia> histmtds = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_historico_metodologia");
            rs = stmt.executeQuery();
            while (rs.next()) {
                HistoricoMetodologia histmtd = new HistoricoMetodologia();
                Metodologia mtd = new Metodologia();
                Cromatografo equip = new Cromatografo();
                Analise anls = new Analise();
                Usuario user_registro = new Usuario();
                histmtd.setHistorico_metodologia_id(rs.getInt("historico_metodologia_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                histmtd.setTipo_ajuste(rs.getString("tipo_ajuste"));
                equip.setSystem_name(rs.getString("system_name"));
                anls.setAnalise(rs.getString("analise"));
                histmtd.setColuna(rs.getString("coluna"));
                histmtd.setFase_movel(rs.getString("fase_movel"));
                histmtd.setCodicao_inicial(rs.getString("codicao_inicial"));
                histmtd.setAjuste(rs.getString("ajuste"));
                histmtd.setObs(rs.getString("obs"));
                user_registro.setUser(rs.getString("user_registro"));
                histmtd.setData_registro(rs.getTimestamp("data_registro"));
                histmtd.setPadronizar(rs.getBoolean("padronizar"));
                histmtd.setMetodologia(mtd);
                histmtd.setCromatografo(equip);
                histmtd.setAnalise(anls);
                histmtd.setUser_registro(user_registro);
                histmtds.add(histmtd);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readHistoricoMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return histmtds;
    }

    public List<HistoricoMetodologia> readHistoricoMetodologia(Integer metodo_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<HistoricoMetodologia> histmtds = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select * FROM tb_historico_metodologia "
                    + "WHERE metodo_id = ?");
            stmt.setInt(1, metodo_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                HistoricoMetodologia histmtd = new HistoricoMetodologia();
                Metodologia mtd = new Metodologia();
                Cromatografo equip = new Cromatografo();
                Analise anls = new Analise();
                Usuario user_registro = new Usuario();
                histmtd.setHistorico_metodologia_id(rs.getInt("historico_metodologia_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                histmtd.setTipo_ajuste(rs.getString("tipo_ajuste"));
                equip.setSystem_name(rs.getString("system_name"));
                anls.setAnalise(rs.getString("analise"));
                histmtd.setColuna(rs.getString("coluna"));
                histmtd.setFase_movel(rs.getString("fase_movel"));
                histmtd.setCodicao_inicial(rs.getString("codicao_inicial"));
                histmtd.setAjuste(rs.getString("ajuste"));
                histmtd.setObs(rs.getString("obs"));
                user_registro.setUser(rs.getString("user_registro"));
                histmtd.setData_registro(rs.getTimestamp("data_registro"));
                histmtd.setPadronizar(rs.getBoolean("padronizar"));
                histmtd.setMetodologia(mtd);
                histmtd.setCromatografo(equip);
                histmtd.setAnalise(anls);
                histmtd.setUser_registro(user_registro);
                histmtds.add(histmtd);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readHistoricoMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return histmtds;
    }

    public void updateHistoricoMetodologia(HistoricoMetodologia histmtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_historico_metodologia SET "
                    + "tipo_ajuste = ?, system_name = ?, analise = ?, "
                    + "coluna = ?, fase_movel = ?, codicao_inicial = ?, "
                    + "ajuste = ?, obs = ?, user_registro = ?, "
                    + "data_registro = ?, padronizar = ? "
                    + "WHERE historico_metodologia_id = ?");
            stmt.setString(1, histmtd.getTipo_ajuste());
            stmt.setString(2, histmtd.getCromatografo().getSystem_name());
            stmt.setString(3, histmtd.getAnalise().getAnalise());
            stmt.setString(4, histmtd.getColuna());
            stmt.setString(5, histmtd.getFase_movel());
            stmt.setString(6, histmtd.getCodicao_inicial());
            stmt.setString(7, histmtd.getAjuste());
            stmt.setString(8, histmtd.getObs());
            stmt.setString(9, histmtd.getUser_registro().getUser());
            stmt.setTimestamp(10, histmtd.getData_registro());
            stmt.setInt(11, (histmtd.getPadronizar()) ? 1 : 0);
            stmt.setInt(12, histmtd.getHistorico_metodologia_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateHistoricoMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectHistoricoMetodologia(HistoricoMetodologia histmtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_historico_metodologia "
                    + "WHERE historico_metodologia_id = ?");
            stmt.setInt(1, histmtd.getHistorico_metodologia_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Cromatografo equip = new Cromatografo();
                Analise anls = new Analise();
                Usuario user_registro = new Usuario();
                histmtd.setHistorico_metodologia_id(rs.getInt("historico_metodologia_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                histmtd.setTipo_ajuste(rs.getString("tipo_ajuste"));
                equip.setSystem_name(rs.getString("system_name"));
                anls.setAnalise(rs.getString("analise"));
                histmtd.setColuna(rs.getString("coluna"));
                histmtd.setFase_movel(rs.getString("fase_movel"));
                histmtd.setCodicao_inicial(rs.getString("codicao_inicial"));
                histmtd.setAjuste(rs.getString("ajuste"));
                histmtd.setObs(rs.getString("obs"));
                user_registro.setUser(rs.getString("user_registro"));
                histmtd.setData_registro(rs.getTimestamp("data_registro"));
                histmtd.setPadronizar(rs.getBoolean("padronizar"));
                histmtd.setMetodologia(mtd);
                histmtd.setCromatografo(equip);
                histmtd.setAnalise(anls);
                histmtd.setUser_registro(user_registro);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectHistoricoMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteHistoricoMetodologia(HistoricoMetodologia histmtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_historico_metodologia "
                    + "WHERE historico_metodologia_id = ?");
            stmt.setInt(1, histmtd.getHistorico_metodologia_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteHistoricoMetodologia");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public static void printHistoricoAjuste(String user, Integer metodo_id) {
        Connection conn = ConnectionFactory.getConnection();
        FrmCarregando frm = new FrmCarregando();
        frm.setVisible(true);
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("metodo_id", metodo_id);
        String src = Reports.getSrc() + "HistoricoAjuste.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            frm.dispose();
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printHistoricoAjuste");
            frm.dispose();
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public static void printHistoricoAjusteGeral(String user) {
        Connection conn = ConnectionFactory.getConnection();
        FrmCarregando frm = new FrmCarregando();
        frm.setVisible(true);
        HashMap map = new HashMap();
        map.put("User_name", user);
        String src = Reports.getSrc() + "HistoricoAjusteGeral.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            frm.dispose();
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printHistoricoAjusteGeral");
            frm.dispose();
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

}
