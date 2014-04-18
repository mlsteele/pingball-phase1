package client.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.StringCanvas;
import common.netprotocol.NetworkMessage.DecodeException;

public class StringCanvasTest {
    /**
     * Tests for Board
     *
     * These tests cover the basic functionality of the StringCanvas class
     *
     * These tests are glass-box in that they know the inner representations
     * of Gadgets and Board. They know which Gadgets produce special Actions
     * (absorber and flipper) and how these Gadgets can subscribe to triggers.
     *
     * Testing strategy for StringCanvas(int width, int height, String filler) constructor
     * -initialized with filler = "\n": throws exception
     * -initialized with normal filler
     * -initialized with square (x,y)
     * -initialized with rectangular (x,y)
     *
     * Testing strategy for setRect(int x, int y, String rep) method
     * -initialized with x and y outside of width/height: throws exception
     * -initialized with rep string that extends beyond bounds of board
     * if initialized at x, y
     *
     * Do I need to test getString???
     */

    @Test(expected=IllegalArgumentException.class)
    public void constructorBigFiller() throws IllegalArgumentException{
        StringCanvas s = new StringCanvas(20,20,"ssss");
    }

    @Test
    public void constructorRegular(){
        StringCanvas s = new StringCanvas(20,20," ");
        assertEquals(s.getString().charAt(0), ' ');
    }

    @Test
    public void constructorRectangle(){
        StringCanvas s = new StringCanvas(20,1,"s");
        assertEquals(s.getString().charAt(4), 's');
    }

    @Test(expected=IllegalArgumentException.class)
    public void badXorY() throws IllegalArgumentException{
        StringCanvas s = new StringCanvas(20,20," ");
        s.setRect(40, 15, "!");
    }

}
