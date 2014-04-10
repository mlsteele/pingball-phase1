package client;

/**
 * Immutable class for events that happen on the board.
 * These events are generally fired by gadgets.
 */
public class BoardEvent {
    /**
     * Rep invariant:
     * - all data is immutable.
     */
    private final String triggeredBy;

    /**
     * Create a new event.
     * @param triggeredBy name of object responsible for triggering
     */
    BoardEvent(String triggeredBy) {
        this.triggeredBy = triggeredBy;
    }

    public String getTriggeredBy() {
        return triggeredBy;
    }
}
