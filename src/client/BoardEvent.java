 package client;

import client.gadgets.Gadget;

/**
 * Immutable class for events that happen on the board.
 * These events are fired by gadgets.
 */
public class BoardEvent {
    /**
     * Rep invariant:
     * - all data is immutable.
     */
    private final Gadget triggerer;

    /**
     * Create a new event.
     * @param triggerer object responsible for triggering
     */
    BoardEvent(Gadget triggerer) {
        this.triggerer = triggerer;
    }

    public Gadget getTriggerer() {
        return triggerer;
    }
}
