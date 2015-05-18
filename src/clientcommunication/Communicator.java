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
        
        
        switch (scanner.nextLine()) {
            case "title":
                String title = scanner.nextLine();
                System.out.println("By title: " + title);
                projects = new ArrayList<>(searchProjectAction.searchByTitle(title));
                break;
              
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
