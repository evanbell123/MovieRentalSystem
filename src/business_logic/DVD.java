/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

/**
 *
 * @author evanb
 */
public class DVD {
    private final String serialNo;
    private final Boolean lost;
    
    private int movieId;

    public DVD(String serialNo, Boolean lost, int movieId) {
        this.serialNo = serialNo;
        this.lost = lost;
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "DVD{" + "serialNo=" + serialNo + ", lost=" + lost + ", movieId=" + movieId + '}';
    }

    
}
