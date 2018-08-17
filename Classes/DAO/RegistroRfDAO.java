/*
 * Copyright (C) 2018 rafael.lopes
 *
 * Este programa é um software livre: você pode redistribuí-lo e / ou modificar
 * sob os termos da GNU General Public License, conforme publicado pela
 * a Free Software Foundation, seja a versão 3 da Licença, quanto
 * qualquer versão posterior.
 *
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem a garantia implícita de
 * COMERCIALIZAÇÃO OU APTIDÃO PARA UM PROPÓSITO PARTICULAR. Veja o
 * GNU General Public License para obter mais detalhes.
 *
 * Você deve ter recebido uma cópia da GNU General Public License
 *  juntamente com este programa. Caso contrário, veja <http://www.gnu.org/licenses/>.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Frm.Principal.FrmCarregando;
import Classes.Modelo.Material;
import Classes.Modelo.RegistroRf;
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
public class RegistroRfDAO {
    
    public void createRegistroRf(RegistroRf regrf) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_registro_rf "
                    + "(material_id, caixa_rf_id, centro_custo, "
                    + "deposito, quantidade, lote, rpm, "
                    + "data_registro, user_registro) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, regrf.getMaterial().getMaterial_id());
            stmt.setInt(2, regrf.getCaixa_rf_id());
            stmt.setString(3, regrf.getCentro_custo());
            stmt.setString(4, regrf.getDeposito());
            stmt.setFloat(5, regrf.getQuantidade());
            stmt.setString(6, regrf.getLote());
            stmt.setString(7, regrf.getRpm());
            stmt.setTimestamp(8, regrf.getData_registro());
            stmt.setString(9, regrf.getUser_registro().getUser());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createRegistroRf");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<RegistroRf> readRegistroRf(Integer limit) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroRf> registrorfs = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT TOP(?) registro_rf_id, "
                    + "cod_material, tb_registro_rf.caixa_rf_id, caixa, "
                    + "centro_custo, deposito, quantidade, lote, "
                    + "rpm, data_registro, user_registro, "
                    + "tb_registro_rf.material_id, material "
                    + "FROM tb_registro_rf "
                    + "INNER JOIN tb_material "
                    + "ON tb_registro_rf.material_id = tb_material.material_id "
                    + "INNER JOIN tb_caixa_rf "
                    + "ON tb_registro_rf.caixa_rf_id = tb_caixa_rf.caixa_rf_id "
                    + "ORDER BY registro_rf_id DESC; ");
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();
            while (rs.next()) {
                RegistroRf regrf = new RegistroRf();
                Material prod = new Material();
                Usuario user = new Usuario();
                regrf.setRegistro_rf_id(rs.getInt("registro_rf_id"));
                regrf.setCaixa_rf_id(rs.getInt("caixa_rf_id"));
                prod.setMaterial_id(rs.getInt("material_id"));
                prod.setCod_material(rs.getString("cod_material"));
                prod.setNome_material(rs.getString("material"));
                regrf.setMaterial(prod);
                regrf.setCentro_custo(rs.getString("centro_custo"));
                regrf.setDeposito(rs.getString("deposito"));
                regrf.setQuantidade(rs.getFloat("quantidade"));
                regrf.setLote(rs.getString("lote"));
                regrf.setRpm(rs.getString("rpm"));
                regrf.setCaixa(rs.getString("caixa"));
                regrf.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_registro"));
                regrf.setUser_registro(user);
                registrorfs.add(regrf);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readRegistroRf");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return registrorfs;
    }

    public void updateRegistroRf(RegistroRf regrf) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_registro_rf SET "
                    + "material_id = ?, caixa_rf_id = ?, centro_custo = ?, "
                    + "deposito = ?, quantidade = ?, lote = ?, rpm = ?, "
                    + "data_registro = ?, user_registro = ? "
                    + "WHERE registro_rf_id = ? ");
            stmt.setInt(1, regrf.getMaterial().getMaterial_id());
            stmt.setInt(2, regrf.getCaixa_rf_id());
            stmt.setString(3, regrf.getCentro_custo());
            stmt.setString(4, regrf.getDeposito());
            stmt.setFloat(5, regrf.getQuantidade());
            stmt.setString(6, regrf.getLote());
            stmt.setString(7, regrf.getRpm());
            stmt.setTimestamp(8, regrf.getData_registro());
            stmt.setString(9, regrf.getUser_registro().getUser());
            stmt.setInt(10, regrf.getRegistro_rf_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateRegistroRf");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectRegistroRf(RegistroRf regrf) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT registro_rf_id, "
                    + "cod_material, tb_caixa_rf.caixa_rf_id, caixa, centro_custo, deposito, "
                    + "quantidade, lote, rpm, data_registro, user_registro, material "
                    + "FROM tb_registro_rf "
                    + "INNER JOIN tb_material "
                    + "ON tb_registro_rf.material_id = tb_material.material_id "
                    + "INNER JOIN tb_caixa_rf "
                    + "ON tb_registro_rf.caixa_rf_id = tb_caixa_rf.caixa_rf_id "
                    + "WHERE registro_rf_id = ?; ");
            stmt.setInt(1, regrf.getRegistro_rf_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Material prod = new Material();
                Usuario user = new Usuario();
                regrf.setRegistro_rf_id(rs.getInt("registro_rf_id"));
                prod.setCod_material(rs.getString("cod_material"));
                prod.setNome_material(rs.getString("material"));
                regrf.setMaterial(prod);
                regrf.setCentro_custo(rs.getString("centro_custo"));
                regrf.setDeposito(rs.getString("deposito"));
                regrf.setQuantidade(rs.getFloat("quantidade"));
                regrf.setLote(rs.getString("lote"));
                regrf.setRpm(rs.getString("rpm"));
                regrf.setCaixa_rf_id(rs.getInt("caixa_rf_id"));
                regrf.setCaixa(rs.getString("caixa"));
                regrf.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_registro"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectRegistroRf");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteRegistroRf(RegistroRf regrf) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_registro_rf "
                    + "WHERE registro_rf_id = ?");
            stmt.setInt(1, regrf.getRegistro_rf_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteRegistroRf");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
    
    public void createCaixa(RegistroRf regrf) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_caixa_rf "
                    + "(caixa, data_inicio, user_inicio) "
                    + "VALUES (?,?,?)");
            stmt.setString(1, regrf.getCaixa());
            stmt.setTimestamp(2, regrf.getData_inicio());
            stmt.setString(3, regrf.getUser_inicio().getUser());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createCaixa");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
        
     public List<RegistroRf> readCaixa() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroRf> registrorfs = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_caixa_rf "
                    + "ORDER BY caixa_rf_id DESC; ");
              rs = stmt.executeQuery();
            while (rs.next()) {
                RegistroRf regrf = new RegistroRf();
                Usuario user_inicio = new Usuario();
                Usuario user_fim = new Usuario();
                regrf.setCaixa_rf_id(rs.getInt("caixa_rf_id"));
                regrf.setCaixa(rs.getString("caixa"));
                regrf.setData_inicio(rs.getTimestamp("data_inicio"));
                regrf.setData_fim(rs.getTimestamp("data_fim"));
                user_inicio.setUser(rs.getString("user_inicio"));
                user_fim.setUser(rs.getString("user_fim"));
                regrf.setUser_inicio(user_inicio);
                regrf.setUser_fim(user_fim);
                registrorfs.add(regrf);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readCaixa");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return registrorfs;
    }
        
    public void selectCaixa(RegistroRf regrf) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT caixa "
                    + "FROM tb_caixa_rf "
                    + "WHERE caixa_rf_id = ?; ");
            stmt.setInt(1, regrf.getCaixa_rf_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                regrf.setCaixa(rs.getString("caixa"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectCaixa");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }
    
    public void selectUltimaCaixa(RegistroRf regrf) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT TOP(1) * FROM tb_caixa_rf "
                    + "ORDER BY caixa_rf_id DESC; ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                regrf.setCaixa_rf_id(rs.getInt("caixa_rf_id"));
                regrf.setCaixa(rs.getString("caixa"));
                regrf.setData_fim(rs.getTimestamp("data_fim"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectUltimaCaixa");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }
    
    public void updateFecharCaixa(RegistroRf regrf) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_caixa_rf SET "
                    + "data_fim = ?, user_fim = ? "
                    + "WHERE caixa_rf_id = ? ");
            stmt.setTimestamp(1, regrf.getData_fim());
            stmt.setString(2, regrf.getUser_fim().getUser());
            stmt.setInt(3, regrf.getCaixa_rf_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateFecharCaixa");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
    
    public static void printLotesCaixa(String user, Integer caixa_id) {
        Connection conn = ConnectionFactory.getConnection();
        FrmCarregando frm = new FrmCarregando();
        frm.setVisible(true);
        HashMap map = new HashMap();
        map.put("User_name", user);
        map.put("caixa_id", caixa_id);
        String src = Reports.getSrc() + "RecepcaoRegistroRf.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            frm.dispose();
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printLotesCaixa");
            frm.dispose();
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }
    
    public static void printEtiquetaCaixa(Integer caixa_id) {
        Connection conn = ConnectionFactory.getConnection();
        FrmCarregando frm = new FrmCarregando();
        frm.setVisible(true);
        HashMap map = new HashMap();
        map.put("caixa_id", caixa_id);
        String src = Reports.getSrc() + "RecepcaoEtiquetaCaixa.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            frm.dispose();
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printEtiquetaCaixa");
            frm.dispose();
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }
    
    public static boolean verificarLoterRegistroRf(String lote) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isExits = false;
        try {
            stmt = conn.prepareStatement("SELECT registro_rf_id "
                    + "FROM tb_registro_rf WHERE lote = ?");
            stmt.setString(1, lote);
            rs = stmt.executeQuery();
            while (rs.next()) {
                isExits = true;
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "verificarLoterRegistroRf");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return isExits;
    }
    
}
