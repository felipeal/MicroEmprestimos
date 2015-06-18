/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.BusinessException;
import business.action.RegisterUserAction;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class RegisterUserCommunication extends AbstractCommunication {
    
    private final RegisterUserAction registerUserAction;
    
    public RegisterUserCommunication(PrintStream toClient, Scanner fromClient) {
        super(toClient, fromClient);
        this.registerUserAction = new RegisterUserAction();
    }
    
    /**
     * Communicates with the client to receive the data to create a new user.
     */
    public void registerUser() {
        // Receive the role
        String role = fromClient.nextLine();
        System.out.println("Role: " + role);
        switch (role) {
//            case "admin":
//                break;
                
            case "donator":
                // Send function confirmation
                toClient.println("success");
                registerDonator();
                break;
                
            case "entrepreneur":
                // Send function confirmation
                toClient.println("success");
                registerEntrepreneur();
                break;
            
            default:
                // Send exception
                toClient.println("exception");
                toClient.println("No such role.");
        }
    }
    
    /**
     * Register a donator
     */
    private void registerDonator() {
        // Receive login
        String login = fromClient.nextLine();
        // Receive password
        String password = fromClient.nextLine();
        // Receive name
        String name = fromClient.nextLine();
        
        System.out.println("Registration");
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);
        System.out.println("Name: " + name);
        
        try {
            // Try to register the user
            registerUserAction.registerDonator(login, password, name);
            // Send the register confirmation
            System.out.println("Done");
            toClient.println("success");
        } catch (BusinessException ex) {
            System.out.println("Error: " + ex.getMessage());
            // If something went wrong, send an exception
            toClient.println("exception");
            toClient.println(ex.getMessage());
        }
    }
    
    /**
     * Register an entrepreneur
     */
    private void registerEntrepreneur() {
        // Receive login
        String login = fromClient.nextLine();
        // Receive password
        String password = fromClient.nextLine();
        // Receive name
        String name = fromClient.nextLine();
        // Receive location
        String location = fromClient.nextLine();
        
        System.out.println("Registration");
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        
        try {
            // Try to register the user
            registerUserAction.registerEntrepreneur(login, password, name, location);
            // Send the register confirmation
            toClient.println("success");
            System.out.println("Done");
        } catch (BusinessException ex) {
            System.out.println("Error: " + ex.getMessage());
            // If something went wrong, send an exception
            toClient.println("exception");
            toClient.println(ex.getMessage());
        }
    }
}
