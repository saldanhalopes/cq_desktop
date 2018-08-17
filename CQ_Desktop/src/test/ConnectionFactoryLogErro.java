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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class ConnectionFactoryLogErro {

    //Conectar SQLite 
    private static final String DRIVER = "org.sqlite.JDBC";
    //private static final String URL = "jdbc:sqlite:M:\\controledequalidade_compartilhada\\CQ_Desktop\\DB\\db_log_erro.db";
    private static final String URL = "jdbc:sqlite:\\\\lace22\\CQ\\db_log_erro.db";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na Conexão: " + ex);
        }
    }

    public static void closeConection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactoryLogErro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConection(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactoryLogErro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConection(Connection conn, PreparedStatement stmt) {
        closeConection(conn);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactoryLogErro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        closeConection(conn, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactoryLogErro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
