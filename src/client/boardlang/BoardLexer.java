// Generated from src/client/boardlang/Board.g4 by ANTLR 4.0

package client.boardlang;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, FIELD_NAME=2, FIELD_GRAVITY=3, FIELD_FRICTION1=4, FIELD_FRICTION2=5, 
		FIELD_X=6, FIELD_Y=7, FIELD_XVELOCITY=8, FIELD_YVELOCITY=9, FIELD_ORIENTATION=10, 
		FIELD_WIDTH=11, FIELD_HEIGHT=12, FIELD_TRIGGER=13, FIELD_ACTION=14, START_BOARD=15, 
		START_BALL=16, START_SQUAREBUMPER=17, START_CIRCLEBUMPER=18, START_TRIANGLEBUMPER=19, 
		START_RIGHTFLIPPER=20, START_LEFTFLIPPER=21, START_ABSORBER=22, START_FIRE=23, 
		ASSIGN=24, ORIENTATION=25, NAME=26, INTEGER=27, FLOAT=28, FlOAT1=29, FlOAT2=30, 
		FlOAT3=31, NEWLINE=32, WHITESPACE=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT", "FIELD_NAME", "FIELD_GRAVITY", "FIELD_FRICTION1", "FIELD_FRICTION2", 
		"FIELD_X", "FIELD_Y", "FIELD_XVELOCITY", "FIELD_YVELOCITY", "FIELD_ORIENTATION", 
		"FIELD_WIDTH", "FIELD_HEIGHT", "FIELD_TRIGGER", "FIELD_ACTION", "'board'", 
		"'ball'", "'squareBumper'", "'circleBumper'", "'triangleBumper'", "'rightFlipper'", 
		"'leftFlipper'", "'absorber'", "'fire'", "'='", "ORIENTATION", "NAME", 
		"INTEGER", "FLOAT", "FlOAT1", "FlOAT2", "FlOAT3", "NEWLINE", "WHITESPACE"
	};
	public static final String[] ruleNames = {
		"COMMENT", "FIELD_NAME", "FIELD_GRAVITY", "FIELD_FRICTION1", "FIELD_FRICTION2", 
		"FIELD_X", "FIELD_Y", "FIELD_XVELOCITY", "FIELD_YVELOCITY", "FIELD_ORIENTATION", 
		"FIELD_WIDTH", "FIELD_HEIGHT", "FIELD_TRIGGER", "FIELD_ACTION", "START_BOARD", 
		"START_BALL", "START_SQUAREBUMPER", "START_CIRCLEBUMPER", "START_TRIANGLEBUMPER", 
		"START_RIGHTFLIPPER", "START_LEFTFLIPPER", "START_ABSORBER", "START_FIRE", 
		"ASSIGN", "ORIENTATION", "NAME", "INTEGER", "FLOAT", "FlOAT1", "FlOAT2", 
		"FlOAT3", "NEWLINE", "WHITESPACE"
	};


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


	public BoardLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Board.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 32: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4#\u017d\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\3\2\3\2\7\2H\n\2\f\2\16\2K\13\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\5\3S\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\5\4`\n\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5o\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6~\n\6\3"+
		"\6\3\6\3\7\3\7\5\7\u0084\n\7\3\7\3\7\3\b\3\b\5\b\u008a\n\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0099\n\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a8\n\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00b9\n\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c4\n\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\5\r\u00d0\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u00dd\n\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u00e9\n\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0153"+
		"\n\32\3\33\3\33\7\33\u0157\n\33\f\33\16\33\u015a\13\33\3\34\6\34\u015d"+
		"\n\34\r\34\16\34\u015e\3\35\5\35\u0162\n\35\3\35\3\35\3\35\5\35\u0167"+
		"\n\35\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3!\5!\u0173\n!\3!\3!\3\""+
		"\6\"\u0178\n\"\r\"\16\"\u0179\3\"\3\"\2#\3\3\1\5\4\1\7\5\1\t\6\1\13\7"+
		"\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37"+
		"\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1"+
		"\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\2\3\2\6\4\f\f\17\17\5C\\a"+
		"ac|\6\62;C\\aac|\4\13\13\"\"\u0194\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\3E\3\2\2\2\5L\3\2\2\2\7V\3\2\2\2\tc\3\2\2\2\13r\3\2\2\2"+
		"\r\u0081\3\2\2\2\17\u0087\3\2\2\2\21\u008d\3\2\2\2\23\u009c\3\2\2\2\25"+
		"\u00ab\3\2\2\2\27\u00bc\3\2\2\2\31\u00c7\3\2\2\2\33\u00d3\3\2\2\2\35\u00e0"+
		"\3\2\2\2\37\u00ec\3\2\2\2!\u00f2\3\2\2\2#\u00f7\3\2\2\2%\u0104\3\2\2\2"+
		"\'\u0111\3\2\2\2)\u0120\3\2\2\2+\u012d\3\2\2\2-\u0139\3\2\2\2/\u0142\3"+
		"\2\2\2\61\u0147\3\2\2\2\63\u0152\3\2\2\2\65\u0154\3\2\2\2\67\u015c\3\2"+
		"\2\29\u0161\3\2\2\2;\u0168\3\2\2\2=\u016a\3\2\2\2?\u016e\3\2\2\2A\u0172"+
		"\3\2\2\2C\u0177\3\2\2\2EI\7%\2\2FH\n\2\2\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2"+
		"\2IJ\3\2\2\2J\4\3\2\2\2KI\3\2\2\2LM\7p\2\2MN\7c\2\2NO\7o\2\2OP\7g\2\2"+
		"PR\3\2\2\2QS\5C\"\2RQ\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\5\61\31\2U\6\3\2\2"+
		"\2VW\7i\2\2WX\7t\2\2XY\7c\2\2YZ\7x\2\2Z[\7k\2\2[\\\7v\2\2\\]\7{\2\2]_"+
		"\3\2\2\2^`\5C\"\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\5\61\31\2b\b\3\2\2\2"+
		"cd\7h\2\2de\7t\2\2ef\7k\2\2fg\7e\2\2gh\7v\2\2hi\7k\2\2ij\7q\2\2jk\7p\2"+
		"\2kl\7\63\2\2ln\3\2\2\2mo\5C\"\2nm\3\2\2\2no\3\2\2\2op\3\2\2\2pq\5\61"+
		"\31\2q\n\3\2\2\2rs\7h\2\2st\7t\2\2tu\7k\2\2uv\7e\2\2vw\7v\2\2wx\7k\2\2"+
		"xy\7q\2\2yz\7p\2\2z{\7\64\2\2{}\3\2\2\2|~\5C\"\2}|\3\2\2\2}~\3\2\2\2~"+
		"\177\3\2\2\2\177\u0080\5\61\31\2\u0080\f\3\2\2\2\u0081\u0083\7z\2\2\u0082"+
		"\u0084\5C\"\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2"+
		"\2\2\u0085\u0086\5\61\31\2\u0086\16\3\2\2\2\u0087\u0089\7{\2\2\u0088\u008a"+
		"\5C\"\2\u0089\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\5\61\31\2\u008c\20\3\2\2\2\u008d\u008e\7z\2\2\u008e\u008f\7X\2"+
		"\2\u008f\u0090\7g\2\2\u0090\u0091\7n\2\2\u0091\u0092\7q\2\2\u0092\u0093"+
		"\7e\2\2\u0093\u0094\7k\2\2\u0094\u0095\7v\2\2\u0095\u0096\7{\2\2\u0096"+
		"\u0098\3\2\2\2\u0097\u0099\5C\"\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2"+
		"\2\2\u0099\u009a\3\2\2\2\u009a\u009b\5\61\31\2\u009b\22\3\2\2\2\u009c"+
		"\u009d\7{\2\2\u009d\u009e\7X\2\2\u009e\u009f\7g\2\2\u009f\u00a0\7n\2\2"+
		"\u00a0\u00a1\7q\2\2\u00a1\u00a2\7e\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4"+
		"\7v\2\2\u00a4\u00a5\7{\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a8\5C\"\2\u00a7"+
		"\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\5\61"+
		"\31\2\u00aa\24\3\2\2\2\u00ab\u00ac\7q\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae"+
		"\7k\2\2\u00ae\u00af\7g\2\2\u00af\u00b0\7p\2\2\u00b0\u00b1\7v\2\2\u00b1"+
		"\u00b2\7c\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7q\2\2"+
		"\u00b5\u00b6\7p\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b9\5C\"\2\u00b8\u00b7"+
		"\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\5\61\31\2"+
		"\u00bb\26\3\2\2\2\u00bc\u00bd\7y\2\2\u00bd\u00be\7k\2\2\u00be\u00bf\7"+
		"f\2\2\u00bf\u00c0\7v\2\2\u00c0\u00c1\7j\2\2\u00c1\u00c3\3\2\2\2\u00c2"+
		"\u00c4\5C\"\2\u00c3\u00c2\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\3\2"+
		"\2\2\u00c5\u00c6\5\61\31\2\u00c6\30\3\2\2\2\u00c7\u00c8\7j\2\2\u00c8\u00c9"+
		"\7g\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7i\2\2\u00cb\u00cc\7j\2\2\u00cc"+
		"\u00cd\7v\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00d0\5C\"\2\u00cf\u00ce\3\2\2"+
		"\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\5\61\31\2\u00d2"+
		"\32\3\2\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7k\2\2\u00d6"+
		"\u00d7\7i\2\2\u00d7\u00d8\7i\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7t\2\2"+
		"\u00da\u00dc\3\2\2\2\u00db\u00dd\5C\"\2\u00dc\u00db\3\2\2\2\u00dc\u00dd"+
		"\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\5\61\31\2\u00df\34\3\2\2\2\u00e0"+
		"\u00e1\7c\2\2\u00e1\u00e2\7e\2\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\7k\2\2"+
		"\u00e4\u00e5\7q\2\2\u00e5\u00e6\7p\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00e9"+
		"\5C\"\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\u00eb\5\61\31\2\u00eb\36\3\2\2\2\u00ec\u00ed\7d\2\2\u00ed\u00ee\7q\2"+
		"\2\u00ee\u00ef\7c\2\2\u00ef\u00f0\7t\2\2\u00f0\u00f1\7f\2\2\u00f1 \3\2"+
		"\2\2\u00f2\u00f3\7d\2\2\u00f3\u00f4\7c\2\2\u00f4\u00f5\7n\2\2\u00f5\u00f6"+
		"\7n\2\2\u00f6\"\3\2\2\2\u00f7\u00f8\7u\2\2\u00f8\u00f9\7s\2\2\u00f9\u00fa"+
		"\7w\2\2\u00fa\u00fb\7c\2\2\u00fb\u00fc\7t\2\2\u00fc\u00fd\7g\2\2\u00fd"+
		"\u00fe\7D\2\2\u00fe\u00ff\7w\2\2\u00ff\u0100\7o\2\2\u0100\u0101\7r\2\2"+
		"\u0101\u0102\7g\2\2\u0102\u0103\7t\2\2\u0103$\3\2\2\2\u0104\u0105\7e\2"+
		"\2\u0105\u0106\7k\2\2\u0106\u0107\7t\2\2\u0107\u0108\7e\2\2\u0108\u0109"+
		"\7n\2\2\u0109\u010a\7g\2\2\u010a\u010b\7D\2\2\u010b\u010c\7w\2\2\u010c"+
		"\u010d\7o\2\2\u010d\u010e\7r\2\2\u010e\u010f\7g\2\2\u010f\u0110\7t\2\2"+
		"\u0110&\3\2\2\2\u0111\u0112\7v\2\2\u0112\u0113\7t\2\2\u0113\u0114\7k\2"+
		"\2\u0114\u0115\7c\2\2\u0115\u0116\7p\2\2\u0116\u0117\7i\2\2\u0117\u0118"+
		"\7n\2\2\u0118\u0119\7g\2\2\u0119\u011a\7D\2\2\u011a\u011b\7w\2\2\u011b"+
		"\u011c\7o\2\2\u011c\u011d\7r\2\2\u011d\u011e\7g\2\2\u011e\u011f\7t\2\2"+
		"\u011f(\3\2\2\2\u0120\u0121\7t\2\2\u0121\u0122\7k\2\2\u0122\u0123\7i\2"+
		"\2\u0123\u0124\7j\2\2\u0124\u0125\7v\2\2\u0125\u0126\7H\2\2\u0126\u0127"+
		"\7n\2\2\u0127\u0128\7k\2\2\u0128\u0129\7r\2\2\u0129\u012a\7r\2\2\u012a"+
		"\u012b\7g\2\2\u012b\u012c\7t\2\2\u012c*\3\2\2\2\u012d\u012e\7n\2\2\u012e"+
		"\u012f\7g\2\2\u012f\u0130\7h\2\2\u0130\u0131\7v\2\2\u0131\u0132\7H\2\2"+
		"\u0132\u0133\7n\2\2\u0133\u0134\7k\2\2\u0134\u0135\7r\2\2\u0135\u0136"+
		"\7r\2\2\u0136\u0137\7g\2\2\u0137\u0138\7t\2\2\u0138,\3\2\2\2\u0139\u013a"+
		"\7c\2\2\u013a\u013b\7d\2\2\u013b\u013c\7u\2\2\u013c\u013d\7q\2\2\u013d"+
		"\u013e\7t\2\2\u013e\u013f\7d\2\2\u013f\u0140\7g\2\2\u0140\u0141\7t\2\2"+
		"\u0141.\3\2\2\2\u0142\u0143\7h\2\2\u0143\u0144\7k\2\2\u0144\u0145\7t\2"+
		"\2\u0145\u0146\7g\2\2\u0146\60\3\2\2\2\u0147\u0148\7?\2\2\u0148\62\3\2"+
		"\2\2\u0149\u0153\7\62\2\2\u014a\u014b\7;\2\2\u014b\u0153\7\62\2\2\u014c"+
		"\u014d\7\63\2\2\u014d\u014e\7:\2\2\u014e\u0153\7\62\2\2\u014f\u0150\7"+
		"\64\2\2\u0150\u0151\79\2\2\u0151\u0153\7\62\2\2\u0152\u0149\3\2\2\2\u0152"+
		"\u014a\3\2\2\2\u0152\u014c\3\2\2\2\u0152\u014f\3\2\2\2\u0153\64\3\2\2"+
		"\2\u0154\u0158\t\3\2\2\u0155\u0157\t\4\2\2\u0156\u0155\3\2\2\2\u0157\u015a"+
		"\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\66\3\2\2\2\u015a"+
		"\u0158\3\2\2\2\u015b\u015d\4\62;\2\u015c\u015b\3\2\2\2\u015d\u015e\3\2"+
		"\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f8\3\2\2\2\u0160\u0162"+
		"\7/\2\2\u0161\u0160\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0166\3\2\2\2\u0163"+
		"\u0167\5;\36\2\u0164\u0167\5=\37\2\u0165\u0167\5? \2\u0166\u0163\3\2\2"+
		"\2\u0166\u0164\3\2\2\2\u0166\u0165\3\2\2\2\u0167:\3\2\2\2\u0168\u0169"+
		"\5\67\34\2\u0169<\3\2\2\2\u016a\u016b\5\67\34\2\u016b\u016c\7\60\2\2\u016c"+
		"\u016d\5\67\34\2\u016d>\3\2\2\2\u016e\u016f\7\60\2\2\u016f\u0170\5\67"+
		"\34\2\u0170@\3\2\2\2\u0171\u0173\7\17\2\2\u0172\u0171\3\2\2\2\u0172\u0173"+
		"\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0175\7\f\2\2\u0175B\3\2\2\2\u0176"+
		"\u0178\t\5\2\2\u0177\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u0177\3\2"+
		"\2\2\u0179\u017a\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017c\b\"\2\2\u017c"+
		"D\3\2\2\2\30\2IR_n}\u0083\u0089\u0098\u00a7\u00b8\u00c3\u00cf\u00dc\u00e8"+
		"\u0152\u0158\u015e\u0161\u0166\u0172\u0179";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}