/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Controls the communication with the clients
 * @author Igor
 */
public class Communicator {

    private final int port;
    
    public Communicator(int port) {
        this.port = port;
    }
    
    /**
     * Open the port and wait for clients, when someone connects creates a new HandleClient for him and run it in a new thread.
     * @throws IOException 
     */
    public void execute() throws IOException {
        ServerSocket server = new ServerSocket(this.port);
        System.out.println("Port "+this.port+" open!");
        
        while (true) {
            // Wait for client
            Socket client = server.accept();
            System.out.println("New connection with client " + client.getInetAddress().getHostAddress());

            HandleClient hc = new HandleClient(new PrintStream(client.getOutputStream()), new Scanner(client.getInputStream()));
            new Thread(hc).start();
        }
    }
}
