package warmup;

import java.util.List;
import physics.*;

public class WarmupBoard {
    private int width;
    private int height;
    private Circle ball;
    private List<LineSegment> walls;

    public WarmupBoard(int width, int height, Vect initialBallPos, Vect initialBallVel) {
        // initialize the board.
    }

    public void run() {
        // start the infinite main loop
        // does physics, does print: every so often
    }

    private void updatePhysics() {
        // move stuff around one tick.
        // handle collisions
        // step
    }

    @Override
    public String toString() {
        // print the board
    }
}
