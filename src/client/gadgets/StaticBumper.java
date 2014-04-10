package client.gadgets;

import java.util.ArrayList;
import java.util.List;

import physics.LineSegment;
import physics.Vect;

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
    
}
