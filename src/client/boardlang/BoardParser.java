// Generated from src/client/boardlang/Board.g4 by ANTLR 4.0

package client.boardlang;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, FIELD_NAME=2, FIELD_GRAVITY=3, FIELD_FRICTION1=4, FIELD_FRICTION2=5, 
		FIELD_X=6, FIELD_Y=7, FIELD_XVELOCITY=8, FIELD_YVELOCITY=9, FIELD_ORIENTATION=10, 
		FIELD_WIDTH=11, FIELD_HEIGHT=12, FIELD_TRIGGER=13, FIELD_ACTION=14, START_BOARD=15, 
		START_BALL=16, START_SQUAREBUMPER=17, START_CIRCLEBUMPER=18, START_TRIANGLEBUMPER=19, 
		START_RIGHTFLIPPER=20, START_LEFTFLIPPER=21, START_ABSORBER=22, START_FIRE=23, 
		ASSIGN=24, NAME=25, INTEGER=26, FLOAT=27, FlOAT1=28, FlOAT2=29, FlOAT3=30, 
		NEWLINE=31, WHITESPACE=32;
	public static final String[] tokenNames = {
		"<INVALID>", "COMMENT", "FIELD_NAME", "FIELD_GRAVITY", "FIELD_FRICTION1", 
		"FIELD_FRICTION2", "FIELD_X", "FIELD_Y", "FIELD_XVELOCITY", "FIELD_YVELOCITY", 
		"FIELD_ORIENTATION", "FIELD_WIDTH", "FIELD_HEIGHT", "FIELD_TRIGGER", "FIELD_ACTION", 
		"'board'", "'ball'", "'squareBumper'", "'circleBumper'", "'triangleBumper'", 
		"'rightFlipper'", "'leftFlipper'", "'absorber'", "'fire'", "'='", "NAME", 
		"INTEGER", "FLOAT", "FlOAT1", "FlOAT2", "FlOAT3", "NEWLINE", "WHITESPACE"
	};
	public static final int
		RULE_boardfile = 0, RULE_boardinfo = 1, RULE_entry = 2, RULE_entries = 3, 
		RULE_comments = 4, RULE_comment = 5, RULE_entry_board = 6, RULE_entry_board_gravity = 7, 
		RULE_entry_board_friction1 = 8, RULE_entry_board_friction2 = 9, RULE_entry_ball = 10, 
		RULE_entry_squarebumper = 11, RULE_entry_circlebumper = 12, RULE_entry_trianglebumper = 13, 
		RULE_entry_rightflipper = 14, RULE_entry_leftflipper = 15, RULE_entry_absorber = 16, 
		RULE_entry_fire = 17;
	public static final String[] ruleNames = {
		"boardfile", "boardinfo", "entry", "entries", "comments", "comment", "entry_board", 
		"entry_board_gravity", "entry_board_friction1", "entry_board_friction2", 
		"entry_ball", "entry_squarebumper", "entry_circlebumper", "entry_trianglebumper", 
		"entry_rightflipper", "entry_leftflipper", "entry_absorber", "entry_fire"
	};

	@Override
	public String getGrammarFileName() { return "Board.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


	    /**
	     * Call this method to have the lexer or parser throw a RuntimeException if
	     * it encounters an error.
	     */
	    public void reportErrorsAsExceptions() {
	        addErrorListener(new ExceptionThrowingErrorListener());
	    }

	    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
	        @Override
	        public void syntaxError(Recognizer<?, ?> recognizer,
	                Object offendingSymbol, int line, int charPositionInLine,
	                String msg, RecognitionException e) {
	            throw new RuntimeException(msg);
	        }
	    }

	public BoardParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class BoardfileContext extends ParserRuleContext {
		public List<EntryContext> entry() {
			return getRuleContexts(EntryContext.class);
		}
		public EntryContext entry(int i) {
			return getRuleContext(EntryContext.class,i);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public TerminalNode EOF() { return getToken(BoardParser.EOF, 0); }
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public BoardinfoContext boardinfo() {
			return getRuleContext(BoardinfoContext.class,0);
		}
		public CommentsContext comments() {
			return getRuleContext(CommentsContext.class,0);
		}
		public BoardfileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardfile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBoardfile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBoardfile(this);
		}
	}

	public final BoardfileContext boardfile() throws RecognitionException {
		BoardfileContext _localctx = new BoardfileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_boardfile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); comments();
			setState(37); boardinfo();
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT) | (1L << START_BALL) | (1L << START_SQUAREBUMPER) | (1L << START_CIRCLEBUMPER) | (1L << START_TRIANGLEBUMPER) | (1L << START_RIGHTFLIPPER) | (1L << START_LEFTFLIPPER) | (1L << START_ABSORBER) | (1L << START_FIRE) | (1L << NEWLINE))) != 0)) {
				{
				setState(40);
				switch (_input.LA(1)) {
				case COMMENT:
				case NEWLINE:
					{
					setState(38); comment();
					}
					break;
				case START_BALL:
				case START_SQUAREBUMPER:
				case START_CIRCLEBUMPER:
				case START_TRIANGLEBUMPER:
				case START_RIGHTFLIPPER:
				case START_LEFTFLIPPER:
				case START_ABSORBER:
				case START_FIRE:
					{
					setState(39); entry();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardinfoContext extends ParserRuleContext {
		public Entry_boardContext entry_board() {
			return getRuleContext(Entry_boardContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(BoardParser.NEWLINE, 0); }
		public BoardinfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardinfo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBoardinfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBoardinfo(this);
		}
	}

	public final BoardinfoContext boardinfo() throws RecognitionException {
		BoardinfoContext _localctx = new BoardinfoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_boardinfo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); entry_board();
			setState(48); match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntryContext extends ParserRuleContext {
		public Entry_ballContext entry_ball() {
			return getRuleContext(Entry_ballContext.class,0);
		}
		public Entry_circlebumperContext entry_circlebumper() {
			return getRuleContext(Entry_circlebumperContext.class,0);
		}
		public Entry_absorberContext entry_absorber() {
			return getRuleContext(Entry_absorberContext.class,0);
		}
		public Entry_rightflipperContext entry_rightflipper() {
			return getRuleContext(Entry_rightflipperContext.class,0);
		}
		public Entry_leftflipperContext entry_leftflipper() {
			return getRuleContext(Entry_leftflipperContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(BoardParser.NEWLINE, 0); }
		public Entry_trianglebumperContext entry_trianglebumper() {
			return getRuleContext(Entry_trianglebumperContext.class,0);
		}
		public Entry_squarebumperContext entry_squarebumper() {
			return getRuleContext(Entry_squarebumperContext.class,0);
		}
		public Entry_fireContext entry_fire() {
			return getRuleContext(Entry_fireContext.class,0);
		}
		public EntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry(this);
		}
	}

	public final EntryContext entry() throws RecognitionException {
		EntryContext _localctx = new EntryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_entry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			switch (_input.LA(1)) {
			case START_BALL:
				{
				setState(50); entry_ball();
				}
				break;
			case START_SQUAREBUMPER:
				{
				setState(51); entry_squarebumper();
				}
				break;
			case START_CIRCLEBUMPER:
				{
				setState(52); entry_circlebumper();
				}
				break;
			case START_TRIANGLEBUMPER:
				{
				setState(53); entry_trianglebumper();
				}
				break;
			case START_RIGHTFLIPPER:
				{
				setState(54); entry_rightflipper();
				}
				break;
			case START_LEFTFLIPPER:
				{
				setState(55); entry_leftflipper();
				}
				break;
			case START_ABSORBER:
				{
				setState(56); entry_absorber();
				}
				break;
			case START_FIRE:
				{
				setState(57); entry_fire();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(60); match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntriesContext extends ParserRuleContext {
		public List<EntryContext> entry() {
			return getRuleContexts(EntryContext.class);
		}
		public EntryContext entry(int i) {
			return getRuleContext(EntryContext.class,i);
		}
		public EntriesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entries; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntries(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntries(this);
		}
	}

	public final EntriesContext entries() throws RecognitionException {
		EntriesContext _localctx = new EntriesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_entries);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << START_BALL) | (1L << START_SQUAREBUMPER) | (1L << START_CIRCLEBUMPER) | (1L << START_TRIANGLEBUMPER) | (1L << START_RIGHTFLIPPER) | (1L << START_LEFTFLIPPER) | (1L << START_ABSORBER) | (1L << START_FIRE))) != 0)) {
				{
				{
				setState(62); entry();
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentsContext extends ParserRuleContext {
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterComments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitComments(this);
		}
	}

	public final CommentsContext comments() throws RecognitionException {
		CommentsContext _localctx = new CommentsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_comments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMENT || _la==NEWLINE) {
				{
				{
				setState(68); comment();
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(BoardParser.NEWLINE, 0); }
		public TerminalNode COMMENT() { return getToken(BoardParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_comment);
		try {
			setState(77);
			switch (_input.LA(1)) {
			case COMMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(74); match(COMMENT);
				setState(75); match(NEWLINE);
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(76); match(NEWLINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_boardContext extends ParserRuleContext {
		public Entry_board_gravityContext entry_board_gravity() {
			return getRuleContext(Entry_board_gravityContext.class,0);
		}
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode FIELD_NAME() { return getToken(BoardParser.FIELD_NAME, 0); }
		public TerminalNode START_BOARD() { return getToken(BoardParser.START_BOARD, 0); }
		public Entry_board_friction1Context entry_board_friction1() {
			return getRuleContext(Entry_board_friction1Context.class,0);
		}
		public Entry_board_friction2Context entry_board_friction2() {
			return getRuleContext(Entry_board_friction2Context.class,0);
		}
		public Entry_boardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_board; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_board(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_board(this);
		}
	}

	public final Entry_boardContext entry_board() throws RecognitionException {
		Entry_boardContext _localctx = new Entry_boardContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_entry_board);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79); match(START_BOARD);
			setState(80); match(FIELD_NAME);
			setState(81); match(NAME);
			setState(83);
			_la = _input.LA(1);
			if (_la==FIELD_GRAVITY) {
				{
				setState(82); entry_board_gravity();
				}
			}

			setState(86);
			_la = _input.LA(1);
			if (_la==FIELD_FRICTION1) {
				{
				setState(85); entry_board_friction1();
				}
			}

			setState(89);
			_la = _input.LA(1);
			if (_la==FIELD_FRICTION2) {
				{
				setState(88); entry_board_friction2();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_board_gravityContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardParser.FLOAT, 0); }
		public TerminalNode FIELD_GRAVITY() { return getToken(BoardParser.FIELD_GRAVITY, 0); }
		public Entry_board_gravityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_board_gravity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_board_gravity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_board_gravity(this);
		}
	}

	public final Entry_board_gravityContext entry_board_gravity() throws RecognitionException {
		Entry_board_gravityContext _localctx = new Entry_board_gravityContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_entry_board_gravity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(FIELD_GRAVITY);
			setState(92); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_board_friction1Context extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardParser.FLOAT, 0); }
		public TerminalNode FIELD_FRICTION1() { return getToken(BoardParser.FIELD_FRICTION1, 0); }
		public Entry_board_friction1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_board_friction1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_board_friction1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_board_friction1(this);
		}
	}

	public final Entry_board_friction1Context entry_board_friction1() throws RecognitionException {
		Entry_board_friction1Context _localctx = new Entry_board_friction1Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_entry_board_friction1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); match(FIELD_FRICTION1);
			setState(95); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_board_friction2Context extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardParser.FLOAT, 0); }
		public TerminalNode FIELD_FRICTION2() { return getToken(BoardParser.FIELD_FRICTION2, 0); }
		public Entry_board_friction2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_board_friction2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_board_friction2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_board_friction2(this);
		}
	}

	public final Entry_board_friction2Context entry_board_friction2() throws RecognitionException {
		Entry_board_friction2Context _localctx = new Entry_board_friction2Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_entry_board_friction2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); match(FIELD_FRICTION2);
			setState(98); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_ballContext extends ParserRuleContext {
		public TerminalNode FIELD_Y() { return getToken(BoardParser.FIELD_Y, 0); }
		public TerminalNode FIELD_YVELOCITY() { return getToken(BoardParser.FIELD_YVELOCITY, 0); }
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(BoardParser.FLOAT); }
		public TerminalNode FIELD_NAME() { return getToken(BoardParser.FIELD_NAME, 0); }
		public TerminalNode FIELD_X() { return getToken(BoardParser.FIELD_X, 0); }
		public TerminalNode START_BALL() { return getToken(BoardParser.START_BALL, 0); }
		public TerminalNode FIELD_XVELOCITY() { return getToken(BoardParser.FIELD_XVELOCITY, 0); }
		public TerminalNode FLOAT(int i) {
			return getToken(BoardParser.FLOAT, i);
		}
		public Entry_ballContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_ball; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_ball(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_ball(this);
		}
	}

	public final Entry_ballContext entry_ball() throws RecognitionException {
		Entry_ballContext _localctx = new Entry_ballContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_entry_ball);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(START_BALL);
			setState(101); match(FIELD_NAME);
			setState(102); match(NAME);
			setState(103); match(FIELD_X);
			setState(104); match(FLOAT);
			setState(105); match(FIELD_Y);
			setState(106); match(FLOAT);
			setState(107); match(FIELD_XVELOCITY);
			setState(108); match(FLOAT);
			setState(109); match(FIELD_YVELOCITY);
			setState(110); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_squarebumperContext extends ParserRuleContext {
		public TerminalNode FIELD_Y() { return getToken(BoardParser.FIELD_Y, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(BoardParser.INTEGER); }
		public TerminalNode START_SQUAREBUMPER() { return getToken(BoardParser.START_SQUAREBUMPER, 0); }
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode FIELD_NAME() { return getToken(BoardParser.FIELD_NAME, 0); }
		public TerminalNode FIELD_X() { return getToken(BoardParser.FIELD_X, 0); }
		public TerminalNode INTEGER(int i) {
			return getToken(BoardParser.INTEGER, i);
		}
		public Entry_squarebumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_squarebumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_squarebumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_squarebumper(this);
		}
	}

	public final Entry_squarebumperContext entry_squarebumper() throws RecognitionException {
		Entry_squarebumperContext _localctx = new Entry_squarebumperContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_entry_squarebumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(START_SQUAREBUMPER);
			setState(113); match(FIELD_NAME);
			setState(114); match(NAME);
			setState(115); match(FIELD_X);
			setState(116); match(INTEGER);
			setState(117); match(FIELD_Y);
			setState(118); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_circlebumperContext extends ParserRuleContext {
		public TerminalNode FIELD_Y() { return getToken(BoardParser.FIELD_Y, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(BoardParser.INTEGER); }
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode FIELD_NAME() { return getToken(BoardParser.FIELD_NAME, 0); }
		public TerminalNode FIELD_X() { return getToken(BoardParser.FIELD_X, 0); }
		public TerminalNode INTEGER(int i) {
			return getToken(BoardParser.INTEGER, i);
		}
		public TerminalNode START_CIRCLEBUMPER() { return getToken(BoardParser.START_CIRCLEBUMPER, 0); }
		public Entry_circlebumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_circlebumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_circlebumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_circlebumper(this);
		}
	}

	public final Entry_circlebumperContext entry_circlebumper() throws RecognitionException {
		Entry_circlebumperContext _localctx = new Entry_circlebumperContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_entry_circlebumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); match(START_CIRCLEBUMPER);
			setState(121); match(FIELD_NAME);
			setState(122); match(NAME);
			setState(123); match(FIELD_X);
			setState(124); match(INTEGER);
			setState(125); match(FIELD_Y);
			setState(126); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_trianglebumperContext extends ParserRuleContext {
		public TerminalNode FIELD_Y() { return getToken(BoardParser.FIELD_Y, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(BoardParser.INTEGER); }
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode FIELD_NAME() { return getToken(BoardParser.FIELD_NAME, 0); }
		public TerminalNode FIELD_X() { return getToken(BoardParser.FIELD_X, 0); }
		public TerminalNode FIELD_ORIENTATION() { return getToken(BoardParser.FIELD_ORIENTATION, 0); }
		public TerminalNode INTEGER(int i) {
			return getToken(BoardParser.INTEGER, i);
		}
		public TerminalNode START_TRIANGLEBUMPER() { return getToken(BoardParser.START_TRIANGLEBUMPER, 0); }
		public Entry_trianglebumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_trianglebumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_trianglebumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_trianglebumper(this);
		}
	}

	public final Entry_trianglebumperContext entry_trianglebumper() throws RecognitionException {
		Entry_trianglebumperContext _localctx = new Entry_trianglebumperContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_entry_trianglebumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); match(START_TRIANGLEBUMPER);
			setState(129); match(FIELD_NAME);
			setState(130); match(NAME);
			setState(131); match(FIELD_X);
			setState(132); match(INTEGER);
			setState(133); match(FIELD_Y);
			setState(134); match(INTEGER);
			setState(135); match(FIELD_ORIENTATION);
			setState(136); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_rightflipperContext extends ParserRuleContext {
		public TerminalNode FIELD_Y() { return getToken(BoardParser.FIELD_Y, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(BoardParser.INTEGER); }
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode FIELD_NAME() { return getToken(BoardParser.FIELD_NAME, 0); }
		public TerminalNode START_RIGHTFLIPPER() { return getToken(BoardParser.START_RIGHTFLIPPER, 0); }
		public TerminalNode FIELD_X() { return getToken(BoardParser.FIELD_X, 0); }
		public TerminalNode FIELD_ORIENTATION() { return getToken(BoardParser.FIELD_ORIENTATION, 0); }
		public TerminalNode INTEGER(int i) {
			return getToken(BoardParser.INTEGER, i);
		}
		public Entry_rightflipperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_rightflipper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_rightflipper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_rightflipper(this);
		}
	}

	public final Entry_rightflipperContext entry_rightflipper() throws RecognitionException {
		Entry_rightflipperContext _localctx = new Entry_rightflipperContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_entry_rightflipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); match(START_RIGHTFLIPPER);
			setState(139); match(FIELD_NAME);
			setState(140); match(NAME);
			setState(141); match(FIELD_X);
			setState(142); match(INTEGER);
			setState(143); match(FIELD_Y);
			setState(144); match(INTEGER);
			setState(145); match(FIELD_ORIENTATION);
			setState(146); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_leftflipperContext extends ParserRuleContext {
		public TerminalNode FIELD_Y() { return getToken(BoardParser.FIELD_Y, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(BoardParser.INTEGER); }
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode FIELD_NAME() { return getToken(BoardParser.FIELD_NAME, 0); }
		public TerminalNode FIELD_X() { return getToken(BoardParser.FIELD_X, 0); }
		public TerminalNode START_LEFTFLIPPER() { return getToken(BoardParser.START_LEFTFLIPPER, 0); }
		public TerminalNode FIELD_ORIENTATION() { return getToken(BoardParser.FIELD_ORIENTATION, 0); }
		public TerminalNode INTEGER(int i) {
			return getToken(BoardParser.INTEGER, i);
		}
		public Entry_leftflipperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_leftflipper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_leftflipper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_leftflipper(this);
		}
	}

	public final Entry_leftflipperContext entry_leftflipper() throws RecognitionException {
		Entry_leftflipperContext _localctx = new Entry_leftflipperContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_entry_leftflipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); match(START_LEFTFLIPPER);
			setState(149); match(FIELD_NAME);
			setState(150); match(NAME);
			setState(151); match(FIELD_X);
			setState(152); match(INTEGER);
			setState(153); match(FIELD_Y);
			setState(154); match(INTEGER);
			setState(155); match(FIELD_ORIENTATION);
			setState(156); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_absorberContext extends ParserRuleContext {
		public TerminalNode FIELD_Y() { return getToken(BoardParser.FIELD_Y, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(BoardParser.INTEGER); }
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode FIELD_NAME() { return getToken(BoardParser.FIELD_NAME, 0); }
		public TerminalNode FIELD_X() { return getToken(BoardParser.FIELD_X, 0); }
		public TerminalNode FIELD_HEIGHT() { return getToken(BoardParser.FIELD_HEIGHT, 0); }
		public TerminalNode INTEGER(int i) {
			return getToken(BoardParser.INTEGER, i);
		}
		public TerminalNode FIELD_WIDTH() { return getToken(BoardParser.FIELD_WIDTH, 0); }
		public TerminalNode START_ABSORBER() { return getToken(BoardParser.START_ABSORBER, 0); }
		public Entry_absorberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_absorber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_absorber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_absorber(this);
		}
	}

	public final Entry_absorberContext entry_absorber() throws RecognitionException {
		Entry_absorberContext _localctx = new Entry_absorberContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_entry_absorber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); match(START_ABSORBER);
			setState(159); match(FIELD_NAME);
			setState(160); match(NAME);
			setState(161); match(FIELD_X);
			setState(162); match(INTEGER);
			setState(163); match(FIELD_Y);
			setState(164); match(INTEGER);
			setState(165); match(FIELD_WIDTH);
			setState(166); match(INTEGER);
			setState(167); match(FIELD_HEIGHT);
			setState(168); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entry_fireContext extends ParserRuleContext {
		public TerminalNode START_FIRE() { return getToken(BoardParser.START_FIRE, 0); }
		public TerminalNode FIELD_ACTION() { return getToken(BoardParser.FIELD_ACTION, 0); }
		public List<TerminalNode> NAME() { return getTokens(BoardParser.NAME); }
		public TerminalNode FIELD_TRIGGER() { return getToken(BoardParser.FIELD_TRIGGER, 0); }
		public TerminalNode NAME(int i) {
			return getToken(BoardParser.NAME, i);
		}
		public Entry_fireContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_fire; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterEntry_fire(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitEntry_fire(this);
		}
	}

	public final Entry_fireContext entry_fire() throws RecognitionException {
		Entry_fireContext _localctx = new Entry_fireContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_entry_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170); match(START_FIRE);
			setState(171); match(FIELD_TRIGGER);
			setState(172); match(NAME);
			setState(173); match(FIELD_ACTION);
			setState(174); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3\"\u00b3\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\3\2\3\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4=\n\4\3\4\3\4"+
		"\3\5\7\5B\n\5\f\5\16\5E\13\5\3\6\7\6H\n\6\f\6\16\6K\13\6\3\7\3\7\3\7\5"+
		"\7P\n\7\3\b\3\b\3\b\3\b\5\bV\n\b\3\b\5\bY\n\b\3\b\5\b\\\n\b\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\2\24\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$\2\2\u00af\2&\3\2\2\2\4\61\3\2\2\2\6<\3\2\2\2\b"+
		"C\3\2\2\2\nI\3\2\2\2\fO\3\2\2\2\16Q\3\2\2\2\20]\3\2\2\2\22`\3\2\2\2\24"+
		"c\3\2\2\2\26f\3\2\2\2\30r\3\2\2\2\32z\3\2\2\2\34\u0082\3\2\2\2\36\u008c"+
		"\3\2\2\2 \u0096\3\2\2\2\"\u00a0\3\2\2\2$\u00ac\3\2\2\2&\'\5\n\6\2\',\5"+
		"\4\3\2(+\5\f\7\2)+\5\6\4\2*(\3\2\2\2*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3"+
		"\2\2\2-/\3\2\2\2.,\3\2\2\2/\60\7\1\2\2\60\3\3\2\2\2\61\62\5\16\b\2\62"+
		"\63\7!\2\2\63\5\3\2\2\2\64=\5\26\f\2\65=\5\30\r\2\66=\5\32\16\2\67=\5"+
		"\34\17\28=\5\36\20\29=\5 \21\2:=\5\"\22\2;=\5$\23\2<\64\3\2\2\2<\65\3"+
		"\2\2\2<\66\3\2\2\2<\67\3\2\2\2<8\3\2\2\2<9\3\2\2\2<:\3\2\2\2<;\3\2\2\2"+
		"=>\3\2\2\2>?\7!\2\2?\7\3\2\2\2@B\5\6\4\2A@\3\2\2\2BE\3\2\2\2CA\3\2\2\2"+
		"CD\3\2\2\2D\t\3\2\2\2EC\3\2\2\2FH\5\f\7\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2"+
		"\2IJ\3\2\2\2J\13\3\2\2\2KI\3\2\2\2LM\7\3\2\2MP\7!\2\2NP\7!\2\2OL\3\2\2"+
		"\2ON\3\2\2\2P\r\3\2\2\2QR\7\21\2\2RS\7\4\2\2SU\7\33\2\2TV\5\20\t\2UT\3"+
		"\2\2\2UV\3\2\2\2VX\3\2\2\2WY\5\22\n\2XW\3\2\2\2XY\3\2\2\2Y[\3\2\2\2Z\\"+
		"\5\24\13\2[Z\3\2\2\2[\\\3\2\2\2\\\17\3\2\2\2]^\7\5\2\2^_\7\35\2\2_\21"+
		"\3\2\2\2`a\7\6\2\2ab\7\35\2\2b\23\3\2\2\2cd\7\7\2\2de\7\35\2\2e\25\3\2"+
		"\2\2fg\7\22\2\2gh\7\4\2\2hi\7\33\2\2ij\7\b\2\2jk\7\35\2\2kl\7\t\2\2lm"+
		"\7\35\2\2mn\7\n\2\2no\7\35\2\2op\7\13\2\2pq\7\35\2\2q\27\3\2\2\2rs\7\23"+
		"\2\2st\7\4\2\2tu\7\33\2\2uv\7\b\2\2vw\7\34\2\2wx\7\t\2\2xy\7\34\2\2y\31"+
		"\3\2\2\2z{\7\24\2\2{|\7\4\2\2|}\7\33\2\2}~\7\b\2\2~\177\7\34\2\2\177\u0080"+
		"\7\t\2\2\u0080\u0081\7\34\2\2\u0081\33\3\2\2\2\u0082\u0083\7\25\2\2\u0083"+
		"\u0084\7\4\2\2\u0084\u0085\7\33\2\2\u0085\u0086\7\b\2\2\u0086\u0087\7"+
		"\34\2\2\u0087\u0088\7\t\2\2\u0088\u0089\7\34\2\2\u0089\u008a\7\f\2\2\u008a"+
		"\u008b\7\34\2\2\u008b\35\3\2\2\2\u008c\u008d\7\26\2\2\u008d\u008e\7\4"+
		"\2\2\u008e\u008f\7\33\2\2\u008f\u0090\7\b\2\2\u0090\u0091\7\34\2\2\u0091"+
		"\u0092\7\t\2\2\u0092\u0093\7\34\2\2\u0093\u0094\7\f\2\2\u0094\u0095\7"+
		"\34\2\2\u0095\37\3\2\2\2\u0096\u0097\7\27\2\2\u0097\u0098\7\4\2\2\u0098"+
		"\u0099\7\33\2\2\u0099\u009a\7\b\2\2\u009a\u009b\7\34\2\2\u009b\u009c\7"+
		"\t\2\2\u009c\u009d\7\34\2\2\u009d\u009e\7\f\2\2\u009e\u009f\7\34\2\2\u009f"+
		"!\3\2\2\2\u00a0\u00a1\7\30\2\2\u00a1\u00a2\7\4\2\2\u00a2\u00a3\7\33\2"+
		"\2\u00a3\u00a4\7\b\2\2\u00a4\u00a5\7\34\2\2\u00a5\u00a6\7\t\2\2\u00a6"+
		"\u00a7\7\34\2\2\u00a7\u00a8\7\r\2\2\u00a8\u00a9\7\34\2\2\u00a9\u00aa\7"+
		"\16\2\2\u00aa\u00ab\7\34\2\2\u00ab#\3\2\2\2\u00ac\u00ad\7\31\2\2\u00ad"+
		"\u00ae\7\17\2\2\u00ae\u00af\7\33\2\2\u00af\u00b0\7\20\2\2\u00b0\u00b1"+
		"\7\33\2\2\u00b1%\3\2\2\2\13*,<CIOUX[";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}