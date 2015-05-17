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
        System.out.println("Porta "+this.port+" aberta!");
        
        // Wait for client
        Socket client = server.accept();
        System.out.println("Nova conexão com o cliente " + client.getInetAddress().getHostAddress());
        
        PrintStream clientOutput = new PrintStream(client.getOutputStream());
        Scanner scanner = new Scanner(client.getInputStream());
        
        switch (scanner.nextLine()) {
            case "search":
                searchAction(clientOutput, scanner);
                break;
        }
    }
    
    private void searchAction(PrintStream client, Scanner scanner) {
        ArrayList<Project> projects = new ArrayList<>(searchProjectAction.searchByTitle(scanner.nextLine()));
        for (Project project : projects) {
            client.println();
        }
    } 
    
}
