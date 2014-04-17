package client;

import common.RepInvariantException;

import physics.Circle;
import physics.Vect;

/**
 * Ball is a mutable class that represents a ball on the Pingball board.
 * Circle, a physics object, is a major part of Ball's construction, and
 * Ball acts as a mutable wrapper for Circle to make it easier to change
 * Balls' positions and velocity.
 *
 * Rep invariant: Ball position.x and Ball position.y must be between 0 and 20
 *
 * Thread safety: everything is confined or final
 */
public class Ball {
    private Circle c;
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
        this.c = new Circle(pos, radius);
        this.vel = vel;
        this.radius = radius;
        checkRep();
        inPlay = true;
    }

    public void setVelocity(Vect vel) {
        this.vel = vel;
    }

    public void setPosition(Vect pos) {
        this.c = new Circle(pos, radius);
        checkRep();
    }

    public Vect getPosition() {
        return c.getCenter();
    }

    public Circle getCircle() {
        return this.c;
    }

    public Vect getVelocity() {
        return this.vel;
    }

    /**
     * Rep invariant: Ball position.x and Ball position.y must be between 0 and 20
     * TODO magic number 20
     */
    private void checkRep(){
        if (!(getPosition().x() >= 0 && getPosition().x() <= 20 && getPosition().y() >= 0 && getPosition().y() <= 20)) {
            throw new RepInvariantException("Rep invariant violated.");
        }
    }

    public boolean isInPlay() {
        return inPlay;
    }

    public void setInPlay(boolean inPlay) {
        this.inPlay = inPlay;
    }
}
