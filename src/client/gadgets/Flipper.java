package client.gadgets;

import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.BoardEvent;

/**
 * generally rectangular rotating shape with bounding box of size 2Lx2L
 * Trigger: generated whenever the ball hits it
 * Action: rotates 90 degrees with a .95 coefficient of reflection
 *
 * Must create a right flipper for every left flipper. Left rotates counter-
 * clockwise and right rotates clockwise.
 *
 * When a flipper is first triggered, it sweeps 90 degrees in the direction according to its
 * position (left or right). If triggered again, the flipper sweeps back 90 degrees to the initial position
 *
 * The flipper rotates at a constant angular velocity of 1080 degrees per second to
 * a position 90 degrees away from its starting position. When its action is triggered a
 * second time, the flipper rotates back to its original position at an angular velocity
 * of 1080 degrees per second.
 */

public class Flipper implements Gadget{
    private Vect startingPoint;
    private String name;
    private String type;
    private final List<LineSegment> geometry;
    private boolean rotated;

    /**
     * Constructor that dictates the position of the flipper.
     * The first LineSegment listed in geometry must begin at the origin point (position.x(), position.y())
     * of the bumper
     */
    private Flipper(String name, String type, List<LineSegment> geometry, boolean rotated) {
        this.name = name;
        this.type = type; //left or right
        startingPoint = geometry.get(0).p1();
        this.geometry = geometry;
        this.rotated = rotated; //rotated indicates that the flipper is horizontal
    }

    @Override
    public BoardEvent handleBall(Ball ball) {
        for (LineSegment line : geometry){
            if (Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity()) < TIMESTEP) {
                double angularRotation = ANGULAR_ROTATION;
                if (type == "left" && !rotated || type == "right" && rotated){
                    angularRotation *= -1; //from specs; counter-clockwise rotation
                }
                Vect velocity = Geometry.reflectRotatingWall(line, ball.getCircle().getCenter(), ANGULAR_ROTATION, ball.getCircle(), ball.getVelocity(), 0.95);
                ball.setVelocity(velocity);
                ball.setPosition(ball.getCircle().getCenter().plus(velocity.times(TIMESTEP)));
                rotated = !rotated;
                return new BoardEvent(this);
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        //if flipper is rotated, it is 1 in width and 2 in height, so we return 1
        //if it is not rotated, it is 2 in width and 1 in height, so we return 2
        //to be interpreted by caller
        //might be easier to just have a 'is rotated' function that could be more easily interpreted
        if (rotated){
            return 1;
        }else{
            return 2;
        }
    }

    @Override
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
    public Vect getPosition() {
        return startingPoint;
    }

    @Override
    public String getName() {
        return name;
    }

}
