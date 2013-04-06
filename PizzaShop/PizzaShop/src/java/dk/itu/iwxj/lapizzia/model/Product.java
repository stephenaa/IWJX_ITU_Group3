/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.model;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
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
     * Constructs an XML representation of the Pizza
     * @return XML representing the Pizza
     */
    public String toXml() {
        StringWriter writer = new StringWriter();
        PrintWriter printer = new PrintWriter(writer);
        
        printer.printf("<pizza id='%d'>", this.id);
        printer.printf("<name>%s</name>", this.name);
        printer.printf("<description>%s</description>", this.description);
        printer.printf("<price>%s</price>", this.price);
        printer.printf("</pizza>");
        
        return writer.toString();
    }
    

    
    /**
     * Constructs a HTML representation of the Pizza
     * @return  The HTML
     */
    public String toHtml() {
        StringWriter writer = new StringWriter();
        PrintWriter printer = new PrintWriter(writer);
        
        printer.printf("<strong>%s</strong>", this.name);
        printer.printf("<p>%s</p>", this.description);
        printer.printf("<br/><strong><span style=\"font-size:+3;\">DKK %s,-</span></strong>", this.price);
        
        return writer.toString();
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
