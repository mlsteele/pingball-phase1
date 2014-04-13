package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Makes a PingballServer that:
 *  * listens for connections on port
 *  * creates instances of ClientHandler
 *  * parses command line arguments
 *  * Handles interactions between ClientHandler threads
 *  * Listens for System.in commands, parses, and handles commands
 *
 */

public class PingballServer {

    private final ServerSocket serverSocket;
    private static final int DEFAULT_PORT = 10987;
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 65535;

    public PingballServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    /**
     * Run the server, listening for client connections and handling them.
     * Never returns unless an exception is thrown.
     *
     * @throws IOException if the main server socket is broken
     */
    public void serve() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();

            Thread thread = new Thread(new ClientHandler(socket));
            thread.start();

        }
    }

    /**
     * Start a PingballServer using the given arguments.
     *
     * Usage: PingballServer [--port PORT]
     *
     * PORT is an optional integer in the range 0 to 65535 inclusive, specifying the port the
     * server should be listening on for incoming connections. E.g. "PingballServer --port 1234"
     * starts the server listening on port 1234.
     *
     * If no port is specified, the default port 10987 will be used.
     *
     */
    public static void main(String[] args) {

        // parse command line arguments
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        int port = DEFAULT_PORT;
        try {
            while ( ! arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < MIN_PORT || port > MAX_PORT) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    } else {
                        throw new IllegalArgumentException("unknown option: \"" + flag + "\"");
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballServer [--port PORT]");
            return;
        }


        // start server
        try {
            PingballServer server = new PingballServer(port);
            server.serve();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
