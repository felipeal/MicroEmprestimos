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
public class DonateToProjectCommunication {
    
    private final PrintStream toClient;
    private final Scanner fromClient;
    private final DonateToProjectAction donateToProjectAction;
    private final SearchProjectAction searchProjectAction;
    
    public DonateToProjectCommunication(DonateToProjectAction donateToProjectAction, SearchProjectAction searchProjectAction, PrintStream toClient, Scanner fromClient) {
        this.toClient = toClient;
        this.fromClient = fromClient;
        this.donateToProjectAction = donateToProjectAction;
        this.searchProjectAction = searchProjectAction;
    }
    
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
