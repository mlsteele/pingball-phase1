package client.gadgets;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.BoardEvent;

/**
 * StaticBumper is a stationary geometric bumper.
 * Could by any of SquareBumper, CircularBumper, TriangularBumper.
 *
 * Rep invariant: bumper must have a position within the board's boundaries
 *
 * Thread safety: All elements are final and confined.
 */
public class StaticBumper implements Gadget {
    private final Vect startingPoint;
    private final String name;
    private final String type;
    private final List<LineSegment> geometry;

    /**
     * Constructor that indicates the shape of the bumper.
     *
     * @param name unique String identifier for this bumper
     * @param type String representing what kind of bumper this is. Used for getting stringRepresentation later
     * @param geometry List of line segments that ball can reflect off of. The first LineSegment listed in geometry
     * must begin at the origin point (position.x(), position.y()) of the bumper
     */
    private StaticBumper(String name, String type, List<LineSegment> geometry) {
        this.name = name;
        this.type = type;
        startingPoint = geometry.get(0).p1();
        this.geometry = geometry;
    }

    /**
     * Factory method for creating SquareBumpers
     * @param name unique String identifier for this bumper
     * @param position starting point (upper left corner) of bumper
     */
    public static StaticBumper createSquareBumper(String name, Vect position) {
        LineSegment top = new LineSegment(position.x(), position.y(), position.x() + 1, position.y());
        LineSegment bottom = new LineSegment(position.x(), position.y() + 1, position.x() + 1, position.y() + 1);
        LineSegment left = new LineSegment(position.x(), position.y(), position.x(), position.y() - 1);
        LineSegment right = new LineSegment(position.x() + 1, position.y(), position.x() + 1, position.y() - 1);

        List<LineSegment> squareBumper = new ArrayList<LineSegment>();
        squareBumper.add(top);
        squareBumper.add(bottom);
        squareBumper.add(left);
        squareBumper.add(right);

        return new StaticBumper(name, "Square", squareBumper);
    }

    /**
     * Factory method for creating TriangularBumpers
     * @param name unique String identifier for this bumper
     * @param position starting point (upper left corner) of bumper
     */
    public static StaticBumper createTriangularBumper(String name, Vect position, int orientation) {
        //TriUp v. TriDown distinguishes between / and \ respectively
        if (orientation == 0 || orientation == 180){
            LineSegment Triangle = new LineSegment(position.x(), position.y(), position.x() + 1, position.y() + 1);
            List<LineSegment> triBumper = new ArrayList<LineSegment>();
            triBumper.add(Triangle);
            return new StaticBumper(name, "TriUp", triBumper);
        } else{
            LineSegment Triangle = new LineSegment(position.x(), position.y(), position.x() + 1, position.y() - 1);
            List<LineSegment> triBumper = new ArrayList<LineSegment>();
            triBumper.add(Triangle);
            return new StaticBumper(name, "TriDown", triBumper);
        }

    }

    /**
     * Factory method for creating CircularBumpers
     * @param name unique String identifier for this bumper
     * @param position starting point (upper left corner) of bumper
     */
    public static StaticBumper createCircularBumper(String name, Vect position) {
        LineSegment top = new LineSegment(position.x(), position.y(), position.x() + 1, position.y());
        LineSegment bottom = new LineSegment(position.x(), position.y() + 1, position.x() + 1, position.y() + 1);
        LineSegment left = new LineSegment(position.x(), position.y(), position.x(), position.y() - 1);
        LineSegment right = new LineSegment(position.x() + 1, position.y(), position.x() + 1, position.y() - 1);

        List<LineSegment> circleBumper = new ArrayList<LineSegment>();
        circleBumper.add(top);
        circleBumper.add(bottom);
        circleBumper.add(left);
        circleBumper.add(right);

        return new StaticBumper(name, "circle", circleBumper);
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
            if (Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity()) < TIMESTEP) {
                Vect velocity = Geometry.reflectWall(line, ball.getVelocity());
                ball.setVelocity(velocity);
                ball.setPosition(ball.getCircle().getCenter().plus(velocity.times(TIMESTEP)));
                return new BoardEvent(this);
            }
        }

        return null; //type + " Bumper " + name + " handled ball.";
    }

    @Override
    /**
     * @return 1; all bumpers take up only one square on a board
     */
    public int getSize() {
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
        if (type == "Square"){
            repString = "#";
        } else if (type == "TriUp"){
            repString = "/";
        } else if (type == "TriDown"){
            repString = "\\";
        } else if (type == "Circle"){
            repString = "0";
        }
        return repString;
    }

    @Override
    public void specialAction() {

    }

    @Override
    /**
     * @return unique String representation of bumper
     */
    public String getName() {
        return name;
    }

}
