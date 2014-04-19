package client.gadgets;

import client.Ball;
import client.BoardEvent;
import client.ServerHandler;
import common.Constants;
import common.RepInvariantException;
import common.netprotocol.BallOutMessage;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Wall is a class for the sidewall of a Board.
 *
 * Walls can one of four types representing the left, right, top, and bottom walls.
 * Walls normally reflect the ball when they collide, but if the wall is
 * connected over the network to another board, then the wall will take the ball
 * and send it to the server.
 *
 * Thread Safety Argument:
 * - Wall is not threadsafe and is used in a confined environment.
 *
 * Rep Invariant:
 * - connectedBoardName can be null if there is no active fuse.
 * - if connectedBoardName is not tull, serverHandler must not be null
 */
public class Wall implements Gadget {
    private final Constants.BoardSide type;
    private final LineSegment wall;
    private String connectedBoardName;
    private ServerHandler serverHandler;

    /**
     * make a Wall of the given type
     * @param wallType what kind of Wall this is. May be TOP, BOTTOM, LEFT, or RIGHT.
     */
    public Wall(Constants.BoardSide wallType) {
        this.type = wallType;
        this.connectedBoardName = null;
        if (type == Constants.BoardSide.TOP){
            wall = new LineSegment(0, 0, Constants.BOARD_WIDTH, 0);
        } else if (type == Constants.BoardSide.BOTTOM){
            wall = new LineSegment(0, Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        } else if (type == Constants.BoardSide.LEFT){
            wall = new LineSegment(0, 0, 0, Constants.BOARD_HEIGHT);
        } else {
            wall = new LineSegment(Constants.BOARD_WIDTH, 0, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        }
    }

    /**
     * If the ball will collide with the wall in the next timestep, the wall:
     *  a) takes the ball and sends it to the server, if the wall is connected to another board
     *  b) reflects the ball if it is not connected to another board over the network
     *
     * @param Ball object from Board
     * @return a BoardEvent if the wall is taking the ball, otherwise null
     * This is different from most gadgets, because a regular wall collision does not trigger an event.
     */
    public BoardEvent handleBall(Ball ball) {
        if (Geometry.timeUntilWallCollision(wall, ball.getCircle(), ball.getVelocity()) <= Constants.TIMESTEP){
            if (connectedBoardName == null){
                Vect reboundVelocity = Geometry.reflectWall(wall, ball.getVelocity());
                ball.setVelocity(reboundVelocity);
            } else {
                // Transform ball position so that it shows up in the correct place on the fused board.
                Vect newBallPosition = ball.getPosition().plus(ball.getVelocity().times(Constants.TIMESTEP));
                if (type == Constants.BoardSide.TOP)    newBallPosition = new Vect(newBallPosition.x(), 20d);
                if (type == Constants.BoardSide.BOTTOM) newBallPosition = new Vect(newBallPosition.x(), 0d);
                if (type == Constants.BoardSide.LEFT)   newBallPosition = new Vect(20d, newBallPosition.y());
                if (type == Constants.BoardSide.RIGHT)  newBallPosition = new Vect(0d, newBallPosition.y());
                ball.setPosition(newBallPosition);

                // Send the ball to the server
                serverHandler.send(new BallOutMessage(ball.getPosition(), ball.getVelocity(), type));

                // Emit an event, notifying the Board that we took the ball out of play.
                return new BoardEvent(this);
            }
        }
        return null;
    }

    /**
     * Called during Board's step function. Each L unit of a sideWall is represented
     * by "." unless it is connected to a board, in which case the connected board's name is also printed
     *
     * @return string representation of sideWall for print out
     */
    public String stringRepresentation() {
        String stringRep = "";
        if (type == Constants.BoardSide.TOP || type == Constants.BoardSide.BOTTOM) {
            for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
                if (connectedBoardName != null && i < connectedBoardName.length()) {
                    stringRep += connectedBoardName.charAt(i);
                } else {
                    stringRep += ".";
                }
            }
            stringRep += "\n";
        } else if (type == Constants.BoardSide.LEFT || type == Constants.BoardSide.RIGHT) {
            for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
                if (connectedBoardName != null && i < connectedBoardName.length()) {
                    stringRep += connectedBoardName.charAt(i) + "\n";
                } else {
                    stringRep += "." + "\n";
                }
            }
        }
        return stringRep;
    }

    /**
     * @return startingPoint Vector representation of point at the top left corner of the wall
     */
    @Override
    public Vect getPosition() {
        return wall.p1();
    }

    /**
     * Which type of wall is this? what side is it?
     * @return the BoardSide which this wall is
     */
    public Constants.BoardSide getType() {
        return type;
    }

    /**
     * Connects this wall to a board over the network
     * @param name the name of the other board
     */
    public void connectToServer(String name) {
        connectedBoardName = name;
    }

    /**
     * Disconnects this wall from a board over the network
     */
    public void disconnectFromServer() {
        connectedBoardName = null;
    }

    /**
     * Sets the serverHandler so that the Wall is able to send
     * the ball to the server
     * @param sh the server handler
     */
    public void setServerHandler(ServerHandler sh) {
        serverHandler = sh;
    }

    /**
     * Return the name of the Wall.
     * Walls do not have names.
     * @return null name
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * @return the height of the wall.
     * Walls of type LEFT or RIGHT will return Constants.BOARD_HEIGHT
     * Walls of type TOP or BOTTOM will return 0.
     */
    @Override
    public double getHeight() {
        if (type == Constants.BoardSide.LEFT || type == Constants.BoardSide.RIGHT)
            return Constants.BOARD_HEIGHT;
        return 0d;
    }

    /**
     * @return the width of the wall.
     * Walls of type LEFT or RIGHT will return 0
     * Walls of type TOP or BOTTOM will return  Constants.BOARD_WIDTH.
     */
    @Override
    public double getWidth() {
        if (type == Constants.BoardSide.TOP || type == Constants.BoardSide.BOTTOM)
            return Constants.BOARD_WIDTH;
        return 0d;
    }

    /**
     * Walls have no special action.
     */
    @Override
    public void specialAction() {
        return;
    }

    /**
     * Verify the rep invariant of the class.
     *
     * Rep Invariant:
     * - connectedBoardName can be null if there is no active fuse.
     * - if connectedBoardName is not null, serverHandler must not be null
     */
    @Override
    public void checkRep() {
        if (connectedBoardName != null && serverHandler == null)
            throw new RepInvariantException("The wall seems connected but there is no serverHandler");
    }
}
