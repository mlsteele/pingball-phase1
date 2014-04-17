package client;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import common.Constants;

public class Wall {
    private final String name;
    private final Constants.BoardSide type;
    private boolean invisible;
    private final LineSegment wall;

    /**
     * Constructor that takes the shape of the wall.
     *
     * @param name unique String identifier for SideWall object
     * @param invisible Indicates whether wall is visible (false) or invisible (true)
     * @param wallType BoardSide enum indicating if the wall is top, bottom, left, or right
     * (BoardSide enum contained in common.Constants class)
     */
    public Wall(String name, boolean invisible, Constants.BoardSide wallType) {
        this.name = name;
        this.invisible = invisible;
        type = wallType;
        if (type == Constants.BoardSide.TOP){
            wall = new LineSegment(0, 0, Constants.BOARD_WIDTH, 0);
        } else if (type == Constants.BoardSide.BOTTOM){
            wall = new LineSegment(0, Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        } else if (type == Constants.BoardSide.LEFT){
            wall = new LineSegment(0, 0, 0, Constants.BOARD_HEIGHT);
        }else{
            wall = new LineSegment(Constants.BOARD_WIDTH, 0, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        }
    }

    public BoardEvent handleBall(Ball ball) {
        if (!invisible && Geometry.timeUntilWallCollision(wall, ball.getCircle(), ball.getVelocity()) < Constants.TIMESTEP) {
            Vect velocity = Geometry.reflectWall(wall, ball.getVelocity());
            ball.setVelocity(velocity);
            ball.setPosition(ball.getCircle().getCenter().plus(velocity.times(Constants.TIMESTEP)));
            return new BoardEvent(this);
        }
        return null;
    }
}
