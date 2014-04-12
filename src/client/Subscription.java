package client;

import client.gadgets.Gadget;

/**
 * A Subscription is an immutable connection between a Gadget that
 * creates BoardEvents and a Gadget that acts on those events.
 *
 */
public class Subscription {
    private final Gadget subscriber;
    private final Gadget triggerer;

    /**
     * TODO
     * @param subscriber
     * @param triggerer
     */
    public Subscription(Gadget subscriber, Gadget triggerer) {
        this.subscriber = subscriber;
        this.triggerer = triggerer;
    }

    public Gadget getTriggerer() {
        return triggerer;
    }

}
