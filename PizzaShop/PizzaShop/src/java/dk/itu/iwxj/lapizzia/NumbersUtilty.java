/*
*
 */
package dk.itu.iwxj.lapizzia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vibekelarsen
 */
public enum NumbersUtilty {

    INSTANCE;

    public boolean isMaxGreaterThanMin(int min, int max) {
        return max > min;
    }
/**
 * 
 */
     public boolean isInteger(String maybeANumber) {
        if (maybeANumber == null) {
            return Boolean.FALSE;
        } else {
            try {
                Integer.parseInt(maybeANumber);
                return Boolean.TRUE;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, "not a number: " + maybeANumber);
                return Boolean.FALSE;
            }
        }
    }
}
