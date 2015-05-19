/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.action.SearchProjectAction;
import business.domain.Project;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class Communicator {

    private final int port;
    private final SearchProjectAction searchProjectAction;
    
    public Communicator(int port, SearchProjectAction searchProjectAction) {
        this.port = port;
        this.searchProjectAction = searchProjectAction;
    }
    
    public void execute() throws IOException {
        ServerSocket server = new ServerSocket(this.port);
        System.out.println("Port "+this.port+" open!");
        
        while (true) {
            // Wait for client
            Socket client = server.accept();
            System.out.println("New connection with client " + client.getInetAddress().getHostAddress());

            PrintStream msgToClient = new PrintStream(client.getOutputStream());
            Scanner msgFromClient = new Scanner(client.getInputStream());

            while (msgFromClient.hasNextLine()) {
                switch (msgFromClient.nextLine()) {
                    case "search":
                        System.out.println("Client " + client.getInetAddress().getHostAddress() + " requested a search:");
                        searchAction(msgToClient, msgFromClient);
                        break;

                    default:
                        msgToClient.println("-1");
                }
            }
        }
    }
    
    private void searchAction(PrintStream client, Scanner scanner) {
        ArrayList<Project> projects;
        
        // Gets the line containing the mode for the search
        switch (scanner.nextLine()) {
            case "title":
                String title = scanner.nextLine();
                System.out.println("By title: " + title);
                projects = new ArrayList<>(searchProjectAction.searchByTitle(title));
                break;
            
            case "enterpreneurName":
                String name = scanner.nextLine();
                System.out.println("By enterpreneur name: " + name);
                projects = new ArrayList<>(searchProjectAction.searchByEnterpreneur(name));
                break;
                
            case "description":
                String description = scanner.nextLine();
                System.out.println("By description: " + description);
                projects = new ArrayList<>(searchProjectAction.searchByDescription(description));
                break;
                
            case "remainingAmount":
                String remainingAmount = scanner.nextLine();
                System.out.println("By remaining amount: " + remainingAmount);
                String[] remainingValues = remainingAmount.split("-");
                projects = new ArrayList<>(searchProjectAction.searchByRemainingAmount(Float.parseFloat(remainingValues[0]), Float.parseFloat(remainingValues.length > 0 ? remainingValues[remainingValues.length] : remainingValues[0])));
                break;
                
            case "achievedAmount":
                String achievedAmount = scanner.nextLine();
                System.out.println("By achieved amount: " + achievedAmount);
                String[] achievedValues = achievedAmount.split("-");
                projects = new ArrayList<>(searchProjectAction.searchByAchievedAmount(Float.parseFloat(achievedValues[0]), Float.parseFloat(achievedValues.length > 0 ? achievedValues[achievedValues.length] : achievedValues[0])));
                break;
                
//            case "expirationDate":
//                String expirationDate = scanner.nextLine();
//                System.out.println("By title: " + expirationDate);
//                projects = new ArrayList<>(searchProjectAction.searchByExpirationDate(expirationDate));
//                break;
                
            case "cancel":
                return;
                
            default:
                client.println("-1");
                return;
        }
        
        for (Project project : projects) {
            System.out.println(project.getId());
            client.println(project.getId());
            System.out.println(project.getTitle());
            client.println(project.getTitle());
        }
        
        client.println("-1");
    } 
    
}
