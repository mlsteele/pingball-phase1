package warmup;

import java.util.ArrayList;
import java.util.List;
import physics.*;

public class WarmupBoard {
    private int width;
    private int height;
    private Circle ball;
    private Vect velocity;
    private final List<LineSegment> walls;
//    private static final double timestamp = 1d/2d;
    private static final double timestamp = 1d/20d;

    public WarmupBoard(int width, int height, Vect initialBallPos, Vect initialBallVel) {
        // initialize the board.
        this.width = width;
        this.height = height;
        LineSegment wallLeft = new LineSegment(0.,0.,0.,height);
        LineSegment wallRight = new LineSegment(20.,0.,20.,20.);
        LineSegment wallUp = new LineSegment(0.,0.,20.,0.);
        LineSegment wallDown = new LineSegment(0.,20.,20.,20.);
        ball = new Circle(initialBallPos, 0.5); //0.5 = radius of ball
        this.velocity = initialBallVel;
        
        //create walls
        walls = new ArrayList<LineSegment>();
        walls.add(wallLeft);
        walls.add(wallRight);
        walls.add(wallUp);
        walls.add(wallDown);
    }

    public void run() {
        // start the infinite main loop
        while(true){
            try {
                Thread.sleep((int) (timestamp * 1000));
                this.updatePhysics();
                System.out.println(this.toString());
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void updatePhysics() {
        // move stuff around one tick.
        // handle collisions
        for (LineSegment wall : walls) {
            if (Geometry.timeUntilWallCollision(wall, ball, velocity) < timestamp) {
                // we will collide this step! Reflect and update velocity. 
                velocity = Geometry.reflectWall(wall, velocity);
            }
        }
        // step
        ball = new Circle(ball.getCenter().plus(velocity.times(timestamp)), ball.getRadius());
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
            boardString += "\n";
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
