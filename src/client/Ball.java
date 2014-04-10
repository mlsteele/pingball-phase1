package client;

import physics.Circle;
import physics.Vect;

/**
 * Mutable ball class
 *
 * Mutable wrapper for Circle to make it easier to work with as a mutable object.
 */
public class Ball {
    private final Circle c;
    private Vect vel; // velocity

    Ball(double radius, Vect pos, Vect vel) {
        this.c = new Circle(pos, radius);
        this.vel = vel;
    }
}
