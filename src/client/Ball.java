package client;

import physics.Circle;
import physics.Vect;

/**
 * Mutable ball class
 *
 * Mutable wrapper for Circle to make it easier to work with as a mutable object.
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
