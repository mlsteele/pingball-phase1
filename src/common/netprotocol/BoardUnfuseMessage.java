package common.netprotocol;

/**
 * Server -> Client
 * Message sent to notify a client of a board unfuse.
 * This is an immutable class.
 */
public class BoardUnfuseMessage extends NetworkMessage {
    private final BoardSide side; // which side to unfuse

    /**
     * Create a message.
     * @param side which side to unfuse
     */
    public BoardUnfuseMessage(BoardSide side) {
        this.side = side;
    }

    public String serialize() {
        // TODO
    }
}
