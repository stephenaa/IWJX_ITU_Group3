/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia;

import java.util.Calendar;

/**
 *
 * @author vibekelarsen
 */
public enum UUIDUtilty {
    INSTANCE;
    
    
    public int getUUID() {
       Calendar now =Calendar.getInstance();
       //as the column of delivery is an int ...
        Long time = now.getTimeInMillis();
        time = time / 10000L;
        
        return new Integer(time.intValue());
    }
    
}
