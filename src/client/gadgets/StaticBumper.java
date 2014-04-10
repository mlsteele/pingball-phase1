package client.gadgets;

import java.util.ArrayList;
import java.util.List;

import physics.LineSegment;
import physics.Vect;
import client.BoardEvent;

/**
 * StaticBumper is a stationary geometric bumper.
 * Could by any of SquareBumper, CircularBumper, TriangularBumper.
 *
 */
public class StaticBumper implements Gadget {

    /**
     * Constructor that takes the shape of the bumper.
     */
    private StaticBumper(String name, List<LineSegment> geometry) {

    }

    /**
     * Factory method for creating SquareBumpers
     */
    public static StaticBumper createSquareBumper(String name, Vect position) {
        // TODO
        return new StaticBumper(name, new ArrayList<LineSegment>());
    }

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
    public client.gadgets.Vect getPosition() {
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
