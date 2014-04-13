package common.netprotocol;

import common.Constants.BoardSide;

/**
 * Server -> Client
 * Message sent to notify a client of a board unfuse.
 * This is an immutable class.
 */
public class BoardUnfuseMessage extends NetworkMessage {
    private final BoardSide side; // which side to unfuse

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
        BoardSide side = NetworkMessage.deserializeBoardSide(units[0]);
        return new BoardUnfuseMessage(side);
    }

    /**
     * Create a message.
     * @param side which side to unfuse
     */
    public BoardUnfuseMessage(BoardSide side) {
        this.side = side;
    }

    public String serialize() {
        String message = this.getClass().getSimpleName() + STD_SEP;
        message += serializeBoardSide(side);
        return message;
    }

    /**
     * @return return side
     */
    public BoardSide getSide() {
        return side;
    }
}
