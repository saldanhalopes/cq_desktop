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
import Classes.Modelo.Material;
import Classes.Modelo.Metodologia;
import Classes.Modelo.Placebo;
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
public class PlaceboDAO {

    public void createPlacebo(Placebo placebo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_placebo "
                    + "(material_id, setor_id, vaga_placebo_id, "
                    + "obs, data_registro, user_registro) "
                    + "VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, placebo.getMaterial().getMaterial_id());
            stmt.setInt(2, placebo.getSetor().getSetor_id());
            stmt.setInt(3, placebo.getVaga_placebo_id());
            stmt.setString(4, placebo.getObs());
            stmt.setTimestamp(5, placebo.getData_registro());
            stmt.setString(6, placebo.getUser_registro().getUser());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createPlacebo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void createPlaceboLote(Placebo placebo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO tb_placebo_lote "
                    + "(placebo_id, data_solicitacao, user_solicitacao, "
                    + "data_recebimento, user_recebimento, lote_placebo, "
                    + "data_fabricacao, data_validade, quantidade, "
                    + "obs, data_registro, user_registro) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, placebo.getPlacebo_id());
            stmt.setTimestamp(2, placebo.getData_solicitacao());
            stmt.setString(3, placebo.getUser_solicitacao().getUser());
            stmt.setTimestamp(4, placebo.getData_recebimento());
            stmt.setString(5, placebo.getUser_recebimento().getUser());
            stmt.setString(6, placebo.getLote_placebo());
            stmt.setTimestamp(7, placebo.getData_fabricacao());
            stmt.setTimestamp(8, placebo.getData_validade());
            stmt.setInt(9, placebo.getQuantidade());
            stmt.setString(10, placebo.getObs());
            stmt.setTimestamp(11, placebo.getData_registro());
            stmt.setString(12, placebo.getUser_registro().getUser());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "createPlaceboLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Placebo> readPlacebo() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Placebo> placebos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_placebo.placebo_id, "
                    + "tb_placebo.material_id, tb_placebo.vaga_placebo_id, "
                    + "tb_placebo_lote.placebo_lote_id, tb_placebo_lote.lote_placebo, "
                    + "tb_placebo_lote.data_solicitacao, tb_placebo_lote.data_recebimento, "
                    + "tb_placebo_lote.data_validade, tb_placebo_lote.quantidade, "
                    + "tb_material.cod_material, tb_material.material, "
                    + "tb_material.tipo "
                    + "FROM tb_placebo "
                    + "LEFT JOIN tb_placebo_lote "
                    + "ON tb_placebo.placebo_id = tb_placebo_lote.placebo_id "
                    + "LEFT JOIN tb_material "
                    + "ON tb_placebo.material_id = tb_material.material_id "
                    + "WHERE tb_placebo_lote.placebo_lote_id IN "
                    + "(SELECT MAX (tb_placebo_lote.placebo_lote_id) FROM tb_placebo_lote "
                    + "GROUP BY tb_placebo_lote.placebo_id) "
                    + "OR tb_placebo_lote.placebo_lote_id IS NULL "
                    + "ORDER BY tb_placebo.vaga_placebo_id ASC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Placebo placebo = new Placebo();
                Material prod = new Material();
                placebo.setPlacebo_id(rs.getInt("placebo_id"));
                prod.setMaterial_id(rs.getInt("material_id"));
                prod.setCod_material(rs.getString("cod_material"));
                prod.setNome_material(rs.getString("material"));
                prod.setTipo(rs.getString("tipo"));
                placebo.setMaterial(prod);
                placebo.setVaga_placebo_id(rs.getInt("vaga_placebo_id"));
                placebo.setPlacebo_lote_id(rs.getInt("placebo_lote_id"));
                placebo.setLote_placebo(rs.getString("lote_placebo"));
                placebo.setData_solicitacao(rs.getTimestamp("data_solicitacao"));
                placebo.setData_recebimento(rs.getTimestamp("data_recebimento"));
                placebo.setData_validade(rs.getTimestamp("data_validade"));
                placebo.setQuantidade(rs.getInt("quantidade"));
                placebos.add(placebo);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readPlacebo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return placebos;
    }

    public void selectPlacebo(Placebo placebo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_placebo.*, "
                    + "tb_material.cod_material, tb_material.material, "
                    + "tb_material.tipo, tb_metodologia.metodo_id, tb_metodologia.cod_metodo, "
                    + "tb_metodologia.metodo, tb_setor.sigla_setor "
                    + "FROM tb_placebo "
                    + "LEFT JOIN tb_material "
                    + "ON tb_placebo.material_id = tb_material.material_id "
                    + "LEFT JOIN tb_metodologia "
                    + "ON tb_material.metodo_id = tb_metodologia.metodo_id "
                    + "LEFT JOIN tb_setor "
                    + "ON tb_placebo.setor_id = tb_setor.setor_id "
                    + "WHERE tb_placebo.placebo_id = ? ");
            stmt.setInt(1, placebo.getPlacebo_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Material prod = new Material();
                Metodologia mtd = new Metodologia();
                Setor setor = new Setor();
                Usuario user_registro = new Usuario();
                prod.setMaterial_id(rs.getInt("material_id"));
                prod.setCod_material(rs.getString("cod_material"));
                prod.setNome_material(rs.getString("material"));
                prod.setTipo(rs.getString("tipo"));
                placebo.setMaterial(prod);
                mtd.setMetodo_id(rs.getInt("metodo_id"));
                mtd.setCod_metodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                placebo.setMetodologia(mtd);
                setor.setSetor_id(rs.getInt("setor_id"));
                setor.setSigla_setor(rs.getString("sigla_setor"));
                placebo.setSetor(setor);
                placebo.setVaga_placebo_id(rs.getInt("vaga_placebo_id"));
                placebo.setObs(rs.getString("obs"));
                user_registro.setUser(rs.getString("user_registro"));
                placebo.setUser_registro(user_registro);
                placebo.setData_registro(rs.getTimestamp("data_registro"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectPlacebo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public List<Placebo> readVagaPlacebo() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Placebo> placebos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_vaga_placebo.vaga_placebo_id, "
                    + "tb_material.material "
                    + "FROM tb_vaga_placebo "
                    + "LEFT JOIN tb_placebo "
                    + "ON tb_vaga_placebo.vaga_placebo_id = tb_placebo.vaga_placebo_id "
                    + "LEFT JOIN tb_material "
                    + "ON tb_placebo.material_id = tb_material.material_id ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Placebo placebo = new Placebo();
                Material prod = new Material();
                placebo.setVaga_placebo_id(rs.getInt("vaga_placebo_id"));
                prod.setNome_material(rs.getString("material"));
                placebo.setMaterial(prod);
                placebos.add(placebo);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readVagaPlacebo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return placebos;
    }

    public void updatePlacebo(Placebo placebo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_placebo SET "
                    + "material_id = ?, setor_id = ?, vaga_placebo_id = ?, "
                    + "obs = ?, data_registro = ?, user_registro = ? "
                    + "WHERE placebo_id = ?");
            stmt.setInt(1, placebo.getMaterial().getMaterial_id());
            stmt.setInt(2, placebo.getSetor().getSetor_id());
            stmt.setInt(3, placebo.getVaga_placebo_id());
            stmt.setString(4, placebo.getObs());
            stmt.setTimestamp(5, placebo.getData_registro());
            stmt.setString(6, placebo.getUser_registro().getUser());
            stmt.setInt(7, placebo.getPlacebo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updatePlacebo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void updatePlaceboLote(Placebo placebo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE tb_placebo_lote SET "
                    + "data_solicitacao = ?, user_solicitacao = ?, "
                    + "data_recebimento = ?, user_recebimento = ?, lote_placebo = ?, "
                    + "data_fabricacao = ?, data_validade = ?, quantidade = ?, "
                    + "obs = ?, data_registro = ?, user_registro  = ? "
                    + "WHERE placebo_lote_id = ?");
            stmt.setTimestamp(1, placebo.getData_solicitacao());
            stmt.setString(2, placebo.getUser_solicitacao().getUser());
            stmt.setTimestamp(3, placebo.getData_recebimento());
            stmt.setString(4, placebo.getUser_recebimento().getUser());
            stmt.setString(5, placebo.getLote_placebo());
            stmt.setTimestamp(6, placebo.getData_fabricacao());
            stmt.setTimestamp(7, placebo.getData_validade());
            stmt.setInt(8, placebo.getQuantidade());
            stmt.setString(9, placebo.getObs());
            stmt.setTimestamp(10, placebo.getData_registro());
            stmt.setString(11, placebo.getUser_registro().getUser());
            stmt.setInt(12, placebo.getPlacebo_lote_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "updatePlaceboLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public List<Placebo> readLotePlacebo(Integer id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Placebo> placebos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_placebo_lote.* "
                    + "FROM tb_placebo_lote "
                    + "WHERE tb_placebo_lote.placebo_id = ? "
                    + "ORDER BY tb_placebo_lote.placebo_lote_id DESC;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Placebo placebo = new Placebo();
                Usuario user_solicitacao = new Usuario();
                Usuario user_recebimento = new Usuario();
                Usuario user_registro = new Usuario();
                placebo.setPlacebo_lote_id(rs.getInt("placebo_lote_id"));
                placebo.setLote_placebo(rs.getString("lote_placebo"));
                placebo.setData_solicitacao(rs.getTimestamp("data_solicitacao"));
                placebo.setData_recebimento(rs.getTimestamp("data_recebimento"));
                placebo.setData_fabricacao(rs.getTimestamp("data_fabricacao"));
                placebo.setData_validade(rs.getTimestamp("data_validade"));
                placebo.setQuantidade(rs.getInt("quantidade"));
                user_solicitacao.setUser(rs.getString("user_solicitacao"));
                placebo.setUser_solicitacao(user_solicitacao);
                user_recebimento.setUser(rs.getString("user_recebimento"));
                placebo.setUser_recebimento(user_recebimento);
                user_registro.setUser(rs.getString("user_registro"));
                placebo.setUser_registro(user_registro);
                placebo.setData_registro(rs.getTimestamp("data_registro"));
                placebo.setObs(rs.getString("obs"));
                placebos.add(placebo);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readPlacebo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return placebos;
    }

    public void selectLotePlacebo(Placebo placebo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_placebo_lote.* "
                    + "FROM tb_placebo_lote "
                    + "WHERE tb_placebo_lote.placebo_lote_id = ? ;");
            stmt.setInt(1, placebo.getPlacebo_lote_id());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user_solicitacao = new Usuario();
                Usuario user_recebimento = new Usuario();
                Usuario user_registro = new Usuario();
                placebo.setLote_placebo(rs.getString("lote_placebo"));
                placebo.setData_solicitacao(rs.getTimestamp("data_solicitacao"));
                placebo.setData_recebimento(rs.getTimestamp("data_recebimento"));
                placebo.setData_fabricacao(rs.getTimestamp("data_fabricacao"));
                placebo.setData_validade(rs.getTimestamp("data_validade"));
                placebo.setQuantidade(rs.getInt("quantidade"));
                user_solicitacao.setUser(rs.getString("user_solicitacao"));
                placebo.setUser_solicitacao(user_solicitacao);
                user_recebimento.setUser(rs.getString("user_recebimento"));
                placebo.setUser_recebimento(user_recebimento);
                user_registro.setUser(rs.getString("user_registro"));
                placebo.setUser_registro(user_registro);
                placebo.setData_registro(rs.getTimestamp("data_registro"));
                placebo.setObs(rs.getString("obs"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectLotePlacebo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }
    
    public void deletePlacebo(Placebo placebo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_placebo WHERE placebo_id = ?");
            stmt.setInt(1, placebo.getPlacebo_id());
            stmt.executeUpdate();
            deletePlaceboLotes(placebo);
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deletePlacebo");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
    
    public void deletePlaceboLotes(Placebo placebo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_placebo_lote WHERE placebo_id = ?");
            stmt.setInt(1, placebo.getPlacebo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deletePlaceboLotes");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }

    public void deletePlaceboLote(Placebo placebo) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM tb_placebo_lote WHERE placebo_lote_id = ?");
            stmt.setInt(1, placebo.getPlacebo_lote_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "deletePlaceboLote");
        } finally {
            ConnectionFactory.closeConection(conn, stmt);
        }
    }
    
}
