package client;

import physics.Circle;
import physics.Vect;

/**
 * Mutable ball class
 *
 * Mutable wrapper for Circle to make it easier to work with as a mutable object.
 */
public class Ball {
    public Circle c;
    public Vect vel; // velocity

    public Ball(double radius, Vect pos, Vect vel) {
        this.c = new Circle(pos, radius);
        this.vel = vel;
    }

    public void setVel(Vect vel) {
        this.vel = vel;
    }

    public void setCircle(Circle c) {
        this.c = c;
    }


}
