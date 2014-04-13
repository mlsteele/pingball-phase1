package common.netprotocol;

/**
 * Client -> Server
 * Message sent when a client wants to connect to a server.
 * This is an immutable class.
 */
public class ClientConnectMessage extends NetworkMessage {
    private final String boardName;

    /**
     * Create a message.
     * @param boardName name of the board connecting
     */
    public ClientConnectMessage(String boardName) {
        this.boardName = boardName;
    }

    public String serialize() {
        // TODO
    }
}
