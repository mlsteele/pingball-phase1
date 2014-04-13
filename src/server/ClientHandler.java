package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Creates thread to handle client's requests. These include ball out messages
 * and client connect/disconnect.
 *
 * Thread safety argument:
 * * A queue will receive client requests and process them in order.
 * * Only client thread will read from the input stream,
 * * and only the server thread will write to the output stream
 * * (though both will do so through this class).
 * * Sockets are thread-safe for concurrent input and output.
 */

public class ClientHandler implements Runnable{

    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;


    /**
     * Make a new ClientHandler
     * @param socket the socket through which we communicate with the client
     * @throws IOException if we are unable to open the input or output stream with the client
     */
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);

    }

    /**
     * Handles the incoming client messages
     */
    @Override
    public void run() {
        // handle the client
        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                // deserialize line

                // handle it
            }
        } catch (IOException e) {
        } finally {
            this.kill();
        }
    }

    /**
     * Send a message to the client. Requires the thread to be running!
     * @param message the message to send to the client
     */
    public void send(NetworkMessage message) {
        String strMessage = message.serialize();
        out.println(strMessage);
    }

    /**
     * Stops the clientHandler thread and termitates the connection to the client
     */
    public void kill() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {}

    }



}
