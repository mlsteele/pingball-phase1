package server;

import common.netprotocol.NetworkMessage;

/**
 * A NetworkMessage and the ClientHandler who received that message.
 *
 * This class will be used to attribute an author to a NetworkMessage,
 * so that the server is able to respond to the client who sent the message.
 *
 * ADT: {NetworkMessage m, ClientHandler c}
 *
 * Thread Safety:
 * * All fields are final
 *  (though its contents are *not* immutable, they have their own thread safety arguments)
 *
 * Rep Invariant:
 * * ch must be the ClientHandler that produced the message
 *
 */
public class AuthoredMessage {

    private final NetworkMessage message;
    private final ClientHandler ch;

    /**
     * create an AuthoredMessage
     * @param message the NetworkMessage
     * @param ch the ClientHandler that produced the message
     */
    public AuthoredMessage(NetworkMessage message, ClientHandler ch) {
        this.message = message;
        this.ch = ch;
    }

    /**
     * getter for message
     * @return the message
     */
    public NetworkMessage getMessage() {
        return message;
    }

    /**
     * getter for client
     * @return the clientHandler
     */
    public ClientHandler getClientHandler() {
        return ch;
    }
}
