package client.boardlang;

import client.Board;

public class BoardParseExample {

    public static void main(String[] args) {
        String boardString =
            "board name=ExampleB gravity=10.0 friction1=1.0 friction2=3.0\n" +
            "ball name=BallA x=1.8 y=4.5 xVelocity=10.4 yVelocity=10.3\n" +
            "fire trigger=Square action=FlipL\n" +
            "fire trigger=SquareB action=FlipL\n";
        Board b = BoardFactory.parse(boardString);
    }
}
