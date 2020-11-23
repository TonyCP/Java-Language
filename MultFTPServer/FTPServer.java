/**********************************************************************************
 * Chesten VanPelt
 * Tonae Patterson
 * Spencer Mueller
 * CIS 457 Project 1
 * Dr. El-Said
 *
 * The FTPServer program will create a connection back to the client when ran,
 * completing the 2 way TCP communication. It then processes each of the client's
 * requests. When connected, if given the command:
 * "list:", the server will return to the client each txt file in its directory.
 * "stor: txt file", the server will store the file the client provides in its
 *  directory.
 * "retr: txt file", the client will retrieve file from server directory.
 * "quit", the server will end all connections & close all streams with the client.
 *********************************************************************************/

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

/*********************************************************************************
 * FTPServer class that creates the server and handles the client commands.
 ********************************************************************************/
public class FTPServer extends Thread {
    // Socket connection //
    private Socket connectionSocket;
    // port variable //
    int port;
    // num processes //
    int count = 1;

    /**
     * Class Constructor
     **/
    public FTPServer(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    /**
     * Run Server
     **/
    public void run() {
        if (count == 1)
            System.out.println("User connected" + connectionSocket.getInetAddress());
        count++;

        // Process client requests //
        try {
            processRequest();

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    /**
     * Client Requests Handling
     **/
    private void processRequest() throws Exception {
        // client request //
        String fromClient;
        // client command //
        String clientCommand;
        // port number //
        String frstln;

        while (true) {
            if (count == 1)
                System.out.println("User connected" + connectionSocket.getInetAddress());
            count++;

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            fromClient = inFromClient.readLine();
            StringTokenizer tokens = new StringTokenizer(fromClient);
            frstln = tokens.nextToken();
            port = Integer.parseInt(frstln);
            clientCommand = tokens.nextToken();

            // List command handling //
            if (clientCommand.equals("list:")) {
                String curDir = System.getProperty("user.dir");

                // Create data socket //
                Socket dataSocket = new Socket(connectionSocket.getInetAddress(), port);
                DataOutputStream dataOutToClient = new DataOutputStream(dataSocket.getOutputStream());
                File dir = new File(curDir);

                String[] children = dir.list();
                if (children == null) {
                    // Either dir does not exist or is not a directory //
                } else {
                    for (int i = 0; i < children.length; i++) {

                        // Get filename of file or directory //
                        String filename = children[i];
                        if (filename.endsWith(".txt")) {
                            dataOutToClient.writeUTF(children[i]);
                        }
                        if (i - 1 == children.length - 2) {
                            dataOutToClient.writeUTF("eof");
                        }//if(i-1)
                    }//for

                    dataOutToClient.close();
                    dataSocket.close();
                }//else
            }//if list:

            // Retr command handling //
            if (clientCommand.equals("retr:")) {
                fromClient = tokens.nextToken();

                // Create data socket //
                String curDir = System.getProperty("user.dir");
                Socket dataSocket = new Socket(connectionSocket.getInetAddress(), port);
                DataOutputStream dataOutToClient = new DataOutputStream(dataSocket.getOutputStream());
                File dir = new File(curDir);
                String[] children = dir.list();

                for (int i = 0; i < children.length; i++) {

                    // Get filename of file or directory //
                    String filename = children[i];
                    if (filename.endsWith(".txt")) {

                        //looking for the file the user wants //
                        if (filename.equals(fromClient)) {

                            try {
                                // Create stream to read in from file //
                                Scanner read = new Scanner(new FileInputStream(filename));
                                String line;

                                // Read from file and write out to client //
                                while (read.hasNextLine()) {
                                    line = read.nextLine();

                                    dataOutToClient.writeUTF(line + "\n");
                                }

                                System.out.println("File " + filename + " sent");
                                dataOutToClient.writeUTF("eof");

                                // Close all streams
                                read.close();
                                dataOutToClient.close();
                                dataSocket.close();
                                break;
                            } catch (FileNotFoundException e) {
                                System.out.println("File not found: " + filename);
                            }

                        }//end of files being the same
                    }//end of file end with .txt

                    //System.out.println(filename);
                    if (i - 1 == children.length - 2) {
                        System.out.println("File not found");

                        System.out.println("eof");
                    }//if(i-1)
                }//end for loop
            }


            // Stor command handling  //
            if (clientCommand.equals("stor:")) {
                fromClient = tokens.nextToken();

                // Create data socket //
                String curDir = System.getProperty("user.dir");
                Socket dataSocket = new Socket(connectionSocket.getInetAddress(), port);
                DataInputStream dataInFromClient = new DataInputStream(dataSocket.getInputStream());

                boolean flag = true;
                BufferedWriter dataOut = new BufferedWriter(new FileWriter(fromClient));

                System.out.println("Downloading File...\n");
                while (flag) {
                    String input = dataInFromClient.readUTF();
                    if (input.equals("eof")) {
                        flag = false;
                    } else {
                        dataOut.write(input);
                    }
                }
                System.out.println("File Downloaded.\n");

                // Close streams & sockets
                dataOut.close();
                dataInFromClient.close();
                dataSocket.close();
            }

            // Quit command handling //
            if (clientCommand.equals("quit")) {
                inFromClient.close();
                outToClient.close();
                connectionSocket.close();
                System.out.println("/127.0.0.1 has disconnected");
                return;
            }
        }//main
    }
}


