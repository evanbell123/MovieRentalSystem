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
public final class Review {
    private final int rating;
    private final String review;
    

    public Review(int rating, String review) {
        if (rating >= 0 && rating <= 10) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("invalid rating value");
        }
        
        this.review = review;
    }
}
