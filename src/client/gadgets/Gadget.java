package client.gadgets;

import physics.Vect;
import client.Ball;
import client.BoardEvent;

/**
 * Gadgets include: bumpers, flippers, absorbers, and walls. These object classes
 * are in the client package and implement Gadget.
 *
 * These objects are initialized on the board before play begins, and stay on the board
 * throughout gameplay.
 *
 * Each gadget may have a trigger and an action. A trigger is an event that happens
 * at the gadget, such as a ball colliding with it. An action is a response that a gadget
 * can make to a trigger happening somewhere on the board. A gadget’s action can be hooked
 * up to another gadget’s trigger, in order to produce Rube Goldberg machines. These hookups
 * are specified by the board file format.
 *
 *
 * Thread Safety Argument: all Gadgets on a Board will be confined to only one Client thread,
 * so we do not have to worry about concurrent calls on methods or mutable objects.
 */
public interface Gadget {

    /**
     * When a ball collides with a Gadget, the Gadget will handle the physics
     * and reflection according to its Spec and return a BoardEvent to the Board
     * class—the Board class can then trigger other events if necessary.
     *
     * @param ball Any legal ball can be handled
     * @return BoardEvent to be queued and handled by the Board class
     */
    //returns a BoardEvent that will be queued and handled by Board class
    //  after the gadget is hit by a ball
    public BoardEvent handleBall(Ball ball);

    /**
     * @return height of Gadget in L unit lengths for rendering purposes
     */
    public int getHeight();

    /**
     * @return width of Gadget in L unit lengths for rendering purposes
     */
    public int getWidth();

    /**
     * @return unique String representation of Gadget
     */
    public String getName();

    /**
     * @return starting point for the Gadget. For most gadgets, it will be
     * the upper left corner of their object's location. For flippers, it will
     * be the point around which the flipper rotates.
     */
    public Vect getPosition();

    /**
     *
     * @return
     */
    public String stringRepresentation();

    //debugging String that includes information about the Gadget object
    //  like size, position, and type
    public String toString();

    /**
     * Completes an action if Gadget triggers an Action when hit. For example, absorbers
     * absorb the ball upon being hit and release a new Ball if it was contained. Flippers
     * and SideWalls may also generate
     */
    public void specialAction();

    /**
     * @return boolean indicating whether the Gadget adheres to its class's rep invariant
     */
    public boolean checkRep();



}
