package common;

import physics.Vect;

/**
 * This is a static class containing the constants that need
 * to be used by both the server and the client.
 *
 */
public class Constants {
    public static final int BOARD_WIDTH = 20;
    public static final int BOARD_HEIGHT = 20;
    public static final double ANGULAR_ROTATION = 18.849555922; //for flipper; radians/sec
    public static final double TIMESTEP = .05; //seconds
    public static final Vect SHOOT_VELOCITY = new Vect(Math.PI/2, 50d); //from absorber; L/s
    public static final int GRAVITY = 25; //L/s^2
    /* TODO there will probably be more of these */

    public enum BoardSide {
        LEFT, RIGHT, TOP, BOTTOM;
    }

    public enum bumperType{
        SQUARE, TRIDOWN, TRIUP, CIRCLE
    }

    public enum flipperType{
        LEFT, RIGHT
    }
}
