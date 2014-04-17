package client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import server.ClientHandler;
import server.CommandLineInterface;
import server.SocketAcceptor;
import client.boardlang.BoardFactory;
import common.Constants;
import common.netprotocol.NetworkMessage;
import java.io.File;


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
 */
public class PingballClient {
    private BlockingQueue incomingMessages; //will be final when implemented
    private int port;
    private String host;
    private static File file;

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

    /**
     * Instantiate a PingballClient
     * @param port the port on which to create the socket
     * @throws IOException if the socket can not be created
     */
    public PingballClient(int port, String host, File file) throws IOException {
        this.port = port;
        this.incomingMessages = new LinkedBlockingQueue<String>();
        this.file = file;
    }


    public static void main(String[] args) throws InterruptedException {
        Board board;
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        int port = Constants.DEFAULT_PORT;
        String host = null;
        try {
            while ( ! arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < Constants.MIN_PORT || port > Constants.MAX_PORT) {
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
                if (flag.equals("--host")){
                    host = arguments.remove();
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballServer [--port PORT]");
            return;
        }

        String boardString = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                boardString = boardString.concat(line).concat("\n");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        board = BoardFactory.parse(boardString);
        //PingballClient client = new PingballClient(port, host, file);
        //client.startClient(board);
    }

    public void startClient(Board b) throws IOException {
        while(true){
            try {
                Thread.sleep((int) (Constants.TIMESTEP * 1000));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            b.step();
        }
    }

}