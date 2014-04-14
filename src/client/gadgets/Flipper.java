package client.gadgets;

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
    private final String type;
    private final List<LineSegment> geometry;
    private boolean rotated;

    /**
     * Constructor that dictates the position of the flipper.
     * @param name unique String identifier for Flipper object
     * @param geometry list LineSegments (probably one) that describe and enclose the Flipper. The first LineSegment
     * listed in geometry must begin at the origin point (around which the Flipper rotates).
     */
    public Flipper(String name, String type, List<LineSegment> geometry, boolean rotated) {
        this.name = name;
        this.type = type; //left or right; I'll make this an enum soon
        startingPoint = geometry.get(0).p1();
        this.geometry = geometry;
        this.rotated = rotated; //rotated indicates that the flipper is horizontal

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
                if (type == "left" && !rotated || type == "right" && rotated){
                    angularRotation *= -1; //from specs; counter-clockwise rotation
                }
                Vect velocity = Geometry.reflectRotatingWall(line, ball.getCircle().getCenter(), Constants.ANGULAR_ROTATION, ball.getCircle(), ball.getVelocity(), 0.95);
                ball.setVelocity(velocity);
                ball.setPosition(ball.getCircle().getCenter().plus(velocity.times(Constants.TIMESTEP)));
                rotated = !rotated;
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
            return "|" + "\n" + "|";
        }else{
            return "--";
        }
    }

    @Override
    public void specialAction() {
        // already did flipper rotation in handleBall...
    }

    @Override
    /**
     * @return startingPoint Vector representation of point at the top left corner of the absorber
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
        //flippers must be allowed to rotate 2 below their rotation point
        if (startingPoint.y() <= 0 || startingPoint.y() >= 18){
            return false;
        }
        //check safety of left flipper
        if (type == "left" && (startingPoint.x() >= 18 || startingPoint.x() <= 0)){
            return false;
        }
        //check safety of right flipper
        if (type == "right" && (startingPoint.x() >= 20 || startingPoint.x() <= 2)){
            return false;
        }
        return true;
    }

}
