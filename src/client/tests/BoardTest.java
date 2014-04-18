package client.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import common.Constants;
import physics.Angle;
import physics.LineSegment;
import physics.Vect;
import client.Ball;
import client.Board;
import client.BoardEventSubscription;
import client.SimpleFileReader;
import client.boardlang.BoardFactory;
import client.gadgets.Absorber;
import client.gadgets.Flipper;
import client.gadgets.Gadget;
import client.gadgets.StaticBumper;

public class BoardTest {

    /**
     * Tests for Board
     *
     * These tests cover the basic functionality of the Board class and the
     * Gadgets, Walls, and Balls it is equipped with.
     *
     * These tests are glass-box in that they know the inner representations
     * of Gadgets and Board. They know which Gadgets produce special Actions
     * (absorber and flipper) and how these Gadgets can subscribe to triggers.
     *
     * Testing strategy for Board construction
     * -initialized with overlapping Gadgets
     * -initialized with Gadgets that extend outside the Board
     * -initialized with a regular set of Gadgets
     *
     * Testing strategy for Gadgets and Walls:
     * -each Gadget/Wall will underGo a receivesBall Test to make sure it can respond to
     * the trigger of a Ball approaching sufficiently close to it
     * +Absorber            contains no balls
     *                      contains more balls than it has space in its
     *                      string representation
     * +Flipper:            Proper rotation given initial orientation/type
     * +StaticBumper:       test different coefficients of reflection
     * +Wall                Fused / Not Fused
     *                      Printing names
     *
     * Testing strategy for BoardEvent and BoardEventSubscription activity:
     * -Each Gadget that can listen for a trigger (it has a special action) will
     * be tested to see if it can respond to BoardEvents from any type of Gadget
     * (including its self)
     *
     * Testing strategy for sharing Walls/Balls activity:
     * -Each number of neighboring Boards from 0 to 4 will be tested for general correctness
     * -General "passing a ball."
     * -Absorber generating a ball and sending it upward into another Board
     *
     * More examples of edge cases we should add:
     * -null initializations
     *
     *Diagnostic printing (not to submit in final):
     *System.out.println("LINE: " + line);
                System.out.println("Velocity1: " + ball.getVelocity());
                System.out.println("XCoord: " + ball.getCircle().getCenter().x());
                System.out.println("YCoord: " + ball.getCircle().getCenter().y());
     */


    /**
     * Testing recievesBall for Absorber
     */
    @Test
    public void absorberRecievesBall() {
        List<Gadget> boardGadgets = new ArrayList<Gadget>();
        int numIterations = 40; //times to activate board.step()
        Vect ballStart = new Vect(9,0);
        Vect ballStart2 = new Vect(11,0);
        Vect absorberStart = new Vect(0,15);
        double velocityMagnitude = 0; //L/s
        Vect ball1Velocity = new Vect(1, velocityMagnitude); //falls downward when Velocity is positive
        Vect ball2Velocity = new Vect(1, 5);
        Absorber absorber = new Absorber("Absorber", absorberStart, 20, 4);
        Ball ball = new Ball(0.25, ballStart, ball1Velocity);
        Ball ball2 = new Ball(0.25, ballStart2, ball2Velocity);
        boardGadgets.add(absorber);

        Board testBoardA = new Board("testBoardA", boardGadgets, 25, 0, 0);
        testBoardA.addBall(ball);
        testBoardA.addBall(ball2);

        for(int i = 0; i < numIterations; i++){
            String out = testBoardA.step();
            System.out.println(out);
        }

        assertEquals(absorber.ballsContained(), 2);
        absorber.specialAction();
        assertEquals(absorber.ballsContained(), 1);
        absorber.specialAction();
        assertEquals(absorber.ballsContained(), 0);
    }

    /**
     * Testing recievesBall for Absorber that triggers itself
     */
    @Test
    public void refAbsorberRecievesBall() {
        List<Gadget> boardGadgets = new ArrayList<Gadget>();
        int numIterations = 400; //times to activate board.step()
        Vect ballStart = new Vect(9,0);
        Vect ballStart2 = new Vect(11,0);
        Vect absorberStart = new Vect(0,15);
        double velocityMagnitude = 0; //L/s
        Vect ball1Velocity = new Vect(1, velocityMagnitude); //falls downward when Velocity is positive
        Vect ball2Velocity = new Vect(1, 5);
        Absorber absorber = new Absorber("Absorber", absorberStart, 20, 4);
        Ball ball = new Ball(0.25, ballStart, ball1Velocity);
        Ball ball2 = new Ball(0.25, ballStart2, ball2Velocity);
        boardGadgets.add(absorber);

        BoardEventSubscription absorbed = new BoardEventSubscription(absorber, absorber);
        Board testBoardA = new Board("testBoardA", boardGadgets, 25, 0, 0);
        testBoardA.addSubscription(absorbed);
        testBoardA.addBall(ball);
        testBoardA.addBall(ball2);

        for(int i = 0; i < numIterations; i++){
            String out = testBoardA.step();
            System.out.println(out);
        }

        /*how do I make this an test?*/
    }


    /**
     * Testing recievesBall for Bumper
     */


    public void bumperRecievesBall() {
        List<Gadget> boardGadgets = new ArrayList<Gadget>();
        int numIterations = 20; //times to activate board.step()
        Vect ballStart = new Vect(9,4);
        Vect squareStart = new Vect(9,7);
        Vect absorberStart = new Vect(0,16);
        double velocityMagnitude = 3; //L/s
        Vect initialBallVelocity = new Vect(1, velocityMagnitude); //falls downward when y-velocity is positive
        StaticBumper square = new StaticBumper("SquareB", Constants.BumperType.SQUARE, squareStart);
        Absorber absorber = new Absorber("Absorber", absorberStart, 20, 4);
        Ball ball = new Ball(0.25, ballStart, initialBallVelocity);
        boardGadgets.add(square);
        boardGadgets.add(absorber);

        Board testBoardA = new Board("testBoardA", boardGadgets, 25, 0, 0);
        testBoardA.addBall(ball);

        for(int i = 0; i < numIterations; i++){
            String out = testBoardA.step();
            System.out.println(out);
        }

        assertTrue(square.getHits() >= 1);
    }

    /**
     * Testing recievesBall for Flipper
     */

    public void flipperRecievesBall() {
        List<Gadget> boardGadgets = new ArrayList<Gadget>();
        int numIterations = 10; //times to activate board.step()
        Vect ballStart = new Vect(9,7);
        Vect flipperStart = new Vect(9,9);
        double velocityMagnitude = 5; //L/s
        Vect initialBallVelocity = new Vect(1, velocityMagnitude); //falls downward when Velocity is positive
        Flipper flipper = new Flipper("FlipperA", flipperStart, 270, Constants.FlipperType.LEFT);
        Ball ball = new Ball(0.25, ballStart, initialBallVelocity);
        boardGadgets.add(flipper);

        Board flipBoardA = new Board("flipBoardA", boardGadgets, 25, 0, 0);
        flipBoardA.addBall(ball);

        for(int i = 0; i < numIterations; i++){
            String out = flipBoardA.step();
            System.out.println(out);
        }

        assertTrue(flipper.isRotated());
    }

    /**
     * Visual test to see if a simple Board can maintain play
     * Note: this is the board that makes you realize how sad walls are
     */

    public void BallLeavesBoard() {
        List<Gadget> boardGadgets = new ArrayList<Gadget>();
        Vect ballStart = new Vect(1,1);
        Vect startForTriA = new Vect(15,15);
        Vect startForSquareB = new Vect(4,8);
        Vect startForCircleC = new Vect(4,8);
        Vect startForSquareD = new Vect(5,8);
        Vect startForSquareE = new Vect(9,9);
        Vect startForAbsorber = new Vect(0,2);
        double velocityMagnitude = 0; //L/s
        Vect initialBallVelocity = new Vect(1, velocityMagnitude); //falls downward when Velocity is positive
        StaticBumper TriA = new StaticBumper("SquareA", Constants.BumperType.TRIUP, startForTriA);
        StaticBumper squareB = new StaticBumper("SquareB", Constants.BumperType.SQUARE, startForSquareB);
        StaticBumper circleC = new StaticBumper("SquareA", Constants.BumperType.CIRCLE, startForCircleC);
        StaticBumper squareD = new StaticBumper("SquareB", Constants.BumperType.SQUARE, startForSquareD);
        StaticBumper squareE = new StaticBumper("SquareA", Constants.BumperType.SQUARE, startForSquareE);
        Flipper flipperA = new Flipper("FlipperA", startForSquareB, 270, Constants.FlipperType.LEFT);
        Ball ball = new Ball(0.25, ballStart, initialBallVelocity);

        boardGadgets.add(circleC);
        boardGadgets.add(squareD);
        boardGadgets.add(TriA);
        boardGadgets.add(flipperA);
        boardGadgets.add(squareE);

        Board testBoardA = new Board("testBoardA", boardGadgets, 25, 0, 0);
        testBoardA.addBall(ball);
        while(true){
            try {
                Thread.sleep((int) (Constants.TIMESTEP * 1000));
                String out = testBoardA.step();
                System.out.println(out);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }

    /**
     * sampleBoard1
     */
    public void sampleBoard1(){
        try {
            Board board = BoardFactory.parse(SimpleFileReader.readFile(new File("boards/sampleBoard1.pb")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
