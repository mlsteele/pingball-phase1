package common;

/**
 * This is a static class containing the constants that need
 * to be used by both the server and the client.
 *
 */
public class Constants {
    public static final int BOARD_WIDTH = 20;
    public static final int BOARD_HEIGHT = 20;
    /* TODO there will probably be more of these */

    public enum BoardSide {
        LEFT, RIGHT, TOP, BOTTOM;
    }
}
