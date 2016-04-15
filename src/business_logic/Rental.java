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
    private Date returnDate;
    private RentalStatus status;
    
    private final int dvdId;
    private final int customerId;
    private final int reviewId;

    public Rental(int dvdId, int customerId, int reviewId) {
        this.dvdId = dvdId;
        this.customerId = customerId;
        this.reviewId = reviewId;
        
        rentDate = new Date();
        
        //GregorianCalendar cal = new GregorianCalendar();
        Calendar cal = GregorianCalendar.getInstance();
        rentDate = cal.getTime();
        
        cal.add(Calendar.DAY_OF_MONTH, 7);
        
        returnDate = cal.getTime();
    }

    @Override
    public String toString() {
        return "Rental{" + "rentDate=" + rentDate + ", returnDate=" + returnDate + ", status=" + status + ", dvdId=" + dvdId + ", customerId=" + customerId + ", reviewId=" + reviewId + '}';
    }
    

    
    
}
