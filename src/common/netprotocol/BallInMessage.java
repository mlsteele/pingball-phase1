package common.netprotocol;

import physics.Vect;
import common.Constants.BoardSide;
import common.Constants;
import common.RepInvariantException;

/**
 * Server -> Client
 * Message sent when a ball should enter a board.
 * This is an immutable class.
 *
 * ADT:
 * * BallInMessage contains the following data:
 *      * ballPos: the position of the ball
 *      * ballVel: the velocity of the ball
 *      * fromSide: the side of the board that the ball will enter through
 *
 * Thread Safety Argument:
 * * all data is immutable.
 *
 * Rep Invariant:
 * * all data is immutable (enforced by type system)
 * * ballPos x and y are between 0 and Constants.BOARD_WIDTH/Constants.BOARD_HEIGHT (inclusive)
 *
 */
public class BallInMessage extends NetworkMessage {

    private final Vect ballPos; // position of the ball
    private final Vect ballVel; // velocity of the ball
    private final BoardSide toSide; // which side the ball enters

    /**
     * Deserialize the message.
     * See NetworkMessage.deserialize for specification.
     * See this NetworkMessage's serialize for specific serialization specification.
     * @param body body of the message
     * @return decoded NetworkMessage
     */
    public static NetworkMessage deserialize(String body) throws DecodeException {
        String units[] = body.split(STD_SEP);
        if (units.length != 3) {
            throw new DecodeException("Wrong body length: " + units.length);
        }
        Vect ballPos = NetworkMessage.deserializeVect(units[0]);
        Vect ballVel = NetworkMessage.deserializeVect(units[1]);
        BoardSide toSide = NetworkMessage.deserializeBoardSide(units[2]);
        return new BallInMessage(ballPos, ballVel, toSide);
    }

    /**
     * Create a message.
     * @param ballPos position of the ball. Requires ballPos.x() and ballPos.y() to be between 0 and 20, inclusive.
     * @param ballVel velocity of the ball
     * @param toSide  which side the ball enters
     */
    public BallInMessage(Vect ballPos, Vect ballVel, BoardSide toSide) {
        this.ballPos = ballPos;
        this.ballVel = ballVel;
        this.toSide = toSide;

        if (Constants.DEBUG) checkRep();
    }

    /**
     * Serialize the message according to specs in NetworkMessage.serialize
     * @return string serialization of message
     */
    public String serialize() {
        String message = this.getClass().getSimpleName() + STD_SEP;
        message += serializeVect(ballPos) + STD_SEP;
        message += serializeVect(ballVel) + STD_SEP;
        message += serializeBoardSide(toSide);
        return message;
    }

    /**
     * @return return ballPos
     */
    public Vect getBallPos() {
        return ballPos;
    }

    /**
     * @return return ballVel
     */
    public Vect getBallVel() {
        return ballVel;
    }

    /**
     * @return return toSide
     */
    public BoardSide getToSide() {
        return toSide;
    }

    /**
     * asserts the rep invariant.
     *
     * Rep Invariant:
     * * ballPos x and y are between 0 and Constants.BOARD_WIDTH/Constasts.BOARD_HEIGHT (inclusive)
     */
    private void checkRep() {
        if (ballPos.x() < 0 || ballPos.x() > Constants.BOARD_WIDTH
         || ballPos.y() < 0 || ballPos.y() > Constants.BOARD_HEIGHT) {
            throw new RepInvariantException("The ball's position is out of bounds");
        }
    }
}
