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
public class Movie implements Presentation, Searchable {
    
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
        
        if (actors.isEmpty()) {
            movieString += "]";
        }
        
        for (int i = 0; i < actors.size(); i++) {
            movieString += actors.get(i).getName();
            if (i != actors.size() - 1) {
                movieString += ", ";
            } else {
                movieString += "]";
            }
        }

        movieString += ", keywords=[";
        
        if (keywords.isEmpty()) {
            movieString += "]";
        }

        for (int i = 0; i < keywords.size(); i++) {
            movieString += keywords.get(i).getName();
            if (i != keywords.size() - 1) {
                movieString += ", ";
            } else {
                movieString += "]";
            }
        }
        
        movieString += "}";

        return movieString;
    }

    @Override
    public String getID() {
        return id;
    }
    
    private boolean searchActors(String text){
        for(Actor theActor: actors){
            if (theActor.contains(text))
                return true;
        }
        return false;
    }
    
     private boolean searchKeywords(String text){
        for(Keyword thekeyWord: keywords){
            if (thekeyWord.contains(text))
                return true;
        }
        return false;
    }

     /*
        If any private variables of Movie including Actors list and Keyword list
        contain search text, return true
     */
    @Override
    public boolean contains(String text) {
        return ( 
                name.contains(text) || id.contains(text)
                || searchActors(text) || searchKeywords(text)
                || genre.toString().contains(text)
                || rating.toString().contains(text)
                || Integer.toString(year).contains(text)
               );
    }
}
