/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.action.DonateToProjectAction;
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
    private final DonateToProjectAction donateToProjectAction;
    
    public Communicator(int port, SearchProjectAction searchProjectAction, DonateToProjectAction donateToProjectAction) {
        this.port = port;
        this.searchProjectAction = searchProjectAction;
        this.donateToProjectAction = donateToProjectAction;
    }
    
    public void execute() throws IOException {
        ServerSocket server = new ServerSocket(this.port);
        System.out.println("Port "+this.port+" open!");
        
        while (true) {
            // Wait for client
            Socket client = server.accept();
            System.out.println("New connection with client " + client.getInetAddress().getHostAddress());

            HandleClient hc = new HandleClient(new PrintStream(client.getOutputStream()), new Scanner(client.getInputStream()), this.searchProjectAction, this.donateToProjectAction);
            new Thread(hc).start();
        }
    }
}
