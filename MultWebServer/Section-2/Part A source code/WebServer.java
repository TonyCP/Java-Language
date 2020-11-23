import java.net.*;

public final class WebServer {
    public static void main(String argv[]) throws Exception {
	int port = 6789;
	
	// Establish the listen socket.
	try(ServerSocket socket = new ServerSocket(port)){
	
		// Process HTTP service requests in an infinite loop.
		while (true) {
			// Listen for a TCP connection request.
			Socket connection = socket.accept();
			
			// Construct an object to process the HTTP request message.
			HttpRequest request = new HttpRequest(connection);
			
			// Create a new thread to process the request.
			Thread thread = new Thread(request);
			
			// Start the thread.
		thread.start();
		}
} catch (Exception e) {
	System.out.println(e);
}

    }
}


 
