package client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import common.Constants;
import physics.LineSegment;
import physics.Vect;
import client.gadgets.Absorber;
import client.gadgets.Gadget;
import client.gadgets.StaticBumper;

public class BoardTest {




    //tests Board printing
    @Test
    public void test() {
        List<Gadget> boardGadgets = new ArrayList<Gadget>();
        Vect startForTriA = new Vect(2,2);
        Vect startForSquareB = new Vect(3,2);
        Vect startForCircleC = new Vect(4,2);
        Vect startForSquareD = new Vect(5,2);
        Vect startForSquareE = new Vect(6,2);
        Vect startForAbsorber = new Vect(0,14);
        StaticBumper TriA = new StaticBumper("SquareA", Constants.BumperType.TRIUP, startForTriA);
        StaticBumper squareB = new StaticBumper("SquareB", Constants.BumperType.SQUARE, startForSquareB);
        StaticBumper circleC = new StaticBumper("SquareA", Constants.BumperType.CIRCLE, startForCircleC);
        StaticBumper squareD = new StaticBumper("SquareB", Constants.BumperType.SQUARE, startForSquareD);
        StaticBumper squareE = new StaticBumper("SquareA", Constants.BumperType.SQUARE, startForSquareE);
        Absorber absorber = new Absorber("Absorber", startForAbsorber, 20, 5);
        boardGadgets.add(TriA);
        boardGadgets.add(squareB);
        boardGadgets.add(circleC);
        boardGadgets.add(squareD);
        boardGadgets.add(squareE);
        Board testBoardA = new Board("testBoardA", boardGadgets);
        testBoardA.step();
    }

}
