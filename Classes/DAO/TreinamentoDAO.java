/*
 * Copyright (C) 2018 rafael.lopes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Metodologia;
import Classes.Modelo.Setor;
import Classes.Modelo.Treinamento;
import Classes.Modelo.Usuario;
import Classes.Util.Reports;
import Frm.Principal.FrmCarregando;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author rafael.lopes
 */
public class TreinamentoDAO {

    public int createTreinamento(Treinamento treinamento) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        int generatedKey = -1;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_matriz_treinamento "
                    + "(data_treinamento, user_treinamento, "
                    + "data_registro, user_registro, titulo, "
                    + "conteudo, vigente, setor_id, turno, "
                    + "metodo_id, metodo_id_2, metodo_id_3, metodo_id_4, "
                    + "versao, versao_2, versao_3, versao_4) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, treinamento.getData_treinamento());
            stmt.setString(2, treinamento.getUser_treinamento().getUser());
            stmt.setTimestamp(3, treinamento.getData_registro());
            stmt.setString(4, treinamento.getUser_registro().getUser());
            stmt.setString(5, treinamento.getTitulo());
            stmt.setString(6, treinamento.getConteudo());
            stmt.setInt(7, (treinamento.getVigente()) ? 1 : 0);
            stmt.setInt(8, treinamento.getSetor().getSetor_id());
            stmt.setString(9, treinamento.getTurno());
            stmt.setInt(10, treinamento.getMetodologia1().getMetodo_id());
            stmt.setInt(11, treinamento.getMetodologia2().getMetodo_id());
            stmt.setInt(12, treinamento.getMetodologia3().getMetodo_id());
            stmt.setInt(13, treinamento.getMetodologia4().getMetodo_id());
            stmt.setInt(14, treinamento.getMetodologia1().getVersao());
            stmt.setInt(15, treinamento.getMetodologia2().getVersao());
            stmt.setInt(16, treinamento.getMetodologia3().getVersao());
            stmt.setInt(17, treinamento.getMetodologia4().getVersao());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedKey = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createTreinamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
        return generatedKey;
    }

    public List<Treinamento> readTreinamento(String setor, String turno) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Treinamento> treinamentos = new ArrayList<>();
        try {
            String where = "";
            if (!"Todos".equals(turno)) {
                where = "AND tb_matriz_treinamento.turno = '" + turno + "' ";
            }
            if (!"Todos".equals(setor)) {
                where = "AND tb_setor.sigla_setor = '" + setor + "' ";
            }
            stmt = conn.prepareStatement("Select tb_matriz_treinamento.*, "
                    + "tb_metodologia.cod_metodo, tb_metodologia.metodo, tb_metodologia.versao, "
                    + "tb_setor.sigla_setor, "
                    + "(SELECT COUNT(tb_treinamento_user.treinamento_user_id) "
                    + "FROM tb_treinamento_user "
                    + "WHERE tb_treinamento_user.treinamento_id = tb_matriz_treinamento.treinamento_id) "
                    + "as numero_usuarios  "
                    + "FROM tb_matriz_treinamento "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_matriz_treinamento.metodo_id = tb_metodologia.metodo_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_matriz_treinamento.setor_id = tb_setor.setor_id "
                    + "LEFT JOIN tb_users "
                    + "ON tb_matriz_treinamento.user_treinamento = tb_users.user_name "
                    + "WHERE 1 = 1 " + where
                    + "ORDER BY tb_matriz_treinamento.treinamento_id DESC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Treinamento treinamento = new Treinamento();
                Metodologia mtd1 = new Metodologia();
                Usuario user_registro = new Usuario();
                Setor setr = new Setor();
                treinamento.setTreinamento_id(rs.getInt("treinamento_id"));
                mtd1.setMetodo_id(rs.getInt("metodo_id"));
                mtd1.setCod_metodo(rs.getString("cod_metodo"));
                mtd1.setMetodo(rs.getString("metodo"));
                mtd1.setVersao(rs.getInt("versao"));
                treinamento.setMetodologia1(mtd1);
                setr.setSigla_setor(rs.getString("sigla_setor"));
                treinamento.setSetor(setr);
                user_registro.setUser(rs.getString("user_registro"));
                treinamento.setTurno(rs.getString("turno"));
                treinamento.setTitulo(rs.getString("titulo"));
                treinamento.setConteudo(rs.getString("conteudo"));
                treinamento.setUser_registro(user_registro);
                treinamento.setData_registro(rs.getTimestamp("data_registro"));
                treinamento.setVigente(rs.getBoolean("vigente"));
                treinamento.setNumero_usuarios(rs.getInt("numero_usuarios"));
                treinamentos.add(treinamento);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readTreinamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return treinamentos;
    }

    public List<Treinamento> selecionarTreinamento(Treinamento treinamento) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Treinamento> treinamentos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select tb_matriz_treinamento.*, "
                    + "tb_setor.sigla_setor "
                    + "FROM tb_matriz_treinamento "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_matriz_treinamento.setor_id = tb_setor.setor_id "
                    + "WHERE treinamento_id = ? ");
            stmt.setInt(1, treinamento.getTreinamento_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user_registro = new Usuario();
                Usuario user_treinamento = new Usuario();
                Setor setor = new Setor();
                Metodologia mtd1 = new Metodologia();
                Metodologia mtd2 = new Metodologia();
                Metodologia mtd3 = new Metodologia();
                Metodologia mtd4 = new Metodologia();
                mtd1.setMetodo_id(rs.getInt("metodo_id"));
                mtd1.setVersao(rs.getInt("versao"));
                treinamento.setMetodologia1(mtd1);
                mtd2.setMetodo_id(rs.getInt("metodo_id_2"));
                mtd2.setVersao(rs.getInt("versao_2"));
                treinamento.setMetodologia2(mtd2);
                mtd3.setMetodo_id(rs.getInt("metodo_id_3"));
                mtd3.setVersao(rs.getInt("versao_3"));
                treinamento.setMetodologia3(mtd3);
                mtd4.setMetodo_id(rs.getInt("metodo_id_4"));
                mtd4.setVersao(rs.getInt("versao_4"));
                treinamento.setMetodologia4(mtd4);  
                setor.setSigla_setor(rs.getString("sigla_setor"));
                treinamento.setSetor(setor);
                user_registro.setUser(rs.getString("user_registro"));
                treinamento.setUser_registro(user_registro);
                treinamento.setTitulo(rs.getString("titulo"));
                treinamento.setTurno(rs.getString("turno"));
                treinamento.setConteudo(rs.getString("conteudo"));
                treinamento.setData_registro(rs.getTimestamp("data_registro"));
                user_treinamento.setUser(rs.getString("user_treinamento"));
                treinamento.setUser_treinamento(user_treinamento);
                treinamento.setData_treinamento(rs.getTimestamp("data_treinamento"));
                treinamento.setVigente(rs.getBoolean("vigente"));
                treinamentos.add(treinamento);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selecionarTreinamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return treinamentos;
    }

    public void updateTreinamento(Treinamento treinamento) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_matriz_treinamento SET "
                    + "data_treinamento = ?, user_treinamento = ?, "
                    + "data_registro = ?, user_registro = ?, titulo = ?, "
                    + "conteudo = ?, vigente = ?, setor_id = ?, turno = ?, "
                    + "metodo_id = ?, metodo_id_2 = ?, metodo_id_3 = ?, metodo_id_4 = ?, "
                    + "versao = ?,  versao_2 = ?,  versao_3 = ?,  versao_4 = ?  "
                    + "WHERE treinamento_id = ?");
            stmt.setTimestamp(1, treinamento.getData_treinamento());
            stmt.setString(2, treinamento.getUser_treinamento().getUser());
            stmt.setTimestamp(3, treinamento.getData_registro());
            stmt.setString(4, treinamento.getUser_registro().getUser());
            stmt.setString(5, treinamento.getTitulo());
            stmt.setString(6, treinamento.getConteudo());
            stmt.setInt(7, (treinamento.getVigente()) ? 1 : 0);
            stmt.setInt(8, treinamento.getSetor().getSetor_id());
            stmt.setString(9, treinamento.getTurno());
            stmt.setInt(10, treinamento.getMetodologia1().getMetodo_id());
            stmt.setInt(11, treinamento.getMetodologia2().getMetodo_id());
            stmt.setInt(12, treinamento.getMetodologia3().getMetodo_id());
            stmt.setInt(13, treinamento.getMetodologia4().getMetodo_id());
            stmt.setInt(14, treinamento.getMetodologia1().getVersao());
            stmt.setInt(15, treinamento.getMetodologia2().getVersao());
            stmt.setInt(16, treinamento.getMetodologia3().getVersao());
            stmt.setInt(17, treinamento.getMetodologia4().getVersao());
            stmt.setInt(18, treinamento.getTreinamento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateTreinamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void deleteTreinamento(Treinamento treinamento) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_matriz_treinamento "
                    + "WHERE treinamento_id = ?");
            stmt.setInt(1, treinamento.getTreinamento_id());
            stmt.executeUpdate();
            deleteTreinamentoUsers(treinamento);
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteTreinamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public static void printMatrizTreinamento(String user, String setor, String turno) {
        Connection conn = ConnectionFactory.getConnection();
        FrmCarregando frm = new FrmCarregando();
        frm.setVisible(true);
        HashMap map = new HashMap();
        map.put("User_name", user);
        String where = "";
        if (!"Todos".equals(turno)) {
            where = "AND tb_matriz_treinamento.turno = '" + turno + "' ";
        }
        if (!"Todos".equals(setor)) {
            where = "AND tb_setor.sigla_setor = '" + setor + "' ";
        }
        map.put("Where", where);
        String src = Reports.getSrc() + "MatrizTreinamento.jasper";
        try {
            JasperPrint jpt = JasperFillManager.fillReport(src, map, conn);
            frm.dispose();
            JasperViewer jv = new JasperViewer(jpt, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logJasperPrint(ex, "printTreinamento");
            frm.dispose();
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
        }
    }

    public void createTreinamentoUser(Treinamento treinamento) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_treinamento_user "
                    + "(treinamento_id, user_id, user_registro, data_registro) "
                    + "VALUES (?,?,?,?)");
            stmt.setInt(1, treinamento.getTreinamento_id());
            stmt.setInt(2, treinamento.getUser().getUser_id());
            stmt.setString(3, treinamento.getUser_registro().getUser());
            stmt.setTimestamp(4, treinamento.getData_registro());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createTreinamentoUser");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Treinamento> selecionarTreinamentoUser(Integer treinamento_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Treinamento> treinamentos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select tb_treinamento_user.*, tb_users.user_name, "
                    + "tb_users.name, tb_users.cracha, tb_users.turno, tb_setor.sigla_setor "
                    + "FROM tb_treinamento_user "
                    + "LEFT JOIN tb_users "
                    + "ON tb_treinamento_user.user_id = tb_users.user_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_users.setor_id = tb_setor.setor_id "
                    + "WHERE treinamento_id = ? "
                    + "ORDER BY tb_users.user_id ASC");
            stmt.setInt(1, treinamento_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Treinamento treinamento = new Treinamento();
                Usuario user = new Usuario();
                Usuario user_registro = new Usuario();
                Setor setor = new Setor();
                treinamento.setTreinamento_user_id(rs.getInt("treinamento_user_id"));
                user.setUser_id(rs.getInt("user_id"));
                user.setUser(rs.getString("user_name"));
                user.setName(rs.getString("name"));
                user.setTurno(rs.getString("turno"));
                user.setCracha(rs.getString("cracha"));
                treinamento.setUser(user);
                setor.setSigla_setor(rs.getString("sigla_setor"));
                treinamento.setSetor(setor);
                user_registro.setUser(rs.getString("user_registro"));
                treinamento.setUser_registro(user_registro);
                treinamento.setData_registro(rs.getTimestamp("data_registro"));
                treinamento.setAprovado(rs.getBoolean("aprovado"));
                treinamentos.add(treinamento);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selecionarTreinamentoUser");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return treinamentos;
    }

    public void deleteTreinamentoUser(Treinamento treinamento) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_treinamento_user "
                    + "WHERE treinamento_user_id = ?");
            stmt.setInt(1, treinamento.getTreinamento_user_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteTreinamentoUser");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void deleteTreinamentoUsers(Treinamento treinamento) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_treinamento_user "
                    + "WHERE treinamento_id = ?");
            stmt.setInt(1, treinamento.getTreinamento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteTreinamentoUsers");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

}
