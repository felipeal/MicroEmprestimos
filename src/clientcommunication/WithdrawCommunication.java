/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommunication;

import business.BusinessException;
import business.action.WithdrawAction;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class WithdrawCommunication extends AbstractCommunication {
    
    private final WithdrawAction withdrawAction;
    
    public WithdrawCommunication(PrintStream toClient, Scanner fromClient) {
        super(toClient, fromClient);
        this.withdrawAction = new WithdrawAction();
    }
    
    public void withdraw(int clientId) {
        // Receive project id
        int projectId = Integer.parseInt(fromClient.nextLine());
        
        try {
            withdrawAction.withdraw(projectId, clientId);
            // Send withdraw confirmation
            toClient.println("success");
        } catch (BusinessException e) {
            // Send exception
            toClient.println("exception");
            toClient.println(e.getMessage());
        }
    }
    
}
