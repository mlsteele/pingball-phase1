package client;

import java.net.Socket;

/**
 * ServerHandler is a runnable meant to run as a thread.
 * It handles communication with the server including wire protocol and non-blockng communication with the socket.
 *
 * Thread Safety Argument:
 * - socket is confined to this thread after construction.
 */
public class ServerHandler implements Runnable {
    private final Socket socket;

    /**
     * Create a ServerHandler.
     *
     * @param socket to communicate with the server.
     *        socket must already be connected.
     *        the caller must throw away their reference to socket after creating a ServerHandler
     */
    ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO listen to and send messages in a loop
    }
}
