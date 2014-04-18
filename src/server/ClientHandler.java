package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import common.netprotocol.*;
import common.*;
import common.netprotocol.NetworkMessage.DecodeException;

/**
 * Creates thread to handle client's requests. These include ball out messages
 * and client connect/disconnect. The Server also calls ClientHandler methods
 * (from the Server thread), but only to send messages to the client.
 *
 * Thread safety argument:
 * * The many ClientHandler threads will all add NetworkMessages to the Server's BlockingQueue (thread safe datatype)
 * * Only client thread will read from the input stream,
 * * and only the server thread will write to the output stream
 * * (though both will do so through this class).
 * * Sockets are thread-safe for concurrent input and output.
 *
 * Rep invariant:
 * * TODO ??
 */

public class ClientHandler implements Runnable{

    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    private final BlockingQueue<AuthoredMessage> messageQueue;
    private final BlockingQueue<ClientHandler> deadClientsQueue;
    /* TODO maybe this should be volatile: */
    private String name;


    /**
     * Make a new ClientHandler
     * @param socket the socket through which we communicate with the client
     * @param queue the Server's queue of messages, on which to put incoming messages
     * @throws IOException if we are unable to open the input or output stream with the client
     */
    public ClientHandler(Socket socket,
            BlockingQueue<AuthoredMessage> queue,
            BlockingQueue<ClientHandler> deadClientsQueue) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.messageQueue = queue;
        this.deadClientsQueue = deadClientsQueue;
    }

    /**
     * Handles the incoming client messages.
     * Listens for input from the client, and sends it to the server.
     * Ignores bad input messages, but prints an error to System.err if there is an IOException
     */
    @Override
    public void run() {
        // handle the client
        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                NetworkMessage message = NetworkMessage.deserialize(line);
                if (message instanceof ClientConnectMessage) {
                    this.name = ((ClientConnectMessage) message).getBoardName();
                }
                messageQueue.add(new AuthoredMessage(message, this));

            }
        } catch (IOException e) {
            if (Constants.DEBUG) {
                System.err.println(e.getMessage());
            }
        } catch (DecodeException e) {
            // ignore bad input
            if (Constants.DEBUG) {
                System.err.println(e.getMessage());
            }
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
        System.out.println("Sending message: " + strMessage);
        out.println(strMessage);
    }

    /**
     * Terminates the connection to the client.
     * This also causes the run() method to finish, because in.close() will make run() fail.
     */
    public void kill() {
        deadClientsQueue.add(this);
        if (!socket.isClosed()) {
            try {
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * getter for board name
     * @return the name of the client board
     */
    public String getName() {
        return this.name;
    }
}
