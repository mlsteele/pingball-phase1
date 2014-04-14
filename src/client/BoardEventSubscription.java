package client;

import client.gadgets.Gadget;

/**
 * A Subscription is an immutable connection between a Gadget that
 * creates BoardEvents and a Gadget that acts on those events.
 *
 * Subscriptions persist for the duration of the board game, unlike
 * BoardEvents which are consumed.
 *
 */
public class BoardEventSubscription {
    private final Gadget subscriber;
    private final Gadget triggerer;

    /**
     * Create a new BoardEventSubscription
     * @param subscriber the Gadget that will do something when triggerer fires a BoardEvent
     * @param triggerer the Gadget that will fire BoardEvents
     */
    public BoardEventSubscription(Gadget triggerer, Gadget subscriber) {
        this.subscriber = subscriber;
        this.triggerer = triggerer;
    }

    /**
     * get the triggerer
     * @return the triggerer, i.e. the Gadget whose BoardEvents will cause the subscriber to act.
     */
    public Gadget getTriggerer() {
        return triggerer;
    }

}
