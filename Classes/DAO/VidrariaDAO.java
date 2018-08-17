/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Setor;
import Classes.Modelo.Unidade;
import Classes.Modelo.Vidraria;
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
public class VidrariaDAO {

    public void createVidraria(Vidraria vidr) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_vidraria "
                    + "(vidraria_tipo_id, setor_id, volume, unidade_id, "
                    + "certificado, data_entrada, data_saida, obs_vidraria, responsavel_entrada) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, vidr.getVidraria_tipo_id());
            stmt.setInt(2, vidr.getSetor().getSetor_id());
            stmt.setString(3, vidr.getVolume());
            stmt.setInt(4, vidr.getUnidade().getUnidade_id());
            stmt.setString(5, vidr.getCertificado());
            stmt.setTimestamp(6, vidr.getData_entrada());
            stmt.setTimestamp(7, vidr.getData_saida());
            stmt.setString(8, vidr.getObs_vidraria());
            stmt.setString(9, System.getProperty("user"));
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createVidraria");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Vidraria> readVidraria(Integer limit, String set, int status) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vidraria> vidrarias = new ArrayList<>();
        try {
            String where = "";
            if ((!"".equals(set) && status != 0)) {
                where = "WHERE tb_setor.sigla_setor = '" + set + "' ";
                if (status == 1) {
                    where = where + "AND tb_vidraria.data_saida is null ";
                } else if (status == 2) {
                    where = where + "AND tb_vidraria.data_saida is not null ";
                }
            } else {
                if ("".equals(set)) {
                    if (status != 0) {
                        if (status == 1) {
                            where = "WHERE tb_vidraria.data_saida is null ";
                        } else if (status == 2) {
                            where = "WHERE tb_vidraria.data_saida is not null ";
                        }
                    }
                } else {
                    where = "WHERE tb_setor.sigla_setor = '" + set + "' ";
                }
            }
            stmt = conn.prepareStatement("SELECT TOP(?) tb_vidraria.vidraria_id, "
                    + "tb_vidraria_tipo.vidraria_tipo, tb_vidraria.volume, "
                    + "tb_unidade.sigla_unidade, tb_vidraria.certificado, "
                    + "tb_vidraria.data_entrada, tb_vidraria.data_saida, "
                    + "tb_setor.sigla_setor, tb_vidraria.obs_vidraria "
                    + "FROM tb_vidraria "
                    + "INNER JOIN tb_vidraria_tipo "
                    + "ON tb_vidraria.vidraria_tipo_id = tb_vidraria_tipo.vidraria_tipo_id "
                    + "INNER JOIN tb_setor "
                    + "ON tb_vidraria.setor_id = tb_setor.setor_id "
                    + "INNER JOIN tb_unidade "
                    + "ON tb_vidraria.unidade_id = tb_unidade.unidade_id "
                    + where
                    + "ORDER BY tb_vidraria.vidraria_id DESC");
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Vidraria vidr = new Vidraria();
                Unidade und = new Unidade();
                Setor setor = new Setor();
                vidr.setVidraria_id(rs.getInt("vidraria_id"));
                vidr.setVidraria_tipo(rs.getString("vidraria_tipo"));
                vidr.setVolume(rs.getString("volume"));
                und.setSigla_unidade(rs.getString("sigla_unidade"));
                vidr.setUnidade(und);
                vidr.setCertificado(rs.getString("certificado"));
                vidr.setData_entrada(rs.getTimestamp("data_entrada"));
                vidr.setData_saida(rs.getTimestamp("data_saida"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                vidr.setSetor(setor);
                vidr.setObs_vidraria(rs.getString("obs_vidraria"));
                vidrarias.add(vidr);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readVidraria");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return vidrarias;
    }

    public void updateVidraria(Vidraria vidr) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_vidraria SET "
                    + "vidraria_tipo_id = ?, setor_id = ?, volume = ?, unidade_id = ?, "
                    + "data_entrada = ?, certificado = ?, obs_vidraria = ?, responsavel_entrada = ? "
                    + "WHERE vidraria_id = ?");
            stmt.setInt(1, vidr.getVidraria_tipo_id());
            stmt.setInt(2, vidr.getSetor().getSetor_id());
            stmt.setString(3, vidr.getVolume());
            stmt.setInt(4, vidr.getUnidade().getUnidade_id());
            stmt.setTimestamp(5, vidr.getData_entrada());
            stmt.setString(6, vidr.getCertificado());
            stmt.setString(7, vidr.getObs_vidraria());
            stmt.setString(8, System.getProperty("user"));
            stmt.setInt(9, vidr.getVidraria_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateVidraria");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateBaixaVidraria(Vidraria vidr) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_vidraria SET "
                    + "data_saida = ?, responsavel_entrada = ? WHERE vidraria_id = ?");
            stmt.setTimestamp(1, vidr.getData_saida());
            stmt.setString(2, System.getProperty("user"));
            stmt.setInt(3, vidr.getVidraria_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateBaixaVidraria");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectVidraria(Vidraria vidr) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_vidraria.vidraria_id, "
                    + "tb_vidraria_tipo.vidraria_tipo, tb_vidraria.volume, "
                    + "tb_unidade.sigla_unidade, tb_vidraria.certificado, "
                    + "tb_vidraria.data_entrada, tb_vidraria.data_saida, "
                    + "tb_setor.sigla_setor, tb_vidraria.obs_vidraria "
                    + "FROM tb_vidraria "
                    + "INNER JOIN tb_vidraria_tipo "
                    + "ON tb_vidraria.vidraria_tipo_id = tb_vidraria_tipo.vidraria_tipo_id "
                    + "INNER JOIN tb_setor "
                    + "ON tb_vidraria.setor_id = tb_setor.setor_id "
                    + "INNER JOIN tb_unidade "
                    + "ON tb_vidraria.unidade_id = tb_unidade.unidade_id "
                    + "WHERE vidraria_id = ?");
            stmt.setInt(1, vidr.getVidraria_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Unidade und = new Unidade();
                Setor setor = new Setor();
                vidr.setVidraria_id(rs.getInt("vidraria_id"));
                vidr.setVidraria_tipo(rs.getString("vidraria_tipo"));
                vidr.setVolume(rs.getString("volume"));
                und.setSigla_unidade(rs.getString("sigla_unidade"));
                vidr.setUnidade(und);
                vidr.setCertificado(rs.getString("certificado"));
                vidr.setData_entrada(rs.getTimestamp("data_entrada"));
                vidr.setData_saida(rs.getTimestamp("data_saida"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                vidr.setSetor(setor);
                vidr.setObs_vidraria(rs.getString("obs_vidraria"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectVidraria");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteVidraria(Vidraria vidr) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_vidraria WHERE vidraria_id = ?");
            stmt.setInt(1, vidr.getVidraria_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteVidraria");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    //tb_vidraria_tipo
    public List<Vidraria> readVidrariaTipo() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vidraria> vidrarias = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_vidraria_tipo");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Vidraria vidr = new Vidraria();
                vidr.setVidraria_tipo_id(rs.getInt("vidraria_tipo_id"));
                vidr.setVidraria_tipo(rs.getString("vidraria_tipo"));
                vidrarias.add(vidr);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readVidrariaTipo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return vidrarias;
    }

    public static void printVidraria(String user, String setor, int status) {
        Connection conn = ConnectionFactory.getConnection();
        String where = "";
        if ((!"".equals(setor) && status != 0)) {
            where = "WHERE tb_setor.sigla_setor = '" + setor + "' ";
            if (status == 1) {
                where = where + "AND tb_vidraria.data_saida is null ";
            } else if (status == 2) {
                where = where + "AND tb_vidraria.data_saida is not null ";
            }
        } else {
            if ("".equals(setor)) {
                if (status != 0) {
                    if (status == 1) {
                        where = "WHERE tb_vidraria.data_saida is null ";
                    } else if (status == 2) {
                        where = "WHERE tb_vidraria.data_saida is not null ";
                    }
                }
            } else {
                where = "WHERE tb_setor.sigla_setor = '" + setor + "' ";
            }
        }
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("Sql", where);
        String src = Reports.getSrc() + "Vidraria.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printVidraria");
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }
}
