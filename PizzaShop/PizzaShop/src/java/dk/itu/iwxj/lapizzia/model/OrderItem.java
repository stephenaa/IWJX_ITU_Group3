/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.model;

import dk.itu.iwxj.lapizzia.data.ProductDataBean;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vibekelarsen
 */
public class OrderItem implements Serializable {

    private int lineId;
    private int fkProductId;
    private int fkOrderId;
    private String name;
    private int price;
    private int quantity;
    private int totalPrice;

    public boolean initFromRequest(HttpServletRequest request) {
        ProductDataBean productBean = ProductDataBean.getInstance();
        Product pizza = productBean.get(Integer.parseInt(request.getParameter("product_id")));
        if (pizza != null) {            
            setFkProductId(pizza.getId());
            setName(pizza.getName());
            setPrice(pizza.getPrice());
            if (request.getParameter("qty") == null || request.getParameter("qty").isEmpty()) {
                setQuantity(1);
            } else {
                setQuantity(Integer.parseInt(request.getParameter("qty")));
            }
        }
        return true;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the lineId
     */
    public int getFkProductId() {
        return fkProductId;
    }

    /**
     * @param lineId the lineId to set
     */
    public void setFkProductId(int fkProductId) {
        this.fkProductId = fkProductId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the totalPrice
     */
    public int getTotalPrice() {
        //as we are usine int no check for null is needed.
        totalPrice = this.price * this.quantity;
        return totalPrice;
    }

    /**
     * @return the lineId
     */
    public int getLineId() {
        return lineId;
    }

    /**
     * @param lineId the lineId to set
     */
    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    /**
     * @return the fkOrderId
     */
    public int getFkOrderId() {
        return fkOrderId;
    }

    /**
     * @param fkOrderId the fkOrderId to set
     */
    public void setFkOrderId(int fkOrderId) {
        this.fkOrderId = fkOrderId;
    }
}
