package client.boardlang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import client.SimpleFileReader;
import client.Board;

public class BoardParseExample {

    public static void main(String[] args) throws InvalidBoardStringException {
        // String boardString =
        //     "board name=ExampleB gravity=10.0 friction1=1.0 friction2=3.0\n" +
        //     "ball name=BallA x=1.8 y=4.5 xVelocity=10.4 yVelocity=10.3\n" +
        //     "# a comment followed by a few blank lines\n" +
        //     "\n\n\n" +
        //     "squareBumper name=SquareA x=7 y=10\n" +
        //     "rightFlipper name=FlipR x=12 y=7 orientation=0\n" +
        //     "  leftFlipper name=FlipL x=10 y=7 orientation=90\n" +
        //     "circleBumper name=Circle x=4 y=3\n" +
        //     "triangleBumper name=Tri x=19 y=3 orientation=180\n" +
        //     " absorber name=Abs x=10 y=17 width=10 height=2\n" +
        //     "fire trigger=SquareA action=FlipL";

        File f = new File("boards/sampleBoard1.pb");
        try {
            String boardString = SimpleFileReader.readFile(f);
            Board b = BoardFactory.parse(boardString);
            System.out.println(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
