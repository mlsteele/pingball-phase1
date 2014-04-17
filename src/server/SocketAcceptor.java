package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import common.netprotocol.NetworkMessage;

/**
 * Runnable that accepts socket connections and starts ClientHandler threads
 *
 * Thread Safety Argument:
 * * This is the only thread that will use the serverSocket.
 * * There will only ever be one SocketAcceptor.
 * * Sockets given to ClientHandlers are no longer touched by this thread.
 *
 */
public class SocketAcceptor implements Runnable {

    private final ServerSocket serverSocket;
    private final BlockingQueue<AuthoredMessage> messageQueue;
    private final BlockingQueue<ClientHandler> deadClientsQueue;

    /**
     * Create a new SocketAcceptor on the specified port
     * @param port the port on which to start a server socket. requires 0 <= port <= 65535
     * @throws IOException if the ServerSocket instantiation fails
     */
    public SocketAcceptor(int port, BlockingQueue<AuthoredMessage> queue, BlockingQueue<ClientHandler> deadClientsQueue) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.messageQueue = queue;
        this.deadClientsQueue = deadClientsQueue;
    }

    /**
     * Waits for server socket connections and creates ClientHandler threads from them
     */
    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ClientHandler(socket, messageQueue, deadClientsQueue));
                thread.start();
            } catch (IOException e) {}
        }
    }

}
