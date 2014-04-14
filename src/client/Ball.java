package client;

import physics.Circle;
import physics.Vect;

/**
 * Ball is a mutable class that represents a ball on the Pingball board.
 * Circle, a physics object, is a major part of Ball's construction, and
 * Ball acts as a mutable wrapper for Circle to make it easier to change
 * Balls' positions and velocity.
 *
 * Rep invariant: position must occur within board
 * Thread safety: everything is confined or final
 */
public class Ball {
    private Circle c;
    private Vect vel; // velocity
    private Vect position;
    private double radius;

    public Ball(double radius, Vect pos, Vect vel) {
        this.c = new Circle(pos, radius);
        this.vel = vel;
        position = pos;
        this.radius = radius;
    }

    public void setVelocity(Vect vel) {
        this.vel = vel;
    }

    public void setPosition(Vect pos) {
        this.c = new Circle(pos, radius);
    }

    public Circle getCircle() {
        return this.c;
    }

    public Vect getVelocity() {
        return this.vel;
    }

}
