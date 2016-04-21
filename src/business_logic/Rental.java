package business_logic;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evanb
 */
public class Rental {
    private Date rentDate;
    private final Date returnDate;
    private RentalStatus status;
    private final double charge;
    private static final double standardPrice = 2.0;
    private static final double feePerLateDay = 0.1;
    
    
    private final String dvdId;
    private final String reviewId;


    public Rental(String dvdId) {
        this.dvdId = dvdId;
        reviewId = null;
        
        charge = standardPrice;
        
        rentDate = new Date();
        
        Calendar cal = GregorianCalendar.getInstance();
        rentDate = cal.getTime();
        
        cal.add(Calendar.DAY_OF_MONTH, 7);
        
        returnDate = cal.getTime();
    }

    @Override
    public String toString() {
        return "Rental{" + "rentDate=" + rentDate + ", returnDate=" + returnDate + ", status=" + status + ", charge=" + charge + ", dvdId=" + dvdId + ", reviewId=" + reviewId + '}';
    }
}
