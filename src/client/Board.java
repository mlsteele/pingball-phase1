package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import client.gadgets.Gadget;

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
     * - gadgets are never added or removed from the list.
     */
    private final String name;
    private final int width;
    private final int height;
    private final List<Gadget> gadgets;
    private List<Ball> balls;

    /**
     * Create a new board.
     * @param name board name
     * @param width board width
     * @param height board height
     * @param gadgets list of gadgets on the board.
     *                caller must never modify this list.
     */
    public Board(String name, int width, int height, List<Gadget> gadgets) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.gadgets = Collections.unmodifiableList(gadgets);
        this.balls = new ArrayList<Ball>();
    }

    public void addBall(Ball ball) {

    }
}
