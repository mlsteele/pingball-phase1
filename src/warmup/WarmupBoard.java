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
        // Round ball position to grid square
        Vect ballpos = ball.getCenter();
        int ballX = (int) ballpos.x();
        int ballY = (int) ballpos.y();

        // Create rep string
        String boardString = "";
        for (int y = 0; y < width; y++) {
        for (int x = 0; x < width; x++) {
            if (ballX == x && ballY == y) {
                boardString += "*";
            } else if (isOnBoundary(x,y)) {
                boardString += ".";
            } else {
                boardString += " ";
            }
        }
        }
        return boardString;
    }

    /**
     * Check if a coordinate point is on the outer boundary of the board.
     * @param  x x coordinate
     * @param  y y coordinate
     * @return   boolean indicating whether point is on board boundary.
     */
    private boolean isOnBoundary(int x, int y) {
        return x == 0
            || x == width -1
            || y == 0
            || y == height -1;
    }
}
