package client.gadgets;
import common.Constants;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.BoardEvent;

/**
 * Sidewalls indicate the boundaries of the playing board, and lie just beyond its edge.
 * One horizontal wall just above y = 0L, one just below y = 20L. One vertical wall just left
 * of x = 0L, one just right of x = 20L.
 *
 * Each wall may be either solid or invisible. A solid wall is reflective,
 * so that a ball bounces off it. An invisible wall allows a ball to pass
 * through it, into another playing area. When the client is playing disconnected
 * from a server, all four walls are solid. When the client is connected to a server,
 * the server determines which of the client's walls are solid and which are transparent,
 * depending on how the client's playing area is currently attached to other clients'
 * playing areas.
 *
 * Rep Invariant: Length of line Segments must be equal to BOARD_WIDTH and BOARD_HEIGHT.
 * Walls cannot tilt at an angle.
 *
 * Thread Safety Argument: all Gadgets on a Board will be confined to only one Client thread.
 */

public class SideWall implements Gadget{
    private final String name;
    private final Constants.BoardSide type;
    private boolean invisible;
    private final LineSegment wall;

    /**
     * Constructor that takes the shape of the wall.
     *
     * @param name unique String identifier for SideWall object
     * @param invisible Indicates whether wall is visible (false) or invisible (true)
     * @param wallType BoardSide enum indicating if the wall is top, bottom, left, or right
     * (BoardSide enum contained in common.Constants class)
     */
    public SideWall(String name, boolean invisible, Constants.BoardSide wallType) {
        this.name = name;
        this.invisible = invisible;
        type = wallType;
        if (type == Constants.BoardSide.TOP){
            wall = new LineSegment(0, 0, Constants.BOARD_WIDTH, 0);
        } else if (type == Constants.BoardSide.BOTTOM){
            wall = new LineSegment(0, Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        } else if (type == Constants.BoardSide.LEFT){
            wall = new LineSegment(0, 0, 0, Constants.BOARD_HEIGHT);
        }else{
            wall = new LineSegment(Constants.BOARD_WIDTH, 0, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        }
    }

    /**
     * When physics' timeUntilWallCollision method detects that a ball from Board will hit a SideWall,
     * the wall will reflect the ball with the appropriate physics methods
     *
     * @param Ball object from Board
     */
    @Override
    public BoardEvent handleBall(Ball ball) {
        if (!invisible && Geometry.timeUntilWallCollision(wall, ball.getCircle(), ball.getVelocity()) < Constants.TIMESTEP) {
            Vect velocity = Geometry.reflectWall(wall, ball.getVelocity());
            ball.setVelocity(velocity);
            ball.setPosition(ball.getCircle().getCenter().plus(velocity.times(Constants.TIMESTEP)));
            return new BoardEvent(this);
        }
        return null;
    }

    /**
     * @return height of SideWall
     */
    @Override
    public int getHeight() {
        // 0 if TOP or BOTTOM, length of line segment otherwise
        return 0;
    }

    /**
     * @return width of SideWall
     */
    @Override
    public int getWidth() {
        //0 if LEFT or RIGHT, length of line segment otherwise
        return 0;
    }



    /**
     * @return null because getPosition is never called for walls
     */
    public Vect getPosition() {
        return null;
    }

    @Override
    /**
     * Called during Board's step function. Each L unit of a sideWall is represented
     * by "*"
     *
     * @return string representation of sideWall for print out
     */
    public String stringRepresentation() {
        // unnecessary for SideWall gadget; walls generated automatically by StringCanvas
        // we might want to change this
        return null;
    }

    @Override
    /**
     * Walls perform no special action, so this initiates nothing
     */
    public void specialAction() {
        // TODO Walls generate no special action: maybe we should handle invisible walls here

    }

    @Override
    /**
     * @return unique String identifier for SideWall object
     */
    public String getName() {
        return name;
    }

    public enum WallType {
        TOP, BOTTOM, LEFT, RIGHT
    }

    /**
     * Rep Invariant: Length of line Segments must be equal to BOARD_WIDTH and BOARD_HEIGHT.
     * Walls cannot tilt at an angle.
     * @return boolean indicating whether the SideWall adheres to the rep invariant
     */
    public boolean checkRep(){
        //TODO: implement checkRep
        return true;
    }

}
