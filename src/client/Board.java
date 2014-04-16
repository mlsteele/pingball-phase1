package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.Constants;

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
 * Rep Invariant: Gadgets are never added or removed from the list of Board gadgets.
 * No gadgets can overlap.
 *
 * Thread Safety Argument:
 * The instance of Board in a client is confined to one thread.
 */
public class Board {

    private final String name;
    private final List<Gadget> gadgets;
    private List<Ball> balls;
    private Queue<BoardEvent> eventQueue;
    private List<BoardEventSubscription> subscriptions;

    /**
     * Create a new board.
     * @param name board name
     * @param width board width
     * @param height board height
     * @param gadgets list of gadgets on the board.
     *                caller must never modify this list.
     */
    public Board(String name, List<Gadget> gadgets) {
        this.name = name;
        this.gadgets = Collections.unmodifiableList(gadgets);
        this.balls = new ArrayList<Ball>();
        this.eventQueue = new LinkedList<BoardEvent>();
        this.subscriptions = new ArrayList<BoardEventSubscription>();
    }

    /**
     * Called by the server when a ball from a neighboring board enters this board
     * @param ball Any legal ball can be handled
     */
    public void addBall(Ball ball) {

    }

    /**
     * Receives events from handleBall Gadget methods and adds them to eventQueue.
     * BoardEventSubscription contains a list of the alternate and self triggers
     * that some BoardEvents generate. eventQueue will always empty because BoardEvents
     * are only produced in one method (handleBall) by each gadget.
     */
    public void step() {
        //TODO: loop through all gadgets and call handleBall for all balls
        //TODO: make board string for output with all gadgets and all balls

        StringCanvas board = new StringCanvas(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, " ", name);
        for (Gadget gadget: gadgets){
            //add gadgets to board
            if (gadget.getHeight() == 1 && gadget.getWidth() == 1){
                board.setRect((int)gadget.getPosition().x(), (int)gadget.getPosition().y(), gadget.stringRepresentation());
            }
            //TODO: Make sure walls aren't included in this-StringCanvas already deals with them
            else{
                board.setChar((int)gadget.getPosition().x(), (int)gadget.getPosition().y(), gadget.stringRepresentation());
            }
        }
        for (Ball ball: balls){
          //add balls to board
            board.setChar((int)ball.getCenter().x(), (int)ball.getCenter().x(), "*");
        }

        //TODO: change all balls' gravity
        String boardOutput = board.getString();
        System.out.println(boardOutput);

        // process events in queue (last part of step)
        while (!eventQueue.isEmpty()) {
            /* beware of infinite looping here. Maybe we need an invariant saying that
             * no event-based action can add an event to the queue.
             */
            BoardEvent e = eventQueue.remove();
            for (BoardEventSubscription s : subscriptions) {
                if (s.getTriggerer() == e.getTriggerer()) {
                    // process the event with the subscriber
                }
            }
        }
    }

    /**
     * Rep Invariant: Gadgets are never added or removed from the list of Board gadgets.
     * No gadgets can overlap.
     * @return boolean indicating whether the Gadget adheres to the rep invariant
     */
    public boolean checkRep(){
        //TODO: implement checkRep for Board
        return true;
    }


}
