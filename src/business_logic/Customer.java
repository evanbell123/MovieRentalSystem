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
public class Customer implements Presentation{
    private final String id;
    private final String email;
    private final String address;
    private final String phone;
    private final String password;
    private final String name;
    
    private final LinkedList<Rental> rentals;

    public Customer(String id, String email, String address, String phone, String password, String name) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.rentals = new LinkedList<>();
    }
    
    public void addRental(String dvdId, RentalPickup pickup) {
        rentals.add(new Rental(dvdId, pickup));
    }
    
    @Override
    public String toString() {
        String customerString = "Customer{" + "id=" + id + ", email=" + email +
                ", address=" + address + ", phone=" + phone + ", password=" + 
                password + ", name=" + name + ", rentals=[";
        
        if (rentals.isEmpty()) {
            customerString += "]";
        }

        for (int i = 0; i < rentals.size(); i++) {
            customerString += rentals.get(i).toString();
            if (i != rentals.size() - 1) {
                customerString += ", ";
            } else {
                customerString += "]";
            }
        }
        
        customerString += "}";

        return customerString;
    }

    @Override
    public String getID() {
        return id;
    }

    /*
        Returns true if movie has been rented 
    */
    public boolean hasRentedDVD(String dvdId){
        for (Rental theRental: rentals){
            if ( theRental.getID().equals(dvdId))
                return true;
        }
        return false;
    }
    
}
