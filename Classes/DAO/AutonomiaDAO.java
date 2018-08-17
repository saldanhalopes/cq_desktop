/*
 * Copyright (C) 2017 rafael.lopes
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
import Classes.Modelo.Autonomia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class AutonomiaDAO {

    public void selectAutonomiaByMaterial(Autonomia auto) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT tb_autonomia.* "
                    + "FROM tb_autonomia "
                    + "WHERE tb_autonomia.cod_material = ?");
            stmt.setString(1, auto.getMaterial().getCod_material());
            rs = stmt.executeQuery();
            while (rs.next()) {
                auto.setAutonomia_id(rs.getInt("autonomia_id"));
                auto.setAutonomia(rs.getDouble("autonomia"));
                auto.setMedia_saida(rs.getDouble("media_saida"));
                auto.setMedia_entrada(rs.getDouble("media_entrada"));
                auto.setMedia_estoque(rs.getDouble("media_estoque"));
                auto.setEstoque_expedicao(rs.getInt("estoque_expedicao"));
                auto.setEstoque_cq(rs.getInt("estoque_cq"));
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "selectAutonomiaByMaterial");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
    }

    public List<Autonomia> readAutonomiaByMaterial() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Autonomia> autom = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_autonomia.* "
                    + "FROM tb_autonomia ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Autonomia auto = new Autonomia();
                auto.setAutonomia_id(rs.getInt("autonomia_id"));
                auto.setAutonomia(rs.getDouble("autonomia"));
                auto.setMedia_saida(rs.getDouble("media_saida"));
                auto.setMedia_entrada(rs.getDouble("media_entrada"));
                auto.setMedia_estoque(rs.getDouble("media_estoque"));
                auto.setEstoque_expedicao(rs.getInt("estoque_expedicao"));
                auto.setEstoque_cq(rs.getInt("estoque_cq"));
                autom.add(auto);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readAutonomiaByMaterial");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return autom;
    }

}
