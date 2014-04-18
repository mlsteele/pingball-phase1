package client;

import common.Constants;
import common.RepInvariantException;
import common.netprotocol.BallOutMessage;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 *
 *
 * Rep Invariant:
 * * if connectedBoardName == null iff visible == false
 */
public class Wall {
    private final Constants.BoardSide type;
    private boolean visible;
    private final LineSegment wall;
    private String connectedBoardName;
    private ServerHandler serverHandler;
    private final Board board;

    /**
     * Ball Constructor
     * @param visible
     * @param wallType
     */
    public Wall(boolean visible, Constants.BoardSide wallType, Board board) {
        this.type = wallType;
        this.visible = visible;
        this.board = board;
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
     * When physics' timeUntilWallCollision method detects that a ball from Board will hit a SideWall,
     * the wall will reflect the ball with the appropriate physics methods
     *
     * @param Ball object from Board
     * @return true if the wall is taking the ball
     * TODO this is janky
     */
    public boolean handleBall(Ball ball) {
        if (Geometry.timeUntilWallCollision(wall, ball.getCircle(), ball.getVelocity()) <= Constants.TIMESTEP){
            if (visible){
                Vect reboundVelocity = Geometry.reflectWall(wall, ball.getVelocity());
                ball.setVelocity(reboundVelocity);
            } else if (!visible){
                //TODO: create an event (a BoardEventforBoards?)
                //  for when the ball is going to pass through a shared wall
                //  or maybe we should just have a board method takeBall() or something

                // ball position will be set to be relative to the other board
                Vect deltaVelFromBoardCross;
                switch (type) {
                    case TOP:
                        deltaVelFromBoardCross = new Vect(0d, -20d);
                        break;
                    case BOTTOM:
                        deltaVelFromBoardCross = new Vect(0d, 20d);
                        break;
                    case LEFT:
                        deltaVelFromBoardCross = new Vect(20d, 0d);
                        break;
                    default: //case RIGHT:
                        deltaVelFromBoardCross = new Vect(-20d, 0d);
                        break;
                }

                // move ball to collide with or pass through wall, and delta it to be relative to new board
                // this must be in 1 step because doing 1 then the other would cause ball to have an invalid state & checkRep would fail
                ball.setPosition(
                        ball.getPosition()
                            .plus(ball.getVelocity().times(Constants.TIMESTEP))
                            .plus(deltaVelFromBoardCross)
                );

                // send the ball to the server
                // TODO do we need to send type?
                serverHandler.send(new BallOutMessage(ball.getPosition(), ball.getVelocity(), type));

                // tell the board we took the ball.
                return true;
            }
        }
        return false;
    }

    /**
     * Called during Board's step function. Each L unit of a sideWall is represented
     * by "." unless it is connected to a board, in which case the connected board's name is also printed
     *
     * @return string representation of sideWall for print out
     */
    public String stringRepresentation() {
        // TODO lots of copypaste here
        String stringRep = "";
        if (type == Constants.BoardSide.TOP || type == Constants.BoardSide.BOTTOM){
            for (int i = 0; i < Constants.BOARD_WIDTH; i++){
                if (connectedBoardName != null && i-1 > 0 && i-1 < connectedBoardName.length()) {
                    stringRep += connectedBoardName.substring(i-1, i);
                } else {
                    stringRep += ".";
                }
            }
            stringRep += "\n";
        } else if (type == Constants.BoardSide.LEFT || type == Constants.BoardSide.RIGHT){
            for (int i = 0; i < Constants.BOARD_HEIGHT; i++){
                if (connectedBoardName != null && i-1 > 0 && i-1 < connectedBoardName.length()) {
                    stringRep += connectedBoardName.substring(i-1, i);
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
    public Vect getPosition() {
        return wall.p1();
    }

    public Constants.BoardSide getType() {
        return type;
    }

    /**
     * Connects this wall to a board over the network
     * @param name the name of the other board
     */
    public void connectToServer(String name) {
        visible = true;
        connectedBoardName = name;
    }

    /**
     * Disconnects this wall from a board over the network
     */
    public void disconnectFromServer() {
        visible = false;
        connectedBoardName = null;
    }

    public void setServerHandler(ServerHandler sh) {
        serverHandler = sh;
    }

    /**
     * Rep Invariant:
     * * if connectedBoardName == null iff visible == false
     */
    private void checkRep() {
        if (visible && connectedBoardName != null || !visible && connectedBoardName == null) {
            throw new RepInvariantException("visible: " + visible + "; board name: " + connectedBoardName);
        }
    }
}
