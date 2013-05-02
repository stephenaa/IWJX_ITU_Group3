/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.data;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import dk.itu.iwxj.lapizzia.model.PartnerProduct;
import dk.itu.iwxj.lapizzia.model.PartnerProductList;
import dk.itu.iwxj.lapizzia.model.Product;
import java.util.List;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author chwu, sma
 */
public class PartnerProductDataBean {
    private final String SERVICEURL = "http://166.78.1.65:8080/LaPizzaria.DataStore/rest/pizzas";
    //private final String SERVICEURL =  "http://www.itu.dk/people/smoa/pizzas.xml";
    
    private static PartnerProductDataBean instance;

    public static PartnerProductDataBean getInstance() {
        if (instance == null) {
            instance = new PartnerProductDataBean();
        }
        return instance;
    }

    private PartnerProductDataBean() {
    }

    /**
     * Retrieve a single Pizza from the database
     *
     * @param id productid of the Pizza
     * @return Found Pizza as a Pizza object, null if no Pizza was found
     */
    public Product get(int id) {
        return null;
    }

    
    public List<PartnerProduct> list(int priceMin, int priceMax) {
        Client c = Client.create();
        WebResource webResource = c.resource(SERVICEURL);
        
        MultivaluedMap params = new MultivaluedMapImpl();
        params.add("priceMin", Integer.toString(priceMin));
        params.add("priceMax", Integer.toString(priceMax));
        ClientResponse response = webResource.queryParams(params).accept("application/xml").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        
        //PartnerProductList list = response.getEntity(PartnerProductList.class);
                
        List<PartnerProduct> products = response.getEntity(new GenericType<List<PartnerProduct>>() {            
        });        
        
        return products;
    }
}
