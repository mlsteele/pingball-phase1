package common.netprotocol;

/**
 * Server -> Client
 * Message sent to notify a client of a board fuse.
 * This is an immutable class.
 */
public class BoardFuseMessage extends NetworkMessage {
    private final String boardName; // name of board fusing
    private final BoardSide side; // which side to fuse

    /**
     * Create a message.
     * @param boardName name of the board fusing
     * @param side      which side to fuse
     */
    public BoardFuseMessage(String boardName, BoardSide side) {
        this.boardName = boardName;
        this.side = side;
    }

    public String serialize() {
        // TODO
    }
}
