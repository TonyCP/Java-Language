/**********************************************************************************
 * Chesten VanPelt
 * Tonae Patterson
 * Spencer Mueller
 * CIS 457 Project 1
 * Dr. El-Said
 *
 * The FTPClient programs will run a main function that prompts to the user
 * the commands and when the user uses the connect command, the client will
 * create a new ControlSocket that sets up a 2 way TCP communication so that
 * the client can tell the server what to do with text files. When connected,
 * if given the command:
 * "list:", the server will return to the client each txt file in its directory.
 * "stor: txt file", the server will store the file the client provides in its
 *  directory.
 * "retr: txt file", the client will retrieve file from server directory.
 * "quit", the server will end all connections & close all streams with the client.
 *********************************************************************************/

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

/*******************************************************************
 * FTPClient class creates the client connection to the server.
 ******************************************************************/
class FTPClient {

    public static void main(String argv[]) throws Exception {
        // Request sentence //
        String sentence;
        // Modified request sentence //
        String modifiedSentence;
        // Open socket boolean //
        boolean isOpen = true;
        // End of file boolean //
        boolean notEnd = true;
        // Ports //
        int port1 = 1221;
        int port = 1200;
        // Client access boolean //
        boolean clientgo = true;

        System.out.println("Welcome to the simple FTP App   \n     Commands  \nconnect servername port# connects to a specified server \nlist: lists files on server \nretr: fileName.txt downloads that text file to your current directory \nstor: fileName.txt Stores the file on the server \nquit terminates the connection to the server\n");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        sentence = inFromUser.readLine();
        StringTokenizer tokens = new StringTokenizer(sentence);

        // Connect command handling //
        if (sentence.startsWith("connect")) {
            String serverName = tokens.nextToken(); // pass the connect command
            serverName = tokens.nextToken();
            port1 = Integer.parseInt(tokens.nextToken());
            System.out.println("You are connected to " + serverName);
            Socket ControlSocket = new Socket(serverName, port1);
            while (isOpen && clientgo) {
                port = port + 2;

                sentence = inFromUser.readLine();
                DataOutputStream outToServer = new DataOutputStream(ControlSocket.getOutputStream());
                DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(ControlSocket.getInputStream()));

                // List command handling //
                if (sentence.equals("list:")) {

                    System.out.println(port);
                    ServerSocket welcomeData = new ServerSocket(port);

                    System.out.println("\n \nThe files on this server are:");
                    // Send request via control connection //
                    outToServer.writeBytes(port + " " + sentence + " " + '\n');

                    // Create client side socket for data connection //
                    Socket dataSocket = welcomeData.accept();
                    DataInputStream inData = new DataInputStream(new BufferedInputStream(dataSocket.getInputStream()));
                    while (notEnd) {
                        modifiedSentence = inData.readUTF();
                        if (modifiedSentence.equals("eof"))
                            break;
                        System.out.println("	" + modifiedSentence);
                    }

                    // Close streams & sockets //
                    inData.close();
                    welcomeData.close();
                    dataSocket.close();
                    System.out.println("\nWhat would you like to do next: \nretr: file.txt ||  stor: file.txt  || quit\n");

                    // Retr command handling //
                } else if (sentence.startsWith("retr: ")) {

                    // Get file name //
                    StringTokenizer token = new StringTokenizer(sentence);
                    String fName = token.nextToken();
                    fName = token.nextToken();

                    ServerSocket welcomeData = new ServerSocket(port);

                    // Send request via control connection //
                    outToServer.writeBytes(port + " " + sentence + '\n');

                    // Create client side socket for data connection //
                    Socket dataSocket = welcomeData.accept();
                    DataInputStream inData = new DataInputStream(new BufferedInputStream(dataSocket.getInputStream()));

                    boolean flag = true;
                    //this is a temp file, because both files are run from the same directory
                    BufferedWriter dataOut = new BufferedWriter(new FileWriter(fName));

                    System.out.println("Downloading File...\n");
                    while (flag) {
                        String input = inData.readUTF();
                        if (input.equals("eof")) {
                            flag = false;
                        } else {
                            dataOut.write(input);
                        }
                    }
                    System.out.println("File Downloaded.\n");

                    // Close streams & sockets //
                    dataOut.close();
                    inData.close();
                    welcomeData.close();
                    dataSocket.close();
                    System.out.println("\nWhat would you like to do next: \nretr: file.txt ||  stor: file.txt  || quit\n");

                    // Stor command handling //
                } else if (sentence.startsWith("stor: ")) {

                    // Get file name //
                    StringTokenizer token = new StringTokenizer(sentence);
                    String fName = token.nextToken();
                    fName = token.nextToken();

                    ServerSocket welcomeData = new ServerSocket(port);

                    // Send command via control connection //
                    outToServer.writeBytes(port + " " + sentence + " " + '\n');

                    // Create client side socket for data connection //
                    Socket dataSocket = welcomeData.accept();
                    DataOutputStream dataOutToServer = new DataOutputStream(dataSocket.getOutputStream());
                    String curDir = System.getProperty("user.dir");
                    File dir = new File(curDir);
                    String[] children = dir.list();

                    for (int i = 0; i < children.length; i++) {

                        // Get filename of file or directory //
                        String filename = children[i];
                        if (filename.endsWith(".txt")) {

                            //looking for the file the user wants //
                            if (filename.equals(fName)) {

                                try {
                                    // Create stream to read in from file //
                                    Scanner read = new Scanner(new FileInputStream(filename));
                                    String line;

                                    // Read from file and write out to client //
                                    while (read.hasNextLine()) {
                                        line = read.nextLine();

                                        dataOutToServer.writeUTF(line + "\n");
                                    }

                                    System.out.println("File " + filename + " sent");
                                    dataOutToServer.writeUTF("eof");

                                    // Close all streams & sockets //
                                    read.close();
                                    dataOutToServer.close();
                                    dataSocket.close();
                                    welcomeData.close();
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

                    System.out.println("\nWhat would you like to do next: \nretr: file.txt ||  stor: file.txt  || quit\n");

                } else {

                    // Quit command handling //
                    if (sentence.equals("quit")) {
                        clientgo = false;
                        outToServer.writeBytes(port + " " + sentence + '\n');
                        System.out.println("Closing connection...");

                        // Close streams & control sockets //
                        inFromUser.close();
                        outToServer.close();
                        inFromServer.close();
                        ControlSocket.close();

                        System.out.println("Terminated.\n");
                        // Exit program //
                        System.exit(0);
                    }
                    System.out.print("No server exists with that name or server not listening on that port try again\n");
                }
            }
        }
    }
}