 package client;

import client.gadgets.Gadget;

/**
 * Immutable class for events that happen on the board.
 * These events are fired by gadgets.
 * Each event is only described by the Gadget who fired it.
 *
 * Thread Safety Argument:
 * * This data type is immutable.
 *
 * Rep Invariant: none
 */
public class BoardEvent {

    private final Gadget triggerer;

    /**
     * Create a new event.
     * @param triggerer Gadget who fires this event
     */
    public BoardEvent(Gadget triggerer) {
        this.triggerer = triggerer;
    }

    /**
     * get the triggerer
     * @return the Gadget who triggered this Event
     */
    public Gadget getTriggerer() {
        return triggerer;
    }
}
