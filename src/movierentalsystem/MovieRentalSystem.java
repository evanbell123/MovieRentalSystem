/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierentalsystem;

import business_logic.Controller;
import business_logic.Actor;
import business_logic.Gender;
import static business_logic.Gender.*;
import static business_logic.Genre.COMEDY;
import business_logic.Keyword;
import static business_logic.MovieRating.PG13;
import business_logic.Presentation;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author evanb
 */
public class MovieRentalSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller controller = Controller.Instance();
        
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

            controller.addMovie(PG13, COMEDY, 2012, "movie name " + Integer.toString(i), actors, keywords);
        }
        
        LinkedList<Presentation> movies = controller.getMovies();
        
        print("Initial Movies", movies);
        
        controller.deleteMovie(movies.getLast().getID());
        
        movies = controller.getMovies();
        
        print("Delete the last movie", movies);
        
        /*
        Generate sample DVD's
        */
        int dvdsPerMovie = 10;
        int lostDvdsRatio = 20; //1 lost dvd for every 20
        
        int dvdCount = 0;
        
        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < dvdsPerMovie; j++) {
                Boolean lost = false;
                if (dvdCount % lostDvdsRatio == 0) {
                    lost = true;
                }
                controller.addDVD(movies.get(i).getID(), lost);
            }
        }
        
        LinkedList<Presentation> dvds = controller.getDVDs();
        
        print("Get all dvds", dvds);
        
    }
    
    private static void print(String description, LinkedList<Presentation> list) {
        System.out.println(description + "\n");
        
        ListIterator<Presentation> it = list.listIterator();
        
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();
    }

}
