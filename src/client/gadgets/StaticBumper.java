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
 * StaticBumper is a mutable class representing a stationary geometric bumper.
 * It can be square, circular, or triangular.
 *
 * Rep invariant:
 * * bumper must have a position within the board's boundaries
 *
 * Thread Safety Argument:
 * * This is not a thread safe data type; StaticBumper must be confined to one thread.
 */
public class StaticBumper implements Gadget {
    private final Vect startingPoint;
    private final String name;
    private final Constants.BumperType type;
    private List<LineSegment> geometry;
    private final boolean isCircle;
    private int hits;

    /**
     * Create a new StaticBumper of the given type.
     *
     * @param name unique String identifier for this bumper
     * @param type BumperType representing what kind of bumper this is. Used for getting stringRepresentation later
     *        TRIDOWN = triangle bumper with initial orientation 90 || 270
     *        TRIUP   = triangle bumper with initial orientation  0 || 180
     *        SQUARE  = square bumper
     *        CIRCLE  = circle bumper
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
            geometry.add(new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + 1, startingPoint.y()));
            geometry.add(new LineSegment(startingPoint.x() + 1, startingPoint.y(), startingPoint.x() + 1, startingPoint.y() + 1));
            geometry.add(new LineSegment(startingPoint.x() + 1, startingPoint.y() + 1, startingPoint.x(), startingPoint.y() + 1));
            geometry.add(new LineSegment(startingPoint.x(), startingPoint.y() + 1, startingPoint.x(), startingPoint.y()));
        } else {
            // circle bumper
            isCircle = true;
        }

        checkRep();
    }

    /**
     * Reflect a ball off of the bumper if there is a collision.
     *
     * @param ball the ball that we want to handle
     * @return a BoardEvent if a collision occurred, otherwise null
     */
    @Override
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
                 /* Create a circle which acts as an identical proxy for the ball
                  * except with a radius of zero.
                  * This is to fix the bug in timeUntilWallCollision whereby circles
                  * are not said to collide with lines when all of the circle would
                  * not collide with the wall.
                  * The ball itself is not changed and the proxy is used only for
                  * detecting an impending collision.
                  */
                Circle babyBall = new Circle(ball.getCircle().getCenter(), .05);
                if (Geometry.timeUntilWallCollision(line, babyBall, ball.getVelocity()) <= Constants.TIMESTEP) {
                    hits ++;
                    Vect velocity = Geometry.reflectWall(line, ball.getVelocity());
                    ball.setVelocity(velocity);
                    return new BoardEvent(this);
                }
            }
        }
        return null;
    }

    /**
     * @return startingPoint Vector representation of point at the top left corner of the bumper
     */
    @Override
    public Vect getPosition() {
        return startingPoint;
    }

    /**
     * Called during Board's step function. Each type of bumper has a different representation:
     *
     * Square   = "#"
     * Triangle = "/" or "\"
     * Circle   = "O"
     *
     * @return string representation of the bumper for print out
     */
    @Override
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

    /**
     * Bumpers perform no special action, so this does nothing
     */
    @Override
    public void specialAction() {

    }

    /**
     * @return 1 height of bumper is always 1
     */
    @Override
    public double getHeight(){
        return 1;
    }

    /**
     * @return 1 width of bumper is always 1
     */
    @Override
    public double getWidth(){
        return 1;
    }

    /**
     * @return unique String representation of bumper
     */
    @Override
    public String getName() {
        return name;
    }


    /**
     * Rep invariant: bumper must have a position within the board's boundaries
     */
    public void checkRep(){
        if(startingPoint.x() + 1 > Constants.BOARD_WIDTH ||
                startingPoint.y() + 1 > Constants.BOARD_HEIGHT ||
                startingPoint.x() < 0 ||
                startingPoint.y() < 0){
            throw new RepInvariantException("bumper out of bounds.");
        }
    }

    /**
     * @return the number of times the bumper has been hit by a ball
     */
    public int getHits(){
        return hits;
    }


}
