package client.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import client.boardlang.BoardFactory;

/**
 * Tests for BoardFactory.
 * These tests cover parsing of files describing Boards.
 *
 * These tests are not very deep.
 * They only test that a parse succeeds for valid inputs and that
 * a Board is output.
 */
public class BoardFactoryTests {
    private static final Map<String, String> lines = new HashMap<String, String>();

    @BeforeClass
    public static void setUpBeforeClass() {
        lines.put("boardinfo1", "board name=ExampleB gravity=10.0 friction1=1.0 friction2=3.0");
        lines.put("ball1", "ball name=BallA x=1.8 y=4.5 xVelocity=10.4 yVelocity=10.3");
        lines.put("square1", "squareBumper name=SquareA x=7 y=10");
        lines.put("square2", "squareBumper name=SquareB x=4 y=91");
        lines.put("flipL1", "leftFlipper name=FlipL x=10 y=7 orientation=90");
        lines.put("flipR1", "leftFlipper name=FlipR x=3 y=20 orientation=270");
        lines.put("fire1", "fire trigger=SquareA action=FlipL");
        lines.put("fire2", "fire trigger=SquareB action=FlipR");
        lines.put("comment1", "# just a regular comment");
        lines.put("comment2", "# a comment with some rEa11y exc!t!ng s@mb0l$. ");
    }

    /**
     * Build a boardfile string for testing.
     * This method is a helper method for other tests.
     * It takes a list of keys specifying longer strings
     * to compose a boardfile string from.
     *
     * @param  keys keys to build this boardfile.
     * @return string representation of a board.
     */
    private static String buildBF(String... keys) {
        String bf = "";
        for (String key : keys) {
            String line = lines.get(key);
            assertNotNull(line);
            bf += line + "\n";
        }
        return bf;
    }

    /**
     * Make sure that the buildBF helper method is working.
     */
    @Test public void testBuildBF() {
        String expected =
            "board name=ExampleB gravity=10.0 friction1=1.0 friction2=3.0\n" +
            "ball name=BallA x=1.8 y=4.5 xVelocity=10.4 yVelocity=10.3\n" +
            "fire trigger=SquareA action=FlipL\n" +
            "fire trigger=SquareB action=FlipR\n";
        assertEquals(expected, buildBF("boardinfo1", "ball1", "fire1", "fire2"));
    }

    @Test public void testBoardNoComments() {
        assertNotNull(BoardFactory.parse(buildBF("boardinfo1", "ball1", "square1", "flipL1", "fire1")));
    }

    @Test public void testBoardCommentsAtEnd() {
        assertNotNull(BoardFactory.parse(buildBF("boardinfo1", "ball1", "square1", "flipL1", "fire1", "comment1", "comment2")));
    }

    @Test public void testBoardCommentsAfterBoardInfo() {
        assertNotNull(BoardFactory.parse(buildBF("boardinfo1", "comment1", "comment2", "ball1", "square1", "flipL1", "fire1")));
    }

    @Test public void testBoardCommentsAtStart() {
        assertNotNull(BoardFactory.parse(buildBF("comment1", "comment2", "boardinfo1", "ball1", "square1", "flipL1", "fire1")));
    }

    @Test public void testBoardCommentsWithEntries() {
        assertNotNull(BoardFactory.parse(buildBF("boardinfo1", "comment1", "ball1", "square1", "fire1", "square2", "flipL1", "flipR1", "comment2", "fire2")));
    }
}
