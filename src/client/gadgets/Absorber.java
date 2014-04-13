package client.gadgets;

import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.BoardEvent;

/**
 * A rectangle kL x mL where k and m are positive integers <= 20.
 * Trigger: generated whenever the ball hits it
 * Action: shoots out a ball (if one is stored) at velocity 50L/sec
 *
 * When a ball hits an absorber, the absorber stops the ball and holds it (unmoving)
 * in the bottom right-hand corner of the absorber. The ball’s center is .25L from the
 * bottom of the absorber and .25L from the right side of the absorber.
 */

public class Absorber implements Gadget {
    private final List<Ball> balls;
    private boolean ballContained;
    private int width;
    private int height;
    private Vect startingPoint;
    private String name;
    private final List<LineSegment> geometry;

    /**
     * Constructor that dictates size of absorber.
     * The first LineSegment listed in geometry must begin at the origin point (position.x(), position.y())
     * of the absorber
     */
    private Absorber(String name, List<LineSegment> geometry){
        this.name = name;
        this.width = (int) geometry.get(0).length();
        this.height = (int) geometry.get(1).length();
        startingPoint = geometry.get(0).p1();
        ballContained = false;
        balls = null;
        this.geometry = geometry;

    }

    @Override
    /**
     * Stores ball when hit and
     */
    public BoardEvent handleBall(Ball ball) {
        //if the ball hits
        for (LineSegment line : geometry){
            if (Geometry.timeUntilWallCollision(line, ball.c, ball.vel) < TIMESTAMP) {
                //position for ball according to specs
                Vect absorberBottom = new Vect(this.getPosition().x() + width - (balls.size()) - 0.25, this.getPosition().y() - height + .25);
                //places ball in the right place and updates contained information
                ball.setVel(new Vect(0, 0));
                ball.setCircle(new Circle(absorberBottom, ball.c.getRadius()));
                ballContained = true;
                balls.add(ball);
                return new BoardEvent(this);
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        return width;
    }

    /**
     *
     * @return string representation of absorber for print out
     * need to change; not separated into lines (?)
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
     * Shoots out ball if a ball is contained
     */
    public void specialAction() {
        Vect shootVelocity = new Vect(Math.PI/2, 20d); //20 L/s, straight up in the air
        if (ballContained){
            Ball newBall = balls.get(0);
            newBall.setVel(shootVelocity);
            newBall.setCircle(new Circle(newBall.c.getCenter().plus(shootVelocity.times(TIMESTAMP)), newBall.c.getRadius()));
        }
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
