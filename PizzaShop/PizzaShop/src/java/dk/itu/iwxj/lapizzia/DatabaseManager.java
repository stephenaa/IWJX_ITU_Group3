/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;

/**
 *
 * @author chwu+vanl+sma
 */
public class DatabaseManager {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // load the MySql driver
                Class.forName("com.mysql.jdbc.Driver");

                // connect to the database               
                connection = DriverManager.getConnection("jdbc:mysql://mysql.itu.dk/chwu", "chwu", "wu1234");
                 //connection = DriverManager.getConnection("jdbc:mysql://localhost/LAPIZZERIA", "sma", "lalle");
                
            } catch (Exception ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                return null;           
            }
        }

        return connection;
    }
}
