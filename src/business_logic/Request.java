/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import static business_logic.RequestStatus.*;
import java.util.Date;

/**
 *
 * @author evanb
 */
public class Request implements Presentation {
    private final String id;
    private final Date requestDate;
    private final Date responseDate;
    private final RequestStatus status;
    
    private final String movieId;
    private final String customerId;

    public Request(String id, String movieId, String customerId) {
        this.id = id;
        this.requestDate = new Date();
        this.responseDate = null;
        //this.responseDate = responseDate;
        this.status = PENDING;
        this.movieId = movieId;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Request{" + "requestDate=" + requestDate + ", responseDate=" + responseDate + ", status=" + status + ", movieId=" + movieId + ", customerId=" + customerId + '}';
    }

    @Override
    public String getID() {
        return id;
    }

    
}
