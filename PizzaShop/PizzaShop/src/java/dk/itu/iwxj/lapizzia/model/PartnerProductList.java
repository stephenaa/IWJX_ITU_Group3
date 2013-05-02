/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.itu.iwxj.lapizzia.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sma
 */
@XmlRootElement(name="pizzalist")
public class PartnerProductList {
    @XmlElementWrapper(name="pizzalist")
    @XmlElement(name="pizza")
    List<PartnerProduct> pizzalist = new ArrayList<PartnerProduct>();

    public List<PartnerProduct> getRecords(){ return pizzalist; }
 //   public void setRecords(List<Data> records) { this.records = records; }
}
