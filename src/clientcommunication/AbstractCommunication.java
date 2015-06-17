/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public abstract class AbstractCommunication {
    
    protected final PrintStream toClient;
    protected final Scanner fromClient;
    
    public AbstractCommunication(PrintStream toClient, Scanner fromClient) {
        this.toClient = toClient;
        this.fromClient = fromClient;
    }
}
