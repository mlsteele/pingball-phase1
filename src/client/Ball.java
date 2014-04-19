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
 * The ball has an in play flag. This determines whether the ball
 * should be considered a part of the game for the purposes of collisions,
 * gravity, and other game interction.
 * This can be set to false to disable the ball if, for example,
 * it is in the clutches of an Absorber.
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
     * @param radius of the ball
     * @param pos position of the ball
     * @param vel velocity of the ball
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

    /**
     * Setter for ball velocity.
     * @param vel new velocity
     */
    public void setVelocity(Vect vel) {
        this.vel = vel;
    }

    /**
     * Setter for ball position.
     * @param pos new position
     */
    public void setPosition(Vect pos) {
        prevCircle = circle;
        circle = new Circle(pos, radius);
        checkRep();
    }

    /**
     * Getter for position.
     * @return centerpoint of the ball
     */
    public Vect getPosition() {
        return circle.getCenter();
    }

    /**
     * Getter for Ball's current Circle.
     * Useful for physics interaction.
     * @return Circle of the ball at its current state.
     */
    public Circle getCircle() {
        return this.circle;
    }

    /**
     * Getter for Ball's current velocity.
     * @return ball's current velocity
     */
    public Vect getVelocity() {
        return this.vel;
    }

    /**
     * Getter for Ball's in play flag.
     * @return ball's current in play status.
     */
    public boolean isInPlay() {
        return inPlay;
    }

    /**
     * Setter for in play flag.
     * @param inPlay new value for in play flag.
     */
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
