package client.gadgets;

/**
 * Gadgets include the three kinds of bumpers, flippers, absorbers,
 * and the board's four walls. These object classes are in the client package
 * and implement Gadget.
 */
public interface Gadget {

    /**
     * The name of the gadget
     * @return name
     */
    public String getName();

    //returns a BoardEvent that will be queued and handled by Board class
    //  after the gadget is hit by a ball
    public BoardEvent handleBall();

    //returns size of gadget in Ls for rendering purposes
    public int getSize();

    //returns position of gadget for physics
    public Vect getPosition();

    //for rendering
    //  Example: Square bumper will return "#"
    public String stringRepresentation();

    //debugging String that includes information about the Gadget object
    //  like size, position, and type
    @Override;
    public String toString();

    //for those gadgets (absorbers, invisible/fused walls, and flippers)
    //    that respond to a trigger with an action
    public void specialAction();

}
