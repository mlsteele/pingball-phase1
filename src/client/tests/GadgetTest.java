package client.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import common.Constants;
import common.RepInvariantException;
import physics.Vect;
import client.Ball;
import client.gadgets.Absorber;
import client.gadgets.Flipper;
import client.gadgets.StaticBumper;

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
 * +Flipper:  input = normal; Flipper initialized with no rotation
 * +Bumper:
 *
 * Partitions for handleBall()
 * NOTE: only default handleBall() returns are covered here; others are covered in BoardTest
 * because otherwise handleBall() requires motion and update of Ball movement
 * +Absorber: Absorber returns a new BoardEvent with null as the triggerer
 * +Flipper: Flipper returns a new BoardEvent with null as the triggerer
 * +Bumper: Bumper returns a new BoardEvent with null as the triggerer
 *
 * Partitions for getName()
 * +Absorber: Absorber returns the name it was initialized with
 * +Flipper: Flipper returns the name it was initialized with
 * +Bumper: Bumper returns the name it was initialized with
 *
 * Partitions for getPosition()
 * +Absorber: Absorber returns the starting point it was initialized with
 * +Flipper: Flipper returns the starting point it was initialized with
 * +Bumper: Bumper returns the starting point it was initialized with
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
 * +Bumper:   square bumper string = #
 *            circle bumper string = 0
 *            triangle bumper string = \ or /
 *
 * Partitions for specialAction()
 * +Absorber: Newly-constructed Absorber will not release a ball
 *            NOTE: Absorber's specialAction (release contained ball) is partially tested in Board
 *            because it requires an initialized Absorber that has dynamically received balls
 * +Flipper:  Flipper orientation switches both ways
 * +Bumper:   Nothing happens
 */
public class GadgetTest {
    private static final Absorber regAbsorber = new Absorber("absorber", new Vect(0,17), 20, 3);
    private static final Flipper regLeftFlipper = new Flipper("left", new Vect(8,2), 0, Constants.FlipperType.LEFT);
    private static final Flipper regRightFlipper = new Flipper("right", new Vect(11,2), 0, Constants.FlipperType.RIGHT);
    private static final StaticBumper squareBumper = new StaticBumper("square", Constants.BumperType.SQUARE, new Vect(0,0));
    private static final StaticBumper circleBumper = new StaticBumper("circle", Constants.BumperType.CIRCLE, new Vect(1,1));
    private static final StaticBumper triUpBumper = new StaticBumper("triUp", Constants.BumperType.TRIUP, new Vect(2,2));
    private static final StaticBumper triDownBumper = new StaticBumper("triDown", Constants.BumperType.TRIDOWN, new Vect(3,3));

    /**
     * Test Absorber constructor, getWidth, getHeight, getName, and getPosition
     */
    @Test
    public void absorberConstructor() {
        assertTrue(regAbsorber.ballsContained() == 0);
        assertTrue(regAbsorber.getName() == "absorber");
        assertTrue(regAbsorber.getWidth() == 20);
        assertTrue(regAbsorber.getHeight() == 3);
        assertTrue(regAbsorber.getPosition().x() == 0);
        assertTrue(regAbsorber.getPosition().y() == 17);
    }

    /**
     * Test Flipper constructor, getWidth, getHeight, getName, and getPosition
     */
    @Test
    public void flipperConstructor() {
        assertFalse(regLeftFlipper.isRotated());
        assertFalse(regRightFlipper.isRotated());
        assertTrue(regLeftFlipper.getName() == "left");
        assertTrue(regLeftFlipper.getWidth() == 2);
        assertTrue(regLeftFlipper.getHeight() == 2);
        assertTrue(regLeftFlipper.getPosition().x() == 8);
        assertTrue(regLeftFlipper.getPosition().y() == 2);
    }

    /**
     * Test bumper constructor, getWidth, getHeight, getName, and getPosition
     */
    @Test
    public void bumperConstructor() {
        assertTrue(squareBumper.getName() == "square");
        assertTrue(squareBumper.getWidth() == 1);
        assertTrue(squareBumper.getHeight() == 1);
        assertTrue(squareBumper.getPosition().x() == 0);
        assertTrue(squareBumper.getPosition().y() == 0);
    }

    /**
     * Test bad absorber constructor
     */
    @Test(expected=RepInvariantException.class)
    public void badAbsorber() throws RepInvariantException{
        Absorber badAbsorber = new Absorber("absorber", new Vect(0,17), 23, 3);
    }

    /**
     * Test boardEvent absorber
     */
    @Test(expected=NullPointerException.class)
    public void absorberBoardEvent() throws NullPointerException{
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4d,5d));;
        assertFalse(regAbsorber.handleBall(b).getTriggerer() == regAbsorber);
    }

    /**
     * Test boardEvent flipper
     */
    @Test(expected=NullPointerException.class)
    public void flipperBoardEvent() throws NullPointerException{
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4d,5d));;
        assertFalse(regLeftFlipper.handleBall(b).getTriggerer() == regLeftFlipper);
    }

    /**
     * Test boardEvent bumper
     */
    @Test(expected=NullPointerException.class)
    public void bumperBoardEvent() throws NullPointerException{
        Ball b = new Ball(Constants.BALL_RADIUS, new Vect(2,3), new Vect(4d,5d));;
        assertFalse(circleBumper.handleBall(b).getTriggerer() == circleBumper);
    }


    /**
     * Test absorber stringRepresentation()
     * number of lines in String equals Absorber height
     * first line is only "=" signs
     */
    @Test
    public void absorberString() {
        String abs = regAbsorber.stringRepresentation();
        String[] lines = abs.split("\n");
        assertTrue(lines.length == regAbsorber.getHeight());
        for (int i = 0; i < lines.length; i++){
            assertTrue(regAbsorber.getWidth() == lines[i].length());
        }
    }

    /**
     * Test flipper stringRepresentation()
     * size of string
     */
    @Test
    public void flipperString() {
        String flip = regRightFlipper.stringRepresentation();
        assertEquals(flip.length(), 5);
    }

    /**
     * Test bumper stringRepresentation()
     */
    @Test
    public void bumperString() {
        assertEquals(squareBumper.stringRepresentation(), "#");
        assertEquals(circleBumper.stringRepresentation(), "O");
        assertEquals(triUpBumper.stringRepresentation(), "/");
        assertEquals(triDownBumper.stringRepresentation(), "\\");
    }

    /**
     * Test absorber specialAction()
     */

    @Test
    public void absorberSpecial() {
        regAbsorber.specialAction();
        assertEquals(regAbsorber.ballsContained(), 0);
    }

    /**
     * Test flipper specialAction()
     */

    @Test
    public void flipperSpecial() {
        assertFalse(regLeftFlipper.isRotated());
        regLeftFlipper.specialAction();
        assertTrue(regLeftFlipper.isRotated());
    }


}
