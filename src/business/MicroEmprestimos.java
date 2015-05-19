package business;

import business.action.DonateToProjectAction;
import business.action.SearchProjectAction;
import clientcommunication.Communicator;
import java.io.IOException;
import persistence.Database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Felipe
 */
public class MicroEmprestimos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Database database = new Database();
        SearchProjectAction searchProjectAction = new SearchProjectAction(database);
        DonateToProjectAction donateToProjectAction = new DonateToProjectAction(database);
        new Communicator(17592, searchProjectAction).execute();
    }
}
