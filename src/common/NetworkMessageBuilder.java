package common;


/**
 * NetworkMessageBuilder is a static class which
 * creates messages to pass between the client and server.
 *
 * The wire protocol is completely contained within this class
 *
 */
public class NetworkMessageBuilder {

    /**
     * This is a server-to-client message that a ball
     * has entered from a portal wall.
     *
     * @param ball the ball that has entered the client's board
     * @return a message to send to the client
     */
    public String ballInMessage(Ball ball) {
        // TODO
    }

    /**
     * This is a client-to-server message that a ball
     * has left through a portal wall.
     *
     * @param ball the ball that has left the client's board
     * @return a message to send to the server
     */
    public String ballOutMessage(Ball ball) {
        // TODO
    }

    /**
     * This is a server-to-client message that another
     * board has connected.
     * @param side in {'l', 'r', 't', 'b'} specifying which side this
     * board will connect to.
     * @return a message to send to the client
     */
    public String boardFusedMessage(String side) {
        // TODO
    }

    /**
     * This is a server-to-client message that a connected
     * board has disconnected.
     * @param side in {'l', 'r', 't', 'b'} specifying which side this
     * board is detaching from.
     * @return a message to send to the client
     */
    public String boardSeparateMessage(String side) {
        // TODO
    }

    /* TODO there will probably be more types of messages */
}
