package client.gadgets;

import physics.Vect;

import client.Ball;
import client.BoardEvent;

/**
 * Gadgets include: StaticBumpers, Flippers, Absorbers, and Walls. These object classes
 * are in the client.gadgets package and implement Gadget.
 *
 * These objects are initialized on the board before play begins, and stay on the board
 * throughout gameplay.
 *
 * Each gadget may have a trigger and an action. A trigger is an event that happens
 * at the gadget, such as a ball colliding with it. An action is a response that a gadget
 * can make to a trigger happening somewhere on the board. A gadget's action can be hooked
 * up to another gadget's trigger, in order to produce Rube Goldberg machines. These hookups
 * are specified by the board file format.
 *
 *
 * Thread Safety Argument:
 * * all Gadgets on a Board will be confined to only one Client thread
 *   so we do not have to worry about concurrent calls on methods or mutable objects.
 */
public interface Gadget {

    /**
     * handleBall will determine whether ball will collide with the Gadget during this timestep.
     * If this causes the gadget to trigger, it will return a BoardEvent representing the trigger.
     * Otherwise it will return null.
     *
     * @param ball the ball which we want to handle
     * @return BoardEvent to be queued and handled by the Board class
     *         returned iff the gadget fires an event. (Usually a ball collision
     *         will trigger an event, but walls for example will return an event
     *         if they take the ball)
     */
    public BoardEvent handleBall(Ball ball);

    /**
     * @return unique String representation of Gadget
     */
    public String getName();

    /**
     * @return starting point for the Gadget. For all gadgets except walls, it will be
     * the upper left corner of their object's location.
     */
    public Vect getPosition();

    /**
     * @return height of gadget in L
     */
    public double getHeight();

    /**
     * @return width of gadget in L
     */
    public double getWidth();

    /**
     * @return string representing gadget for rendering purposes for all gadgets
     */
    public String stringRepresentation();

    /**
     * The action that happens when the Gadget is bound to a Gadget which triggers.
     * For example, Flipper's specialAction is to rotate 90 degrees.
     */
    public void specialAction();

    /**
     * Check that the Gadget adheres to its class's rep invariant.
     */
    public void checkRep();

}
