// Generated from src/client/boardlang/Board.g4 by ANTLR 4.0

package client.boardlang;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface BoardListener extends ParseTreeListener {
	void enterEntry_ball(BoardParser.Entry_ballContext ctx);
	void exitEntry_ball(BoardParser.Entry_ballContext ctx);

	void enterEntry_circlebumper(BoardParser.Entry_circlebumperContext ctx);
	void exitEntry_circlebumper(BoardParser.Entry_circlebumperContext ctx);

	void enterEntry_absorber(BoardParser.Entry_absorberContext ctx);
	void exitEntry_absorber(BoardParser.Entry_absorberContext ctx);

	void enterEntry_rightflipper(BoardParser.Entry_rightflipperContext ctx);
	void exitEntry_rightflipper(BoardParser.Entry_rightflipperContext ctx);

	void enterEntry_leftflipper(BoardParser.Entry_leftflipperContext ctx);
	void exitEntry_leftflipper(BoardParser.Entry_leftflipperContext ctx);

	void enterEntry_squarebumper(BoardParser.Entry_squarebumperContext ctx);
	void exitEntry_squarebumper(BoardParser.Entry_squarebumperContext ctx);

	void enterEntry_trianglebumper(BoardParser.Entry_trianglebumperContext ctx);
	void exitEntry_trianglebumper(BoardParser.Entry_trianglebumperContext ctx);

	void enterEntry_fire(BoardParser.Entry_fireContext ctx);
	void exitEntry_fire(BoardParser.Entry_fireContext ctx);

	void enterBoardinfo(BoardParser.BoardinfoContext ctx);
	void exitBoardinfo(BoardParser.BoardinfoContext ctx);

	void enterBoardfile(BoardParser.BoardfileContext ctx);
	void exitBoardfile(BoardParser.BoardfileContext ctx);

	void enterEntry(BoardParser.EntryContext ctx);
	void exitEntry(BoardParser.EntryContext ctx);

	void enterEntries(BoardParser.EntriesContext ctx);
	void exitEntries(BoardParser.EntriesContext ctx);

	void enterComment(BoardParser.CommentContext ctx);
	void exitComment(BoardParser.CommentContext ctx);

	void enterComments(BoardParser.CommentsContext ctx);
	void exitComments(BoardParser.CommentsContext ctx);
}