package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import physics.Circle;
import physics.Vect;
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
    private final double gravity;
    private final double frictionOne;
    private final double frictionTwo;

    /**
     * Create a new board.
     * @param name board name
     * @param width board width
     * @param height board height
     * @param gadgets list of gadgets on the board.
     *                caller must never modify this list.
     */
    public Board(String name, List<Gadget> gadgets, double gravity, double f1, double f2) {
        this.name = name;
        this.gadgets = Collections.unmodifiableList(gadgets);
        this.balls = new ArrayList<Ball>();
        this.eventQueue = new LinkedList<BoardEvent>();
        this.subscriptions = new ArrayList<BoardEventSubscription>();
        this.gravity = gravity;
        this.frictionOne = f1;
        this.frictionTwo = f2;
        //TODO: make Walls instead of SideWalls
    }

    /**
     * Called by the server when a ball from a neighboring board enters this board
     * @param ball Any legal ball can be handled
     */
    public void addBall(Ball ball) {
        balls.add(ball);
    }

    /**
     * Called by the server when a new subscription is created
     * @param s Any legal subscription can be handled
     */
    public void addSubscription(BoardEventSubscription s){
        subscriptions.add(s);
    }

    /**
     * Receives events from handleBall Gadget methods and adds them to eventQueue.
     * BoardEventSubscription contains a list of the alternate and self triggers
     * that some BoardEvents generate. eventQueue will always empty because BoardEvents
     * are only produced in one method (handleBall) by each gadget.
     */
    public void step() {

        StringCanvas boardString = new StringCanvas(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, " ");

        //loop through all gadgets and call handleBall for all balls
        for (Gadget gadget: gadgets){
            for (Ball ball: balls){
                BoardEvent e = gadget.handleBall(ball);
                if (e != null){
                    eventQueue.add(e);
                }
            }
        }

        //add gadgets to boardString
        for (Gadget gadget: gadgets){
            boardString.setRect((int)gadget.getPosition().x(), (int)gadget.getPosition().y(), gadget.stringRepresentation());
        }

        //update every ball for gravity
        for (Ball ball: balls){
            Vect gravity = new Vect(ball.getVelocity().angle(), Constants.GRAVITY*Constants.TIMESTEP);
            ball.setPosition(ball.getCircle().getCenter().plus(ball.getVelocity().minus(gravity)));
            //ball.setPosition(ball.getCircle().getCenter().plus(velocity.times(Constants.TIMESTEP)));
        }

        //move balls along an appropriate amount according to their velocity
        for (Ball ball: balls){
            ball.setPosition(ball.getCircle().getCenter().plus(ball.getVelocity().times(Constants.TIMESTEP)));
        }

        //add balls to boardString to complete it
        for (Ball ball: balls){
            boardString.setRect((int)ball.getCenter().x(), (int)ball.getCenter().x(), "*");
        }

        String boardOutput = boardString.getString();
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
     */
    public void checkRep(){
        //TODO: implement checkRep for Board
        // throw new RepInvariantException("Rep invariant violated.");
    }


}
