package client.boardlang;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import client.Board;


/**
 * Wraps the clean and concise (LOL) ANTLR boilerplate.
 */
public class BoardFactory {
    public static Board parse(String input) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(input);
        BoardLexer lexer = new BoardLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);

        // Feed the tokens into the parser.
        BoardParser parser = new BoardParser(tokens);
        parser.reportErrorsAsExceptions();

        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.boardfile(); // "board" is the starter rule

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

    private static class BoardBuilder extends BoardBaseListener {
        @Override public void exitEntry_ball(BoardParser.Entry_ballContext ctx) { }
        @Override public void exitEntry_circlebumper(BoardParser.Entry_circlebumperContext ctx) { }
        @Override public void exitEntry_absorber(BoardParser.Entry_absorberContext ctx) { }
        @Override public void exitEntry_rightflipper(BoardParser.Entry_rightflipperContext ctx) { }
        @Override public void exitEntry_leftflipper(BoardParser.Entry_leftflipperContext ctx) { }
        @Override public void exitEntry_squarebumper(BoardParser.Entry_squarebumperContext ctx) { }
        @Override public void exitEntry_trianglebumper(BoardParser.Entry_trianglebumperContext ctx) { }
        @Override public void exitEntry_fire(BoardParser.Entry_fireContext ctx) { }
        @Override public void exitBoardinfo(BoardParser.BoardinfoContext ctx) { }
        @Override public void exitBoardfile(BoardParser.BoardfileContext ctx) { }
    }
}
