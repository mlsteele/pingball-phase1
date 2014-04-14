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
		NAME=24, ASSIGN=25, INTEGER=26, FLOAT=27, FlOAT1=28, FlOAT2=29, FlOAT3=30, 
		ORIENTATION=31, NEWLINE=32, WHITESPACE=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT", "FIELD_NAME", "FIELD_GRAVITY", "FIELD_FRICTION1", "FIELD_FRICTION2", 
		"FIELD_X", "FIELD_Y", "FIELD_XVELOCITY", "FIELD_YVELOCITY", "FIELD_ORIENTATION", 
		"FIELD_WIDTH", "FIELD_HEIGHT", "FIELD_TRIGGER", "FIELD_ACTION", "'board'", 
		"'ball'", "'squareBumper'", "'circleBumper'", "'triangleBumper'", "'rightFlipper'", 
		"'leftFlipper'", "'absorber'", "'fire'", "NAME", "'='", "INTEGER", "FLOAT", 
		"FlOAT1", "FlOAT2", "FlOAT3", "ORIENTATION", "NEWLINE", "WHITESPACE"
	};
	public static final String[] ruleNames = {
		"COMMENT", "FIELD_NAME", "FIELD_GRAVITY", "FIELD_FRICTION1", "FIELD_FRICTION2", 
		"FIELD_X", "FIELD_Y", "FIELD_XVELOCITY", "FIELD_YVELOCITY", "FIELD_ORIENTATION", 
		"FIELD_WIDTH", "FIELD_HEIGHT", "FIELD_TRIGGER", "FIELD_ACTION", "START_BOARD", 
		"START_BALL", "START_SQUAREBUMPER", "START_CIRCLEBUMPER", "START_TRIANGLEBUMPER", 
		"START_RIGHTFLIPPER", "START_LEFTFLIPPER", "START_ABSORBER", "START_FIRE", 
		"NAME", "ASSIGN", "INTEGER", "FLOAT", "FlOAT1", "FlOAT2", "FlOAT3", "ORIENTATION", 
		"NEWLINE", "WHITESPACE"
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
		"\2\4#\u0156\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\3\2\3\2\7\2H\n\2\f\2\16\2K\13\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\7\31\u0123\n\31\f\31\16\31\u0126\13\31\3\32\3\32\3\33\6\33\u012b\n"+
		"\33\r\33\16\33\u012c\3\34\5\34\u0130\n\34\3\34\3\34\3\34\5\34\u0135\n"+
		"\34\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \5 \u0149\n \3!\5!\u014c\n!\3!\3!\3\"\6\"\u0151\n\"\r\"\16\"\u0152"+
		"\3\"\3\"\2#\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1"+
		"\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25"+
		"\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1"+
		"= \1?!\1A\"\1C#\2\3\2\6\4\f\f\17\17\5C\\aac|\6\62;C\\aac|\4\13\13\"\""+
		"\u0160\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5"+
		"L\3\2\2\2\7S\3\2\2\2\t]\3\2\2\2\13i\3\2\2\2\ru\3\2\2\2\17x\3\2\2\2\21"+
		"{\3\2\2\2\23\u0087\3\2\2\2\25\u0093\3\2\2\2\27\u00a1\3\2\2\2\31\u00a9"+
		"\3\2\2\2\33\u00b2\3\2\2\2\35\u00bc\3\2\2\2\37\u00c5\3\2\2\2!\u00cb\3\2"+
		"\2\2#\u00d0\3\2\2\2%\u00dd\3\2\2\2\'\u00ea\3\2\2\2)\u00f9\3\2\2\2+\u0106"+
		"\3\2\2\2-\u0112\3\2\2\2/\u011b\3\2\2\2\61\u0120\3\2\2\2\63\u0127\3\2\2"+
		"\2\65\u012a\3\2\2\2\67\u012f\3\2\2\29\u0136\3\2\2\2;\u0138\3\2\2\2=\u013c"+
		"\3\2\2\2?\u0148\3\2\2\2A\u014b\3\2\2\2C\u0150\3\2\2\2EI\7%\2\2FH\n\2\2"+
		"\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\4\3\2\2\2KI\3\2\2\2LM\7p\2"+
		"\2MN\7c\2\2NO\7o\2\2OP\7g\2\2PQ\3\2\2\2QR\5\63\32\2R\6\3\2\2\2ST\7i\2"+
		"\2TU\7t\2\2UV\7c\2\2VW\7x\2\2WX\7k\2\2XY\7v\2\2YZ\7{\2\2Z[\3\2\2\2[\\"+
		"\5\63\32\2\\\b\3\2\2\2]^\7h\2\2^_\7t\2\2_`\7k\2\2`a\7e\2\2ab\7v\2\2bc"+
		"\7k\2\2cd\7q\2\2de\7p\2\2ef\7\63\2\2fg\3\2\2\2gh\5\63\32\2h\n\3\2\2\2"+
		"ij\7h\2\2jk\7t\2\2kl\7k\2\2lm\7e\2\2mn\7v\2\2no\7k\2\2op\7q\2\2pq\7p\2"+
		"\2qr\7\64\2\2rs\3\2\2\2st\5\63\32\2t\f\3\2\2\2uv\7z\2\2vw\5\63\32\2w\16"+
		"\3\2\2\2xy\7{\2\2yz\5\63\32\2z\20\3\2\2\2{|\7z\2\2|}\7X\2\2}~\7g\2\2~"+
		"\177\7n\2\2\177\u0080\7q\2\2\u0080\u0081\7e\2\2\u0081\u0082\7k\2\2\u0082"+
		"\u0083\7v\2\2\u0083\u0084\7{\2\2\u0084\u0085\3\2\2\2\u0085\u0086\5\63"+
		"\32\2\u0086\22\3\2\2\2\u0087\u0088\7{\2\2\u0088\u0089\7X\2\2\u0089\u008a"+
		"\7g\2\2\u008a\u008b\7n\2\2\u008b\u008c\7q\2\2\u008c\u008d\7e\2\2\u008d"+
		"\u008e\7k\2\2\u008e\u008f\7v\2\2\u008f\u0090\7{\2\2\u0090\u0091\3\2\2"+
		"\2\u0091\u0092\5\63\32\2\u0092\24\3\2\2\2\u0093\u0094\7q\2\2\u0094\u0095"+
		"\7t\2\2\u0095\u0096\7k\2\2\u0096\u0097\7g\2\2\u0097\u0098\7p\2\2\u0098"+
		"\u0099\7v\2\2\u0099\u009a\7c\2\2\u009a\u009b\7v\2\2\u009b\u009c\7k\2\2"+
		"\u009c\u009d\7q\2\2\u009d\u009e\7p\2\2\u009e\u009f\3\2\2\2\u009f\u00a0"+
		"\5\63\32\2\u00a0\26\3\2\2\2\u00a1\u00a2\7y\2\2\u00a2\u00a3\7k\2\2\u00a3"+
		"\u00a4\7f\2\2\u00a4\u00a5\7v\2\2\u00a5\u00a6\7j\2\2\u00a6\u00a7\3\2\2"+
		"\2\u00a7\u00a8\5\63\32\2\u00a8\30\3\2\2\2\u00a9\u00aa\7j\2\2\u00aa\u00ab"+
		"\7g\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7i\2\2\u00ad\u00ae\7j\2\2\u00ae"+
		"\u00af\7v\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\5\63\32\2\u00b1\32\3\2\2"+
		"\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7t\2\2\u00b4\u00b5\7k\2\2\u00b5\u00b6"+
		"\7i\2\2\u00b6\u00b7\7i\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7t\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00ba\u00bb\5\63\32\2\u00bb\34\3\2\2\2\u00bc\u00bd\7c\2"+
		"\2\u00bd\u00be\7e\2\2\u00be\u00bf\7v\2\2\u00bf\u00c0\7k\2\2\u00c0\u00c1"+
		"\7q\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\5\63\32\2\u00c4"+
		"\36\3\2\2\2\u00c5\u00c6\7d\2\2\u00c6\u00c7\7q\2\2\u00c7\u00c8\7c\2\2\u00c8"+
		"\u00c9\7t\2\2\u00c9\u00ca\7f\2\2\u00ca \3\2\2\2\u00cb\u00cc\7d\2\2\u00cc"+
		"\u00cd\7c\2\2\u00cd\u00ce\7n\2\2\u00ce\u00cf\7n\2\2\u00cf\"\3\2\2\2\u00d0"+
		"\u00d1\7u\2\2\u00d1\u00d2\7s\2\2\u00d2\u00d3\7w\2\2\u00d3\u00d4\7c\2\2"+
		"\u00d4\u00d5\7t\2\2\u00d5\u00d6\7g\2\2\u00d6\u00d7\7D\2\2\u00d7\u00d8"+
		"\7w\2\2\u00d8\u00d9\7o\2\2\u00d9\u00da\7r\2\2\u00da\u00db\7g\2\2\u00db"+
		"\u00dc\7t\2\2\u00dc$\3\2\2\2\u00dd\u00de\7e\2\2\u00de\u00df\7k\2\2\u00df"+
		"\u00e0\7t\2\2\u00e0\u00e1\7e\2\2\u00e1\u00e2\7n\2\2\u00e2\u00e3\7g\2\2"+
		"\u00e3\u00e4\7D\2\2\u00e4\u00e5\7w\2\2\u00e5\u00e6\7o\2\2\u00e6\u00e7"+
		"\7r\2\2\u00e7\u00e8\7g\2\2\u00e8\u00e9\7t\2\2\u00e9&\3\2\2\2\u00ea\u00eb"+
		"\7v\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed\7k\2\2\u00ed\u00ee\7c\2\2\u00ee"+
		"\u00ef\7p\2\2\u00ef\u00f0\7i\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7g\2\2"+
		"\u00f2\u00f3\7D\2\2\u00f3\u00f4\7w\2\2\u00f4\u00f5\7o\2\2\u00f5\u00f6"+
		"\7r\2\2\u00f6\u00f7\7g\2\2\u00f7\u00f8\7t\2\2\u00f8(\3\2\2\2\u00f9\u00fa"+
		"\7t\2\2\u00fa\u00fb\7k\2\2\u00fb\u00fc\7i\2\2\u00fc\u00fd\7j\2\2\u00fd"+
		"\u00fe\7v\2\2\u00fe\u00ff\7H\2\2\u00ff\u0100\7n\2\2\u0100\u0101\7k\2\2"+
		"\u0101\u0102\7r\2\2\u0102\u0103\7r\2\2\u0103\u0104\7g\2\2\u0104\u0105"+
		"\7t\2\2\u0105*\3\2\2\2\u0106\u0107\7n\2\2\u0107\u0108\7g\2\2\u0108\u0109"+
		"\7h\2\2\u0109\u010a\7v\2\2\u010a\u010b\7H\2\2\u010b\u010c\7n\2\2\u010c"+
		"\u010d\7k\2\2\u010d\u010e\7r\2\2\u010e\u010f\7r\2\2\u010f\u0110\7g\2\2"+
		"\u0110\u0111\7t\2\2\u0111,\3\2\2\2\u0112\u0113\7c\2\2\u0113\u0114\7d\2"+
		"\2\u0114\u0115\7u\2\2\u0115\u0116\7q\2\2\u0116\u0117\7t\2\2\u0117\u0118"+
		"\7d\2\2\u0118\u0119\7g\2\2\u0119\u011a\7t\2\2\u011a.\3\2\2\2\u011b\u011c"+
		"\7h\2\2\u011c\u011d\7k\2\2\u011d\u011e\7t\2\2\u011e\u011f\7g\2\2\u011f"+
		"\60\3\2\2\2\u0120\u0124\t\3\2\2\u0121\u0123\t\4\2\2\u0122\u0121\3\2\2"+
		"\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\62"+
		"\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u0128\7?\2\2\u0128\64\3\2\2\2\u0129"+
		"\u012b\4\62;\2\u012a\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012a\3\2"+
		"\2\2\u012c\u012d\3\2\2\2\u012d\66\3\2\2\2\u012e\u0130\7/\2\2\u012f\u012e"+
		"\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0134\3\2\2\2\u0131\u0135\59\35\2\u0132"+
		"\u0135\5;\36\2\u0133\u0135\5=\37\2\u0134\u0131\3\2\2\2\u0134\u0132\3\2"+
		"\2\2\u0134\u0133\3\2\2\2\u01358\3\2\2\2\u0136\u0137\5\65\33\2\u0137:\3"+
		"\2\2\2\u0138\u0139\5\65\33\2\u0139\u013a\7\60\2\2\u013a\u013b\5\65\33"+
		"\2\u013b<\3\2\2\2\u013c\u013d\7\60\2\2\u013d\u013e\5\65\33\2\u013e>\3"+
		"\2\2\2\u013f\u0149\7\62\2\2\u0140\u0141\7;\2\2\u0141\u0149\7\62\2\2\u0142"+
		"\u0143\7\63\2\2\u0143\u0144\7:\2\2\u0144\u0149\7\62\2\2\u0145\u0146\7"+
		"\64\2\2\u0146\u0147\79\2\2\u0147\u0149\7\62\2\2\u0148\u013f\3\2\2\2\u0148"+
		"\u0140\3\2\2\2\u0148\u0142\3\2\2\2\u0148\u0145\3\2\2\2\u0149@\3\2\2\2"+
		"\u014a\u014c\7\17\2\2\u014b\u014a\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d"+
		"\3\2\2\2\u014d\u014e\7\f\2\2\u014eB\3\2\2\2\u014f\u0151\t\5\2\2\u0150"+
		"\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2"+
		"\2\2\u0153\u0154\3\2\2\2\u0154\u0155\b\"\2\2\u0155D\3\2\2\2\13\2I\u0124"+
		"\u012c\u012f\u0134\u0148\u014b\u0152";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}