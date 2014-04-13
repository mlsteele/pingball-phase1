package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Creates thread to handle client's requests. These include ball out messages
 * and client connect/disconnect
 *
 * Thread safety argument:
 * A queue will receive client requests and process them in order.
 */

public class ClientHandler implements Runnable{

    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    private boolean running;

    /**
     * Make a new ClientHandler
     * @param socket the socket through which we communicate with the client
     * @throws IOException
     */
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.running = false;
    }

    /**
     * Handles the incoming client messages
     */
    @Override
    public void run() {
        running = true;
        // handle the client
        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                // deserialize line

                // handle it
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            running = false;
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Send a message to the client. Requires the thread to be running!
     * @param message the message to send to the client
     */
    public void send(NetworkMessage message) {
        if (running) {
            String strMessage = message.serialize();
            out.println(strMessage);
        }
    }



}
