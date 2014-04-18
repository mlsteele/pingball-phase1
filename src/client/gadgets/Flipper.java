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
 * that rotates 90 degrees when struck by a ball.
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
 * Rep invariant: starting position must allow full rotation within board boundaries
 *
 * Thread Safety Argument: all Gadgets on a Board will be confined to only one Client thread.
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
     * @param startingPoint upper left-hand corner coordinates for the flipper.
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

    @Override
    /** type (left or right) and whether it is already flipped
     * or not. It may impart linear velocity to the Ball that hit it while it rotates
     * @param Ball object from Board
     */
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

    @Override
    /**
     * Called during Board's step function. A horizontal flipper is represented by "--". A vertical
     * flipper is represented by two | bars directly on top of each other
     *
     * @return string representation of flipper for print out
     */
    public String stringRepresentation() {
        /**
         * TODO: fix here to be RFC and non-repetitive
         * I want this to not be the worst but right now I want it to work more,
         * so it will be the worst until other things work
         *
        List<String> leftPivot = new ArrayList<String>();
        leftPivot.add("|" + " " + "\n" + "|" + " ");
        leftPivot.add("  " + "\n" + "--");
        leftPivot.add(" " + "/" + "\n" + "/" + " ");
        List<String> rightPivot = new ArrayList<String>();
        rightPivot.add(" " + "|" + "\n" + " " + "|");
        rightPivot.add("--" + "\n" + "  ");
        rightPivot.add("\\" + " " + "\n" + " " + "\\");
         */

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

    @Override
    public void specialAction() {
        rotated = !rotated;
        geometry.remove(0);
        geometry.add(setFlipperLine());
    }

    /**
     * @return 2 height of flipper is always 2
     */
    public double getHeight(){
        return 2;
    }

    /**
     * @return 2 width of flipper is always 2
     */
    public double getWidth(){
        return 2;
    }

    @Override
    /**
     * @return startingPoint Vector representation of point at the top left corner of the flipper
     */
    public Vect getPosition() {
        return startingPoint;
    }

    @Override
    /**
     * @return name unique String Absorber was initialized with
     */
    public String getName() {
        return name;
    }

    /**
     * Rep invariant: starting position of Flipper must allow full rotation within board boundaries
     */
    public void checkRep() {
        //flippers must be allowed to rotate 2 Ls below their rotation point
        if(startingPoint.x() + 2 >= Constants.BOARD_WIDTH ||
            startingPoint.y() + 2 >= Constants.BOARD_HEIGHT ||
            startingPoint.x() < 0 ||
            startingPoint.y() < 0){
            throw new RepInvariantException("Rep invariant violated.");
        }
    }

    /**
     * @return LineSegment appropriate to the flipper's orientation and type.
     */
    public LineSegment setFlipperLine(){
        //TODO: fix here to be RFC and non-repetitive
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

    public boolean isRotated(){
        return rotated;
    }

}
