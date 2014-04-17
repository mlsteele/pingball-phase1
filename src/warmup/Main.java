package warmup;
import java.util.List;
import physics.*;

/**
 * TODO: put documentation for your class here
 */
public class Main {

    /**
     * TODO: describe your main function's command line arguments here
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