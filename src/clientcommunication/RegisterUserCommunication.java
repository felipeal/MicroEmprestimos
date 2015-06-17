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
    
    public void registerUser() {
        // Receive the role
        String role = fromClient.nextLine();
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
    
    private void registerDonator() {
        // Receive login
        String login = fromClient.nextLine();
        // Receive password
        String password = fromClient.nextLine();
        // Receive name
        String name = fromClient.nextLine();
        
        try {
            // Try to register the user
            registerUserAction.registerDonator(login, password, name);
            // Send the register confirmation
            toClient.println("success");
        } catch (BusinessException ex) {
            // If something went wrong, send an exception
            toClient.println("exception");
            toClient.println(ex.getMessage());
        }
    }
    
    private void registerEntrepreneur() {
        // Receive login
        String login = fromClient.nextLine();
        // Receive password
        String password = fromClient.nextLine();
        // Receive name
        String name = fromClient.nextLine();
        // Receive location
        String location = fromClient.nextLine();
        
        try {
            // Try to register the user
            registerUserAction.registerEntrepreneur(login, password, name, location);
            // Send the register confirmation
            toClient.println("success");
        } catch (BusinessException ex) {
            // If something went wrong, send an exception
            toClient.println("exception");
            toClient.println(ex.getMessage());
        }
    }
}
