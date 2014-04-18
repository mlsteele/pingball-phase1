package common.netprotocol;


/**
 * Server -> Client
 * Message sent to notify a client that the server refused its connection.
 * This is an immutable class.
 *
 * ADT:
 * * ConnectionRefusedMessage contains the following data:
 *      * reason: the reason the server refused
 *
 * Thread Safety Argument:
 * * all data is immutable.
 *
 * Rep Invariant:
 * * all data is immutable (enforced by type system)
 *
 */
public class ConnectionRefusedMessage extends NetworkMessage {

    private final String reason; // why the server refused

    /**
     * Deserialize the message.
     * See NetworkMessage.deserialize for specification.
     * See this NetworkMessage's serialize for specific serialization specification.
     *
     * @param body body of the message
     * @return decoded NetworkMessage
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

        checkRep();
    }

    /**
     * Serialize the message according to specs in NetworkMessage.serialize
     * @return string serialization of message
     */
    public String serialize() {
        String message = this.getClass().getSimpleName() + STD_SEP;
        message += serializeString(reason);
        return message;
    }

    /**
     * @return return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Our rep invariant is enforced by Java type-checking.
     *
     * This is a do-nothing method.
     */
    private void checkRep() {
        return;
    }
}
