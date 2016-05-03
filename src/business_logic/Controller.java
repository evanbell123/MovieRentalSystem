/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author ebbmf
 */
public class Controller {

    private static Controller singleton;

    private final LinkedList<Actor> allActors;
    private final LinkedList<Keyword> allKeywords;
    
    private Map<String, Request> allRequests;
    
    private final Map<String, Movie> allMovies;
    private final Map<String, DVD> allDVDs;
    private final Map<String, Customer> allCustomers;

    private Controller() {
        allActors = new LinkedList<>();
        allKeywords = new LinkedList<>();
        
        allRequests = new HashMap();
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
        return mapToList(allMovies);
    }

    public void addDVD(String movieId, Boolean lost) {
        String uniqueID = UUID.randomUUID().toString();
        if (!this.allDVDs.containsKey(uniqueID)) {
            allDVDs.put(uniqueID, new DVD(uniqueID, movieId, lost));
        }
    }

    public LinkedList<Presentation> getDVDs() {
        return mapToList(allDVDs);
    }

    public void addCustomer(String email, String address, String phone, String password, String name) {
        String uniqueID = UUID.randomUUID().toString();
        if (!this.allCustomers.containsKey(uniqueID)) {
            allCustomers.put(uniqueID, new Customer(uniqueID, email, address, phone, password, name));
        }
    }
    
    public LinkedList<Presentation> getCustomers() {
        return mapToList(allCustomers);
    }

    public void addRental(String customerId, String dvdId, RentalPickup pickup) {
        Customer cust = allCustomers.get(customerId);
        cust.addRental(dvdId, pickup);
    }
    
    public void addRequest(String movieId, String customerId) {
        
        String uniqueID = UUID.randomUUID().toString();
        if (!this.allRequests.containsKey(uniqueID)) {
            allRequests.put(uniqueID, new Request(uniqueID, movieId, customerId));
        }
    }
    
    public LinkedList<Presentation> getRequests() {
        return mapToList(allRequests);
    }
    
    
    public LinkedList<Presentation> searchMovies(String text){
        LinkedList<Presentation> result = new LinkedList();
        Iterator itr = allMovies.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            Searchable movie = (Searchable) pair.getValue();
            if ( movie.contains(text)){
                result.add((Presentation) pair.getValue());
            }
            
        }
        return result;
    }
    /*
        check if DVD with matching movieid is not lost
        return dvdID of available dvd
    */
    private String findDVDForMovie(String movieId){
      Iterator itr = allDVDs.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            DVD tempDVD = (DVD) pair.getValue();
            String tempMovieId = tempDVD.getMovieId();
            if ( tempMovieId.equals(movieId) && !tempDVD.Lost() ){
                return tempDVD.getID();
            }
        }   
        return "NODVD";
    }
    
    /*
        Check if any customers currently have this dvdId in there rented list
    */
    private boolean dvdIsRented(String dvdId){
        Iterator itr = allCustomers.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            Customer theCustomer = (Customer) pair.getValue();
            if ( theCustomer.hasRentedDVD(dvdId)){
                return true; 
            }
        }
        return false;
    }
    
      /*
        Given a dvd id find the rental
        find customer with dvd and mark it has returned
        check dates of return 
        return amount of fees
    */
    public double returnDVD(String dvdId, Date returnDate){
        Iterator itr = allCustomers.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            Customer theCustomer = (Customer) pair.getValue();
            if (theCustomer.hasRentedDVD(dvdId)){
                return theCustomer.returnDvd(dvdId, returnDate);
            }

        }
        return -1; 
    }
    
    //Check and see if a movie has a dvd available
    public String dvdIsAvailable(String movieId){
        //check if there exists a dvd that is not lost for movieId
        String dvdId = findDVDForMovie(movieId);  
        if ( dvdId.equals("NODVD") )
            return "NOTAVAILABLE";
        
        //now check is the this dvdId has been rented already
        if ( dvdIsRented(dvdId) )
            return "NOTAVAILABLE";
        
        //dvd is availabe return the dvdID
        return dvdId;
        
    }
    
    private static LinkedList<Presentation> mapToList(Map map) {
        LinkedList<Presentation> result = new LinkedList();
        Iterator itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            result.add((Presentation) pair.getValue());
        }
        return result;
    }
    
  

    
}
