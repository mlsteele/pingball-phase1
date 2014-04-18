package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.Constants;
import common.RepInvariantException;
import client.gadgets.Gadget;

/**
 * String Canvas takes the string representations of a Boards' walls, balls, and Gadgets, and
 * condenses them into one string for the board to print.
 *
 * rep invariant: all strings in userBoard representation are either characters in the alphabet, spaces,
 * an * to represent a ball, or contained within this Gadget-string set: {=, 0, #, /, \, |, -, .}
 * (this assumes that we only accept alphabet characters for Board names, which may not be true...)
 *
 * Thread Safety: not threadsafe
 */

public class StringCanvas {

    private final int columns;
    private final int rows;
    private String[][] userBoard;
    private final int wallBorderSize;

    /**
     * Create a new String Canvas
     * @param width board width
     * @param height board height
     * @param filler String that goes between gadgets as a placeholder. " " is recommended
     */
    public StringCanvas(int width, int height, String filler) {
        columns = width;
        rows = height;
        wallBorderSize = 1;
        userBoard = new String[rows + 2*wallBorderSize][columns + 2*wallBorderSize];

        //create skeleton with walls
        for (int i = 0; i < rows + 2; i++){
            for (int j = 0; j < columns + 2; j++){
                if (isOnBoundary(j,i)){
                    userBoard[i][j] = ".";
                }
                else{
                    userBoard[i][j] = filler;
                }
            }
        }
    }


    //probably unnecessary: now done in constructor instead.
    public void setWalls(Constants.BoardSide type, String rep){

        /*        int x;
        int y;
        String[] lines = rep.split("\n");
        if (type == Constants.BoardSide.TOP || type == Constants.BoardSide.LEFT){
            x = 0;
            y = 0;
        } else if (type == Constants.BoardSide.BOTTOM){
            x = 0;
            y = Constants.BOARD_HEIGHT + 2*wallBorderSize;
        } else {
            x = Constants.BOARD_WIDTH + 2*wallBorderSize;
            y = 0;
        }
        for (int i = 0; i < lines.length; i++){
            for (int j = 0; j < lines[i].length(); j++){
                int xCoord = y + i;
                int yCoord = x + j;
                //userBoard[yCoord][xCoord] = "" + lines[i].charAt(j);
            }
        }*/
    }

    /**
     * Joined walls must have a name written on them in their string
     * representation to indicate which walls with what names are being shared.
     *
     * @param wall Wall to place name on
     * @param name String user wants printed on the side of their shared Board
     *        (recommended: board.name)
     */
    //TODO: addWallName(Wall wall, String name);

    /**
     * Takes the representation of rectangular gadgets and incorporates them
     * into the board representation
     *
     * @param x must be >= 0 and <= columns
     * @param y must be >= 0 and <= rows
     * @param rep stringRepresentation of gadget object
     */
    public void setRect(int x, int y, String rep){
        String[] lines = rep.split("\n");
        for (int i = 0; i < lines.length; i++){
            for (int j = 0; j < lines[i].length(); j++){
                userBoard[y+i+1][x+j+1] = "" + lines[i].charAt(j);
            }
        }
    }

    /**
     * @return String representation of Board
     */
    public String getString(){
        String board = "";
        for(int j = 0; j < this.rows + 2; j++){
            for(int i = 0; i < this.columns + 2; i++){
                board += userBoard[j][i];
            }
            board += "\n";
        }
        return board;
    }

    /**
     * Check if a coordinate point is on the outer boundary of the board.
     * @param x coordinate
     * @param y coordinate
     * @return   boolean indicating whether point is on board boundary.
     */
    private boolean isOnBoundary(int x, int y) {
        return x == 0
            || x == Constants.BOARD_WIDTH + 1
            || y == 0
            || y == Constants.BOARD_HEIGHT + 1;
    }

    /**
     * Rep invariant: boundaries should be
     */
    public void checkRep() {
        //flippers must be allowed to rotate 2 Ls below their rotation point

    }


}
