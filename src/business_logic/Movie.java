/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import java.util.LinkedList;

/**
 *
 * @author evanb
 */
public class Movie {
    private final MovieRating rating;
    private final Genre genre;
    private final int year;
    private final String name;
    private final LinkedList<Actor> actors;
    private final LinkedList<Keyword> keywords;

    public Movie(MovieRating rating, Genre genre, int year, String name, LinkedList<Actor> actors, LinkedList<Keyword> keywords) {
        this.rating = rating;
        this.genre = genre;
        this.year = year;
        this.name = name;
        this.actors = actors;
        this.keywords = keywords;
    }

    
    
    
}
