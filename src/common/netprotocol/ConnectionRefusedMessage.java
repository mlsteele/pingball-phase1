package common.netprotocol;

/**
 * Server -> Client
 * Message sent to notify a client that the server refused its connection.
 * This is an immutable class.
 */
public class ConnectionRefusedMessage extends NetworkMessage {
    private final String reason; // why the server refused

    public ConnectionRefusedMessage(String reason) {
        this.reason = reason;
    }

    public String serialize() {
        // TODO
    }
}
