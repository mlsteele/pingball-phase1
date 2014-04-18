package client.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Vect;
import common.Constants;
import client.Ball;

/**
 * Tests for Ball.
 *
 * These tests are not very deep.
 * They only test that a parse succeeds for valid inputs and that
 * a Board is output.
 *
 * CheckRep does not appear in these tests because it is called internally
 * inside of Ball at critical points.
 *
 * Testing Strategy:
 * - test constructor with getters
 * - test getters and setters together
 * - test revert
 *     - once
 *     - intially before any changes to position
 *     - multiple times after a change to position
 */
public class BallTests {
    @Test public void testConstructAndGet() {
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4d,5d));
        assertEquals(new Vect(2,3), b.getPosition());
        assertEquals(new Vect(2,3), b.getCircle().getCenter());
        assertEquals(new Vect(4,5), b.getVelocity());
        assertTrue(b.isInPlay());
    }

    @Test public void testSetGetPosition() {
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4,5));
        b.setPosition(new Vect(6,7));
        assertEquals(new Vect(6,7), b.getPosition());
    }

    @Test public void testSetGetVelocity() {
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4,5));
        b.setVelocity(new Vect(6,7));
        assertEquals(new Vect(6,7), b.getVelocity());
    }

    @Test public void testSetGetInPlay() {
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4,5));
        b.setInPlay(false);
        assertEquals(false, b.isInPlay());
        b.setInPlay(true);
        assertEquals(true, b.isInPlay());
    }

    @Test public void testGetCircle() {
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4,5));
        b.setPosition(new Vect(6,7));
        assertEquals(new Vect(6,7), b.getCircle().getCenter());
    }

    @Test public void testRevertOnce() {
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4,5));
        b.setPosition(new Vect(6,7));
        b.revertPosition();
        assertEquals(new Vect(2,3), b.getPosition());
    }

    @Test public void testRevertOnceWithVelocity() {
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4,5));
        b.setPosition(new Vect(6,7));
        b.setVelocity(new Vect(8,9));
        b.revertPosition();
        assertEquals(new Vect(2,3), b.getPosition());
        assertEquals(new Vect(8,9), b.getVelocity());
    }

    @Test public void testRevertInitially() {
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4,5));
        b.revertPosition();
        assertEquals(new Vect(2,3), b.getPosition());
    }

    @Test public void testRevertMulti() {
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4,5));
        b.setPosition(new Vect(6,7));
        b.revertPosition();
        assertEquals(new Vect(2,3), b.getPosition());
        b.revertPosition();
        assertEquals(new Vect(2,3), b.getPosition());
        b.revertPosition();
        b.revertPosition();
        assertEquals(new Vect(2,3), b.getPosition());
    }
}
