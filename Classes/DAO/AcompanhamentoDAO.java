/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.BancoDados.ConnectionFactory;
import Classes.Modelo.Acompanhamento;
import Classes.Modelo.Lote;
import Classes.Modelo.Material;
import Classes.Modelo.Metodologia;
import Classes.Modelo.Setor;
import Classes.Modelo.Usuario;
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
public class AcompanhamentoDAO {

    public void createAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_registro_acompanhamento "
                    + "(metodo_id, setor_id, "
                    + "registro_acompanhamento, sigla_registro_acompanhamento, "
                    + "categoria, versao) "
                    + "VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, acomp.getMetodo().getMetodo_id());
            stmt.setInt(2, acomp.getSetor().getSetor_id());
            stmt.setString(3, acomp.getRegistro_acompanhamento());
            stmt.setString(4, acomp.getSigla_registro_acompanhamento());
            stmt.setString(5, acomp.getCategoria());
            stmt.setInt(6, acomp.getVersao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Acompanhamento> readAcompanhamento() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Acompanhamento> acomps = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_registro_acompanhamento.*, "
                    + "tb_metodologia.cod_metodo, tb_setor.sigla_setor "
                    + "FROM tb_registro_acompanhamento "
                    + "LEFT JOIN  tb_metodologia "
                    + "ON tb_registro_acompanhamento.metodo_id =  tb_metodologia.metodo_id "
                    + "LEFT JOIN  tb_setor "
                    + "ON tb_registro_acompanhamento.setor_id =  tb_setor.setor_id ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Acompanhamento acomp = new Acompanhamento();
                Metodologia mtd = new Metodologia();
                Setor setor = new Setor();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                acomp.setSetor(setor);
                acomp.setMetodo(mtd);
                acomp.setRegistro_acompanhamento(rs.getString("registro_acompanhamento"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                acomp.setCategoria(rs.getString("categoria"));
                acomp.setVersao(rs.getInt("versao"));
                acomps.add(acomp);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return acomps;
    }

    public List<Acompanhamento> readAcompanhamento(Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Acompanhamento> acomps = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("Select tb_registro_acompanhamento.*, "
                    + "tb_metodologia.cod_metodo, tb_setor.sigla_setor "
                    + "FROM tb_registro_acompanhamento "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_registro_acompanhamento.metodo_id = tb_metodologia.metodo_id "
                    + "INNER JOIN tb_setor "
                    + "ON tb_registro_acompanhamento.setor_id = tb_setor.setor_id "
                    + "WHERE tb_registro_acompanhamento.metodo_id = ? "
                    + "ORDER BY registro_acompanhamento ");
            stmt.setInt(1, mtd.getMetodo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Acompanhamento acomp = new Acompanhamento();
                Metodologia metd = new Metodologia();
                Setor setor = new Setor();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                acomp.setSetor(setor);
                metd.setCod_metodo(rs.getString("cod_metodo"));
                acomp.setMetodo(metd);
                acomp.setRegistro_acompanhamento(rs.getString("registro_acompanhamento"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                acomp.setCategoria(rs.getString("categoria"));
                acomp.setVersao(rs.getInt("versao"));
                acomps.add(acomp);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return acomps;
    }

    public List<Acompanhamento> readLoteAcompanhamento(Integer metodo_id, Integer lote_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Acompanhamento> acomps = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_registro_acompanhamento.registro_acompanhamento_id, "
                    + "tb_registro_acompanhamento.metodo_id, "
                    + "tb_registro_acompanhamento.registro_acompanhamento, "
                    + "tb_lote_acompanhamento.lote_acompanhamento_id, "
                    + "tb_lote_acompanhamento.lote_id, "
                    + "tb_lote_acompanhamento.data_registro, "
                    + "tb_lote_acompanhamento.user_registro, "
                    + "tb_lote_acompanhamento.codigo_barras "
                    + "FROM tb_registro_acompanhamento "
                    + "LEFT JOIN tb_lote_acompanhamento "
                    + "ON tb_lote_acompanhamento.registro_acompanhamento_id = "
                    + "tb_registro_acompanhamento.registro_acompanhamento_id "
                    + "AND tb_lote_acompanhamento.lote_id = ? "
                    + "WHERE tb_registro_acompanhamento.metodo_id = ? "
                    + "ORDER BY registro_acompanhamento ");
            stmt.setInt(1, lote_id);
            stmt.setInt(2, metodo_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Acompanhamento acomp = new Acompanhamento();
                Usuario user = new Usuario();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                acomp.setRegistro_acompanhamento(rs.getString("registro_acompanhamento"));
                acomp.setLote_acompanhamento_id(rs.getInt("lote_acompanhamento_id"));
                acomp.setCodigo_barras(rs.getString("codigo_barras"));
                acomp.setData_registro(rs.getTimestamp("data_registro"));
                user.setUser(rs.getString("user_registro"));
                acomp.setUser_registro(user);
                acomps.add(acomp);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return acomps;
    }

    public void updateAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_registro_acompanhamento SET "
                    + "metodo_id = ?, setor_id = ?, "
                    + "registro_acompanhamento = ?, sigla_registro_acompanhamento = ?, "
                    + "categoria = ?, versao = ? "
                    + "WHERE registro_acompanhamento_id = ?");
            stmt.setInt(1, acomp.getMetodo().getMetodo_id());
            stmt.setInt(2, acomp.getSetor().getSetor_id());
            stmt.setString(3, acomp.getRegistro_acompanhamento());
            stmt.setString(4, acomp.getSigla_registro_acompanhamento());
            stmt.setString(5, acomp.getCategoria());
            stmt.setInt(6, acomp.getVersao());
            stmt.setInt(7, acomp.getRegistro_acompanhamento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updateLoteAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_lote_acompanhamento SET "
                    + "data_registro = ?, user_registro = ?, codigo_barras = ? "
                    + "WHERE lote_acompanhamento_id = ?");
            stmt.setTimestamp(1, acomp.getData_registro());
            stmt.setString(2, acomp.getUser_registro().getUser());
            stmt.setString(3, acomp.getCodigo_barras());
            stmt.setInt(4, acomp.getLote_acompanhamento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updateLoteAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void selectAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_registro_acompanhamento.*, "
                    + "tb_metodologia.cod_metodo, tb_metodologia.metodo, "
                    + "tb_setor.sigla_setor, tb_setor.setor "
                    + "FROM tb_registro_acompanhamento "
                    + "LEFT JOIN  tb_metodologia "
                    + "ON tb_registro_acompanhamento.metodo_id =  tb_metodologia.metodo_id "
                    + "LEFT JOIN  tb_setor "
                    + "ON tb_registro_acompanhamento.setor_id =  tb_setor.setor_id "
                    + "WHERE registro_acompanhamento_id = ?");
            stmt.setInt(1, acomp.getRegistro_acompanhamento_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Setor setor = new Setor();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setMetodo(rs.getString("metodo"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSetor(rs.getString("setor"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                acomp.setSetor(setor);
                acomp.setMetodo(mtd);
                acomp.setRegistro_acompanhamento(rs.getString("registro_acompanhamento"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                acomp.setCategoria(rs.getString("categoria"));
                acomp.setVersao(rs.getInt("versao"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void selectAcompanhamento(Acompanhamento acomp, Metodologia mtd) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("Select * From tb_registro_acompanhamento "
                    + "WHERE metodo_id = ? "
                    + "ORDER BY sigla_registro_acompanhamento ");
            stmt.setInt(1, mtd.getMetodo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                setor.setSetor_id(rs.getInt("setor_id"));
                acomp.setSetor(setor);
                acomp.setMetodo(mtd);
                acomp.setRegistro_acompanhamento(rs.getString("registro_acompanhamento"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                acomp.setCategoria(rs.getString("categoria"));
                acomp.setVersao(rs.getInt("versao"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public void deleteAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_registro_acompanhamento "
                    + "WHERE registro_acompanhamento_id = ?");
            stmt.setInt(1, acomp.getRegistro_acompanhamento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Acompanhamento> procurarCodigoBarras(String codigo_barras) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Acompanhamento> acomps = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_lote_acompanhamento.registro_acompanhamento_id, "
                    + "tb_lote_acompanhamento.codigo_barras, tb_lote.lote_id, tb_lote.lote_produto, "
                    + "tb_material.cod_material, tb_material.material "
                    + "FROM tb_lote_acompanhamento "
                    + "INNER JOIN tb_lote "
                    + "ON tb_lote_acompanhamento.lote_id = tb_lote.lote_id "
                    + "INNER JOIN tb_material "
                    + "ON tb_lote.material_id = tb_material.material_id "
                    + "WHERE tb_lote_acompanhamento.codigo_barras = ?");
            stmt.setString(1, codigo_barras);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Acompanhamento acomp = new Acompanhamento();
                Lote lot = new Lote();
                Material prod = new Material();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                acomp.setCodigo_barras(rs.getString("codigo_barras"));
                lot.setLote_id(rs.getInt("lote_id"));
                lot.setLote_produto(rs.getString("lote_produto"));
                acomp.setLote(lot);
                prod.setCod_material(rs.getString("cod_material"));
                prod.setNome_material(rs.getString("material"));
                acomp.setMaterial(prod);
                acomps.add(acomp);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "procurarCodigoBarras");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return acomps;
    }
    
    public static Boolean verificaCodigoBarras(String codigo_barras) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean result = false;
        try {
            stmt = conn.prepareStatement("SELECT tb_lote_acompanhamento.codigo_barras "
                    + "FROM tb_lote_acompanhamento "
                    + "WHERE tb_lote_acompanhamento.codigo_barras = ?");
            stmt.setString(1, codigo_barras);
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "verificaCodigoBarras");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return result;
    }

    public List<Acompanhamento> readAcompanhamentoByLote(int lote_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Acompanhamento> acomps = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_registro_acompanhamento.*, "
                    + "tb_setor.setor, tb_metodologia.cod_metodo, "
                    + "CASE WHEN tb_lote_acompanhamento.registro_acompanhanto_id > 0 THEN 1 ELSE 0 END AS status, "
                    + "tb_lote_acompanhamento.data_registro, tb_lote_acompanhamento.user_registro "
                    + "FROM tb_registro_acompanhamento "
                    + "INNER JOIN tb_material "
                    + "ON tb_registro_acompanhamento.metodo_id = tb_material.metodo_id "
                    + "INNER JOIN tb_lote "
                    + "ON tb_material.material_id = tb_lote.material_id "
                    + "INNER JOIN tb_metodologia "
                    + "ON tb_registro_acompanhamento.metodo_id = tb_metodologia.metodo_id "
                    + "INNER JOIN tb_setor "
                    + "ON tb_registro_acompanhamento.setor_id = tb_setor.setor_id "
                    + "LEFT JOIN tb_lote_acompanhamento "
                    + "ON tb_registro_acompanhamento.registro_acompanhamento_id = tb_lote_acompanhamento.registro_acompanhanto_id "
                    + "WHERE tb_lote.lote_id = ? "
                    + "ORDER BY tb_registro_acompanhamento.setor_id, "
                    + "tb_registro_acompanhamento.sigla_registro_acompanhamento");
            stmt.setInt(1, lote_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Acompanhamento acomp = new Acompanhamento();
                Setor setor = new Setor();
                Metodologia mtd = new Metodologia();
                Usuario user = new Usuario();
                acomp.setRegistro_acompanhamento_id(rs.getInt("registro_acompanhamento_id"));
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSetor(rs.getString("setor"));
                user.setUser(rs.getString("user_registro"));
                acomp.setSetor(setor);
                acomp.setMetodo(mtd);
                acomp.setUser_registro(user);
                acomp.setData_registro(rs.getTimestamp("data_registro"));
                acomp.setRegistro_acompanhamento(rs.getString("registro_acompanhamento"));
                acomp.setSigla_registro_acompanhamento(rs.getString("sigla_registro_acompanhamento"));
                acomp.setCategoria(rs.getString("categoria"));
                acomp.setVersao(rs.getInt("versao"));
                acomp.setStatus(rs.getBoolean("status"));
                acomps.add(acomp);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAcompanhamentoByLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return acomps;
    }

    public void deleteLoteAcompanhamento(Acompanhamento acomp) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_lote_acompanhamento "
                    + "WHERE lote_acompanhamento_id = ?");
            stmt.setInt(1, acomp.getLote_acompanhamento_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deleteLoteAcompanhamento");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

}
