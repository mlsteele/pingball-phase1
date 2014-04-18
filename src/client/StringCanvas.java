package client;
import common.RepInvariantException;

/**
 * String Canvas creates a string width X height (according to constructor).
 * Each character is filler
 *
 * rep invariant: userBoard cannot have any nulls
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
     * @param filler String that goes between gadgets as a placeholder.
     *          Cannot be null. filler.length() must equal 1.
     *          " " is recommended
     */
    public StringCanvas(int width, int height, String filler) {
        if (filler.length() > 1 || filler == null){
            throw new IllegalArgumentException();
        }
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
     * @param rep stringRepresentation must fit within StringCanvas
     */
    public void setRect(int x, int y, String rep){
        if (x < 0 || y < 0 || x > columns || y > rows){
            throw new IllegalArgumentException();
        }
        String[] lines = rep.split("\n");
        if (lines.length + y > rows || lines[0].length() + x > columns){
            throw new IllegalArgumentException();
        }
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
     * Rep invariant: userBoard cannot have any nulls
     */
    public void checkRep() {
        for(int j = 0; j < this.rows; j++){
            for(int i = 0; i < this.columns; i++){
                if (userBoard[j][i] == null){
                    throw new RepInvariantException("Rep invariant violated.");
                }
            }
        }
    }


}
