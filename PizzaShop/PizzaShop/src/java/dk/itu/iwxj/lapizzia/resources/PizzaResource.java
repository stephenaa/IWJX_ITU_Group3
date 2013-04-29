/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.resources;

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
     * Retrieves representation of an instance of
     * dk.itu.iwxj.lapizzia.resources.PizzaResource
     *
     * @return an instance of java.lang.String
     */
    @Path("{priceMin}/{priceMax}")
    @GET
    @Produces("application/xml")
    public String getXml(
            @PathParam("priceMin") int priceMin,
            @PathParam("priceMax") int priceMax) {
        StringBuilder builder = new StringBuilder();
        builder.append("<pizzalist>");

        List<Product> pizze = ProductDataBean.getInstance().list(0, 100, priceMin, priceMax);
        for (Product p : pizze) {
            builder.append(p.toXml());
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

        Product pizza = ProductDataBean.getInstance().get(Integer.parseInt(pizzaId));
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
