/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.domain.Donator;
import business.domain.Entrepreneur;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class LoginAction extends AbstractAction  {
    
    // Returns logged entrepreneur on success, null on failure

    /**
     *
     * @param username
     * @param password
     * @return logged entrepreneur or null
     */
        public Entrepreneur loginAsEntrepreneur(String username, String password) {
        
        Database database = Database.getInstance();
        
        for (Entrepreneur entrepreneur : database.getAllEntrepreneurs()) {
            
            if (entrepreneur.getName().equals(username) &&
                entrepreneur.getPassword().equals(password)) {
                return entrepreneur;
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
