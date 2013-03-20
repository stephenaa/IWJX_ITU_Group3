/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.model;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sma
 */
public class Product implements Serializable{

    private int id;
    private String name;
    private String description;
    private int price;

    public boolean initFromRequest(HttpServletRequest request) {
        setName(request.getParameter("name"));
        setDescription(request.getParameter("description"));
        setPrice(Integer.parseInt(request.getParameter("price")));
        return true;       
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
}
