package client;

import java.util.Collections;

/**
 * Board class to contain and run physics and interaction in a pingball board.
 * Board is a mutable representation of the state of a board.
 *
 * The board remembers metadata like its size and name.
 * The board holds onto the balls and gadgets on the board.
 * The step method of the board progresses the game state,
 * including advancing physics, handling events between
 * gadgets, and notifying the client of balls that have left
 * the arena.
 *
 * The board can accept new balls from other clients, and
 * can be notified when it is fused or separated from other boards.
 *
 * Thread Safety Argument:
 * The instance of Board in a client is confined to one thread.
 */
public class Board {
    /*
     * Rep Invariant:
     * - list of gadgets is never changed.
     */
    private final String name;
    private final int width;
    private final int height;
    private final List<Gadgets>

    /**
     * Create a new board.
     * @param name board name
     * @param width board width
     * @param height board height
     * @param gadgets list of gadgets on the board
     */
    public Board(String name, int width, int height, List<Gadget> gadgets) {
        this.name = name;
        this.width = width;
        this.height = height;
        Collections.unmodifiableList(gadgets);
    }

    public void addBall(Ball ball) {

    }
}
