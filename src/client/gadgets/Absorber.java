package client.gadgets;
import common.Constants;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.BoardEvent;
import common.Constants;

/**
 * Absorber is a mutable class that represents an object on the Pingball board
 * which simulates the ball-return mechanism familiar to us from pinball.
 *
 * A rectangle kL x mL where k and m are positive integers <= 20.
 * Trigger: generated whenever the ball hits it
 * Action: shoots out a ball (if one is stored) at velocity 50L/sec
 *
 * When a ball hits an absorber, the absorber stops the ball and holds it (unmoving)
 * in the bottom right-hand corner of the absorber. The ball's center is .25L from the
 * bottom of the absorber and .25L from the right side of the absorber.
 *
 * Rep invariant: geometry (the list) must have four lines. The four corners of the lineSegments
 * must occur within the board (their x and y coordinates are less than 20 and greater than 0)
 *
 * Thread Safety Argument: all Gadgets on a Board will be confined to only one Client thread.
 */

public class Absorber implements Gadget {
    private final List<Ball> balls;
    private boolean ballContained;
    private final int width;  //L
    private final int height; //L
    private final Vect startingPoint;
    private final String name;
    private final List<LineSegment> geometry;

    /**
     * Absorber constructor that initializes an Absorber object with no Ball objects contained. Absorber must
     * receive a handleBall() call in order to add Ball objects to 'balls' list.
     *
     * @param name unique String identifier for Absorber object
     * @param geometry list of four LineSegments that describe and enclose the Absorber. The first LineSegment
     * listed in geometry must begin at the origin point (upper left-hand corner) of the Absorber. All horizontal
     * LineSegments must be less than the width of the board, all vertical LineSegments must be shorter
     * than the height of the board.
     *
     */
    public Absorber(String name, Vect startingPoint, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        this.startingPoint = startingPoint;
        List<LineSegment> geometry = new ArrayList<LineSegment>();
        geometry.add(new LineSegment(startingPoint.x(), startingPoint.y(), startingPoint.x() + width, startingPoint.y()));
        geometry.add(new LineSegment(startingPoint.x() + width, startingPoint.y(), startingPoint.x() + width, startingPoint.y() + height));
        geometry.add(new LineSegment(startingPoint.x() + width, startingPoint.y() + height, startingPoint.x(), startingPoint.y() + height));
        geometry.add(new LineSegment(startingPoint.x(), startingPoint.y() + height, startingPoint.x(), startingPoint.y()));
        ballContained = false;
        balls = null;
        this.geometry = geometry;

        checkRep();
    }

    @Override
    /**
     * When physics' timeUntilWallCollision method detects that a ball from Board will hit the Absorber,
     * the Absorber absorbs the ball and stores it .25L from its bottom and .25L from its right side. If
     * it contained another ball, it will shoot that ball up toward the board's ceiling.
     *
     * @param Ball object from Board
     */
    public BoardEvent handleBall(Ball ball) {
        //if the ball hits
        for (LineSegment line : geometry){
            if (Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity()) < Constants.TIMESTEP) {
                //position for ball according to specs;
                Vect absorberBottom = new Vect(this.getPosition().x() + width - (balls.size()) -
                        0.25, this.getPosition().y() - height + .25);
                //places ball in the right place and updates contained information
                ball.setVelocity(new Vect(0, 0));
                ball.setPosition(absorberBottom);
                ballContained = true;
                balls.add(ball);

                checkRep();
                return new BoardEvent(this);
            }
        }
        return null;
    }

    /**
     * @return height of absorber
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * @return width of absorber
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Called during Board's step function. Each L unit of width for an absorber is represented
     * by "=". The two side walls are represented by double || on the left and right boundaries.
     *
     * @return string representation of absorber for print out
     */
    @Override
    public String stringRepresentation() {
        String absorber = "";
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (i == 0 || i == height-1){ //top and bottom rows of absorber
                    absorber += "=";
                }
                else{
                    if (j == 0 || j == width-1){
                        absorber += "|";
                    } else{
                        absorber += " ";
                    }
                }
            }
            absorber += "\n";
        }

        return absorber;
    }

    @Override
    /**
     * Shoots out ball if a ball is contained. Triggered by Board and Board Event
     */
    public void specialAction() {
        if (ballContained){
            Ball newBall = balls.get(0);
            newBall.setVelocity(Constants.SHOOT_VELOCITY);
            newBall.setPosition(newBall.getCircle().getCenter().plus(Constants.SHOOT_VELOCITY.times(Constants.TIMESTEP)));
            checkRep();
        }
    }

    /**
     * @return startingPoint Vector representation of point at the top left corner of the absorber
     */
    public Vect getPosition() {
        return startingPoint;
    }

    /**
     * @return name unique String Absorber was initialized with
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Rep invariant: geometry (the list) must have four lines. The four
     * corners of the lineSegments must occur within the board (their x and y
     * coordinates are less than 20 and greater than 0)
     */
    public void checkRep(){
        //TODO: implement and change checkRep
        // throw new RepInvariantException("Rep invariant violated.");
    }
}
