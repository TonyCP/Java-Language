/*******************************************************************************
 * Chesten VanPelt
 * Tonae Patterson
 * Spencer Mueller
 * CIS 457 Project 1
 * Dr. El-Said
 *
 * The FTPMulti program is ran in order to run multiple clients using only
 * one server. It creates a server socket with the port 1200. It then attempts
 * to listen to the server and while successful will create a new server thread
 * so that multiple clients can connect at once.
 *****************************************************************************/

import java.io.IOException;
import java.net.ServerSocket;

/*******************************************************
 * FTPMulti class creates new server thread.
 ******************************************************/
public class FTPMulti {
    public static void main(String[] args) throws IOException {
        // Server socket //
        ServerSocket serverSocket = null;
        // listening boolean //
        boolean listening = true;
        // Instance of server //
        FTPServer w;

        // Try to listen on port 1200 //
        try {
            serverSocket = new ServerSocket(1200);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 1200.");
            System.exit(-1);
        }

        // Instantiate server and thread while listening //
        while (listening) {
            w = new FTPServer(serverSocket.accept());
            Thread t = new Thread(w);
            t.start();
        }
    }

}

