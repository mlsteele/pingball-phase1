package client.gadgets;
import common.Constants;
import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.BoardEvent;

/**
 * StaticBumper is a stationary geometric bumper. They are composed of
 * LineSegments and will reflect the ball at its same velocity when it hits a lineSegment
 * Could by any of SquareBumper, CircularBumper, TriangularBumper.
 *
 * Rep invariant: bumper must have a position within the board's boundaries
 *
 * Thread Safety Argument: all Gadgets on a Board will be confined to only one Client thread.
 */
public class StaticBumper implements Gadget {
    private final Vect startingPoint;
    private final String name;
    private final Constants.bumperType type;
    private List<LineSegment> geometry;

    /**
     * Constructor that indicates the shape and starting point of the bumper.
     *
     * @param name unique String identifier for this bumper
     * @param type bumperType representing what kind of bumper this is. Used for getting stringRepresentation later
     * @param geometry List of line segments that ball can reflect off of. The first LineSegment listed in geometry
     * must begin at the origin point (position.x(), position.y()) of the bumper
     */
    public StaticBumper(String name, Constants.bumperType type, Vect startingPoint) {
        this.name = name;
        this.type = type;
        this.startingPoint = startingPoint;
        List<LineSegment>geometry = new ArrayList<LineSegment>();

        if (type == Constants.bumperType.TRIDOWN){
            //orientation == 90 | 270
            geometry.add(new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + 1, startingPoint.y() - 1));
        } else if (type == Constants.bumperType.TRIUP){
            //orientation == 0 | 180
            geometry.add(new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + 1, startingPoint.y() + 1));
        }else{
            //circle or square bumper
            geometry.add(new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + 1, startingPoint.y()));
            geometry.add(new LineSegment(startingPoint.x() + 1, startingPoint.y(), startingPoint.x() + 1, startingPoint.y() + 1));
            geometry.add(new LineSegment(startingPoint.x() + 1, startingPoint.y() + 1, startingPoint.x(), startingPoint.y() + 1));
            geometry.add(new LineSegment(startingPoint.x(), startingPoint.y() + 1, startingPoint.x(), startingPoint.y()));
        }

    }

    @Override
    /**
     * When physics' timeUntilWallCollision method detects that a ball from Board will hit the bumper,
     * the bumper will reflect the ball off of the appropriate lineSegment
     *
     * @param Ball object from Board
     */
    public BoardEvent handleBall(Ball ball) {
        for (LineSegment line : geometry){
            if (Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity()) < Constants.TIMESTEP) {
                Vect velocity = Geometry.reflectWall(line, ball.getVelocity());
                ball.setVelocity(velocity);
                ball.setPosition(ball.getCircle().getCenter().plus(velocity.times(Constants.TIMESTEP)));
                return new BoardEvent(this);
            }
        }

        return null; //type + " Bumper " + name + " handled ball.";
    }

    @Override
    /**
     * @return 1; all bumpers take up only one square on a board
     */
    public int getWidth() {
        return 1;
    }

    @Override
    /**
     * @return 1; all bumpers take up only one square on a board
     */
    public int getHeight() {
        return 1;
    }

    @Override
    /**
     * @return startingPoint Vector representation of point at the top left corner of the bumper
     */
    public Vect getPosition() {
        return startingPoint;
    }

    @Override
    /**
     * Called during Board's step function. Each type of bumper has a different
     * representation. Square = "#"; Triangle = "/" or "\", Circle = "0"
     *
     * @return string representation of absorber for print out
     */
    public String stringRepresentation() {
        String repString = "";
        if (type == Constants.bumperType.SQUARE){
            repString = "#";
        } else if (type == Constants.bumperType.TRIUP){
            repString = "/";
        } else if (type == Constants.bumperType.TRIDOWN){
            repString = "\\";
        } else if (type == Constants.bumperType.CIRCLE){
            repString = "0";
        }
        return repString;
    }

    @Override
    /**
     * Bumpers perform no special action, so this initiates nothing
     */
    public void specialAction() {

    }

    @Override
    /**
     * @return unique String representation of bumper
     */
    public String getName() {
        return name;
    }


    /**
     * Rep invariant: bumper must have a position within the board's boundaries
     * @return boolean indicating whether the Bumper adheres to the rep invariant
     */
    public boolean checkRep(){
        //TODO: implement checkRep
        return true;
    }

}
