package client.gadgets;

import physics.Vect;
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

public class Wall implements Gadget{

    private boolean invisible;

    @Override
    public BoardEvent handleBall() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Vect getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String stringRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void specialAction() {
        // TODO Auto-generated method stub

    }

}
