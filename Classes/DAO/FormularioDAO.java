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
import Classes.Modelo.Formulario;
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
public class FormularioDAO {

    public List<Formulario> readFormulario() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Formulario> forms = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_form ORDER BY form_name");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Formulario form = new Formulario();
                form.setForm_id(rs.getInt("form_id"));
                form.setForm_name(rs.getString("form_name"));
                forms.add(form);
            }
        } catch (SQLException ex) {
            LogDAO logDAO = new LogDAO();
            logDAO.logSQLException(ex, "readFormulario");
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return forms;
    }
}
