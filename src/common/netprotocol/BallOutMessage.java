package common.netprotocol;

/**
 * Client -> Server
 * Message sent when a ball leaves a board.
 * This is an immutable class.
 * The position and velocity of the ball are cached when the message is construccted.
 */
public class BallOutMessage extends NetworkMessage {
    private final Vect ballPos; // position of the ball
    private final Vect ballVel; // velocity of the ball
    private final BoardSide fromSide; // which side the ball departs from

    /**
     * Create a message.
     * @param ballPos  position of the ball
     * @param ballVel  velocity of the ball
     * @param fromSide which side the ball departs from
     */
    public BallOutMessage(Vect ballPos, Vect ballVel, BoardSide fromSide) {
        this.ballPos = ballPos;
        this.ballVel = ballVel;
        this.fromSide = fromSide;
    }

    public String serialize() {
        // TODO
    }
}
