package common.netprotocol;

import common.Constants;
import common.Constants.BoardSide;

/**
 * Server -> Client
 * Message sent to notify a client of a board fuse.
 * This is an immutable class.
 *
 * ADT:
 * * BoardFuseMessage contains the following data:
 *      * boardName: the name of the board that will fuse to the client board
 *      * side: which side the new board will fuse to on the client
 *
 * Thread Safety Argument:
 * * all data is immutable.
 *
 * Rep Invariant:
 * * all data is immutable (enforced by type system)
 */
public class BoardFuseMessage extends NetworkMessage {

    private final String boardName; // name of board fusing
    private final BoardSide side; // which side to fuse

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
        if (units.length != 2) {
            throw new DecodeException("Wrong body length: " + units.length);
        }
        String boardName = units[0];
        if (boardName.isEmpty()) {
            throw new DecodeException("Empty boardName");
        }
        BoardSide side = NetworkMessage.deserializeBoardSide(units[1]);
        return new BoardFuseMessage(boardName, side);
    }

    /**
     * Create a message.
     * @param boardName name of the board fusing
     * @param side      which side to fuse
     */
    public BoardFuseMessage(String boardName, BoardSide side) {
        this.boardName = boardName;
        this.side = side;

        if (Constants.DEBUG) checkRep();
    }

    /**
     * Serialize the message according to specs in NetworkMessage.serialize
     * @return string serialization of message
     */
    public String serialize() {
        String message = this.getClass().getSimpleName() + STD_SEP;
        message += serializeString(boardName) + STD_SEP;
        message += serializeBoardSide(side);
        return message;
    }

    /**
     * @return return boardName
     */
    public String getBoardName() {
        return boardName;
    }

    /**
     * @return return side
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
