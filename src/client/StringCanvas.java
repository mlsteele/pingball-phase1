package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.Constants;
import client.gadgets.Gadget;

/**
 *
 * rep invariant: all strings in userBoard representation are either characters in the alphabet,
 * an * to represent a ball, or contained within this Gadget-string set: {=, 0, #, /, \, |, -, .}
 * (this assumes that we only accept alphabet characters for Board names, which may not be true...)
 */

public class StringCanvas {

    private final int columns;
    private final int rows;
    private String filler;
    private String[][] userBoard;
    private final int wallBorderSize;

    /**
     * Create a new String Canvas
     * @param width board width
     * @param height board height
     * @param name name of board
     * @param filler String that goes between gadgets as a placeholder. Default is " "
     * @param gadgets list of gadgets on the board.
     *                caller must never modify this list.
     */
    public StringCanvas(int width, int height, String filler, String name) {
        columns = width;
        rows = height;
        this.filler = filler;
        wallBorderSize = 1;
        userBoard = new String[rows + 2*wallBorderSize][columns + 2*wallBorderSize];


        //TODO: put board name into top row (or side column?) of board string

        //Create walls
        for (int i = 0; i < rows + 2; i++){
            for (int j = 0; j < columns + 2; j++){
                if (i != 0 && i != rows - 1 && j != 0 && j != columns - 1){
                    userBoard[i][j] = filler;
                } else{
                    userBoard[i][j] = ".";
                }
            }
        }
    }

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
                userBoard[y+i+wallBorderSize][x+j+wallBorderSize] = "" + lines[i].charAt(j);
            }
        }
    }

    /**
     * Takes the representation of single-character gadgets (bumpers)
     * or a ball (*) and incorporates them into the board representation
     *
     * @param x must be >= 0 and <= columns
     * @param y must be >= 0 and <= rows
     * @param rep stringRepresentation of gadget object
     */
    public void setChar(int x, int y, String rep){
        userBoard[y+wallBorderSize][x+wallBorderSize] = rep;
    }


    /**
     * @return String representation of Board
     */
    public String getString(){
        String board = "";
        for(int j = 0; j < this.rows; j++){
            for(int i = 0; i < this.columns; i++){
                board += userBoard[j][i];
                board += " ";
            }
            board = board.substring(0, board.length() - 1);
            board += "\n";
        }
        return board;
    }


}
