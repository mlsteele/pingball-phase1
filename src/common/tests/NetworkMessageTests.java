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
 * - just once, test deserializing data with a bad header.
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
 * TODO write tests for the other NetworkMessage types.
 *
 */
public class NetworkMessageTests {
    private static final Vect vZero = new Vect(0, 0);
    private static final Vect vPos = new Vect(0, 0);
    private static final Vect vNeg = new Vect(0, 0);

    private static final String ballInMessageString = "BallInMessage\n0.0 0.0\n0.0 0.0\nL";
    private static final String ballInMessageStringBad = "BallInMessage\n0.0 0.0\nL";

    @Test(expected=NetworkMessage.DecodeException.class)
    public void testDeserializeBadHeader() throws DecodeException {
        NetworkMessage.deserialize("thisisnotavalidheader\nballPos 0.0\nballVel 0.0\ntoSide L");
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
