package common;

import physics.Vect;

/**
 * This is a static class containing the constants that need
 * to be used by both the server and the client.
 *
 */
public class Constants {
    public static final int BOARD_WIDTH = 20; // in L
    public static final int BOARD_HEIGHT = 20; // in L
    public static final double ANGULAR_ROTATION = 18.849555922; //for flipper; radians/sec
    public static final double TIMESTEP = .05; //seconds
    public static final Vect SHOOT_VELOCITY = new Vect(Math.PI/2, 50d); //from absorber; L/s
    public static final double GRAVITY = 25; //L/s^2
    public static final double DEFAULT_FRICTION1 = 0;
    public static final double DEFAULT_FRICTION2 = 0;
    public static final double BALL_RADIUS = 0.5; // in L

    public enum BoardSide {
        LEFT, RIGHT, TOP, BOTTOM;
    }

    public enum BumperType{
        SQUARE, TRIDOWN, TRIUP, CIRCLE
    }

    public enum FlipperType{
        LEFT, RIGHT
    }
}
