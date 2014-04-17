package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import common.Constants;
import common.RepInvariantException;
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
 * The instance of Board in a client is confined (with its objects, like
 * StringCanvas and Gadgets) to one thread.
 */
public class Board {

    private final String name;
    private final List<Gadget> gadgets;
    private List<Ball> balls;
    private List<Wall> walls;
    private Queue<BoardEvent> eventQueue;
    private List<BoardEventSubscription> subscriptions;
    private final double gravity;
    private final double frictionOne;
    private final double frictionTwo;

    /**
     * Create a new board.
     * @param name board name
     * @param gadgets list of gadgets on the board.
     *                caller must never modify this list.
     * @param gravity force of gravity on ball in L/s^2
     * @param f1 force of friction on ball in L/s^2
     * @param f2 force of friction on ball in L/s^2
     */
    public Board(String name, List<Gadget> gadgets, double gravity, double f1, double f2) {
        this.name = name;
        this.gadgets = Collections.unmodifiableList(gadgets);
        this.balls = new ArrayList<Ball>();
        this.walls = new ArrayList<Wall>();
        this.eventQueue = new LinkedList<BoardEvent>();
        this.subscriptions = new ArrayList<BoardEventSubscription>();
        this.gravity = gravity;
        this.frictionOne = f1;
        this.frictionTwo = f2;

        Wall top = new Wall(true, Constants.BoardSide.TOP);
        Wall bottom = new Wall(true, Constants.BoardSide.BOTTOM);
        Wall left = new Wall(true, Constants.BoardSide.LEFT);
        Wall right = new Wall(true, Constants.BoardSide.RIGHT);
        walls.add(top);
        walls.add(bottom);
        walls.add(left);
        walls.add(right);
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
    public String step() {
        StringCanvas boardString = new StringCanvas(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, " ");

        //make sure the ball isn't going to crash into any walls
        for (Wall wall: walls){
            for (Ball ball: balls){
                wall.handleBall(ball);
            }
        }

        //loop through all gadgets and call handleBall for all balls; add relevant events to boardQueue
        //  then add gadgets to boardString
        for (Gadget gadget: gadgets){
            for (Ball ball: balls){
                BoardEvent e = gadget.handleBall(ball);
                if (e != null){
                    eventQueue.add(e);
                }
            }
            boardString.setRect((int)gadget.getPosition().x(), (int)gadget.getPosition().y(), gadget.stringRepresentation());
        }

        // process events in queue
        while (!eventQueue.isEmpty()) {
            BoardEvent e = eventQueue.remove();
            for (BoardEventSubscription s : subscriptions) {
                if (s.getTriggerer() == e.getTriggerer()) {
                    s.getSubscriber().specialAction();
                }
            }
        }

        //update every ball for gravity and their velocity
        for (Ball ball: balls){
            if (ball.isInPlay() == true) {
                // vel += gravity * timestep
                // pos += vel * timestep

                ball.setVelocity(ball.getVelocity().plus(new Vect(0, gravity * Constants.TIMESTEP)));
                ball.setPosition(ball.getPosition().plus(ball.getVelocity().times(Constants.TIMESTEP)));
            }
        }

        //add balls to boardString to complete it
        for (Ball ball: balls){
            if (ball.isInPlay() == true){
                boardString.setRect((int) Math.round(ball.getCircle().getCenter().x()), (int) Math.round(ball.getCircle().getCenter().y()), "*");
            }
        }
        return boardString.getString();
    }

    /**
     * Rep Invariant: Gadgets are never added or removed from the list of Board gadgets.
     * No gadgets can overlap.
     */
    public void checkRep(){
        for (Gadget gadget: gadgets){
            for (Gadget gadget2: gadgets){
                if (gadget != gadget2){
                    if ((gadget.getPosition().x() <= gadget2.getPosition().x() &&
                        gadget.getPosition().x() + gadget.getWidth() > gadget2.getPosition().x()) ||
                                (gadget.getPosition().y() <= gadget2.getPosition().x() &&
                                gadget.getPosition().y() + gadget.getHeight() >= gadget2.getPosition().y())){
                        throw new RepInvariantException("Rep invariant violated.");
                    }
                }
            }
        }
    }
}
