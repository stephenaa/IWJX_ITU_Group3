/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

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
    
    
    /**
     * Initialize a product from an XML fragment. Possibly set default values from existing Product object.
     * @param src
     * @param xml
     * @return 
     */
    public Product initFromXml(Product src, InputStream xml) {        
        Product product = null;
        
        if (src != null) {            
            // TODO: Implement a clone() method on the Product class
            product = new Product();
            
            product.setId(src.getId());
            product.setDescription(src.getDescription());
            product.setName(src.getName());
            product.setPrice(src.getPrice());
        }
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);
            
            NodeList elements = doc.getElementsByTagName("pizza");
            for (int temp = 0; temp < elements.getLength(); temp++) {
     
                    Node node = elements.item(temp);
                    Element element = (Element) node;
                                        
                    if(node.getNodeName().equalsIgnoreCase("pizza")) {                    
                        
                        NodeList list = element.getElementsByTagName("price");
                        if(list.getLength() > 0) {                        
                            product.setPrice(Integer.parseInt(list.item(0).getTextContent()));
                        }
                        
                        list = element.getElementsByTagName("name");
                        if(list.getLength() > 0) {
                            product.setName(list.item(0).getTextContent());
                        }
                        
                        list = element.getElementsByTagName("description");                        
                        if(list.getLength() > 0) {                        
                            product.setDescription(list.item(0).getTextContent());
                        }
                    }

            }            
            
        } catch (SAXException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
                            
        return product;
    }
}
