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
public class DVD {
    private final String serialNo;
    private final Boolean lost;

    public DVD(String serialNo, Boolean lost) {
        this.serialNo = serialNo;
        this.lost = lost;
    }
}
