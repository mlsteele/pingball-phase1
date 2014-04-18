package client;

import common.Constants;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class Wall {
    private final Constants.BoardSide type;
    private boolean visible;
    private final LineSegment wall;

    /**
     * Ball Constructor
     * @param visible
     * @param wallType
     */
    public Wall(boolean visible, Constants.BoardSide wallType) {
        this.type = wallType;
        this.visible = visible;
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
     */
    public BoardEvent handleBall(Ball ball) {
        if (Geometry.timeUntilWallCollision(wall, ball.getCircle(), ball.getVelocity()) <= Constants.TIMESTEP){
            if (visible){
                Vect reboundVelocity = Geometry.reflectWall(wall, ball.getVelocity());
                ball.setVelocity(reboundVelocity);
            } else if (!visible){
                //TODO: create an event (a BoardEventforBoards)
                //  for when the ball is going to pass through a shared wall
            }
        }
        return null;
    }

    /**
     * Called during Board's step function. Each L unit of a sideWall is represented
     * by "*"
     *
     * @return string representation of sideWall for print out
     */
    public String stringRepresentation() {
        String stringRep = "";
        if (type == Constants.BoardSide.TOP || type == Constants.BoardSide.BOTTOM){
            for (int i = 0; i < Constants.BOARD_WIDTH; i++){
                stringRep += ".";
            }
            stringRep += "\n";
        } else if (type == Constants.BoardSide.LEFT || type == Constants.BoardSide.RIGHT){
            for (int i = 0; i < Constants.BOARD_HEIGHT; i++){
                stringRep += "." + "\n";
            }
        }
        return stringRep;
    }

    /**
     * @return startingPoint Vector representation of point at the top left corner of the absorber
     */
    public Vect getPosition() {
        return wall.p1();
    }

    public Constants.BoardSide getType() {
        return type;
    }
}
