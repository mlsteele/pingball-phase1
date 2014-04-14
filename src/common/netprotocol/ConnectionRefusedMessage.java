package common.netprotocol;

/**
 * Server -> Client
 * Message sent to notify a client that the server refused its connection.
 * This is an immutable class.
 */
public class ConnectionRefusedMessage extends NetworkMessage {
    private final String reason; // why the server refused

    /**
     * Deserialize the message.
     * See NetworkMessage.deserialize for specification.
     * See this NetworkMessage's serialize for specific serialization specification.
     * @return decoded NetworkMessage
     * @param body body of the message
     */
    public static NetworkMessage deserialize(String body) throws DecodeException {
        String units[] = body.split(STD_SEP);
        if (units.length != 1) {
            throw new DecodeException("Wrong body length: " + units.length);
        }
        String reason = units[0];
        if (reason.isEmpty()) {
            throw new DecodeException("Empty reason");
        }
        return new ConnectionRefusedMessage(reason);
    }

    public ConnectionRefusedMessage(String reason) {
        this.reason = reason;
    }

    public String serialize() {
        String message = this.getClass().getSimpleName() + STD_SEP;
        message += reason;
        return message;
    }

    /**
     * @return return reason
     */
    public String getReason() {
        return reason;
    }
}
