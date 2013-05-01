/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.resources;

import dk.itu.iwxj.lapizzia.NumbersUtilty;
import dk.itu.iwxj.lapizzia.data.ProductDataBean;
import dk.itu.iwxj.lapizzia.model.Product;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


/*
 * Doc:
 * http://www.mkyong.com/webservices/jax-rs/jax-rs-pathparam-example/
 * http://technotes.tostaky.biz/2012/08/jaxb-annotation-summary-crash-course.html
 */
/**
 * REST Web Service
 *
 * @author stephen
 */
@Path("/pizza")
public class PizzaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PizzaResource
     */
    public PizzaResource() {
    }

    /**
     * Retrieves representation of a list of the products(pizze).
     *
     * dk.itu.iwxj.lapizzia.resources.PizzaResource TODO fetch min/max of price
     * from DB instead of hard code them.
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml(@QueryParam("priceMin") String priceMin,
            @QueryParam("priceMax") String priceMax) {
        StringBuilder builder = new StringBuilder();
        int priceMinInt = -1, priceMaxInt = -1;
        builder.append("<pizzalist>");
        
        //validate, and ensure param to db in properly initialized as param is optional
        priceMinInt = NumbersUtilty.INSTANCE.isInteger(priceMin) ? Integer.parseInt(priceMin) : 0;
        priceMaxInt = NumbersUtilty.INSTANCE.isInteger(priceMax) ? Integer.parseInt(priceMax) : 999;

        if (NumbersUtilty.INSTANCE.isMaxGreaterThanMin(priceMinInt, priceMaxInt)) {
            List<Product> pizze = ProductDataBean.getInstance().list(0, 100, priceMinInt, priceMaxInt);
            for (Product p : pizze) {
                builder.append(p.toXml());
            }
        }
        builder.append("</pizzalist>");
        return builder.toString();
    }

    @Path("{id}")
    @GET
    @Produces("application/xml")
    public String getXml(@PathParam("id") String pizzaId) {
        StringBuilder builder = new StringBuilder();
        builder.append("<pizzalist>");

        Product pizza = ProductDataBean.getInstance().get(NumbersUtilty.INSTANCE.isInteger(pizzaId) ? Integer.parseInt(pizzaId) : 0);
        builder.append(pizza.toXml());
        builder.append("</pizzalist>");
        return builder.toString();
    }

    /**
     * PUT method for updating or creating an instance of PizzaResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
