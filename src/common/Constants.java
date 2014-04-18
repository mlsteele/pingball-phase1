package common;

import physics.Vect;

/**
 * This is a static class containing the constants that need
 * to be used by both the server and the client.
 */
public class Constants {
    public static final int BOARD_WIDTH = 20; // in L
    public static final int BOARD_HEIGHT = 20; // in L
    public static final double ANGULAR_ROTATION = 18.849555922; //for flipper; radians/sec
    public static final double TIMESTEP = 1d/20d; //seconds
    public static final double SHOOT_VELOCITY = -50d; //from absorber straight into air; L/s
    public static final double DEFAULT_FRICTION1 = 0.025; // per second
    public static final double DEFAULT_FRICTION2 = 0.025; // per L
    public static final double BALL_RADIUS = 0.5; // in L
    public static final double GRAVITY = 25; //L/s^2
    public static final double DEFAULT_CIRCLE_BUMPER_RADIUS = 1; //L
    public static final int DEFAULT_PORT = 10987;
    public static final int MIN_PORT = 0;
    public static final int MAX_PORT = 65535;

    public enum BoardSide {
        LEFT, RIGHT, TOP, BOTTOM;
    }

    public enum BumperType{
        SQUARE, TRIDOWN, TRIUP, CIRCLE
    }

    public enum FlipperType{
        LEFT, RIGHT
    }
    /* TODO set this to false in the final version! */
    public static final boolean DEBUG = true;
}
