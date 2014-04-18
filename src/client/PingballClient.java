package client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import client.boardlang.BoardFactory;
import common.Constants;
import common.netprotocol.*;

import java.io.File;
import java.net.Socket;

import physics.Vect;


/**
 * Pingball game client.
 *
 * Pingball client creates a game board from a text file, connects
 * to a pingball server, and runs the pingball game while
 * communicating with the server about inter-board fuses and ball
 * transfers.
 *
 * Thread Safety Argument:
 * - board is confined to the main thread.
 * - incomingMessages is a threadsafe datatype.
 * // TODO update specs in this class
 */
public class PingballClient {
    private final Board board;
    private final Socket socket;
    private final ServerHandler serverHandler;
    private final BlockingQueue<NetworkMessage> incomingMessages;

    /**
     * Instantiate a PingballClient.
     *
     * @param socket socket to communicate with the server
     *               socket can be null!
     *               if socket is null, server communication is disabled.
     * @throws IOException if the socket can not be created
     */
    public PingballClient(Board board, Socket socket) throws IOException {
        this.board = board;
        this.socket = socket;
        this.incomingMessages = new LinkedBlockingQueue<NetworkMessage>();
        if (socket != null) {
            serverHandler = new ServerHandler(socket, incomingMessages);
            serverHandler.send(new ClientConnectMessage(board.getName()));
            board.setServerHandler(serverHandler);
        } else {
            this.serverHandler = null;
        }
    }

    public void startClient() throws IOException {
        if (socket != null) {
            serverHandler.run();
        }

        while(true){
            try {
                // Sleep to limit framerate.
                Thread.sleep((int) (Constants.TIMESTEP * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            while (!incomingMessages.isEmpty()) {
                NetworkMessage message = incomingMessages.remove();
                if (message instanceof BallInMessage) { // TODO the sending board is responsible for making ballPos okay
                    // TODO it is sketchy to initialize the ball with default radius. maybe the radius should not be a parameter.
                    Vect ballPos = ((BallInMessage) message).getBallPos();
                    Vect ballVel = ((BallInMessage) message).getBallVel();
                    board.addBall(new Ball(Constants.BALL_RADIUS, ballPos, ballVel));
                } else if (message instanceof BoardFuseMessage) {
                    Constants.BoardSide side = ((BoardFuseMessage) message).getSide();
                    String name = ((BoardFuseMessage) message).getBoardName();
                    board.connectWallToServer(side, name);
                } else if (message instanceof BoardUnfuseMessage) {
                    Constants.BoardSide side = ((BoardFuseMessage) message).getSide();
                    board.disconnectWallFromServer(side);
                } else if (message instanceof ConnectionRefusedMessage) {
                    // the serverHandler has already killed itself
                    if (Constants.DEBUG) {
                        System.err.println("Connection refused by server. Reason: " + ((ConnectionRefusedMessage) message).getReason());
                    }
                }
            }

            String boardView = board.step();
            System.out.println(boardView);
        }
    }

    /**
     * Start a PingballClient using the given arguments.
     *
     * Usage: PingballClient [--host HOST] [--port PORT] FILE
     *
     * HOST is an optional hostname or IP address of the server to connect to.
     * If no HOST is provided, then the client starts in single-machine play mode.
     *
     * PORT is an optional integer in the range 0 to 65535 inclusive, specifying the port
     * where the server is listening for incoming connections. The default port is 10987.
     *
     * FILE is a required argument specifying a file pathname
     * of the Pingball board that this client should run.
     *
     * If no port is specified, the default port 10987 will be used.
     * @throws InterruptedException
     *
     */
    public static void main(String[] args) {
        int port = Constants.DEFAULT_PORT;
        String hostname = null;
        String boardFilePath = null;

        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while ( ! arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < Constants.MIN_PORT || port > Constants.MAX_PORT) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    } else if (flag.equals("--host")) {
                        hostname = arguments.remove();
                    } else {
                        if (boardFilePath != null) {
                            throw new IllegalArgumentException("Extra argument: " + flag);
                        }
                        boardFilePath = flag;
                        // throw new IllegalArgumentException("unknown option: \"" + flag + "\"");
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + flag);
                }
            }

            if (boardFilePath == null) {
                throw new IllegalArgumentException("Missing BOARD filepath");
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("Usage: PingballClient [--host HOST] [--port PORT] FILE");
            return;
        }

        // Create assets for the client and start it.

        Board board;
        try {
            board = BoardFactory.parse(SimpleFileReader.readFile(new File(boardFilePath)));
        } catch (FileNotFoundException e) {
            System.err.println("Board file not found at " + boardFilePath);
            return;
        } catch (IOException e) {
            System.err.println("Error while reading board file at " + boardFilePath);
            return;
        }

        Socket socket = null;
        if (hostname != null) {
            try {
                socket = new Socket(hostname, port);
            } catch (IOException e) {
                System.err.println("Could not connect to server " + hostname + ":" + port);
                return;
            }
        }

        PingballClient client = null;
        try {
            client = new PingballClient(board, socket);
        } catch (IOException e) {
            System.err.println("Error while connecting to server");
        }

        if (client != null) {
            try {
                client.startClient();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Terminated due to erorr while communicating with server.");
                return;
            }
        }
    }
}
