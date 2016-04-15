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
public class Customer {
    private final String email;
    private final String address;
    private final String phone;
    private final String password;
    private final String name;

    public Customer(String email, String address, String phone, String password, String name) {
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.name = name;
    }
}
