/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.data;

import dk.itu.iwxj.lapizzia.DatabaseManager;
import dk.itu.iwxj.lapizzia.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chwu
 */
public class UserDataBean {
    private static UserDataBean instance;
    
    private UserDataBean() {
        
    }
    
    public static UserDataBean getInstance() {
        if (instance == null) {
            instance = new UserDataBean();            
        }
        return instance;
    }
    
    public User getUser(String name, String password) {
        User user = null;

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement getUserStmt;
        System.out.println("User,password:" + name + "," + password);
        try {
            getUserStmt = connection.prepareStatement(
                    "SELECT * FROM users WHERE name=? AND password=?");

            getUserStmt.setString(1, name);
            getUserStmt.setString(2, password);

            ResultSet results = getUserStmt.executeQuery();
            
            while (results.next()) {
                user = new User();
                user.setUserid(results.getInt("userid"));
                user.setUsername(results.getString("name"));
                user.setAddress(results.getString("address"));
                user.setEmail(results.getString("email"));
                user.setPhone(results.getString("phone"));
                user.setRole(results.getInt("role"));
                user.setZipcode(results.getString("zipcode"));
                
                System.out.println("Got user object: " + user);
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    
    public boolean add(User user) {
        try {
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement addUserStmt =
                    connection.prepareStatement(
                    "INSERT INTO users ( "
                    + "name, password, address, zipcode,phone,email,role ) "
                    + "VALUES ( ?, ?, ? ,?,?,?,1)");

            addUserStmt.setString(1, user.getUsername());
            addUserStmt.setString(2, user.getPassword());
            addUserStmt.setString(3, user.getAddress());
            addUserStmt.setString(4, user.getZipcode());
            addUserStmt.setString(5, user.getPhone());
            addUserStmt.setString(6, user.getEmail());

            addUserStmt.executeUpdate();

            return true;

        }  catch (SQLException ex) {
            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
