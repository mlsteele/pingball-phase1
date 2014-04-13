package common.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import physics.Vect;
import common.Constants.BoardSide;
import common.netprotocol.BallInMessage;
import common.netprotocol.NetworkMessage;
import common.netprotocol.NetworkMessage.DecodeException;

/**
 * Tests for NetworkMessages.
 * These tests cover serialization and deserialization of NetworkMessages
 * as well as basic functionality like extracting data from them.
 *
 * These tests are glass-box in that they know that NetworkMessages share
 * common code for serializing and deserializing individual data like
 * Vects and BoardSide's. This means that edge cases for these are not
 * tested for each NetworkMessage type but for NetworkMessages as a whole.
 * This cuts down on the number of required tests significantly.
 *
 * Testing strategy for each NetworkMessage type:
 * - test constructor and serialization into known string
 * - test data after deserialization from know string
 * - test for exception when deserializing bad data (with correct header)
 *
 * Testing strategy for Vects in NetworkMessages:
 * - Test Vects with positive components
 * - Test Vects with zero components
 * - Test Vects with negative components
 *
 * Testing strategy for BoardSides in NetworkMessages:
 * - Test serialization for each of left, right, top, and bottom sides
 * - Test deserialization for each of left, right, top, and bottom sides
 *
 * Testing strategy for malformed serializations:
 * (these tests cause various DecodeExceptions)
 * - Empty message
 * - No header
 * - Bad header
 * - Missing some body data
 * - Wrong body data
 * - Missing all body data
 * - Extra body data
 *
 * TODO write tests for the other NetworkMessage types.
 *
 */
public class NetworkMessageTests {
    private static final Vect vZero = new Vect(0, 0);
    private static final Vect vPos = new Vect(0, 0);
    private static final Vect vNeg = new Vect(0, 0);

    private static final String ballInMessageString               = "BallInMessage#0.0 0.0#0.0 0.0#L";
    private static final String ballInMessageStringBad            = "BallInMessage#0.0 0.0#L"; // missing data
    private static final String ballOutMessageString              = "BallInMessage#0.0 0.0#0.0 0.0#L";
    private static final String ballOutMessageStringBad           = "BallInMessage#0.0 0.0#0.0 0.0#L"; // extra data
    private static final String boardFuseMessageString            = "BallInMessage#0.0 0.0#0.0 0.0#L";
    private static final String boardFuseMessageStringBad         = "BallInMessage#0.0 0.0#0.0 0.0#L"; // missing body
    private static final String boardUnfuseMessageString          = "BallInMessage#0.0 0.0#0.0 0.0#L";
    private static final String boardUnfuseMessageStringBad       = "BallInMessage#0.0 0.0#0.0 0.0#L"; // wrong data
    private static final String clientConnectMessageString        = "BallInMessage#0.0 0.0#0.0 0.0#L";
    private static final String clientConnectMessageStringBad     = "BallInMessage#0.0 0.0#0.0 0.0#L"; // wrong data
    private static final String connectionRefusedMessageString    = "BallInMessage#0.0 0.0#0.0 0.0#L";
    private static final String connectionRefusedMessageStringBad = "BallInMessage#0.0 0.0#0.0 0.0#L"; // missing data


    @Test(expected=NetworkMessage.DecodeException.class)
    public void testDeserializeEmptyMessage() throws DecodeException {
        NetworkMessage.deserialize("");
    }

    @Test(expected=NetworkMessage.DecodeException.class)
    public void testDeserializeNoHeader() throws DecodeException {
        NetworkMessage.deserialize("ballPos 0.0#ballVel 0.0#toSide L");
    }

    @Test(expected=NetworkMessage.DecodeException.class)
    public void testDeserializeBadHeader() throws DecodeException {
        NetworkMessage.deserialize("thisisnotavalidheader#ballPos 0.0#ballVel 0.0#toSide L");
    }

    @Test public void testBallInMessageSerialize() {
        BallInMessage msg = new BallInMessage(vZero, vZero, BoardSide.LEFT);
        assertEquals(ballInMessageString, msg.serialize());
    }

    @Test public void testBallInMessageDeserialize() throws DecodeException {
        BallInMessage msg = (BallInMessage) NetworkMessage.deserialize(ballInMessageString);
        assertEquals(msg.getBallPos(), vZero);
        assertEquals(msg.getBallVel(), vZero);
        assertEquals(msg.getToSide(), BoardSide.LEFT);
    }

    @Test(expected=NetworkMessage.DecodeException.class)
    public void testBallInMessageDeserializeBad() throws DecodeException {
        NetworkMessage.deserialize(ballInMessageStringBad);
    }
}
