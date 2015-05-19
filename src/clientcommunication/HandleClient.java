/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.BusinessException;
import business.action.DonateToProjectAction;
import business.action.SearchProjectAction;
import business.domain.Project;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor
 */
public class HandleClient implements Runnable {
    
    private int clientId;
    private PrintStream toClient;
    private Scanner fromClient;
    private final SearchProjectAction searchProjectAction;
    private final DonateToProjectAction donateToProjectAction;
    
    public HandleClient(PrintStream toClient, Scanner fromClient, SearchProjectAction searchProjectAction, DonateToProjectAction donateToProjectAction) {
        this.clientId = -1; // -1 means client is not logged in
        this.toClient = toClient;
        this.fromClient = fromClient;
        this.searchProjectAction = searchProjectAction;
        this.donateToProjectAction = donateToProjectAction;
    }
    
    public void run() {
        while (fromClient.hasNextLine()) {
            switch (fromClient.nextLine()) {
                case "login":
                    loginAction();
                    break;
                
                case "search":
                    System.out.println("teste");
                    searchAction();
                    break;
                    
                case "getProject":
                    getProject();
                    break;
                    
                case "donate":
                    donateAction();
                    break;

                default:
                    toClient.println("-1");
            }
        }
    }
    
    private void loginAction() {
        // HARDCODED LOGIN
        fromClient.nextLine(); // Discard username
        fromClient.nextLine(); // Discard password
        this.clientId = 0;
        toClient.println("success");
        toClient.println(searchProjectAction.getCurrentDonator(this.clientId).getBalance());
    }
    
    private boolean checkLogin () {
        if (this.clientId == -1) {
            toClient.println("exception");
            toClient.println("Client not logged in.");
            return false;
        } else
            return true;
    }
    
    private void searchAction() {
        
        if (checkLogin() == false) {
            fromClient.nextLine(); // Discard value
            return;
        }
        
        ArrayList<Project> projects;
        
        String mode = fromClient.nextLine();
        System.out.println(mode);
        
        // Gets the line containing the mode for the search
        switch (mode) {
            case "title":
                String title = fromClient.nextLine();
                System.out.println("By title: " + title);
                projects = new ArrayList<>(searchProjectAction.searchByTitle(title));
                break;
            
            case "enterpreneurName":
                String name = fromClient.nextLine();
                System.out.println("By enterpreneur name: " + name);
                projects = new ArrayList<>(searchProjectAction.searchByEnterpreneur(name));
                break;
                
            case "locale":
                String locale = fromClient.nextLine();
                System.out.println("By locale: " + locale);
                projects = new ArrayList<>(searchProjectAction.searchByLocation(locale));
                break;
                
            case "description":
                String description = fromClient.nextLine();
                System.out.println("By description: " + description);
                projects = new ArrayList<>(searchProjectAction.searchByDescription(description));
                break;
                
            case "remainingAmount":
                String remainingAmount = fromClient.nextLine();
                System.out.println("By remaining amount: " + remainingAmount);
                String[] remainingValues = remainingAmount.split("-");
                projects = new ArrayList<>(searchProjectAction.searchByRemainingAmount(Float.parseFloat(remainingValues[0]), Float.parseFloat(remainingValues[remainingValues.length-1])));
                break;
                
            case "achievedAmount":
                String achievedAmount = fromClient.nextLine();
                System.out.println("By achieved amount: " + achievedAmount);
                String[] achievedValues = achievedAmount.split("-");
                projects = new ArrayList<>(searchProjectAction.searchByAchievedAmount(Float.parseFloat(achievedValues[0]), Float.parseFloat(achievedValues[achievedValues.length-1])));
                break;
                
            case "expirationDate":
                String expirationDate = fromClient.nextLine();
                System.out.println("By expiration date: " + expirationDate);
                String[] expirationValues = expirationDate.split("-");
                projects = new ArrayList<>(searchProjectAction.searchByExpirationDate(expirationValues[0], expirationValues[expirationValues.length-1]));
                break;
                
            case "cancel":
                return;
                
            default:
                fromClient.nextLine(); // Discard value
                toClient.println("exception");
                toClient.println("No such function.");
                return;
        }
        
        for (Project project : projects) {
            System.out.println(project.getId());
            toClient.println(project.getId());
            System.out.println(project.getTitle());
            toClient.println(project.getTitle());
        }
        
        toClient.println("-1");
    } 

    private void getProject() {
        Project project = searchProjectAction.getSelectedProject(Integer.parseInt(fromClient.nextLine()));
        
        if (project == null) {
            toClient.println("exception");
            toClient.println("No project found.");
            return;
        } else {
            toClient.println("success");
        }
        
        toClient.println(project.getTitle());
        toClient.println(searchProjectAction.getSelectedProjectEnterpreneur(project.getId()).getName());
        toClient.println(project.getDescription());
        toClient.println(project.getMinDonationAmount());
        toClient.println(project.getDonatedAmount());
        toClient.println(project.getTargetValue());
        toClient.println(project.getLimitDate());
    }
    
    private void donateAction() {
        
        if (checkLogin() == false) {
            fromClient.nextLine(); // Discard projectId
            fromClient.nextLine(); // Discard amount
            return;
        }
        
        try {
            donateToProjectAction.donateToProject(this.clientId, Integer.parseInt(fromClient.nextLine()), Float.parseFloat(fromClient.nextLine()));
            toClient.println("success");
            toClient.println(searchProjectAction.getCurrentDonator(this.clientId).getBalance());
        } catch (BusinessException ex) {
            toClient.println("exception");
            toClient.println(ex.getMessage());
        }
    }
}
