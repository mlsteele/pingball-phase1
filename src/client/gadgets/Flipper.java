package client.gadgets;

import java.util.ArrayList;
import java.util.List;

import common.Constants;
import common.RepInvariantException;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.BoardEvent;

/**
 * Flipper is a mutable class that represents an object on the Pingball board
 * that rotates 90 degrees when triggered.
 *
 * Rectangular rotating shape with bounding box of size 2Lx2L
 * Trigger: generated whenever the ball hits it
 * Action: rotates 90 degrees with a .95 coefficient of reflection
 *
 * A right flipper must be created for every left flipper and vice versa. When a flipper is first
 * triggered, it sweeps 90 degrees in the direction according to its position (left or right).
 * If triggered again, the flipper sweeps back 90 degrees to the initial position
 *
 * As strings, flippers are considered a 2x2 character square with one line filled in depending on
 * orientation and rotation boolean.
 *
 * The flipper rotates at a constant angular velocity of 1080 degrees per second to
 * a position 90 degrees away from its starting position.
 *
 * Rep invariant:
 * * the 2Lx2L bounding box of the flipper must be entirely contained within the board
 *
 * Thread Safety Argument:
 * * This class is not thread safe, and is always used in a confined environment.
 *
 */
public class Flipper implements Gadget{
    private final Vect startingPoint;
    private final String name;
    private final Constants.FlipperType type;
    private List<LineSegment> geometry;
    private boolean rotated;
    private int orientation;

    /**
     * Flipper constructor that initializes a Flipper object according to its rotation.
     *
     * @param name unique String identifier for Flipper object
     * @param startingPoint upper left-hand corner coordinates for the flipper. Coordinates must be between 0 and BOARD_HEIGHT/BOARD_WIDTH
     * @param orientation either 0, 90, 180, or 270. Orientation indicates the degree
     * on the unit circle that you want to draw a line to from the center of the unit circle.
     * That line is your flipper's 'unflipped', !rotated position
     * @param type RIGHT (clockwise rotation) or LEFT (counter-clockwise rotation)
     */
    public Flipper(String name, Vect startingPoint, int orientation, Constants.FlipperType type) {
        this.name = name;
        this.type = type;
        this.startingPoint = startingPoint;
        this.rotated = false;
        this.orientation = orientation;
        geometry = new ArrayList<LineSegment>();
        geometry.add(setFlipperLine());
        checkRep();
    }

    /**
     * When physics' timeUntilWallCollision method detects that a ball from Board will hit the Flipper,
     * the flipper will rotate according to its type (left or right) and whether it is already flipped
     * or not. It may impart linear velocity to the Ball that hit it while it rotates
     *
     * @param Ball object from Board
     * @return a BoardEvent if the ball hit the flipper. Else returns null
     */
    @Override
    public BoardEvent handleBall(Ball ball) {
        for (LineSegment line : geometry){
            double angularRotation = Constants.ANGULAR_ROTATION;
            if ((type == Constants.FlipperType.LEFT && !rotated) || (type == Constants.FlipperType.RIGHT && rotated)){
                angularRotation *= -1; //from specs; counter-clockwise rotation
            }
            if (Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity()) < Constants.TIMESTEP) {
                Vect velocity = Geometry.reflectRotatingWall(line, ball.getCircle().getCenter(), angularRotation, ball.getCircle(), ball.getVelocity(), 0.95);
                ball.setVelocity(velocity);
                return new BoardEvent(this);
            }
        }
        return null;
    }

    /**
     * Called during Board's step function.
     * A horizontal flipper:
     *    --
     *
     * A vertical flipper:
     *    |
     *    |
     *
     * @return string representation of flipper for print out
     */
    @Override
    public String stringRepresentation() {
        if (((type == Constants.FlipperType.LEFT) && ((orientation ==  0 && !rotated) || (orientation ==  270 && rotated))) ||
                (((type == Constants.FlipperType.RIGHT) && ((orientation ==  180 && !rotated) || (orientation ==  270 && rotated))))){
            return "|" + " " + "\n" + "|" + " ";
        }else if (((type == Constants.FlipperType.LEFT) && ((orientation ==  270 && !rotated) || (orientation ==  180 && rotated))) ||
                (((type == Constants.FlipperType.RIGHT) && ((orientation ==  90 && !rotated) || (orientation ==  180 && rotated))))){
            return "  " + "\n" + "--";
        } else if(((type == Constants.FlipperType.LEFT) && ((orientation ==  180 && !rotated) || (orientation ==  90 && rotated))) ||
                (((type == Constants.FlipperType.RIGHT) && ((orientation ==  0 && !rotated) || (orientation ==  90 && rotated))))){
            return " " + "|" + "\n" + " " + "|";
        } else{
            return "--" + "\n" + "  ";
        }
    }

    /**
     * The flipper rotates
     */
    @Override
    public void specialAction() {
        rotated = !rotated;
        geometry.remove(0);
        geometry.add(setFlipperLine());
    }

    /**
     * @return 2 height of flipper is always 2
     */
    @Override
    public double getHeight(){
        return 2;
    }

    /**
     * @return 2 width of flipper is always 2
     */
    @Override
    public double getWidth(){
        return 2;
    }

    /**
     * @return startingPoint Vector representation of point at the top left corner of the flipper
     */
    @Override
    public Vect getPosition() {
        return startingPoint;
    }

    /**
     * @return name unique String that Flipper was initialized with
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Rep invariant:
     * * the 2Lx2L bounding box of the flipper must be entirely contained within the board
     */
    public void checkRep() {
        if(startingPoint.x() + 2 >= Constants.BOARD_WIDTH ||
            startingPoint.y() + 2 >= Constants.BOARD_HEIGHT ||
            startingPoint.x() < 0 ||
            startingPoint.y() < 0){
            throw new RepInvariantException("The flipper is outside the bounds of the board.");
        }
    }

    /**
     * @return LineSegment appropriate to the flipper's orientation and type.
     */
    public LineSegment setFlipperLine(){
        int flipLength = 2;
        LineSegment flipperNew;

        if (((type == Constants.FlipperType.LEFT) && ((orientation ==  0 && !rotated) || (orientation ==  270 && rotated))) ||
                (((type == Constants.FlipperType.RIGHT) && ((orientation ==  180 && !rotated) || (orientation ==  270 && rotated))))){
            flipperNew = new LineSegment(startingPoint.x() + flipLength, startingPoint.y(), startingPoint.x() + flipLength, startingPoint.y() + flipLength);
        }else if (((type == Constants.FlipperType.LEFT) && ((orientation ==  270 && !rotated) || (orientation ==  180 && rotated))) ||
                (((type == Constants.FlipperType.RIGHT) && ((orientation ==  90 && !rotated) || (orientation ==  180 && rotated))))){
            flipperNew = new LineSegment(startingPoint.x(), startingPoint.y() + flipLength, startingPoint.x() + flipLength, startingPoint.y() + flipLength);
        } else if(((type == Constants.FlipperType.LEFT) && ((orientation ==  180 && !rotated) || (orientation ==  90 && rotated))) ||
                (((type == Constants.FlipperType.RIGHT) && ((orientation ==  0 && !rotated) || (orientation ==  90 && rotated))))){
            flipperNew = new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x(), startingPoint.y() + flipLength);
        } else{
            flipperNew = new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + flipLength, startingPoint.y() + 2);
        }
        return flipperNew;

    }

    /**
     * @return true if the flipper is rotated 90 degrees from its starting position
     */
    public boolean isRotated(){
        return rotated;
    }

}
