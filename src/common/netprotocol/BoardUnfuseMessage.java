package common.netprotocol;

import common.Constants;
import common.Constants.BoardSide;

/**
 * Server -> Client
 * Message sent to notify a client of a board unfuse.
 * This is an immutable class.
 *
 * ADT:
 * * BoardUnfuseMessage contains the following data:
 *      * side: which side the unfusing board was connected to
 *
 * Thread Safety Argument:
 * * all data is immutable.
 *
 * Rep Invariant:
 * * all data is immutable (enforced by type system)
 *
 */
public class BoardUnfuseMessage extends NetworkMessage {

    private final BoardSide side; // which side to unfuse

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
        BoardSide side = NetworkMessage.deserializeBoardSide(units[0]);
        return new BoardUnfuseMessage(side);
    }

    /**
     * Create a message.
     * @param side which side to unfuse
     */
    public BoardUnfuseMessage(BoardSide side) {
        this.side = side;

        if (Constants.DEBUG) checkRep();
    }

    /**
     * Serialize the message according to specs in NetworkMessage.serialize
     * @return string serialization of message
     */
    public String serialize() {
        String message = this.getClass().getSimpleName() + STD_SEP;
        message += serializeBoardSide(side);
        return message;
    }

    /**
     * @return side
     */
    public BoardSide getSide() {
        return side;
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
