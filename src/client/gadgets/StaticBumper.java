package client.gadgets;
import common.Constants;
import common.RepInvariantException;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
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
    private final Constants.BumperType type;
    private List<LineSegment> geometry;
    private final boolean isCircle;
    private int hits;

    /**
     * Constructor that indicates the shape and starting point of the bumper.
     *
     * @param name unique String identifier for this bumper
     * @param type BumperType representing what kind of bumper this is. Used for getting stringRepresentation later
     *        TRIDOWN = triangle bumper with initial orientation 90 || 270
     *        TRIUP   = triangle bumper with initial orientation  0 || 180
     * @param startingPoint upper left-hand corner coordinates for the bumper
     */
    public StaticBumper(String name, Constants.BumperType type, Vect startingPoint) {
        this.name = name;
        this.type = type;
        this.startingPoint = startingPoint;
        this.hits = 0; //number of times Ball has hit it
        geometry = new ArrayList<LineSegment>();

        if (type == Constants.BumperType.TRIDOWN){
            this.isCircle = false;
            //orientation == 90 | 270
            geometry.add(new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + 1, startingPoint.y() + 1));
        } else if (type == Constants.BumperType.TRIUP){
            this.isCircle = false;
            //orientation == 0 | 180
            geometry.add(new LineSegment(startingPoint.x(), startingPoint.y() + 1, startingPoint.x() + 1, startingPoint.y()));
        } else if (type == Constants.BumperType.SQUARE) {
            this.isCircle = false;
            //circle or square bumper
            geometry.add(new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + 1, startingPoint.y()));
            geometry.add(new LineSegment(startingPoint.x() + 1, startingPoint.y(), startingPoint.x() + 1, startingPoint.y() + 1));
            geometry.add(new LineSegment(startingPoint.x() + 1, startingPoint.y() + 1, startingPoint.x(), startingPoint.y() + 1));
            geometry.add(new LineSegment(startingPoint.x(), startingPoint.y() + 1, startingPoint.x(), startingPoint.y()));
        } else {
            // circle bumper
            isCircle = true;
        }
    }

    @Override
    /**
     * Reflect a ball off of the bumper if there is a collision.
     *
     * @param Ball object from Board
     * @return a BoardEvent if a collision occurred, otherwise null
     */
    public BoardEvent handleBall(Ball ball) {
        if (isCircle) {
            Circle bumperCircle = new Circle(startingPoint, Constants.DEFAULT_CIRCLE_BUMPER_RADIUS);
            if (Geometry.timeUntilCircleCollision(bumperCircle, ball.getCircle(), ball.getVelocity()) <= Constants.TIMESTEP) {
                hits ++;
                Vect velocity = Geometry.reflectCircle(bumperCircle.getCenter(), ball.getCircle().getCenter(), ball.getVelocity());
                ball.setVelocity(velocity);
                return new BoardEvent(this);
            }
        } else {
            for (LineSegment line : geometry){
                if (Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity()) <= Constants.TIMESTEP) {
                    hits ++;
                    // TODO what does this mean (below)?
                    //options to make more sensitive: increase radius of ball, increase timestep multiplication,
                    //go based on center instead of Geometry, accelerate by gravity
                    Vect velocity = Geometry.reflectWall(line, ball.getVelocity());
                    ball.setVelocity(velocity);
                    return new BoardEvent(this);
                }
            }
        }
        return null;
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
        if (type == Constants.BumperType.SQUARE){
            repString = "#";
        } else if (type == Constants.BumperType.TRIUP){
            repString = "/";
        } else if (type == Constants.BumperType.TRIDOWN){
            repString = "\\";
        } else if (type == Constants.BumperType.CIRCLE){
            repString = "O";
        }
        return repString;
    }

    @Override
    /**
     * Bumpers perform no special action, so this initiates nothing
     */
    public void specialAction() {

    }

    /**
     * @return 1 height of bumper is always 1
     */
    public double getHeight(){
        return 1;
    }

    /**
     * @return 1 width of bumper is always 1
     */
    public double getWidth(){
        return 1;
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
     */
    public void checkRep(){
        if(startingPoint.x() + 1 >= Constants.BOARD_WIDTH ||
                startingPoint.y() + 1 >= Constants.BOARD_HEIGHT ||
                startingPoint.x() < 0 ||
                startingPoint.y() < 0){
                throw new RepInvariantException("Rep invariant violated.");
            }
        throw new RepInvariantException("Rep invariant violated.");
    }

    public int getHits(){
        return hits;
    }


}
