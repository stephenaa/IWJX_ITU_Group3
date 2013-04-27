/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.data;

import dk.itu.iwxj.lapizzia.DatabaseManager;
import dk.itu.iwxj.lapizzia.model.Product;
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
 * @author chwu, sma
 */
public class ProductDataBean {

    private static ProductDataBean instance;

    public static ProductDataBean getInstance() {
        if (instance == null) {
            instance = new ProductDataBean();
        }
        return instance;
    }

    private ProductDataBean() {
    }

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

    /**
     * Retrieve a single Pizza from the database
     *
     * @param id productid of the Pizza
     * @return Found Pizza as a Pizza object, null if no Pizza was found
     */
    public Product get(int id) {
        Connection connection = DatabaseManager.getConnection();
        Product product = null;

        try {
            PreparedStatement getProductsStmt = connection.prepareStatement(
                    "SELECT * FROM products WHERE productid = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            getProductsStmt.setInt(1, id);
            ResultSet results = getProductsStmt.executeQuery();
            if (results != null && results.first()) {
                product = new Product();
                product.setId(results.getInt("productid"));
                product.setName(results.getString("name"));
                product.setDescription(results.getString("description"));
                product.setPrice(results.getInt("price"));
            }
            results.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return product;
    }

//    public List<Product> list() {
//        List<Product> list = new ArrayList<Product>();
//
//        Connection connection = DatabaseManager.getConnection();
//        PreparedStatement getProductsStmt;
//        try {
//            getProductsStmt = connection.prepareStatement(
//                    "SELECT * FROM products ORDER BY name");
//
//            ResultSet results = getProductsStmt.executeQuery();
//
//            while (results.next()) {
//                Product product = new Product();
//                product.setId(results.getInt("productid"));
//                product.setName(results.getString("name"));
//                product.setDescription(results.getString("description"));
//                product.setPrice(results.getInt("price"));
//
//                list.add(product);
//            }
//            results.close();
//        } catch (Exception ex) {
//            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return list;
//    }
    public List<Product> list(int start, int count, int priceMin, int priceMax) {
        List<Product> list = new ArrayList<Product>();

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement getProductsStmt;
        try {
            getProductsStmt = connection.prepareStatement(
                    "SELECT * FROM (SELECT ROW_NUMBER() OVER() AS rownum, products.* FROM products where price between " + priceMin + " and  " + priceMax + "  ORDER BY name  ) AS tmp WHERE rownum > ? AND rownum <= ?");

            getProductsStmt.setInt(1, start);
            getProductsStmt.setInt(2, start + count);

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

    public int numberOfProducts(int priceMin, int priceMax) {
        Connection connection = DatabaseManager.getConnection();
        int number = 0;

        PreparedStatement getProductsStmt;
        try {
            getProductsStmt = connection.prepareStatement(
                    "SELECT count(*) FROM products where price between " + priceMin + " and  " + priceMax,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
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

    /**
     * Update product in database TODO: Extend to handle all properties of the
     * model
     *
     * @param product
     * @return
     */
    public boolean update(Product product) {
        try {
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement stmt =
                    connection.prepareStatement("UPDATE products SET price = ?, name = ?, description = ? WHERE productid=?");

            stmt.setInt(1, product.getPrice());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setInt(4, product.getId());

            if (stmt.executeUpdate() != 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
}
