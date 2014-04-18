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
     * +Absorber:
     * +Flipper:
     * +Bumper:
     *
     * Partitions for getName()
     * +Absorber:
     * check
     * +Flipper:
     * +Bumper:
     *
     * Partitions getHeight()
     * +Absorber:
     * +Flipper:
     * +Bumper:
     *
     * Partitions for getWidth()
     * +Absorber:
     * +Flipper:
     * +Bumper:
     *
     * Partitions for stringRepresentation()
     * +Absorber:
     * +Flipper:
     * +Bumper:
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
