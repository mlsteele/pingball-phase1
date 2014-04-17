package client.gadgets;

import java.util.ArrayList;
import java.util.List;

import common.Constants;
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
 *
 * A right flipper must be created for every left flipper and vice versa. When a flipper is first
 * triggered, it sweeps 90 degrees in the direction according to its position (left or right).
 * If triggered again, the flipper sweeps back 90 degrees to the initial position
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
    private final Constants.flipperType type;
    private List<LineSegment> geometry;
    private boolean rotated;

    /**
     * Flipper constructor that initializes a Flipper object according to its rotation.
     *
     * @param name unique String identifier for Flipper object
     * @param geometry list LineSegments (probably one) that describe and enclose the Flipper. The first LineSegment
     * listed in geometry must begin at the origin point (around which the Flipper rotates).
     */
    public Flipper(String name, Constants.flipperType type, Vect startingPoint, boolean rotated) {
        this.name = name;
        this.type = type;
        this.startingPoint = startingPoint;
        this.rotated = rotated; //rotated indicates that the flipper is horizontal
        List<LineSegment> geometry = new ArrayList<LineSegment>();
        geometry.add(setFlipperLine());

        if (!checkRep()){
            System.out.println("Error: rep invariant broken");
            System.exit(0);
        }
    }

    @Override
    /**
     * When physics' timeUntilWallCollision method detects that a ball from Board will hit the Flipper,
     * the flipper will rotate according to its type (left or right) and whether it is already flipped
     * or not. It may impart linear velocity to the Ball that hit it while it rotates
     * @param Ball object from Board
     */
    public BoardEvent handleBall(Ball ball) {
        for (LineSegment line : geometry){
            if (Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity()) < Constants.TIMESTEP) {
                double angularRotation = Constants.ANGULAR_ROTATION;
                if ((type == Constants.flipperType.LEFT && !rotated) || (type == Constants.flipperType.RIGHT && rotated)){
                    angularRotation *= -1; //from specs; counter-clockwise rotation
                }
                Vect velocity = Geometry.reflectRotatingWall(line, ball.getCircle().getCenter(), angularRotation, ball.getCircle(), ball.getVelocity(), 0.95);
                ball.setVelocity(velocity);
                ball.setPosition(ball.getCircle().getCenter().plus(velocity.times(Constants.TIMESTEP)));
                specialAction();
                return new BoardEvent(this);
            }
        }
        return null;
    }

    @Override
    /**
     *@return height if flipper is rotated, it is 2 squares in height
     *             if it is not rotated,  it is 1 square in height
     *             to be interpreted by caller
     */
    public int getHeight() {
        if (rotated){
            return 2;
        }else{
            return 1;
        }
    }

    @Override
    /**
     *@return width if flipper is rotated, it is 1 square in width
     *             if it is not rotated, it is 2 squares in width
     *             to be interpreted by caller
     */
    public int getWidth() {
        if (rotated){
            return 2;
        }else{
            return 1;
        }
    }

    @Override
    /**
     * Called during Board's step function. A horizontal flipper is represented by "--". A vertical
     * flipper is represented by two | bars directly on top of each other
     *
     * @return string representation of flipper for print out
     */
    public String stringRepresentation() {
        if (rotated){
            return " " + "|" + "\n" + " " + "|";
        }else{
            return "  " + "\n" + "--";
        }
    }

    @Override
    public void specialAction() {
        rotated = !rotated;
        geometry.remove(0);
        geometry.add(setFlipperLine());
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
     * @return boolean indicating whether the Flipper adheres to the rep invariant
     */
    public boolean checkRep() {
        //flippers must be allowed to rotate 2 Ls below their rotation point
        if (startingPoint.y() <= 0 || startingPoint.y() >= 18){
            return false;
        }
        if (startingPoint.x() >= 18 || startingPoint.x() <= 0){
            return false;
        }
        return true;
    }

    /**
     * @return LineSegment appropriate to the flipper's orientation and type.
     */
    public LineSegment setFlipperLine(){
        LineSegment flipperNew;

        if (!rotated && type == Constants.flipperType.LEFT){
            flipperNew = new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x(), startingPoint.y() + 2);
        } else if (!rotated && type == Constants.flipperType.RIGHT){
            flipperNew = new LineSegment(startingPoint.x() + 2, startingPoint.y(), startingPoint.x() + 2, startingPoint.y() + 2);
        } else {
            //Line Segment is the same for both types of rotated flippers
            flipperNew = new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + 2, startingPoint.y());
        }

        return flipperNew;

    }

}
