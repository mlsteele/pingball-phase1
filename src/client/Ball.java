package client;

import common.Constants;
import common.RepInvariantException;
import physics.Circle;
import physics.Vect;

/**
 * Ball is a mutable class that represents a ball on the Pingball board.
 * Circle, a physics object, is a major part of Ball's construction, and
 * Ball acts as a mutable wrapper for Circle to make it easier to change
 * Balls' positions and velocity.
 *
 * Ball has the ability to revert to a previous position so that
 * can be guaranteed to be in a valid place.
 *
 * Rep invariant:
 * - Ball position.x must be in the range [0, Constants.BOARD_WIDTH]
 * - Ball position.y must be in the range [0, Constants.BOARD_HEIGHT]
 *
 * Thread safety:
 * - This is not a threadsafe datatype.
 */
public class Ball {
    private Circle circle;
    private Circle prevCircle; // store the balls previous position
    private Vect vel; // velocity
    private double radius;
    private boolean inPlay;

    /**
     * Ball Constructor
     * @param radius
     * @param pos
     * @param vel
     */
    public Ball(double radius, Vect pos, Vect vel) {
        this.circle = new Circle(pos, radius);
        this.prevCircle = this.circle;
        this.vel = vel;
        this.radius = radius;
        checkRep();
        inPlay = true;
    }

    /**
     * Revert the Balls position to the previously set position.
     *
     * Calling revert multiple times without changing the position
     * in between calls will NOT result in multiple reversions,
     * but in a single reversion to the previous position followed
     * by no-ops until the position is changed.
     *
     * Revert does NOT modify the Balls velocity.
     */
    public void revertPosition() {
        circle = prevCircle;
    }

    public void setVelocity(Vect vel) {
        this.vel = vel;
    }

    public void setPosition(Vect pos) {
        prevCircle = circle;
        circle = new Circle(pos, radius);
        checkRep();
    }

    public Vect getPosition() {
        return circle.getCenter();
    }

    public Circle getCircle() {
        return this.circle;
    }

    public Vect getVelocity() {
        return this.vel;
    }

    public boolean isInPlay() {
        return inPlay;
    }

    public void setInPlay(boolean inPlay) {
        this.inPlay = inPlay;
    }

    /**
     * Check that the rep invariant is not violated.
     * @throws RepInvariantException to indicate failure.
     */
    public void checkRep(){
        if (!(getPosition().x() >= 0
            && getPosition().x() <= Constants.BOARD_WIDTH
            && getPosition().y() >= 0
            && getPosition().y() <= Constants.BOARD_HEIGHT)) {
            throw new RepInvariantException("Rep invariant violated.");
        }
    }
}
