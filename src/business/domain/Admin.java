/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.domain;

/**
 *
 * @author Felipe
 */
public class Admin extends User {

    /**
     * System administrator constructor
     * @param login unique login
     * @param password
     * @param name real name
     */
    public Admin(String login, String password, String name) {
        super(login, password, name);
    }
    
}
