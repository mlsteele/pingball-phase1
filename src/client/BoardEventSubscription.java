package client;

import client.gadgets.Gadget;

/**
 * A Subscription is an immutable connection between a Gadget that
 * creates BoardEvents and a Gadget that acts on those events.
 *
 * Subscriptions persist for the duration of the board game, unlike
 * BoardEvents which are consumed.
 *
 * Thread Safety Argument:
 * * this is an immutable data type.
 *
 * Rep invariant: none.
 *
 */
public class BoardEventSubscription {
    private final Gadget subscriber;
    private final Gadget triggerer;

    /**
     * Create a new BoardEventSubscription
     * @param subscriber the Gadget that will perform specialAction when triggerer fires a BoardEvent
     * @param triggerer the Gadget that will fire BoardEvents
     */
    public BoardEventSubscription(Gadget triggerer, Gadget subscriber) {
        this.triggerer = triggerer;
        this.subscriber = subscriber;
    }

    /**
     * get the triggerer
     * @return the Gadget whose BoardEvents will cause the subscriber to act.
     */
    public Gadget getTriggerer() {
        return triggerer;
    }

    /**
     * get the subscriber
     * @return the Gadget who is listening for the triggerer's BoardEvents.
     */
    public Gadget getSubscriber() {
        return subscriber;
    }

}
