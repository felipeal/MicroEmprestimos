/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.BusinessException;
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
 *
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
    
    @Override
    public void run() {
        while (fromClient.hasNextLine()) {
            switch (fromClient.nextLine()) {
                case "login":
                    // Try to login and get the id and role of the client
                    Pair<Integer,Role> loginData = new LoginCommunication(loginAction, toClient, fromClient).login();
                    // Set the id
                    clientId = loginData.getKey();
                    // Set the role
                    clientRole = loginData.getValue();
                    break;
                    
                case "register":
                    new RegisterUserCommunication(registerUserAction, toClient, fromClient).registerUser();
                    break;
                
                case "search":
                    // Can only be done if logged in
                    if (checkLogin() == true) {
                        new SearchProjectCommunication(searchProjectAction, toClient, fromClient).search();
                    }
                    break;
                    
                case "getProject":
                    new SearchProjectCommunication(searchProjectAction, toClient, fromClient).getProject();
                    break;
                    
                case "donate":
                    if (checkLogin(Role.Donator) == true) {
                        new DonateToProjectCommunication(donateToProjectAction, searchProjectAction, toClient, fromClient).donate(this.clientId);
                    }
                    break;

                default:
                    toClient.println("exception");
                    toClient.println("No such command.");
            }
        }
    }
    
    /**
     * 
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
            toClient.println("exception");
            toClient.println("Client not logged in.");
            return false;
        }
    }
    
}
