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
import client.BoardEventSubscription;
import client.gadgets.Absorber;
import client.gadgets.Flipper;
import client.gadgets.Gadget;
import client.gadgets.StaticBumper;


/**
 * Wraps the ANTLR boilerplate into a factory
 * which converts a String into a Board.
 *
 * Note that the board grammar requires exactly a
 * trailing newline, but that BoardFactory takes
 * care of this detail so that the caller of parse
 * does not need to worry about the end of the string at all.
 *
 * TODO tell syntaxError to throw a more descriptive exception.
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
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);

        // Feed the tokens into the parser.
        BoardParser parser = new BoardParser(tokens);
        // TODO report as errors
        parser.reportErrorsAsExceptions();

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
        private static final boolean DEBUG = false;

        private final Map<String, String> subscriptions = new HashMap<String, String>();
        private final Map<String, Gadget> gadgets = new HashMap<String, Gadget>();
        private final List<Ball> balls = new ArrayList<Ball>();
        private String boardName;
        private double gravity = Constants.GRAVITY;
        private double friction1 = Constants.DEFAULT_FRICTION1;
        private double friction2 = Constants.DEFAULT_FRICTION2;

        // board name=NAME gravity=FLOAT friction1=FLOAT friction2=FLOAT
        @Override public void exitEntry_board(BoardParser.Entry_boardContext ctx) {
            boardName = ctx.NAME().getText();
            if (DEBUG) System.out.println("boardinfo n=" + boardName + " g=" + gravity + " f1=" + friction1 + " f2=" + friction2);
        }

        @Override public void exitEntry_board_gravity(BoardParser.Entry_board_gravityContext ctx) {
            gravity = Float.parseFloat(ctx.FLOAT().getText());
        }

        @Override public void exitEntry_board_friction1(BoardParser.Entry_board_friction1Context ctx) {
            friction1 = Float.parseFloat(ctx.FLOAT().getText());
        }

        @Override public void exitEntry_board_friction2(BoardParser.Entry_board_friction2Context ctx) {
            friction2 = Float.parseFloat(ctx.FLOAT().getText());
        }


        // ball name=NAME x=FLOAT y=FLOAT xVelocity=FLOAT yVelocity=FLOAT
        @Override public void exitEntry_ball(BoardParser.Entry_ballContext ctx) {
            // ignore the name of the ball.
            // String name = ctx.NAME().getText();
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
            gadgets.put(name, new StaticBumper(name, Constants.BumperType.SQUARE, new Vect(x, y)));
        }

        // circleBumper name=NAME x=INTEGER y=INTEGER
        @Override public void exitEntry_circlebumper(BoardParser.Entry_circlebumperContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            if (DEBUG) System.out.println("circleBumper name=" + name + " x=" + x + " y=" + y);
            gadgets.put(name, new StaticBumper(name, Constants.BumperType.CIRCLE, new Vect(x, y)));
        }

        // triangleBumper name=NAME x=INTEGER y=INTEGER f=ORIENTATION
        @Override public void exitEntry_trianglebumper(BoardParser.Entry_trianglebumperContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            int orientation = getAndCheckOrientation(ctx.INTEGER(2).getText());
            if (DEBUG) System.out.println("triangleBumper name=" + name + " x=" + x + " y=" + y + " or=" + orientation);
            if (orientation == 0 || orientation == 180) {
                gadgets.put(name, new StaticBumper(name, Constants.BumperType.TRIUP, new Vect(x, y)));
            } else if (orientation == 90 || orientation == 270) {
                gadgets.put(name, new StaticBumper(name, Constants.BumperType.TRIDOWN, new Vect(x, y)));
            } else {
                // TODO throw what exception?
                throw new RuntimeException("Inexplicably rotated triangleBumper: " + orientation);
            }
        }

        // rightFlipper name=NAME x=INTEGER y=INTEGER orientation=ORIENTATION
        @Override public void exitEntry_rightflipper(BoardParser.Entry_rightflipperContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            int orientation = getAndCheckOrientation(ctx.INTEGER(2).getText());
            if (DEBUG) System.out.println("rightFlipper name=" + name + " x=" + x + " y=" + y + " or=" + orientation);
            gadgets.put(name, new Flipper(name, new Vect(x, y), orientation, Constants.FlipperType.RIGHT));
        }

        // leftFlipper name=NAME x=INTEGER y=INTEGER orientation=ORIENTATION
        @Override public void exitEntry_leftflipper(BoardParser.Entry_leftflipperContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            int orientation = getAndCheckOrientation(ctx.INTEGER(2).getText());
            if (DEBUG) System.out.println("leftFlipper name=" + name + " x=" + x + " y=" + y + " or=" + orientation);
            gadgets.put(name, new Flipper(name, new Vect(x, y), orientation, Constants.FlipperType.LEFT));
        }

        // absorber name=NAME x=INTEGER y=INTEGER width=INTEGER height=INTEGER
        @Override public void exitEntry_absorber(BoardParser.Entry_absorberContext ctx) {
            String name = ctx.NAME().getText();
            int x = Integer.parseInt(ctx.INTEGER(0).getText());
            int y = Integer.parseInt(ctx.INTEGER(1).getText());
            int width = Integer.parseInt(ctx.INTEGER(2).getText());
            int height = Integer.parseInt(ctx.INTEGER(3).getText());
            if (DEBUG) System.out.println("absorber name=" + name + " x=" + x + " y=" + y + " w=" + width + " h=" + height);
            gadgets.put(name, new Absorber(name, new Vect(x, y), width, height));
        }

        // fire trigger=NAME action=NAME
        @Override public void exitEntry_fire(BoardParser.Entry_fireContext ctx) {
            String firer = ctx.NAME(0).getText();
            String subscriber = ctx.NAME(1).getText();
            if (DEBUG) System.out.println("fire " + firer + " -> " + subscriber);
            subscriptions.put(firer, subscriber);
        }

        /**
         * Assemble a board from the information collected while walking the tree.
         * This method may be called ONLY ONCE for each instance of BoardBuilder.
         * @return a Board assembled from the tree
         */
        public Board getBoard() {
            Board board = new Board(boardName, new ArrayList<Gadget>(gadgets.values()), gravity, friction1, friction2);

            // Add subscriptions.
            for (Map.Entry<String, String> entry : subscriptions.entrySet()) {
                String triggererName = entry.getKey();
                String subscriberName = entry.getValue();
                Gadget triggerer = gadgets.get(triggererName);
                Gadget subscriber = gadgets.get(subscriberName);
                if (triggerer == null || subscriber == null) {
                    // TODO what kind of exception?
                    throw new RuntimeException("Board mentions subscription without named gadget: " + triggererName + " -> " + subscriberName);
                } else {
                    board.addSubscription(new BoardEventSubscription(triggerer, subscriber));
                }
            }

            // Add the balls.
            for (Ball ball : balls) {
                board.addBall(ball);
            }

            return board;
        }

        /**
         * Parse an orientation string and or throw an exception if the orientation is
         * not one of '0' | '90' | '180' | '270'.s
         * @param  orientation string of the orientation, must be a valid integer.s
         * @return int of the orientation if it is valid.
         */
        private static int getAndCheckOrientation(String orientation) {
            int v = Integer.parseInt(orientation);
            if (v == 0 || v == 90 || v == 180 || v == 270) {
                return v;
            } else {
                // TODO what exception?
                throw new RuntimeException("Invalid orientation: " + orientation);
            }
        }
    }
}
