package client.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for StringCanvas.
 * These tests cover converting Boards to
 *
 * These tests are not very deep.
 * They only test that a parse succeeds for valid inputs and that
 * a Board is output.
 *
 * Partitions regarding getRect:
 * - no gadgets
 * - board filled with gadgets
 * - overlapped gadgets
 * - comments intermixed with entries
 * - comments at end of string
 * - includes blank lines
 *
 * Partitions regarding walls:
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
 * Partitions regarding field values:
 * - parse NAME with a numeric in it like zer0
 * - parse NAME with an underscore
 * - parse negative float
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

public class StringCanvasTest {
    @BeforeClass
    public static void setUpBeforeClass() {

    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}
