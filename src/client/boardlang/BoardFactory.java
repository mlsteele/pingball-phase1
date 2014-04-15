package client.boardlang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import physics.Vect;
import common.Constants;
import client.Ball;
import client.Board;
import client.gadgets.Gadget;


/**
 * Wraps the ANTLR boilerplate into a factory
 * which converts a String into a Board.
 *
 * Note that the board grammar requires exactly a
 * trailing newline, but that BoardFactory takes
 * care of this detail so that the caller of parse
 * does not need to worry about the end of the string at all.
 */
public class BoardFactory {
    /**
     * Parse a string and return a Board instance.
     * TODO write full specs for board language
     * @param  input input string.
     * @return a Board
     */
    public static Board parse(String input) {
        input = input + "\n";

        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(input);
        BoardLexer lexer = new BoardLexer(stream);
        // TODO report as errors
        // lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);

        // Feed the tokens into the parser.
        BoardParser parser = new BoardParser(tokens);
        // TODO report as errors
        // parser.reportErrorsAsExceptions();

        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.boardfile(); // "boardfile" is the starter rule

        // debugging option #1: print the tree to the console
        // System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
        // ((RuleContext)tree).inspect(parser);

        // debugging option #3: walk the tree with a listener
        // new ParseTreeWalker().walk(new PrintEverythingListener(), tree);

        // Finally, construct an AST value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        BoardBuilder listener = new BoardBuilder();
        walker.walk(listener, tree);

        // return the value that the listener created
        return listener.getBoard();
    }

    /**
     * Listener that walks the tree of a parsed board
     * and assembles a Board.
     */
    private static class BoardBuilder extends BoardBaseListener {
        private static final boolean DEBUG = true;
        private final Map<String, String> subscriptions = new HashMap<String, String>();
        private final List<Gadget> gadgets = new ArrayList<Gadget>();
        private final List<Ball> balls = new ArrayList<Ball>();

        // board name=NAME gravity=FLOAT friction1=FLOAT friction2=FLOAT
        @Override public void exitEntry_board(BoardParser.Entry_boardContext ctx) {
            String name = ctx.NAME().getText();
            double gravity = Float.parseFloat(ctx.FLOAT(0).getText());
            double friction1 = Float.parseFloat(ctx.FLOAT(1).getText());
            double friction2 = Float.parseFloat(ctx.FLOAT(2).getText());
            if (DEBUG) System.out.println("boardinfo g=" + gravity + " f1=" + friction1 + " f2=" + friction2);
        }

        // ball name=NAME x=FLOAT y=FLOAT xVelocity=FLOAT yVelocity=FLOAT
        @Override public void exitEntry_ball(BoardParser.Entry_ballContext ctx) {
            String name = ctx.NAME().getText();
            double x = Float.parseFloat(ctx.FLOAT(0).getText());
            double y = Float.parseFloat(ctx.FLOAT(1).getText());
            double xVel = Float.parseFloat(ctx.FLOAT(2).getText());
            double yVel = Float.parseFloat(ctx.FLOAT(3).getText());
            if (DEBUG) System.out.println("ball x=" + x + " y=" + y + " xVel=" + xVel + " yVel=" + yVel);
            balls.add(new Ball(Constants.BALL_RADIUS, new Vect(x, y), new Vect(xVel, yVel)));
        }

        // squareBumper name=NAME x=INTEGER y=INTEGER
        @Override public void exitEntry_squarebumper(BoardParser.Entry_squarebumperContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            if (DEBUG) System.out.println("squareBumper name=" + name + " x=" + x + " y=" + y);
        }

        // circleBumper name=NAME x=INTEGER y=INTEGER
        @Override public void exitEntry_circlebumper(BoardParser.Entry_circlebumperContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            if (DEBUG) System.out.println("circleBumper name=" + name + " x=" + x + " y=" + y);
        }

        // triangleBumper name=NAME x=INTEGER y=INTEGER orientation=ORIENTATION
        @Override public void exitEntry_trianglebumper(BoardParser.Entry_trianglebumperContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            int orientation = Integer.parseInt(ctx.ORIENTATION().getText());
            if (DEBUG) System.out.println("triangleBumper name=" + name + " x=" + x + " y=" + y + " or=" + orientation);
        }

        // rightFlipper name=NAME x=INTEGER y=INTEGER orientation=ORIENTATION
        @Override public void exitEntry_rightflipper(BoardParser.Entry_rightflipperContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            int orientation = Integer.parseInt(ctx.ORIENTATION().getText());
            if (DEBUG) System.out.println("rightFlipper name=" + name + " x=" + x + " y=" + y + " or=" + orientation);
        }

        // leftFlipper name=NAME x=INTEGER y=INTEGER orientation=ORIENTATION
        @Override public void exitEntry_leftflipper(BoardParser.Entry_leftflipperContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            int orientation = Integer.parseInt(ctx.ORIENTATION().getText());
            if (DEBUG) System.out.println("leftFlipper name=" + name + " x=" + x + " y=" + y + " or=" + orientation);
        }

        // absorber name=NAME x=INTEGER y=INTEGER width=INTEGER height=INTEGER
        @Override public void exitEntry_absorber(BoardParser.Entry_absorberContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            int width = Integer.parseInt(ctx.INTEGER(2).getText());
            int height = Integer.parseInt(ctx.INTEGER(3).getText());
            if (DEBUG) System.out.println("absorber name=" + name + " x=" + x + " y=" + y + " w=" + width + " h=" + height);
        }

        // fire trigger=NAME action=NAME
        @Override public void exitEntry_fire(BoardParser.Entry_fireContext ctx) {
            String firer = ctx.NAME(0).getText();
            String subscriber = ctx.NAME(1).getText();
            if (DEBUG) System.out.println("fire " + firer + " -> " + subscriber);
            subscriptions.put(firer, subscriber);
        }

        public Board getBoard() {
            // TODO make work
            return new Board("TODO", Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, new ArrayList<Gadget>());
        }
    }
}
