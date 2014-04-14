package client.gadgets;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.BoardEvent;

/**
 * Sidewalls lie indicate the boundaries of the playing board, and lie just beyond its edge.
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
 * Rep Invariant: covered by Java type considerations
 *
 * Thread safety: The only mutable element of SideWall is the boolean invisible. It is only
 * mutated when appropriate (when a Client receives a Board Fuse/Unfuse message), and there will only
 * ever be one Client thread at a time to enter the method to change it. Otherwise it is confined.
 */

public class SideWall implements Gadget{
    private final Vect startingPoint;
    private final String name;
    private final LineSegment wall;
    private boolean invisible;

    /**
     * Constructor that takes the shape of the bumper.
     * The first LineSegment listed in geometry must begin at the origin point (position.x(), position.y())
     * of the bumper
     *
     * @param name unique String identifier for SideWall object
     * @param invisible Indicates whether wall is visible (false) or invisible (true)
     * @param wall Vector with start and end points of side wall
     */
    private SideWall(String name, boolean invisible, LineSegment wall) {
        this.name = name;
        this.invisible = invisible;
        startingPoint = wall.p1();
        this.wall = wall; // only one wall?
    }

    /**
     * When physics' timeUntilWallCollision method detects that a ball from Board will hit a SideWall,
     * the wall will reflect the ball with the appropriate physics methods
     *
     * @param Ball object from Board
     */
    @Override
    public BoardEvent handleBall(Ball ball) {
        if (Geometry.timeUntilWallCollision(wall, ball.getCircle(), ball.getVelocity()) < TIMESTEP && !invisible) {
            Vect velocity = Geometry.reflectWall(wall, ball.getVelocity());
            ball.setVelocity(velocity);
            ball.setPosition(ball.getCircle().getCenter().plus(velocity.times(TIMESTEP)));
            return new BoardEvent(this);
        }
        return null;
    }

    /**
     * @return height of SideWall
     */
    @Override
    public int getSize() {
        // TODO What are a set of walls' size?
        return 0;
    }


    /**
     * @return Vect point at the top of the sideWall indicating its beginning
     */
    public Vect getPosition() {
        return startingPoint;
    }

    @Override
    /**
     * Called during Board's step function. Each L unit of a sideWall is represented
     * by "*"
     *
     * @return string representation of sideWall for print out
     */
    public String stringRepresentation() {
        // TODO Auto-generated method stub
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

}
