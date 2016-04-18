/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author ebbmf
 */
public class Controller {

    private static Controller singleton;

    private final LinkedList<Actor> allActors;
    private final LinkedList<Keyword> allKeywords;
    private final Map<String, Movie> allMovies;
    private final Map<String, DVD> allDVDs;
    private final Map<String, Customer> allCustomers;

    private Controller() {
        allActors = new LinkedList<>();
        allKeywords = new LinkedList<>();
        allMovies = new HashMap();
        allDVDs = new HashMap();
        allCustomers = new HashMap();
    }

    public static Controller Instance() {
        if (singleton == null) {
            singleton = new Controller();
        }
        return singleton;
    }

    public void addActor(String id, String name, Gender gender) {
        allActors.add(new Actor(name, gender));
    }

    public void addKeyword(String id, String name) {
        allKeywords.add(new Keyword(name));
    }

    public void addMovie(MovieRating rating, Genre genre, int year, String name, LinkedList<Actor> actors, LinkedList<Keyword> keywords) {
        String uniqueID = UUID.randomUUID().toString();
        if (!this.allMovies.containsKey(uniqueID)) {
            allMovies.put(uniqueID, new Movie(uniqueID, rating, genre, year, name, actors, keywords));
        }
    }

    public void deleteMovie(String movieId) {
        allMovies.remove(movieId);
    }

    public LinkedList<Presentation> getMovies() {
        LinkedList<Presentation> result = new LinkedList();
        Iterator itr = allMovies.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            result.add((Presentation) pair.getValue());
        }
        return result;
    }

    public void addDVD(String movieId, Boolean lost) {
        String uniqueID = UUID.randomUUID().toString();
        if (!this.allDVDs.containsKey(uniqueID)) {
            allDVDs.put(uniqueID, new DVD(uniqueID, movieId, lost));
        }
    }

    public LinkedList<Presentation> getDVDs() {
        LinkedList<Presentation> result = new LinkedList();
        Iterator itr = allDVDs.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            result.add((Presentation) pair.getValue());
        }
        return result;
    }

    public void addCustomer(String email, String address, String phone, String password, String name) {
        String uniqueID = UUID.randomUUID().toString();
        if (!this.allCustomers.containsKey(uniqueID)) {
            allCustomers.put(uniqueID, new Customer(uniqueID, email, address, phone, password, name));
        }
    }
    
    public LinkedList<Presentation> getCustomers() {
        LinkedList<Presentation> result = new LinkedList();
        Iterator itr = allCustomers.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            result.add((Presentation) pair.getValue());
        }
        return result;
    }

    public void addRental(String customerId, String dvdId) {
        Customer cust = allCustomers.get(customerId);
        cust.addRental(dvdId);
    }

    
}
