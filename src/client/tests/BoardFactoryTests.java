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
 *
 * Partitions regarding comments:
 * - no comments
 * - comments before boardinfo header
 * - comments between boardinfo and other entries
 * - comments intermixed with entries
 * - comments at end of string
 * - includes blank lines
 *
 * Partitions regarding elements:
 * - boardinfo header
 * - parse a ball entry
 * - parse a squarebumper entry
 * - parse a circlebumper entry
 * - parse a trianglebumper entry
 * - parse a rightflipper entry
 * - parse a leftflipper entry
 * - parse a absorber entry
 * - parse a fire entry
 *
 * Partitions regarding header:
 * - header includes no parameters but name
 * - header includes gravity
 * - header includes friction1
 * - header includes friction2
 * - header includes gravity and friction2 but not friction1
 *
 * Partitions regarding spacing:
 * - includes uneven non-zero indentation
 * - includes spaces between equals in fields
 */
public class BoardFactoryTests {
    private static final Map<String, String> lines = new HashMap<String, String>();

    @BeforeClass
    public static void setUpBeforeClass() {
        lines.put("boardinfo1", "board name=ExampleB gravity=10.0 friction1=1.0 friction2=3.0");
        lines.put("boardinfo2", "board name=ExampleB");
        lines.put("boardinfo3", "board name=ExampleB gravity=10.0 friction2=3.4");
        lines.put("ball1", "ball name=BallA x=1.8 y=4.5 xVelocity=10.4 yVelocity=10.3");
        lines.put("square1", "squareBumper name=SquareA x=7 y=10");
        lines.put("square2", "squareBumper name=SquareB x=2 y=5");
        lines.put("circle1", "circleBumper name=Circle4 x=4 y=3");
        lines.put("triangle1", "triangleBumper name=Tri1 x=8 y=9 orientation=270");
        lines.put("flipperL1", "leftFlipper name=FlipL x=10 y=7 orientation=90");
        lines.put("flipperR1", "leftFlipper name=FlipR x=3 y=17 orientation=270");
        lines.put("absorber1", "absorber name=Abs x=0 y=19 width=20 height=1 ");
        lines.put("fire1", "fire trigger=SquareA action=FlipL");
        lines.put("fire2", "fire trigger=SquareB action=FlipR");
        lines.put("comment1", "# just a regular comment");
        lines.put("comment2", "# a comment with some rEa11y exc!t!ng s@mb0l$. ");
        lines.put("blankline", "");
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
        assertNotNull(BoardFactory.parse(buildBF("boardinfo1", "ball1", "square1", "flipperL1", "fire1")));
    }

    @Test public void testBoardCommentsAtEnd() {
        assertNotNull(BoardFactory.parse(buildBF("boardinfo1", "ball1", "square1", "flipperL1", "fire1", "comment1", "comment2")));
    }

    @Test public void testBoardCommentsAfterBoardInfo() {
        assertNotNull(BoardFactory.parse(buildBF("boardinfo1", "comment1", "comment2", "ball1", "square1", "flipperL1", "fire1")));
    }

    @Test public void testBoardCommentsAtStart() {
        assertNotNull(BoardFactory.parse(buildBF("comment1", "comment2", "boardinfo1", "ball1", "square1", "flipperL1", "fire1")));
    }

    @Test public void testBoardCommentsWithEntries() {
        assertNotNull(BoardFactory.parse(buildBF("boardinfo1", "comment1", "ball1", "square1", "fire1", "square2", "flipperL1", "flipperR1", "comment2", "fire2")));
    }
}
