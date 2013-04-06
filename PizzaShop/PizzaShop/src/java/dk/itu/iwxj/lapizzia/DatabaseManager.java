/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chwu+vanl+sma
 */
public class DatabaseManager {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {

            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                connection = DriverManager.getConnection("jdbc:derby://localhost:1527/LaPizzeria;user=pizza;password=gorgonzola");
            } catch (ClassNotFoundException e) {
                System.out.println("Unable to load the driver class");
            } catch (SQLException e2) {
                System.out.println("SQL Exception:" + e2);
            }
        }

        return connection;
    }
}
