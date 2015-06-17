/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.BusinessException;
import business.action.DonateToProjectAction;
import business.action.SearchProjectAction;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class DonateToProjectCommunication extends AbstractCommunication {
    
    private final DonateToProjectAction donateToProjectAction;
    private final SearchProjectAction searchProjectAction;
    
    public DonateToProjectCommunication(PrintStream toClient, Scanner fromClient) {
        super(toClient, fromClient);
        this.donateToProjectAction = new DonateToProjectAction();
        this.searchProjectAction = new SearchProjectAction();
    }
    
    /**
     * Communicates with the client to receive the data for a donation.
     * @param clientId 
     */
    public void donate(int clientId) {
        // Send the function permission success
        toClient.println("success");
        // Receive the project id
        int projectId = Integer.parseInt(fromClient.nextLine());
        // Receive the amount
        float amount = Float.parseFloat(fromClient.nextLine());
        
        try {
            // Make the donation
            donateToProjectAction.donateToProject(clientId, projectId, amount);
            // Send the donation confirmation
            toClient.println("success");
            // Send the client's current balance
            toClient.println(searchProjectAction.getCurrentDonator(clientId).getBalance());
        } catch (BusinessException ex) {
            // If something went wrong, send the exception
            toClient.println("exception");
            toClient.println(ex.getMessage());
        }
    }
}
