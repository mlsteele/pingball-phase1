package warmup;
import java.util.List;
import physics.*;

/**
 * Warmup exercise for Pingball.
 * Starts a simple board with a bouncing ball.
 */
public class Main {
    /**
     * Start the board.
     */
    public static void main(String[] args) {
        // make a board and run it
        Angle velocityAngle = new Angle(1.2);
        double velocityMagnitude = 50; //L/s
        Vect initialBallPosition = new Vect(10,10);
        Vect initialBallVelocity = new Vect(velocityAngle, velocityMagnitude);

        WarmupBoard newBoard = new WarmupBoard(20, 20, initialBallPosition, initialBallVelocity);
        newBoard.run();
    }

}
