/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.model;

import java.io.Serializable;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chwu
 */
public class Order implements Serializable{

    private int orderId;
    private int fkUserid;
    private Date deliveryTime;
    private String comment;

    public boolean initFromRequest(HttpServletRequest request, User user) {

        this.fkUserid = user.getUserid();
        this.comment = request.getParameter("comment");
        this.deliveryTime = Date.valueOf((String)request.getParameter("delivery"));
        return true;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @return the fkUserid
     */
    public int getFkUserid() {
        return fkUserid;
    }

   
    /**
     * @return the deliveryTime
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
