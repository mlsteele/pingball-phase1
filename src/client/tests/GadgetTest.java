package client.tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class GadgetTest {
    /**
     * Tests for Gadgets
     *
     * These tests cover the basic functionality of the Gadgets class. For each
     * method in the Gadget interface, the tests will look to each type of
     * Gadget (Absorber, Flipper, StaticBumper) and test their implementation of
     * the method.
     *
     * These tests are glass-box in that they know the inner representations
     * of Gadgets and Board. They know which Gadgets produce special Actions
     * (absorber and flipper) and how these Gadgets can subscribe to triggers.
     *
     * Partitions for Gadget constuctor (input varies)
     * +Absorber: input = normal; Absorber is initialized with no balls
     *            input = initial width or height > 20
     *            input =
     * +Flipper:  Flipper initialized with no rotation
     *
     * +Bumper:
     *
     * Partitions for handleBall()
     * +Absorber: Absorber returns a new BoardEvent with itself as the trigger argument
     * +Flipper: Flipper returns a new BoardEvent with itself as the trigger argument
     * +Bumper: Bumper returns a new BoardEvent with itself as the trigger argument
     *
     * Partitions for getName()
     * +Absorber: Absorber returns the name it was initialized with
     * +Flipper: Flipper returns the name it was initialized with
     * +Bumper: Bumper returns the name it was initialized with
     *
     * Partitions getHeight()
     * +Absorber: Absorber returns the height it was initialized with
     * +Flipper: Flipper returns 2
     * +Bumper: Bumper returns 1
     *
     * Partitions for getWidth()
     * +Absorber: Absorber returns the width it was initialized with
     * +Flipper: Flipper returns 2
     * +Bumper: Bumper returns 1
     *
     * Partitions for stringRepresentation()
     * +Absorber: number of lines in String equals Absorber height
     *            number of letters in each line is equal
     *            first line is only "=" signs
     * +Flipper:  size of string and rows
     *            every letter is contained in {"\", "/", "-", " "}
     * +Bumper:   square bumper string = #
     *            circle bumper string = 0
     *            triangle bumper string = \ or /
     *
     * Partitions for specialAction()
     * +Absorber:
     * -Test that newly-constructed Absorber will not release a ball
     * -Absorber's specialAction (release contained ball) is partially tested in Board
     * because it requires
     * +Flipper:
     * -Test Flipper orientation switching
     * +Bumper:
     * -Test that nothing happens
     */

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}
