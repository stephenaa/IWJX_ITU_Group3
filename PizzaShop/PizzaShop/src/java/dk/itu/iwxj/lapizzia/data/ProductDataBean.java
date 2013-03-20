/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.data;

import dk.itu.iwxj.lapizzia.DatabaseManager;
import dk.itu.iwxj.lapizzia.model.Product;
import dk.itu.iwxj.lapizzia.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chwu
 */
public class ProductDataBean {

    public boolean add(Product product) {
        try {
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement addProductStmt =
                    connection.prepareStatement(
                    "INSERT INTO products ( "
                    + "name, description, price) "
                    + "VALUES ( ?, ?, ?)");

            addProductStmt.setString(1, product.getName());
            addProductStmt.setString(2, product.getDescription());
            addProductStmt.setInt(3, product.getPrice());
            addProductStmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    
     public List<Product> list() {
        List<Product> list = new ArrayList<Product>();

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement getProductsStmt;
        try {
            getProductsStmt = connection.prepareStatement(
                    "SELECT * FROM products ORDER BY name");

            ResultSet results = getProductsStmt.executeQuery();

            while (results.next()) {
                Product product = new Product();
                product.setId(results.getInt("productid"));
                product.setName(results.getString("name"));
                product.setDescription(results.getString("description"));
                product.setPrice(results.getInt("price"));
                
                list.add(product);
            }
            results.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
        
    public List<Product> list(int start, int count) {
        List<Product> list = new ArrayList<Product>();

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement getProductsStmt;
        try {
            getProductsStmt = connection.prepareStatement(
                    "SELECT * FROM products ORDER BY name LIMIT ?,?");
            getProductsStmt.setInt(1, start);
            getProductsStmt.setInt(2, count);

            ResultSet results = getProductsStmt.executeQuery();
            while (results.next()) {
                Product product = new Product();
                product.setId(results.getInt("productid"));
                product.setName(results.getString("name"));
                product.setDescription(results.getString("description"));
                product.setPrice(results.getInt("price"));

                list.add(product);

            }
            results.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    
    public int numberOfProducts() {        
        Connection connection = DatabaseManager.getConnection();
        int number = 0;
        
        PreparedStatement getProductsStmt;
        try {
            getProductsStmt = connection.prepareStatement(
                    "SELECT count(*) FROM products");
            ResultSet results = getProductsStmt.executeQuery();            
            results.first();
            number = results.getInt(1);
            results.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

    
    public boolean del(int id) {
        try {
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement delProductStmt =
                    connection.prepareStatement("DELETE FROM products WHERE productid=?");

            delProductStmt.setInt(1, id);
            
            if (delProductStmt.executeUpdate() != 0) {
                return true;
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
                    
        return false;
    }
    
}
