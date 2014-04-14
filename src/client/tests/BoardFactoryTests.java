package client.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for BoardFactory.
 * These tests cover parsing of files describing Boards.
 */
public class BoardFactoryTests {
    private static final Map<String, String> lines = new HashMap<String, String>();

    @BeforeClass
    public static void setUpBeforeClass() {
        lines.put("boardinfo1", "board name=ExampleB gravity=10.0 friction1=1.0 friction2=3.0");
        lines.put("ball1", "ball name=BallA x=1.8 y=4.5 xVelocity=10.4 yVelocity=10.3");
        lines.put("fire1", "fire trigger=Square action=FlipL");
        lines.put("fire2", "fire trigger=SquareB action=FlipL");
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
            bf += lines.get(key) + "\n";
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
            "fire trigger=Square action=FlipL\n" +
            "fire trigger=SquareB action=FlipL\n";
        assertEquals(expected, buildBF("boardinfo1", "ball1", "fire1", "fire2"));
    }
}
