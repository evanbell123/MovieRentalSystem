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
public class Keyword implements Searchable {
    private String name;

    public Keyword(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean contains(String text) {
        return name.contains(text);
    }
}
