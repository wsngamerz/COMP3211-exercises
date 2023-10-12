package Week1.TCP; // TCPServer

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author Karim Djemame
 * @version 1.1 [2021-09-24]
 */

class TCPServer {

    public static void main(String argv[]) throws Exception {
        String clientSentence, capitalizedSentence;

        ServerSocket welcomeSocket = new ServerSocket(6789);

        System.out.println("Starting Server ...");

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();

            System.out.println("Waiting for client...");

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();

            capitalizedSentence = clientSentence.toUpperCase() + '\n';

            outToClient.writeBytes(capitalizedSentence);
        }
    }
} 
