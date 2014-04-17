package client;

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
    private Vect position;
    private double radius;

    /**
     * Ball Constructor
     * @param radius
     * @param pos
     * @param vel
     */
    public Ball(double radius, Vect pos, Vect vel) {
        this.c = new Circle(pos, radius);
        this.vel = vel;
        position = pos;
        this.radius = radius;
        if (!checkRep()){
            System.out.println("Error: rep invariant broken");
            System.exit(0);
        }
    }

    public void setVelocity(Vect vel) {
        this.vel = vel;
    }

    public void setPosition(Vect pos) {
        this.c = new Circle(pos, radius);
        if (!checkRep()){
            System.out.println("Error: rep invariant broken");
            System.exit(0);
        }
    }

    public Circle getCircle() {
        return this.c;
    }

    public Vect getVelocity() {
        return this.vel;
    }

    public Vect getCenter(){
        return this.position;
    }

    /**
     * Rep invariant: Ball position.x and Ball position.y must be between 0 and 20
     * @return boolean indicating whether the ball adheres to the rep invariant
     */
    private boolean checkRep(){
        if (position.x() >= 0 && position.x() <= 20 && position.y() >= 0 && position.y() <= 20){
            return true;
        } else{
            return false;
        }
    }
}
