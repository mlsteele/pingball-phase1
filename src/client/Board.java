package client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import common.Constants;
import common.RepInvariantException;
import client.gadgets.Gadget;
import client.gadgets.Wall;

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

        Wall top = new Wall(Constants.BoardSide.TOP, this);
        Wall bottom = new Wall(Constants.BoardSide.BOTTOM, this);
        Wall left = new Wall(Constants.BoardSide.LEFT, this);
        Wall right = new Wall(Constants.BoardSide.RIGHT, this);
        walls.add(top);
        walls.add(bottom);
        walls.add(left);
        walls.add(right);
    }

    /**
     * get the board name
     * @return the board name
     */
    public String getName() {
        return name;
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
     * Connect the wall on side to a board over the network
     * @param side the side to connect
     * @param name the name of the connected
     */
    public void connectWallToServer(Constants.BoardSide side, String name) {
        getWall(side).connectToServer(name);
    }

    /**
     * Disconnect the wall on side from this board
     * @param side the side to disconnect
     */
    public void disconnectWallFromServer(Constants.BoardSide side) {
        getWall(side).disconnectFromServer();
    }

    /**
     * sets the server handler so that the walls can connect to the server
     * @param sh the server handler
     */
    public void setServerHandler(ServerHandler sh) {
        for (Wall wall: walls) {
            wall.setServerHandler(sh);
        }
    }

    /**
     * get the wall on the given side
     * @param side the side
     * @return the wall with type of that side
     */
    public Wall getWall(Constants.BoardSide side) {
        for (Wall wall: walls) {
            if (wall.getType() == side) return wall;
        }
        throw new RuntimeException("Unexpected state: no wall of type " + side);
    }

    /**
     * Receives events from handleBall Gadget methods and adds them to eventQueue.
     * BoardEventSubscription contains a list of the alternate and self triggers
     * that some BoardEvents generate. eventQueue will always empty because BoardEvents
     * are only produced in one method (handleBall) by each gadget.
     */
    public String step() {
        //String representation of Board
        //adds room for walls on all sides, so must increment
        //height and weight by 2
        StringCanvas boardString = new StringCanvas(Constants.BOARD_WIDTH + 2, Constants.BOARD_HEIGHT + 2, " ");

        // Draw the four corners
        boardString.setRect(0,0,".");
        boardString.setRect(0,21,".");
        boardString.setRect(21,0,".");
        boardString.setRect(21,21,".");

        // Balls which were taken by fused walls.
        Set<Ball> ballsToRemove = new HashSet<Ball>();

        //loop through all gadgets and call handleBall for all balls; add relevant events to boardQueue
        //  then add gadgets to boardString
        for (Gadget gadget: gadgets){
            for (Ball ball: balls){
                BoardEvent e = gadget.handleBall(ball);
                if (e != null){
                    eventQueue.add(e);
                }
            }
            boardString.setRect((int)gadget.getPosition().x() + 1, (int)gadget.getPosition().y() + 1, gadget.stringRepresentation());
        }

        // Handle Wall collisions
        for (Ball ball: balls){
            for (Wall wall: walls){
                // Handle collisions
                if (wall.handleBall(ball) != null) { // the wall took the ball
                    ballsToRemove.add(ball);
                }
            }
        }

        // Draw Walls
        for (Wall wall: walls){
            // Draw the Wall
            if (wall.getType() == Constants.BoardSide.TOP){
                boardString.setRect((int)wall.getPosition().x()+1, (int)wall.getPosition().y(), wall.stringRepresentation());
            } else if (wall.getType() == Constants.BoardSide.LEFT){
                boardString.setRect((int)wall.getPosition().x(), (int)wall.getPosition().y()+1, wall.stringRepresentation());
            } else if (wall.getType() == Constants.BoardSide.BOTTOM){
                boardString.setRect((int)wall.getPosition().x()+1, (int)wall.getPosition().y()+1, wall.stringRepresentation());
            } else{ //RIGHT
                boardString.setRect((int)wall.getPosition().x()+1, (int)wall.getPosition().y()+1, wall.stringRepresentation());
            }
        }

        // Remove balls from Board.
        for (Ball ball: ballsToRemove) {
            balls.remove(ball);
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

        // update every ball for gravity and their velocity
        for (Ball ball: balls){
            if (ball.isInPlay() == true) {
                // Update algorithm (not used anymore):
                // vel += gravity * timestep
                // pos += vel * timestep

                // Update algorithm (simultaneously):
                // vel += gravity * timestep
                // pos += vel * timestep + (1/2 * gravity * timestep^2)

                Vect oldPos = ball.getPosition();
                Vect oldVel = ball.getVelocity();

                Vect term1 = oldVel.times(Constants.TIMESTEP);
                Vect term2 = new Vect(0, 0.5 * gravity * Constants.TIMESTEP * Constants.TIMESTEP);

                Vect newPos = oldPos.plus(term1).plus(term2);
                double frictionScalar = 1-(frictionOne)*(Constants.TIMESTEP) - frictionTwo*oldVel.length()*Constants.TIMESTEP;
                Vect frictionVel = oldVel.times(frictionScalar);
                Vect newVel = frictionVel.plus(new Vect(0, gravity * Constants.TIMESTEP));

                ball.setPosition(newPos);
                ball.setVelocity(newVel);
            }
        }

        //add balls to boardString to complete it
        for (Ball ball: balls){
            if (ball.isInPlay() == true){
                boardString.setRect((int)(ball.getCircle().getCenter().x() + 1), (int)(ball.getCircle().getCenter().y() + 1), "*");
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
