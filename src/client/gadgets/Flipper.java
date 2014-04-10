package client.gadgets;

import physics.Vect;
import client.BoardEvent;

/**
 * generally rectangular rotating shape with bounding box of size 2Lx2L
 * Trigger: generated whenever the ball hits it
 * Action: rotates 90 degrees with a .95 coefficient of reflection
 *
 * Must create a right flipper for every left flipper. Left rotates counter-
 * clockwise and right rotates clockwise.
 *
 * When a flipper is first triggered, it sweeps 90 degrees in the direction according to its
 * position (left or right). If triggered again, the flipper sweeps back 90 degrees to the initial position
 *
 * The flipper rotates at a constant angular velocity of 1080 degrees per second to
 * a position 90 degrees away from its starting position. When its action is triggered a
 * second time, the flipper rotates back to its original position at an angular velocity
 * of 1080 degrees per second.
 */

public class Flipper implements Gadget{

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String stringRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void specialAction() {
        // TODO Auto-generated method stub

    }

    @Override
    public BoardEvent handleBall() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vect getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

}
