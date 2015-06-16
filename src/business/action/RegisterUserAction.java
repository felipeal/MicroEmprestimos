/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.BusinessException;
import business.domain.Donator;
import business.domain.Entrepreneur;
import java.util.ArrayList;
import java.util.List;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class RegisterUserAction {
    
    public Entrepreneur registerEntrepreneur(String login, String password, String name, String location) throws BusinessException {
        
        Database database = Database.getInstance();
        List<Entrepreneur> entrepreneurs = new ArrayList<>(database.getAllEntrepreneurs());
        for (Entrepreneur entrepreneur : entrepreneurs) {
            if (entrepreneur.getLogin().equals("login"))
                throw new BusinessException("Username already in use.");
        }
        
        Entrepreneur entrepreneur = new Entrepreneur(login, password, name, location);
        database.save(entrepreneur);
        
        return entrepreneur;
    }
    
    public Donator registerDonator(String login, String password, String name) throws BusinessException {
        
        Database database = Database.getInstance();
        List<Donator> donators = new ArrayList<>(database.getAllDonators());
        for (Donator donator : donators) {
            if (donator.getLogin().equals("login"))
                throw new BusinessException("Username already in use.");
        }
        
        Donator donator = new Donator(login, password, name, 0f);
        database.save(donator);
        
        return donator;
    }
}
