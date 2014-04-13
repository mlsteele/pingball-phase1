package client.gadgets;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.BoardEvent;

/**
 * Walls lie just beyond the playing board. One horizontal wall just above y = 0L,
 * one just below y = 20L. One vertical wall just left of x = 0L, one just right of x = 20L.
 *
 * Each wall may be either solid or invisible. A solid wall is reflective,
 * so that a ball bounces off it. An invisible wall allows a ball to pass
 * through it, into another playing area. When the client is playing disconnected
 * from a server, all four walls are solid. When the client is connected to a server,
 * the server determines which of the client’s walls are solid and which are transparent,
 * depending on how the client’s playing area is currently attached to other clients’
 * playing areas.
 *
 */

public class SideWall implements Gadget{
    private Vect startingPoint;
    private String name;
    private final LineSegment wall;
    private boolean invisible;

    /**
     * Constructor that takes the shape of the bumper.
     * The first LineSegment listed in geometry must begin at the origin point (position.x(), position.y())
     * of the bumper
     */
    private SideWall(String name, boolean invisible, LineSegment wall) {
        this.name = name;
        this.invisible = invisible;
        startingPoint = wall.p1();
        this.wall = wall; // only one wall?
    }


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

    @Override
    public int getSize() {
        // TODO What are a set of walls' size?
        return 0;
    }

    @Override
    public Vect getPosition() {
        return startingPoint;
    }

    @Override
    public String stringRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void specialAction() {
        // TODO Walls generate no special action: maybe we should handle invisible walls here

    }


    @Override
    public String getName() {
        return name;
    }

}
