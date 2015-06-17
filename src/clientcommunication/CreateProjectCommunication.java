/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.action.CreateProjectAction;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class CreateProjectCommunication extends AbstractCommunication {
    
    private final CreateProjectAction createProjectAction;
    
    public CreateProjectCommunication(PrintStream toClient, Scanner fromClient) {
        super(toClient, fromClient);
        this.createProjectAction = new CreateProjectAction();
    }
    
    public void createProject(int clientId) {
        // Receive title
        String title = fromClient.nextLine();
        // Receive description
        String description = fromClient.nextLine();
        // Receive minimum donation value
        float minDonationValue = Float.parseFloat(fromClient.nextLine());
        // Receive target value
        float target = Float.parseFloat(fromClient.nextLine());
        // Receive limit date
        String limitDate = fromClient.nextLine();
        
        createProjectAction.createProject(clientId, title, description, minDonationValue, target, limitDate);
    }
    
}
