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
public class Movie implements Presentation {
    
    private final String id;
    private final MovieRating rating;
    private final Genre genre;
    private final int year;
    private final String name;
    private final LinkedList<Actor> actors;
    private final LinkedList<Keyword> keywords;

    public Movie(String id, MovieRating rating, Genre genre, int year, String name, LinkedList<Actor> actors, LinkedList<Keyword> keywords) {
        this.id = id;
        this.rating = rating;
        this.genre = genre;
        this.year = year;
        this.name = name;
        this.actors = actors;
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        String movieString = "Movie{" + "rating=" + rating + ", genre=" + genre + ", year=" + year + ", name=" + name + ", actors=[";

        for (int i = 0; i < actors.size(); i++) {
            movieString += actors.get(i).getName();
            if (i != actors.size() - 1) {
                movieString += ", ";
            } else {
                movieString += "]";
            }
        }

        movieString += ", keywords=[";

        for (int i = 0; i < keywords.size(); i++) {
            movieString += keywords.get(i).getName();
            if (i != keywords.size() - 1) {
                movieString += ", ";
            } else {
                movieString += "]";
            }
        }

        return movieString;
    }

    @Override
    public String getID() {
        return id;
    }
}
