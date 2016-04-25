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
import business_logic.Genre;
import static business_logic.Genre.*;
import business_logic.Keyword;
import business_logic.MovieRating;
import static business_logic.MovieRating.*;
import business_logic.Presentation;
import static business_logic.RentalPickup.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

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

        int minYear = 1960;
        int maxYear = 2016;

        for (int i = 0; i < totalMovies; i++) {
            /*
            Generate random list of actors
             */
            Collections.shuffle(allActors);

            LinkedList<Actor> actors = new LinkedList<>();
            for (int j = 0; j < actorsPerMovie; j++) {
                actors.add(allActors.get(j));
            }

            /*
            Generate random list of keywords
             */
            Collections.shuffle(allKeywords);

            LinkedList<Keyword> keywords = new LinkedList<>();

            for (int k = 0; k < keywordsPerMovie; k++) {
                keywords.add(allKeywords.get(k));
            }

            /*
            Generate random year
             */
            int year = ThreadLocalRandom.current().nextInt(minYear, maxYear + 1);

            /*
            Pick a genre
             */
            Genre genre;
            if (i % 7 == 0) {
                genre = WESTERN;
            } else if (i % 6 == 0) {
                genre = ROMANCE;
            } else if (i % 5 == 0) {
                genre = HORROR;
            } else if (i % 4 == 0) {
                genre = DRAMA;
            } else if (i % 3 == 0) {
                genre = COMEDY;
            } else {
                genre = ACTION;
            }

            /*
            Pick a rating
             */
            MovieRating rating;
            if (i % 4 == 0) {
                rating = R;
            } else if (i % 3 == 0) {
                rating = PG13;
            } else if (i % 2 == 0) {
                rating = PG;
            } else {
                rating = G;
            }

            controller.addMovie(rating, genre, year, "movie name " + Integer.toString(i), actors, keywords);
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
                dvdCount++;
                controller.addDVD(movies.get(i).getID(), lost);
            }
        }

        LinkedList<Presentation> dvds = controller.getDVDs();

        print("Get all dvds", dvds);

        /*
        Create test customer
         */
        controller.addCustomer("random@email.com", "KCMO", "555-444-3333", "password", "Bob");

        LinkedList<Presentation> customers = controller.getCustomers();

        print("Get all customers", customers);

        //controller.addRental(customers.getFirst().getID(), dvds.getLast().getID(), IN_STORE);
        customers = controller.getCustomers();

        print("Rent a dvd", customers);

        LinkedList<Presentation> matchedMovies = controller.searchMovies("keyword17");
        print("Searched for Keyword17", matchedMovies);

        LinkedList<Presentation> matchedMovies2 = controller.searchMovies("actor0");
        print("Searched for actor0", matchedMovies2);

        LinkedList<Presentation> matchedMovies3 = controller.searchMovies("WESTERN");
        print("Searched for western", matchedMovies3);
        
        LinkedList<Presentation> matchedMovies4 = controller.searchMovies("R");
        print("Searched for R", matchedMovies4);

        LinkedList<Presentation> matchedMovies10 = controller.searchMovies("actor");

        print("Searched for 'actor'", matchedMovies10);

        //Simulate Customer Checking if Movie is available
        //then let customer rent if available
        String theMovieId = matchedMovies10.getFirst().getID();
        if (!controller.dvdIsAvailable(theMovieId).equals("NOTAVAILABLE")) {
            // Yeah! movie is available to rent
            controller.addRental(customers.getFirst().getID(), dvds.getLast().getID(), MAIL);
            System.out.println("Rental Successful \n" + customers.getFirst());
        } else {
            System.out.println("Movie was not available, customer is requesting");
            //darn! movie is either rented or lost
            //but you can request it
            controller.addRequest(theMovieId, customers.getFirst().getID());
            //print("Request unavailable movie", allRequests);
        }
        
        /*
        Test rentals and requests
        */
        controller.addMovie(G, DRAMA, maxYear, "1", allActors, allKeywords);
        
        /*
        Add an unavailable DVD
        */
        controller.addDVD("1", TRUE);
        
        if (!controller.dvdIsAvailable("1").equals("NOTAVAILABLE")) {
            // Yeah! movie is available to rent
            controller.addRental(customers.getFirst().getID(), "1", MAIL);
            System.out.println("Rental Successful \n" + customers.getFirst());
        } else {
            System.out.println("Movie was not available, customer is requesting");
            //darn! movie is either rented or lost
            //but you can request it
            controller.addRequest("1", customers.getFirst().getID());
            //print("Request unavailable movie", allRequests);
        }
        
        print("add request for unavaiable DVD", controller.getRequests());
        
        
        System.out.println();
        

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
