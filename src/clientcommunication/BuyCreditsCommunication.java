/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.action.BuyCreditsAction;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class BuyCreditsCommunication extends AbstractCommunication {
    
    private final BuyCreditsAction buyCreditsAction;
    
    public BuyCreditsCommunication(Scanner fromClient) {
        super(null, fromClient);
        this.buyCreditsAction = new BuyCreditsAction();
    }
    
    public void buyCredits(int clientId) {
        // Receive amount
        float amount = Float.parseFloat(fromClient.nextLine());
        
        // Execute the purchase
        buyCreditsAction.buyCredits(clientId, amount);
    }
}
