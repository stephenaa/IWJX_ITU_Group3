/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.data;

import dk.itu.iwxj.lapizzia.DatabaseManager;
import dk.itu.iwxj.lapizzia.model.Order;
import dk.itu.iwxj.lapizzia.model.OrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chwu
 */
public class OrderDataBean {
    private static OrderDataBean instance;
    
    private OrderDataBean() {
        
    }
    
    public static OrderDataBean getInstance() {
        if (instance == null) {
            instance = new OrderDataBean();
        }
        return instance;
    }
    
    
    public boolean add(Order newOrder) {
        try {
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement addOrderStmt =
                    connection.prepareStatement(
                    "INSERT INTO orders ( "
                    + "orderid, userid, comment, deliverytime) "
                    + "VALUES ( ?, ?, ?, ?)");
            addOrderStmt.setInt(1, newOrder.getOrderId());
            addOrderStmt.setInt(2, newOrder.getFkUserid());
            addOrderStmt.setString(3, newOrder.getComment());
            addOrderStmt.setDate(4, newOrder.getDeliveryTime());
            addOrderStmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OrderDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean add(OrderItem newOrderLine) {
        try {
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement addOrderStmt =
                    connection.prepareStatement(
                    "INSERT INTO orderlines ( "
                    + "fk_orderid, fk_productid, qty) "
                    + "VALUES ( ?, ?, ?)");
            addOrderStmt.setInt(1, newOrderLine.getFkOrderId());
            addOrderStmt.setInt(2, newOrderLine.getFkProductId());
            addOrderStmt.setInt(3, newOrderLine.getQuantity());
            addOrderStmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OrderDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
