package business;

import clientcommunication.Communicator;
import java.io.IOException;



/**
 *
 * @author Felipe
 */
public class MicroEmprestimos {

    /**
     * Entry point for server
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        new Communicator(17592).execute();
    }
}
