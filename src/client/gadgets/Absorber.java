package client.gadgets;

import common.Constants;
import common.RepInvariantException;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import client.Ball;
import client.BoardEvent;

/**
 * Absorber is a mutable class that represents an object on the Pingball board
 * which simulates the ball-return mechanism familiar to us from pinball.
 *
 * A rectangle kL x mL where k and m are positive integers <= Constants.BOARD_WIDTH and Constants.BOARD_HEIGHT.
 *
 * Trigger: generated whenever the ball hits it
 * Special Action: shoots out a ball (if one is stored) at Constants.SHOOT_VELOCITY L/sec
 *
 * When a ball hits an absorber, the absorber stops the ball and holds it (unmoving)
 * in the bottom right-hand corner of the absorber. The ball's center is .25L from the
 * bottom of the absorber and .25L from the right side of the absorber.
 *
 * Rep invariant:
 * * geometry (the list) must have four lines.
 * * The four corners of the lineSegments must occur within the board
 *   (their x and y coordinates are less than or equal to Constants.BOARD_WIDTH/Constants.BOARD_HEIGHT and greater than or equal to 0)
 * * width must equal the width of the horizontal LineSegments
 * * height must equal the height of the vertical LineSegments
 * * startingPoint.x(), width, and (startingPoint.x() + width) must all be between 0 and Constants.BOARD_WIDTH
 * * startingPoint.y() , height, and (startingPoint.y() + height) must all be between 0 and Constants.BOARD_HEIGHT
 *
 * Thread Safety Argument:
 * * This class is not thread safe, and is always used in a confined environment.
 *
 */

public class Absorber implements Gadget {
    private final List<Ball> balls;
    private final int width;  //L
    private final int height; //L
    private final Vect startingPoint;
    private final String name;
    private final List<LineSegment> geometry;

    /**
     * Absorber constructor that initializes an Absorber object with no Ball objects contained. Absorber must
     * receive a handleBall() call in order to add Ball objects to 'balls' list.
     *
     * @param name unique String identifier for Absorber object
     * @param startingPoint upper left-hand corner coordinates for the Absorber.
     *      Requires 0 <= startingPoint.x() <= Constants.BOARD_WIDTH
     *      and      0 <= startingPoint.y() <= Constants.BOARD_HEIGHT
     * @param width width of Absorber, must be between 0 and BOARD_WIDTH
     * @param height height of Absorber, must be between 0 and BOARD_HEIGHT
     */
    public Absorber(String name, Vect startingPoint, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        this.startingPoint = startingPoint;
        balls = new ArrayList<Ball>();

        // Create list of LineSegments to represent Absorber boundary rectangle.
        geometry = new ArrayList<LineSegment>();
        geometry.add(new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + width, startingPoint.y()));
        geometry.add(new LineSegment(startingPoint.x() + width, startingPoint.y(), startingPoint.x() + width, startingPoint.y() + height));
        geometry.add(new LineSegment(startingPoint.x() + width, startingPoint.y() + height, startingPoint.x(), startingPoint.y() + height));
        geometry.add(new LineSegment(startingPoint.x(), startingPoint.y() + height, startingPoint.x(), startingPoint.y()));

        checkRep();
    }

    /**
     * When physics' timeUntilWallCollision method detects that a ball from Board will hit the Absorber,
     * the Absorber absorbs the ball and stores it .25L from its bottom and .25L from its right side. If
     * it contained another ball, it will shoot that ball up toward the board's ceiling.
     *
     * @param ball object from Board
     * @return BoardEvent if the ball collides with the absorber. Otherwise, returns null.
     */
    @Override
    public BoardEvent handleBall(Ball ball) {
        // Check for ball hit.
        for (LineSegment line : geometry) {
            double timeUntilCollision = Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity());
            if (timeUntilCollision <= Constants.TIMESTEP) {
                //position for ball according to specs (bottom right corner)
                Vect absorberBottom = new Vect(
                    this.getPosition().x() + width  - 0.25,
                    this.getPosition().y() + height - 0.25);
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
     * Called during Board's step function.
     * The top and bottom borders of the absorber are represented by "=" characters.
     * The side borders are represented by "|" characters.
     * For example, a 4x4 absorber would return:
     *      ====
     *      |  |
     *      |  |
     *      ====
     *
     * If there is a ball in the absorber, it will be printed in the bottom right corner like this:
     *      ====
     *      |  |
     *      |  |
     *      ===*
     *
     * @return string representation of absorber for print out
     */
    @Override
    public String stringRepresentation() {
        String stringRep = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (balls.size() > 0 && y == height-1 && x == width-1) {
                    // Draw a Ball at the bottom right if there is a ball loaded.
                    stringRep += "*";
                } else if (y == 0 || y == height-1) {
                    // Draw the top and bottom rows of the Absorber.
                    stringRep += "=";
                } else if (x == 0 || x == width-1) {
                    // Draw pipes on the left and right columns of the Absorber
                    stringRep += "|";
                } else {
                    // If we're inside the Absorber, leave the space empty.
                    stringRep += " ";
                }
            }
            stringRep += "\n";
        }
        return stringRep;
    }

    /**
     * If a ball is currently stored in the absorber, this
     * shoots out ball upwards at Constants.SHOOT_VELOCITY.
     */
    @Override
    public void specialAction() {
        if (balls.size() > 0){
            Ball newBall = balls.remove(0);
            Vect velocityAfterAbsorber = new Vect(0, -Constants.SHOOT_VELOCITY);
            newBall.setVelocity(velocityAfterAbsorber);
            newBall.setInPlay(true);
            Vect shootStart = new Vect(
                startingPoint.x() + width - 0.25,
                startingPoint.y() - 0.25);
            newBall.setPosition(shootStart);
            checkRep();
        }
    }

    /**
     * @return height
     */
    @Override
    public double getHeight(){
        return height;
    }

    /**
     * @return weight
     */
    @Override
    public double getWidth(){
        return width;
    }

    /**
     * @return startingPoint Vector representation of point at the top left corner of the absorber
     */
    @Override
    public Vect getPosition() {
        return startingPoint;
    }

    /**
     * @return name unique String that Absorber was initialized with
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Asserts the rep invariant by throwing a RepInvariantException if the rep invariant is violated
     *
     * Rep invariant:
     * * geometry (the list) must have four lines.
     * * The four corners of the lineSegments must occur within the board
     *   (their x and y coordinates are less than or equal to Constants.BOARD_WIDTH/Constants.BOARD_HEIGHT and greater than or equal to 0)
     * * width must equal the width of the horizontal LineSegments
     * * height must equal the height of the vertical LineSegments
     * * startingPoint.x(), width, and (startingPoint.x() + width) must all be between 0 and Constants.BOARD_WIDTH
     * * startingPoint.y() , height, and (startingPoint.y() + height) must all be between 0 and Constants.BOARD_HEIGHT
     *
     */
    public void checkRep(){

        if (geometry.size() != 4) {
            throw new RepInvariantException("There are not four sides!");
        }
        for (LineSegment line: geometry) {
            if (line.p1().x() < 0 || line.p1().x() > Constants.BOARD_WIDTH
                    || line.p1().y() < 0 || line.p1().y() > Constants.BOARD_HEIGHT
                    || line.p2().x() < 0 || line.p2().x() > Constants.BOARD_WIDTH
                    || line.p2().y() < 0 || line.p2().y() > Constants.BOARD_HEIGHT) {
                throw new RepInvariantException("The sides go outside the board!");
            }
            if (line.length() != width && line.length() != height)
                throw new RepInvariantException("A side is not width or height!");
        }
        if (width < 0 || width > Constants.BOARD_WIDTH)
            throw new RepInvariantException("width is invalid");
        if (height < 0 || height > Constants.BOARD_HEIGHT)
            throw new RepInvariantException("height is invalid");
        if (startingPoint.x() + width > Constants.BOARD_WIDTH)
            throw new RepInvariantException("The absorber goes too wide");
        if (startingPoint.y() + height > Constants.BOARD_HEIGHT)
            throw new RepInvariantException("The absorber goes too tall");
    }


    /**
     * How many balls are contained in this absorber?
     * @return the number of balls contained in the absorber
     */
    public int ballsContained(){
        return balls.size();
    }
}
