package common.netprotocol;

/**
 * Client -> Server
 * Message sent when a client wants to connect to a server.
 * This is an immutable class.
 */
public class ClientConnectMessage extends NetworkMessage {
    private final String boardName;

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
        String boardName = units[0];
        if (boardName.isEmpty()) {
            throw new DecodeException("Empty boardName");
        }
        return new ClientConnectMessage(boardName);
    }

    /**
     * Create a message.
     * @param boardName name of the board connecting
     */
    public ClientConnectMessage(String boardName) {
        this.boardName = boardName;
    }

    public String serialize() {
        String message = this.getClass().getSimpleName() + STD_SEP;
        message += boardName;
        return message;
    }

    /**
     * @return return boardName
     */
    public String getBoardName() {
        return boardName;
    }
}
