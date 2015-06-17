/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.action.SearchProjectAction;
import business.domain.Project;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class SearchProjectCommunication {
    
    private final PrintStream toClient;
    private final Scanner fromClient;
    private final SearchProjectAction searchProjectAction;
    
    public SearchProjectCommunication(SearchProjectAction searchProjectAction, PrintStream toClient, Scanner fromClient) {
        this.toClient = toClient;
        this.fromClient = fromClient;
        this.searchProjectAction = searchProjectAction;
    }
    
    public void search() {
        
        ArrayList<Project> projects;
        
        // Receive the search mode
        String mode = fromClient.nextLine();
        System.out.println(mode);
        
        // Gets the line containing the mode for the search
        switch (mode) {
            case "title":
                toClient.println("success");
                String title = fromClient.nextLine();
                System.out.println("By title: " + title);
                projects = new ArrayList<>(searchProjectAction.searchByTitle(title));
                break;
            
            case "entrepreneurName":
                toClient.println("success");
                String name = fromClient.nextLine();
                System.out.println("By entrepreneur name: " + name);
                projects = new ArrayList<>(searchProjectAction.searchByEntrepreneur(name));
                break;
                
            case "locale":
                toClient.println("success");
                String locale = fromClient.nextLine();
                System.out.println("By locale: " + locale);
                projects = new ArrayList<>(searchProjectAction.searchByLocation(locale));
                break;
                
            case "description":
                toClient.println("success");
                String description = fromClient.nextLine();
                System.out.println("By description: " + description);
                projects = new ArrayList<>(searchProjectAction.searchByDescription(description));
                break;
                
            case "remainingAmount":
                toClient.println("success");
                String remainingAmount = fromClient.nextLine();
                System.out.println("By remaining amount: " + remainingAmount);
                // Divide the values if it contains -
                String[] remainingValues = remainingAmount.split("-");
                // Search using the first and the last values converted to float
                projects = new ArrayList<>(searchProjectAction.searchByRemainingAmount(Float.parseFloat(remainingValues[0]), Float.parseFloat(remainingValues[remainingValues.length-1])));
                break;
                
            case "achievedAmount":
                toClient.println("success");
                String achievedAmount = fromClient.nextLine();
                System.out.println("By achieved amount: " + achievedAmount);
                // Divide the values if it contains -
                String[] achievedValues = achievedAmount.split("-");
                // Search using the first and the last values converted to float
                projects = new ArrayList<>(searchProjectAction.searchByAchievedAmount(Float.parseFloat(achievedValues[0]), Float.parseFloat(achievedValues[achievedValues.length-1])));
                break;
                
            case "expirationDate":
                toClient.println("success");
                String expirationDate = fromClient.nextLine();
                System.out.println("By expiration date: " + expirationDate);
                // Divide the values if it contains -
                String[] expirationValues = expirationDate.split("-");
                // Search using the first and the last values
                projects = new ArrayList<>(searchProjectAction.searchByExpirationDate(expirationValues[0], expirationValues[expirationValues.length-1]));
                break;
                
            case "cancel":
                return;
                
            default:
                toClient.println("exception");
                toClient.println("No such function.");
                return;
        }
        
        // For each project, send the id and the title
        for (Project project : projects) {
            System.out.println(project.getId());
            toClient.println(project.getId());
            System.out.println(project.getTitle());
            toClient.println(project.getTitle());
        }
        
        // Send the finish mark value
        toClient.println("end");
    }
    
    public void getOwnedProjects(int clientId) {
        // Get all projects from entrepreneur
        List<Project> projects = new ArrayList<>(searchProjectAction.searchByEntrepreneurId(clientId));
        
        for (Project project : projects) {
            // Send project id
            toClient.println(project.getId());
            // Send project title
            toClient.println(project.getTitle());
        }
        
        // Send the finish mark value
        toClient.println("end");
    }
    
    public void getProject() {
        // Search for the project with the id received
        Project project = searchProjectAction.getSelectedProject(Integer.parseInt(fromClient.nextLine()));
        
        // If no project found, send an exception
        if (project == null) {
            toClient.println("exception");
            toClient.println("No project found.");
            return;
        } else {
            toClient.println("success");
        }
        
        // Send project title
        toClient.println(project.getTitle());
        // Send entrepreneur's name
        toClient.println(searchProjectAction.getSelectedProjectEntrepreneur(project.getId()).getName());
        // Send project description
        toClient.println(project.getDescription());
        // Send minimum donation amount
        toClient.println(project.getMinDonationAmount());
        // Send donated amount
        toClient.println(project.getDonatedAmount());
        // Send target value
        toClient.println(project.getTargetValue());
        // Send limit date
        toClient.println(project.getLimitDate());
    }
}
