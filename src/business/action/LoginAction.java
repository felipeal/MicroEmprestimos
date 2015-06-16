/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.domain.Donator;
import business.domain.Enterpreneur;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class LoginAction extends AbstractAction  {
    
    // Returns logged enterpreneur on success, null on failure

    /**
     *
     * @param username
     * @param password
     * @return logged entrepreneur or null
     */
        public Enterpreneur loginAsEnterpreneur(String username, String password) {
        
        Database database = Database.getInstance();
        
        for (Enterpreneur enterpreneur : database.getAllEnterpreneurs()) {
            
            if (enterpreneur.getName().equals(username) &&
                enterpreneur.getPassword().equals(password)) {
                return enterpreneur;
            }
        }
        
        return null; // Not found
    }
    
    /**
     *
     * @param username
     * @param password
     * @return logged donator or null
     */
        public Donator loginAsDonator(String username, String password) {
        
        Database database = Database.getInstance();
        
        for (Donator donator : database.getAllDonators()) {
            
            if (donator.getName().equals(username) &&
                donator.getPassword().equals(password)) {
                return donator;
            }
        }
        
        return null; // Not found
    }
}
