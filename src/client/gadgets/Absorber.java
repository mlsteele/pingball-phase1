package client.gadgets;
import common.Constants;
import common.RepInvariantException;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.Board;
import client.BoardEvent;
import common.Constants;

/**
 * Absorber is a mutable class that represents an object on the Pingball board
 * which simulates the ball-return mechanism familiar to us from pinball.
 *
 * A rectangle kL x mL where k and m are positive integers <= 20.
 * Trigger: generated whenever the ball hits it
 * Special Action: shoots out a ball (if one is stored) at velocity 50L/sec
 *
 * When a ball hits an absorber, the absorber stops the ball and holds it (unmoving)
 * in the bottom right-hand corner of the absorber. The ball's center is .25L from the
 * bottom of the absorber and .25L from the right side of the absorber.
 *
 * Rep invariant: geometry (the list) must have four lines. The four corners of the lineSegments
 * must occur within the board (their x and y coordinates are less than or equal to 20 and greater than or equal to 0)
 *
 * Thread Safety Argument: all Gadgets on a Board will be confined to only one Client thread.
 */

public class Absorber implements Gadget {
    private final List<Ball> balls;
    private final int width;  //L
    private final int height; //L
    private final Vect startingPoint;
    private final double gravity;
    private final String name;
    private final List<LineSegment> geometry;

    /**
     * Absorber constructor that initializes an Absorber object with no Ball objects contained. Absorber must
     * receive a handleBall() call in order to add Ball objects to 'balls' list.
     *
     * @param name unique String identifier for Absorber object
     * @param startingPoint upper left-hand corner coordinates for the Absorber
     * @param width width of Absorber, must be less than BOARD_WIDTH
     * @param height height of Absorber, must less than BOARD_HEIGHT
     * @param gravity magnitude of gravity of the board the Absorber is on.
     */
    public Absorber(String name, Vect startingPoint, int width, int height, double gravity){
        this.name = name;
        this.width = width;
        this.height = height;
        this.startingPoint = startingPoint;
        this.gravity = gravity;
        balls = new ArrayList<Ball>();

        // Create list of LineSegments to represent Absorber boundary rectangle.
        geometry = new ArrayList<LineSegment>();
        geometry.add(new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + width, startingPoint.y()));
        geometry.add(new LineSegment(startingPoint.x() + width, startingPoint.y(), startingPoint.x() + width, startingPoint.y() + height));
        geometry.add(new LineSegment(startingPoint.x() + width, startingPoint.y() + height, startingPoint.x(), startingPoint.y() + height));
        geometry.add(new LineSegment(startingPoint.x(), startingPoint.y() + height, startingPoint.x(), startingPoint.y()));

        checkRep();
    }

    @Override
    /**
     * When physics' timeUntilWallCollision method detects that a ball from Board will hit the Absorber,
     * the Absorber absorbs the ball and stores it .25L from its bottom and .25L from its right side. If
     * it contained another ball, it will shoot that ball up toward the board's ceiling.
     *
     * @param ball object from Board
     */
    public BoardEvent handleBall(Ball ball) {
        // Check for ball hit.
        for (LineSegment line : geometry) {
            double timeUntilCollision = Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity());
            if (timeUntilCollision <= Constants.TIMESTEP) {
                //position for ball according to specs (bottom right corner)
                Vect absorberBottom = new Vect(this.getPosition().x() + width - (1) -
                        0.25, this.getPosition().y() - height + .25);
                //places ball in the right place and updates contained information
                ball.setVelocity(new Vect(0, 0));
                ball.setPosition(absorberBottom);
                ball.setInPlay(false);
                balls.add(ball);
                checkRep();
                return new BoardEvent(this);
            }
        }
        return null;
    }

    /**
     * Called during Board's step function. Each L unit of width for an absorber is represented
     * by "=". The two side walls are represented by | bars on the left and right boundaries.
     * @return string representation of absorber for print out
     */
    @Override
    public String stringRepresentation() {
        String absorber = "";
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (i == 0){ //top and bottom rows of absorber
                    absorber += "=";
                } else if(i == height-2){
                    if (j == 0 || j == width-1){
                        absorber += "|";
                    } else if (j > width-2-balls.size()){
                        absorber += "*";
                    } else{
                        absorber += " ";
                    }
                }
                else{
                    if (j == 0 || j == width-1){
                        absorber += "|";
                    } else{
                        absorber += " ";
                    }
                }
            }
            absorber += "\n";
        }
        return absorber;
    }

    @Override
    /**
     * Shoots out ball if a ball is contained. Triggered only by Board.
     */
    public void specialAction() {
        if (balls.size() > 0){
            Ball newBall = balls.remove(0);
            //change velocity for time ball spends in absorber
            double velocityAfterAbsorberMag = -1.0*Math.sqrt((Constants.SHOOT_VELOCITY*Constants.SHOOT_VELOCITY + (2*gravity*height)));
            Vect velocityAfterAbsorber = new Vect(0, velocityAfterAbsorberMag);
            newBall.setVelocity(velocityAfterAbsorber);
            //TODO: add ball to Board
            newBall.setInPlay(true);
            Vect shootStart = new Vect(startingPoint.x() + width - 0.25, startingPoint.y() - 0.25);
            newBall.setPosition(shootStart);
            checkRep();
        }
    }

    /**
     * @return height
     */
    public double getHeight(){
        return height;
    }

    /**
     * @return weight
     */
    public double getWidth(){
        return width;
    }

    /**
     * @return startingPoint Vector representation of point at the top left corner of the absorber
     */
    public Vect getPosition() {
        return startingPoint;
    }

    /**
     * @return name unique String Absorber was initialized with
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Rep invariant: The four corners of the Absorber must occur within
     * the board (their x and y coordinates are less than or equal to BOARD_HEIGHT/WIDTH
     * and greater than or equal to 0)
     *
     * @return boolean indicating whether the Absorber adheres to the rep invariant
     */
    public void checkRep(){
        if (startingPoint.x() + width > Constants.BOARD_WIDTH ||
                startingPoint.y() + height > Constants.BOARD_HEIGHT ||
                startingPoint.x() < 0 ||
                startingPoint.y() < 0) {
            throw new RepInvariantException("Rep invariant violated. "
                + startingPoint.x() + " + " + width + " >=" + Constants.BOARD_WIDTH + " or "
                + startingPoint.y() + " + " + height + " >=" + Constants.BOARD_HEIGHT);
        }
    }

    public int ballsContained(){
        return balls.size();
    }
}
