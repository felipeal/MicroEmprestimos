/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.action;

import business.domain.Donator;
import persistence.Database;

/**
 *
 * @author Felipe
 */
public class BuyCreditsAction {
    
    public float buyCredits(int donatorId, float amount) {
        
        Database database = Database.getInstance();
        Donator donator = database.getDonator(donatorId);
        
        float newBalance = donator.getBalance() + amount;
        donator.setBalance(newBalance);
        
        return newBalance;
    }
    
}
