/*
 * @class Type:  Util Class
 * @description: This class is a collections of methods relating to request of 
 *               current date and time.
 */

package numbersliderpuzzlegame.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * project: A desktop based number sliding puzzle game application
 * email:   chenc5@andrew.cmu.edu
 * @author  Chen Chen
 * Created in 2016, at the University of Nottingham, UK  
 */

public class DateTimeOperator {
    
    /**
     * Default Constructor
     * <p> Empty method
     */
    public DateTimeOperator(){} 
    
    /**
     * Requesting current time
     * 
     * @return current time in string format 
     */
    public static String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return (dateFormat.format(cal.getTime())); 
    } // end method
    
} // end class
