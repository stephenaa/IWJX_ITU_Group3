/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia.data;

import dk.itu.iwxj.lapizzia.model.PartnerProduct;
import java.util.List;

/**
 *
 * @author sma
 */
public class dataTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<PartnerProduct>partnerPizzas = PartnerProductDataBean.getInstance().list(0, 1000);       
        System.out.println("Got:" + partnerPizzas);
        System.out.println("Got:" + partnerPizzas.get(0).getName());
    }
}
