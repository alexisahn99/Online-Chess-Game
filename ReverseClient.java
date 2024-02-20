import java.io.*;
import java.net.*;

/**
 * This program demonstrates a simple TCP/IP socket client that sends messages
 * to a server and receives the messages reversed from the server.
 */
public class ReverseClient {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ReverseClient <server IP> <port number>");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            Console console = System.console();
            String text;

            do {
                text = console.readLine("Enter text: ");
                writer.println(text);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String response = reader.readLine(); // Read the response from the server
                System.out.println("Server response: " + response);

            } while (!text.equals("bye"));

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
