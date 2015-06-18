/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.BusinessException;
import business.action.LoginAction;
import business.domain.Donator;
import business.domain.Entrepreneur;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.util.Pair;
import clientcommunication.HandleClient.Role;

/**
 *
 * @author Igor
 */
public class LoginCommunication extends AbstractCommunication {
    
    private final LoginAction loginAction;
    
    public LoginCommunication(PrintStream toClient, Scanner fromClient) {
        super(toClient, fromClient);
        this.loginAction = new LoginAction();
    }
    
    /**
     * Communicates with the client to receive login information, then execute te corresponding function.
     * @return a pair containing the client id and it's role
     */
    public Pair<Integer,Role> login() {
        // The return variable, it has the id and the role of the client
        Pair<Integer,Role> loginData;
        
        // Receive role
        String role = fromClient.nextLine();
        // Receive username
        String username = fromClient.nextLine();
        // Receive password
        String password = fromClient.nextLine();
        
        System.out.println("Login");
        System.out.println("Role: " + role);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        
        try {
            switch (role) {
//                case "admin":
//                    loginData = new Pair<>(loginAdmin(String username, String password), Role.Administrator);
//                    break;

                case "donator":
                    loginData = new Pair<>(loginDonator(username, password), Role.Donator);
                    break;

                case "entrepreneur":
                    loginData = new Pair<>(loginEntrepreneur(username, password), Role.Entrepreneur);
                    break;

                default:
                    loginData = new Pair<>(-1, Role.NotLogged);
                    toClient.println("exception");
                    toClient.println("No function available");
            }
            System.out.println("Done");
        } catch (BusinessException e) {
            System.out.println("Error: " + e.getMessage());
            // If failed to login, set as not logged and send the exception message
            loginData = new Pair<>(-1, Role.NotLogged);
            toClient.println("exception");
            toClient.println(e.getMessage());
        }
        
        return loginData;
    }
    
//    private int loginAdmin(String username, String password) throws BusinessException {
//        
//    }
    /**
     * Check login for a donator
     * @param username
     * @param password
     * @return the donator's id
     * @throws BusinessException 
     */
    private int loginDonator(String username, String password) throws BusinessException {
        Donator donator = loginAction.loginAsDonator(username, password);

        // If the combination donatorXpassword doesn't exist return exception
        if (donator == null) {
            throw new BusinessException("Incorrect login or password.");
        }
        // Else send the success message
        toClient.println("success");

        // Send the donator's name
        toClient.println(donator.getName());
        // Send the donator's balance
        toClient.println(donator.getBalance());
        
        // Return the id
        return donator.getId();
    } 
    /**
     * Check login for an entrepreneur
     * @param username
     * @param password
     * @return the entrepreneur's id
     * @throws BusinessException 
     */
    private int loginEntrepreneur(String username, String password) throws BusinessException {
        Entrepreneur entrepreneur = loginAction.loginAsEntrepreneur(username, password);

        // If the combination entrepreneurXpassword doesn't exist return exception
        if (entrepreneur == null) {
            throw new BusinessException("Incorrect login or password.");
        }
        // Else send the success message
        toClient.println("success");

        // Send the entrepreneur's name
        toClient.println(entrepreneur.getName());
        
        // Return the id
        return entrepreneur.getId();
    }
    
}
