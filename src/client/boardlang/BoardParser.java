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
		ORIENTATION=1, COMMENT=2, FIELD_NAME=3, FIELD_GRAVITY=4, FIELD_FRICTION1=5, 
		FIELD_FRICTION2=6, FIELD_X=7, FIELD_Y=8, FIELD_XVELOCITY=9, FIELD_YVELOCITY=10, 
		FIELD_ORIENTATION=11, FIELD_WIDTH=12, FIELD_HEIGHT=13, FIELD_TRIGGER=14, 
		FIELD_ACTION=15, START_BOARD=16, START_BALL=17, START_SQUAREBUMPER=18, 
		START_CIRCLEBUMPER=19, START_TRIANGLEBUMPER=20, START_RIGHTFLIPPER=21, 
		START_LEFTFLIPPER=22, START_ABSORBER=23, START_FIRE=24, INTEGER=25, FLOAT=26, 
		FlOAT1=27, FlOAT2=28, FlOAT3=29, NAME=30, NEWLINE=31, WHITESPACE=32;
	public static final String[] tokenNames = {
		"<INVALID>", "ORIENTATION", "COMMENT", "'name='", "'gravity='", "'friction1='", 
		"'friction2='", "'x='", "'y='", "'xVelocity='", "'yVelocity='", "'orientation='", 
		"'width='", "'height='", "'trigger='", "'action='", "'board'", "'ball'", 
		"'squareBumper'", "'circleBumper'", "'triangleBumper'", "'rightFlipper'", 
		"'leftFlipper'", "'absorber'", "'fire'", "INTEGER", "FLOAT", "FlOAT1", 
		"FlOAT2", "FlOAT3", "NAME", "NEWLINE", "WHITESPACE"
	};
	public static final int
		RULE_boardfile = 0, RULE_entry = 1, RULE_entries = 2, RULE_comment = 3, 
		RULE_comments = 4, RULE_boardinfo = 5, RULE_entry_ball = 6, RULE_entry_squarebumper = 7, 
		RULE_entry_circlebumper = 8, RULE_entry_trianglebumper = 9, RULE_entry_rightflipper = 10, 
		RULE_entry_leftflipper = 11, RULE_entry_absorber = 12, RULE_entry_fire = 13;
	public static final String[] ruleNames = {
		"boardfile", "entry", "entries", "comment", "comments", "boardinfo", "entry_ball", 
		"entry_squarebumper", "entry_circlebumper", "entry_trianglebumper", "entry_rightflipper", 
		"entry_leftflipper", "entry_absorber", "entry_fire"
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
		public TerminalNode EOF() { return getToken(BoardParser.EOF, 0); }
		public EntriesContext entries() {
			return getRuleContext(EntriesContext.class,0);
		}
		public CommentsContext comments(int i) {
			return getRuleContext(CommentsContext.class,i);
		}
		public BoardinfoContext boardinfo() {
			return getRuleContext(BoardinfoContext.class,0);
		}
		public List<CommentsContext> comments() {
			return getRuleContexts(CommentsContext.class);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); comments();
			setState(29); boardinfo();
			setState(30); comments();
			setState(31); entries();
			setState(32); comments();
			setState(33); match(EOF);
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
		enterRule(_localctx, 2, RULE_entry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			switch (_input.LA(1)) {
			case START_BALL:
				{
				setState(35); entry_ball();
				}
				break;
			case START_SQUAREBUMPER:
				{
				setState(36); entry_squarebumper();
				}
				break;
			case START_CIRCLEBUMPER:
				{
				setState(37); entry_circlebumper();
				}
				break;
			case START_TRIANGLEBUMPER:
				{
				setState(38); entry_trianglebumper();
				}
				break;
			case START_RIGHTFLIPPER:
				{
				setState(39); entry_rightflipper();
				}
				break;
			case START_LEFTFLIPPER:
				{
				setState(40); entry_leftflipper();
				}
				break;
			case START_ABSORBER:
				{
				setState(41); entry_absorber();
				}
				break;
			case START_FIRE:
				{
				setState(42); entry_fire();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(45); match(NEWLINE);
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
		enterRule(_localctx, 4, RULE_entries);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << START_BALL) | (1L << START_SQUAREBUMPER) | (1L << START_CIRCLEBUMPER) | (1L << START_TRIANGLEBUMPER) | (1L << START_RIGHTFLIPPER) | (1L << START_LEFTFLIPPER) | (1L << START_ABSORBER) | (1L << START_FIRE))) != 0)) {
				{
				{
				setState(47); entry();
				}
				}
				setState(52);
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
		enterRule(_localctx, 6, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); match(COMMENT);
			setState(54); match(NEWLINE);
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(56); comment();
					}
					} 
				}
				setState(61);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class BoardinfoContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(BoardParser.FLOAT); }
		public TerminalNode FIELD_NAME() { return getToken(BoardParser.FIELD_NAME, 0); }
		public TerminalNode START_BOARD() { return getToken(BoardParser.START_BOARD, 0); }
		public TerminalNode FIELD_FRICTION2() { return getToken(BoardParser.FIELD_FRICTION2, 0); }
		public TerminalNode FIELD_FRICTION1() { return getToken(BoardParser.FIELD_FRICTION1, 0); }
		public TerminalNode FIELD_GRAVITY() { return getToken(BoardParser.FIELD_GRAVITY, 0); }
		public TerminalNode FLOAT(int i) {
			return getToken(BoardParser.FLOAT, i);
		}
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
		enterRule(_localctx, 10, RULE_boardinfo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(START_BOARD);
			setState(63); match(FIELD_NAME);
			setState(64); match(NAME);
			setState(65); match(FIELD_GRAVITY);
			setState(66); match(FLOAT);
			setState(67); match(FIELD_FRICTION1);
			setState(68); match(FLOAT);
			setState(69); match(FIELD_FRICTION2);
			setState(70); match(FLOAT);
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
		enterRule(_localctx, 12, RULE_entry_ball);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(START_BALL);
			setState(73); match(FIELD_NAME);
			setState(74); match(NAME);
			setState(75); match(FIELD_X);
			setState(76); match(FLOAT);
			setState(77); match(FIELD_Y);
			setState(78); match(FLOAT);
			setState(79); match(FIELD_XVELOCITY);
			setState(80); match(FLOAT);
			setState(81); match(FIELD_YVELOCITY);
			setState(82); match(FLOAT);
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
		enterRule(_localctx, 14, RULE_entry_squarebumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); match(START_SQUAREBUMPER);
			setState(85); match(FIELD_NAME);
			setState(86); match(NAME);
			setState(87); match(FIELD_X);
			setState(88); match(INTEGER);
			setState(89); match(FIELD_Y);
			setState(90); match(INTEGER);
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
		enterRule(_localctx, 16, RULE_entry_circlebumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); match(START_CIRCLEBUMPER);
			setState(93); match(FIELD_NAME);
			setState(94); match(NAME);
			setState(95); match(FIELD_X);
			setState(96); match(INTEGER);
			setState(97); match(FIELD_Y);
			setState(98); match(INTEGER);
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
		public TerminalNode ORIENTATION() { return getToken(BoardParser.ORIENTATION, 0); }
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
		enterRule(_localctx, 18, RULE_entry_trianglebumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(START_TRIANGLEBUMPER);
			setState(101); match(FIELD_NAME);
			setState(102); match(NAME);
			setState(103); match(FIELD_X);
			setState(104); match(INTEGER);
			setState(105); match(FIELD_Y);
			setState(106); match(INTEGER);
			setState(107); match(FIELD_ORIENTATION);
			setState(108); match(ORIENTATION);
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
		public TerminalNode ORIENTATION() { return getToken(BoardParser.ORIENTATION, 0); }
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
		enterRule(_localctx, 20, RULE_entry_rightflipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); match(START_RIGHTFLIPPER);
			setState(111); match(FIELD_NAME);
			setState(112); match(NAME);
			setState(113); match(FIELD_X);
			setState(114); match(INTEGER);
			setState(115); match(FIELD_Y);
			setState(116); match(INTEGER);
			setState(117); match(FIELD_ORIENTATION);
			setState(118); match(ORIENTATION);
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
		public TerminalNode ORIENTATION() { return getToken(BoardParser.ORIENTATION, 0); }
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
		enterRule(_localctx, 22, RULE_entry_leftflipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); match(START_LEFTFLIPPER);
			setState(121); match(FIELD_NAME);
			setState(122); match(NAME);
			setState(123); match(FIELD_X);
			setState(124); match(INTEGER);
			setState(125); match(FIELD_Y);
			setState(126); match(INTEGER);
			setState(127); match(FIELD_ORIENTATION);
			setState(128); match(ORIENTATION);
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
		enterRule(_localctx, 24, RULE_entry_absorber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); match(START_ABSORBER);
			setState(131); match(FIELD_NAME);
			setState(132); match(NAME);
			setState(133); match(FIELD_X);
			setState(134); match(INTEGER);
			setState(135); match(FIELD_Y);
			setState(136); match(INTEGER);
			setState(137); match(FIELD_WIDTH);
			setState(138); match(INTEGER);
			setState(139); match(FIELD_HEIGHT);
			setState(140); match(INTEGER);
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
		enterRule(_localctx, 26, RULE_entry_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142); match(START_FIRE);
			setState(143); match(FIELD_TRIGGER);
			setState(144); match(NAME);
			setState(145); match(FIELD_ACTION);
			setState(146); match(NAME);
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
		"\2\3\"\u0097\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3.\n\3\3\3\3\3\3"+
		"\4\7\4\63\n\4\f\4\16\4\66\13\4\3\5\3\5\3\5\3\6\7\6<\n\6\f\6\16\6?\13\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\2\2\u0091\2\36\3\2\2\2\4-\3\2\2\2\6\64\3\2\2\2\b\67\3\2\2\2\n=\3\2\2"+
		"\2\f@\3\2\2\2\16J\3\2\2\2\20V\3\2\2\2\22^\3\2\2\2\24f\3\2\2\2\26p\3\2"+
		"\2\2\30z\3\2\2\2\32\u0084\3\2\2\2\34\u0090\3\2\2\2\36\37\5\n\6\2\37 \5"+
		"\f\7\2 !\5\n\6\2!\"\5\6\4\2\"#\5\n\6\2#$\7\1\2\2$\3\3\2\2\2%.\5\16\b\2"+
		"&.\5\20\t\2\'.\5\22\n\2(.\5\24\13\2).\5\26\f\2*.\5\30\r\2+.\5\32\16\2"+
		",.\5\34\17\2-%\3\2\2\2-&\3\2\2\2-\'\3\2\2\2-(\3\2\2\2-)\3\2\2\2-*\3\2"+
		"\2\2-+\3\2\2\2-,\3\2\2\2./\3\2\2\2/\60\7!\2\2\60\5\3\2\2\2\61\63\5\4\3"+
		"\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\7\3\2\2"+
		"\2\66\64\3\2\2\2\678\7\4\2\289\7!\2\29\t\3\2\2\2:<\5\b\5\2;:\3\2\2\2<"+
		"?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\13\3\2\2\2?=\3\2\2\2@A\7\22\2\2AB\7\5\2"+
		"\2BC\7 \2\2CD\7\6\2\2DE\7\34\2\2EF\7\7\2\2FG\7\34\2\2GH\7\b\2\2HI\7\34"+
		"\2\2I\r\3\2\2\2JK\7\23\2\2KL\7\5\2\2LM\7 \2\2MN\7\t\2\2NO\7\34\2\2OP\7"+
		"\n\2\2PQ\7\34\2\2QR\7\13\2\2RS\7\34\2\2ST\7\f\2\2TU\7\34\2\2U\17\3\2\2"+
		"\2VW\7\24\2\2WX\7\5\2\2XY\7 \2\2YZ\7\t\2\2Z[\7\33\2\2[\\\7\n\2\2\\]\7"+
		"\33\2\2]\21\3\2\2\2^_\7\25\2\2_`\7\5\2\2`a\7 \2\2ab\7\t\2\2bc\7\33\2\2"+
		"cd\7\n\2\2de\7\33\2\2e\23\3\2\2\2fg\7\26\2\2gh\7\5\2\2hi\7 \2\2ij\7\t"+
		"\2\2jk\7\33\2\2kl\7\n\2\2lm\7\33\2\2mn\7\r\2\2no\7\3\2\2o\25\3\2\2\2p"+
		"q\7\27\2\2qr\7\5\2\2rs\7 \2\2st\7\t\2\2tu\7\33\2\2uv\7\n\2\2vw\7\33\2"+
		"\2wx\7\r\2\2xy\7\3\2\2y\27\3\2\2\2z{\7\30\2\2{|\7\5\2\2|}\7 \2\2}~\7\t"+
		"\2\2~\177\7\33\2\2\177\u0080\7\n\2\2\u0080\u0081\7\33\2\2\u0081\u0082"+
		"\7\r\2\2\u0082\u0083\7\3\2\2\u0083\31\3\2\2\2\u0084\u0085\7\31\2\2\u0085"+
		"\u0086\7\5\2\2\u0086\u0087\7 \2\2\u0087\u0088\7\t\2\2\u0088\u0089\7\33"+
		"\2\2\u0089\u008a\7\n\2\2\u008a\u008b\7\33\2\2\u008b\u008c\7\16\2\2\u008c"+
		"\u008d\7\33\2\2\u008d\u008e\7\17\2\2\u008e\u008f\7\33\2\2\u008f\33\3\2"+
		"\2\2\u0090\u0091\7\32\2\2\u0091\u0092\7\20\2\2\u0092\u0093\7 \2\2\u0093"+
		"\u0094\7\21\2\2\u0094\u0095\7 \2\2\u0095\35\3\2\2\2\5-\64=";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}