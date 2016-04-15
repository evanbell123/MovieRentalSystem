/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import java.util.Date;

/**
 *
 * @author evanb
 */
public class Request {
    private final Date requestDate;
    private final Date responseDate;
    private final RequestStatus status;
    
    private final int movieId;
    private final int customerId;

    public Request(Date requestDate, Date responseDate, RequestStatus status, int movieId, int customerId) {
        this.requestDate = requestDate;
        this.responseDate = responseDate;
        this.status = status;
        this.movieId = movieId;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Request{" + "requestDate=" + requestDate + ", responseDate=" + responseDate + ", status=" + status + ", movieId=" + movieId + ", customerId=" + customerId + '}';
    }

    
}
