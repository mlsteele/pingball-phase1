package client.gadgets;

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
 * Thread safety: The only mutable elements of Absorber are the list of Ball objects and the
 * the boolean ballContained. Each is only mutated when appropriate (when a Ball collides with the
 * Absorber), and there will only ever be one Client thread at a time to enter the method.
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
    public Absorber(String name, List<LineSegment> geometry){
        this.name = name;
        this.width = (int) geometry.get(0).length();
        this.height = (int) geometry.get(1).length();
        startingPoint = geometry.get(0).p1();
        ballContained = false;
        balls = null;
        this.geometry = geometry;

        if (!checkRep()){
            System.out.println("Error: rep invariant broken");
            System.exit(0);
        }

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
            if (Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity()) < TIMESTEP) {
                //position for ball according to specs;
                Vect absorberBottom = new Vect(this.getPosition().x() + width - (balls.size()) -
                        0.25, this.getPosition().y() - height + .25);
                //places ball in the right place and updates contained information
                ball.setVelocity(new Vect(0, 0));
                ball.setPosition(absorberBottom);
                ballContained = true;
                balls.add(ball);

                if (!checkRep()){
                    System.out.println("Error: rep invariant broken");
                    System.exit(0);
                }
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
                        absorber += "=";
                    } else{
                        absorber += " ";
                    }
                }
            }
            absorber += "/n";
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
            newBall.setVelocity(SHOOT_VELOCITY);
            newBall.setPosition(newBall.getCircle().getCenter().plus(SHOOT_VELOCITY.times(TIMESTEP)));
            if (!checkRep()){
                System.out.println("Error: rep invariant broken");
                System.exit(0);
            }
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
     *
     * @return boolean indicating whether the Absorber adheres to the rep invariant
     */
    private boolean checkRep(){
        boolean safe = true;
        if (geometry.size() == 4){
            safe = false;
        }
        //check status of all corners of line segments
        for (int i = 0; i < geometry.size(); i++){
            if (!((geometry.get(i).p1().x() > 0 && geometry.get(i).p1().x() < 20) &&
                    (geometry.get(i).p1().y() > 0 && geometry.get(i).p1().y() < 20))){
                safe = false;
            }
        }


        return safe;
    }
}
