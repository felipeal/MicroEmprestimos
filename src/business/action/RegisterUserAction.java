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
public class RegisterUserAction {
    
    Entrepreneur registerEntrepreneur(String login, String password, String name, String location) {
        
        Database database = Database.getInstance();
        Entrepreneur entrepreneur = new Entrepreneur(login, password, name, location);
        database.save(entrepreneur);
        
        return entrepreneur;
    }
    
    Donator registerDonator(String login, String password, String name) {
        
        Database database = Database.getInstance();
        Donator donator = new Donator(login, password, name, 0f);
        database.save(donator);
        
        return donator;
    }
}
