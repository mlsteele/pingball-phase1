 package client;

import client.gadgets.Gadget;

/**
 * Immutable class for events that happen on the board.
 * These events are fired by gadgets.
 * Each event is only described by the Gadget who fired it.
 */
public class BoardEvent {
    /*
     * Rep invariant:
     * - all data is immutable.
     */
    private final Gadget triggerer;

    /**
     * Create a new event.
     * @param triggerer object responsible for triggering
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
