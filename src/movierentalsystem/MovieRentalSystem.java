/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierentalsystem;

import business_logic.Actor;
import business_logic.Customer;
import business_logic.DVD;
import business_logic.Gender;
import static business_logic.Gender.*;
import static business_logic.Genre.*;
import business_logic.Keyword;
import business_logic.Movie;
import static business_logic.MovieRating.PG13;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author evanb
 */
public class MovieRentalSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        Create sample customers
        */
        Map customers = new HashMap();

        customers.put(1, new Customer("random@email.com", "KCMO", "555-444-3333", "password", "Bob"));
        customers.put(2, new Customer("email@random.com", "KCMO", "456-444-3333", "password", "Mary"));

        /*
        Generate sample actors
        */
        LinkedList<Actor> allActors = new LinkedList<>();
        
        int totalActors = 50;

        for (int i = 0; i < totalActors; i++) {
            Gender gender;
            if (i % 2 == 0) {
                gender = MALE;
            } else {
                gender = FEMALE;
            }
            allActors.add(new Actor("actor" + Integer.toString(i), gender));
        }
        
        /*
        Generate sample keywords
        */
        LinkedList<Keyword> allKeywords = new LinkedList<>();
        int totalKeywords = 20;

        for (int i = 0; i < totalKeywords; i++) {
            allKeywords.add(new Keyword("keyword" + Integer.toString(i)));
        }
        
        /*
        Generate sample movies
        */
        int totalMovies = 5;
        int actorsPerMovie = 4;
        int keywordsPerMovie = 3;

        Map movies = new HashMap();

        for (int i = 0; i < totalMovies; i++) {

            Collections.shuffle(allActors);

            LinkedList<Actor> actors = new LinkedList<>();
            for (int j = 0; j < actorsPerMovie; j++) {
                actors.add(allActors.get(j));
            }

            Collections.shuffle(allKeywords);

            LinkedList<Keyword> keywords = new LinkedList<>();

            for (int k = 0; k < keywordsPerMovie; k++) {
                keywords.add(allKeywords.get(k));
            }

            movies.put(i, new Movie(PG13, COMEDY, 2012, "movie name " + Integer.toString(i), actors, keywords));
        }

        Iterator movieIterator = movies.entrySet().iterator();
        printMap(movieIterator);
        
        /*
        Generate sample DVD's
        */
        int dvdsPerMovie = 10;
        int lostDvdsRatio = 20; //1 lost dvd for every 20
        
        int dvdCount = 0;
        
        Map dvds = new HashMap();
        
        for (int i = 0; i < totalMovies; i++) {
            for (int j = 0; j < dvdsPerMovie; j++) {
                Boolean lost = false;
                if ((i+j) % lostDvdsRatio == 0) {
                    lost = true;
                }
                dvds.put(dvdCount, new DVD(UUID.randomUUID().toString(), lost, i));
                dvdCount++;
            }
        }
        
        Iterator dvdIterator = dvds.entrySet().iterator();
        printMap(dvdIterator);
    }
    
    private static void printMap(Iterator it) {
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue().toString());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

}
