package client;

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

    /**
     * Create a new String Canvas
     * @param width board width
     * @param height board height
     * @param filler String that goes between gadgets as a placeholder. " " is recommended
     */
    public StringCanvas(int width, int height, String filler) {
        columns = width;
        rows = height;
        userBoard = new String[rows][columns];

        //create skeleton
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                userBoard[i][j] = filler;
            }
        }
    }

    /**
     * Takes string representations and incorporates them
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
                userBoard[y+i][x+j] = "" + lines[i].charAt(j);
            }
        }
    }

    /**
     * @return String representation of Board
     */
    public String getString(){
        String board = "";
        for(int j = 0; j < this.rows; j++){
            for(int i = 0; i < this.columns; i++){
                board += userBoard[j][i];
            }
            board += "\n";
        }
        return board;
    }

    /**
     * Rep invariant: boundaries should be
     */
    public void checkRep() {
        //flippers must be allowed to rotate 2 Ls below their rotation point

    }


}
