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

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

/**
 *
 * @author rafael.lopes
 */
public class SQL {
    // Connect to your database.  
    // Replace server name, username, and password with your credentials  

    public static void main(String[] args) {
        String connectionString
                = "jdbc:sqlserver://PRTMC1939:1433;"
                + "database=db_sys;"
                + "user=db_sys;"
                + "password=Beast666;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;"
                + "loginTimeout=30;";

        // Declare the JDBC objects.  
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(connectionString);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
    }

}

// Use the JDBC driver  

