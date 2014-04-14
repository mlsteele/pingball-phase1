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
 */
public class StaticBumper implements Gadget {
    private Vect startingPoint;
    private String name;
    private String type;
    private final List<LineSegment> geometry;

    /**
     * Constructor that takes the shape of the bumper.
     * The first LineSegment listed in geometry must begin at the origin point (position.x(), position.y())
     * of the bumper
     */
    private StaticBumper(String name, String type, List<LineSegment> geometry) {
        this.name = name;
        this.type = type;
        startingPoint = geometry.get(0).p1();
        this.geometry = geometry;
    }

    /**
     * Factory method for creating SquareBumpers
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
    public int getSize() {
        return 1;
    }

    @Override
    public Vect getPosition() {
        return startingPoint;
    }

    @Override
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
    public String getName() {
        return name;
    }

}
