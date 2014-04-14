package client;


/**
 * Pingball game client.
 *
 * Pingball client creates a game board from a text file, connects
 * to a pingball server, and runs the pingball game while
 * communicating with the server about inter-board fuses and ball
 * transfers.
 *
 * Thread Safety Argument:
 * - board is confined to the main thread
 * - socket is confined to the ServerHandler thread
 * - messages are passed between the two threads using a threadsafe queue.
 */
public class PingballClient {
    public static void main(String[] args) {
        Board board;

        // parse command line arguments
        // load board
        // start client


    }
}
