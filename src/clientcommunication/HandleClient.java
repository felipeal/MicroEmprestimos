/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.action.BuyCreditsAction;
import business.action.CreateProjectAction;
import business.action.DonateToProjectAction;
import business.action.LoginAction;
import business.action.RegisterUserAction;
import business.action.SearchProjectAction;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.util.Pair;

/**
 * Handle each client to control it's session.
 * @author Igor
 */
public class HandleClient implements Runnable {
    
    private int clientId;
    private Role clientRole;
    private final PrintStream toClient;
    private final Scanner fromClient;
    
    // Actions
    private final BuyCreditsAction buyCreditsAction;
    private final CreateProjectAction createProjectAction;
    private final DonateToProjectAction donateToProjectAction;
    private final LoginAction loginAction;
    private final RegisterUserAction registerUserAction;
    private final SearchProjectAction searchProjectAction;
    
    public enum Role {
        Administrator, Donator, Entrepreneur, NotLogged;
    }
    
    public HandleClient(PrintStream toClient, Scanner fromClient) {
        this.clientRole = Role.NotLogged;
        this.toClient = toClient;
        this.fromClient = fromClient;
        
        // Inicialize actions
        this.buyCreditsAction = new BuyCreditsAction();
        this.createProjectAction = new CreateProjectAction();
        this.donateToProjectAction = new DonateToProjectAction();
        this.loginAction = new LoginAction();
        this.registerUserAction = new RegisterUserAction();
        this.searchProjectAction = new SearchProjectAction();
    }
    
    /**
     * Get the clients request and executes the corresponding function.
     */
    @Override
    public void run() {
        while (fromClient.hasNextLine()) {
            switch (fromClient.nextLine()) {
                case "buyCredits":
                    if (checkLogin(Role.Donator) == true) {
                        new BuyCreditsCommunication(fromClient).buyCredits(this.clientId);
                    }
                    break;
                    
                case "createProject":
                    if (checkLogin(Role.Entrepreneur) == true) {
                        new CreateProjectCommunication(toClient, fromClient).createProject(this.clientId);
                    }
                    break;
                
                case "donate":
                    if (checkLogin(Role.Donator) == true) {
                        new DonateToProjectCommunication(toClient, fromClient).donate(this.clientId);
                    }
                    break;
                
                case "getProject":
                    new SearchProjectCommunication(toClient, fromClient).getProject();
                    break;
                    
                case "login":
                    System.out.println("Client is trying to login.");
                    // Try to login and get the id and role of the client
                    Pair<Integer,Role> loginData = new LoginCommunication(toClient, fromClient).login();
                    // Set the id
                    clientId = loginData.getKey();
                    // Set the role
                    clientRole = loginData.getValue();
                    break;
                    
                case "register":
                    System.out.println("Client is trying to register.");
                    new RegisterUserCommunication(toClient, fromClient).registerUser();
                    break;
                
                case "search":
                    // Can only be done if logged in
                    if (checkLogin() == true) {
                        System.out.println("Client is searching projects.");
                        new SearchProjectCommunication(toClient, fromClient).search(this.clientId, this.clientRole);
                    }
                    break;
                    
                case "withdraw":
                    if (checkLogin(Role.Entrepreneur) == true) {
                        new WithdrawCommunication(toClient, fromClient).withdraw(this.clientId);
                    }
                    break;

                default:
                    toClient.println("exception");
                    toClient.println("No such command.");
            }
        }
    }
    
    /**
     * Check if the client is logged in one of the given roles.
     * @param roles
     * @return true if logged in one of the given roles
     */
    private boolean checkLogin(Role... roles) {
        boolean nullArgs = true;
        boolean permissionGranted = false;
        
        for (Role role : roles) {
            nullArgs = false;
            if (clientRole == role)
                permissionGranted = true;
        }
        if (nullArgs == true) {
            if (clientRole != Role.NotLogged)
                permissionGranted = true;
        }
        
        if (permissionGranted) {
            toClient.println("success");
            return true;
        } else {
            System.out.println("Error: client not logged in.");
            toClient.println("exception");
            toClient.println("Client not logged in.");
            return false;
        }
    }
    
}
