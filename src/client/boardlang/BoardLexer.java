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
		ORIENTATION=1, COMMENT=2, FIELD_NAME=3, FIELD_GRAVITY=4, FIELD_FRICTION1=5, 
		FIELD_FRICTION2=6, FIELD_X=7, FIELD_Y=8, FIELD_XVELOCITY=9, FIELD_YVELOCITY=10, 
		FIELD_ORIENTATION=11, FIELD_WIDTH=12, FIELD_HEIGHT=13, FIELD_TRIGGER=14, 
		FIELD_ACTION=15, START_BOARD=16, START_BALL=17, START_SQUAREBUMPER=18, 
		START_CIRCLEBUMPER=19, START_TRIANGLEBUMPER=20, START_RIGHTFLIPPER=21, 
		START_LEFTFLIPPER=22, START_ABSORBER=23, START_FIRE=24, INTEGER=25, FLOAT=26, 
		FlOAT1=27, FlOAT2=28, FlOAT3=29, NAME=30, NEWLINE=31, WHITESPACE=32;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"ORIENTATION", "COMMENT", "'name='", "'gravity='", "'friction1='", "'friction2='", 
		"'x='", "'y='", "'xVelocity='", "'yVelocity='", "'orientation='", "'width='", 
		"'height='", "'trigger='", "'action='", "'board'", "'ball'", "'squareBumper'", 
		"'circleBumper'", "'triangleBumper'", "'rightFlipper'", "'leftFlipper'", 
		"'absorber'", "'fire'", "INTEGER", "FLOAT", "FlOAT1", "FlOAT2", "FlOAT3", 
		"NAME", "NEWLINE", "WHITESPACE"
	};
	public static final String[] ruleNames = {
		"ORIENTATION", "COMMENT", "FIELD_NAME", "FIELD_GRAVITY", "FIELD_FRICTION1", 
		"FIELD_FRICTION2", "FIELD_X", "FIELD_Y", "FIELD_XVELOCITY", "FIELD_YVELOCITY", 
		"FIELD_ORIENTATION", "FIELD_WIDTH", "FIELD_HEIGHT", "FIELD_TRIGGER", "FIELD_ACTION", 
		"START_BOARD", "START_BALL", "START_SQUAREBUMPER", "START_CIRCLEBUMPER", 
		"START_TRIANGLEBUMPER", "START_RIGHTFLIPPER", "START_LEFTFLIPPER", "START_ABSORBER", 
		"START_FIRE", "INTEGER", "FLOAT", "FlOAT1", "FlOAT2", "FlOAT3", "NAME", 
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
		case 31: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\"\u0147\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2M\n"+
		"\2\3\3\3\3\7\3Q\n\3\f\3\16\3T\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\6\32\u0120"+
		"\n\32\r\32\16\32\u0121\3\33\5\33\u0125\n\33\3\33\3\33\3\33\5\33\u012a"+
		"\n\33\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\7\37\u0137"+
		"\n\37\f\37\16\37\u013a\13\37\3 \5 \u013d\n \3 \3 \3!\6!\u0142\n!\r!\16"+
		"!\u0143\3!\3!\2\"\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23"+
		"\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1"+
		"\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1"+
		";\37\1= \1?!\1A\"\2\3\2\6\4\f\f\17\17\5C\\aac|\6\62;C\\aac|\4\13\13\""+
		"\"\u0151\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3L\3\2\2\2\5N\3\2\2\2"+
		"\7U\3\2\2\2\t[\3\2\2\2\13d\3\2\2\2\ro\3\2\2\2\17z\3\2\2\2\21}\3\2\2\2"+
		"\23\u0080\3\2\2\2\25\u008b\3\2\2\2\27\u0096\3\2\2\2\31\u00a3\3\2\2\2\33"+
		"\u00aa\3\2\2\2\35\u00b2\3\2\2\2\37\u00bb\3\2\2\2!\u00c3\3\2\2\2#\u00c9"+
		"\3\2\2\2%\u00ce\3\2\2\2\'\u00db\3\2\2\2)\u00e8\3\2\2\2+\u00f7\3\2\2\2"+
		"-\u0104\3\2\2\2/\u0110\3\2\2\2\61\u0119\3\2\2\2\63\u011f\3\2\2\2\65\u0124"+
		"\3\2\2\2\67\u012b\3\2\2\29\u012d\3\2\2\2;\u0131\3\2\2\2=\u0134\3\2\2\2"+
		"?\u013c\3\2\2\2A\u0141\3\2\2\2CM\7\62\2\2DE\7;\2\2EM\7\62\2\2FG\7\63\2"+
		"\2GH\7:\2\2HM\7\62\2\2IJ\7\64\2\2JK\79\2\2KM\7\62\2\2LC\3\2\2\2LD\3\2"+
		"\2\2LF\3\2\2\2LI\3\2\2\2M\4\3\2\2\2NR\7%\2\2OQ\n\2\2\2PO\3\2\2\2QT\3\2"+
		"\2\2RP\3\2\2\2RS\3\2\2\2S\6\3\2\2\2TR\3\2\2\2UV\7p\2\2VW\7c\2\2WX\7o\2"+
		"\2XY\7g\2\2YZ\7?\2\2Z\b\3\2\2\2[\\\7i\2\2\\]\7t\2\2]^\7c\2\2^_\7x\2\2"+
		"_`\7k\2\2`a\7v\2\2ab\7{\2\2bc\7?\2\2c\n\3\2\2\2de\7h\2\2ef\7t\2\2fg\7"+
		"k\2\2gh\7e\2\2hi\7v\2\2ij\7k\2\2jk\7q\2\2kl\7p\2\2lm\7\63\2\2mn\7?\2\2"+
		"n\f\3\2\2\2op\7h\2\2pq\7t\2\2qr\7k\2\2rs\7e\2\2st\7v\2\2tu\7k\2\2uv\7"+
		"q\2\2vw\7p\2\2wx\7\64\2\2xy\7?\2\2y\16\3\2\2\2z{\7z\2\2{|\7?\2\2|\20\3"+
		"\2\2\2}~\7{\2\2~\177\7?\2\2\177\22\3\2\2\2\u0080\u0081\7z\2\2\u0081\u0082"+
		"\7X\2\2\u0082\u0083\7g\2\2\u0083\u0084\7n\2\2\u0084\u0085\7q\2\2\u0085"+
		"\u0086\7e\2\2\u0086\u0087\7k\2\2\u0087\u0088\7v\2\2\u0088\u0089\7{\2\2"+
		"\u0089\u008a\7?\2\2\u008a\24\3\2\2\2\u008b\u008c\7{\2\2\u008c\u008d\7"+
		"X\2\2\u008d\u008e\7g\2\2\u008e\u008f\7n\2\2\u008f\u0090\7q\2\2\u0090\u0091"+
		"\7e\2\2\u0091\u0092\7k\2\2\u0092\u0093\7v\2\2\u0093\u0094\7{\2\2\u0094"+
		"\u0095\7?\2\2\u0095\26\3\2\2\2\u0096\u0097\7q\2\2\u0097\u0098\7t\2\2\u0098"+
		"\u0099\7k\2\2\u0099\u009a\7g\2\2\u009a\u009b\7p\2\2\u009b\u009c\7v\2\2"+
		"\u009c\u009d\7c\2\2\u009d\u009e\7v\2\2\u009e\u009f\7k\2\2\u009f\u00a0"+
		"\7q\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a2\7?\2\2\u00a2\30\3\2\2\2\u00a3\u00a4"+
		"\7y\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6\7f\2\2\u00a6\u00a7\7v\2\2\u00a7"+
		"\u00a8\7j\2\2\u00a8\u00a9\7?\2\2\u00a9\32\3\2\2\2\u00aa\u00ab\7j\2\2\u00ab"+
		"\u00ac\7g\2\2\u00ac\u00ad\7k\2\2\u00ad\u00ae\7i\2\2\u00ae\u00af\7j\2\2"+
		"\u00af\u00b0\7v\2\2\u00b0\u00b1\7?\2\2\u00b1\34\3\2\2\2\u00b2\u00b3\7"+
		"v\2\2\u00b3\u00b4\7t\2\2\u00b4\u00b5\7k\2\2\u00b5\u00b6\7i\2\2\u00b6\u00b7"+
		"\7i\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7t\2\2\u00b9\u00ba\7?\2\2\u00ba"+
		"\36\3\2\2\2\u00bb\u00bc\7c\2\2\u00bc\u00bd\7e\2\2\u00bd\u00be\7v\2\2\u00be"+
		"\u00bf\7k\2\2\u00bf\u00c0\7q\2\2\u00c0\u00c1\7p\2\2\u00c1\u00c2\7?\2\2"+
		"\u00c2 \3\2\2\2\u00c3\u00c4\7d\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7c\2"+
		"\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7f\2\2\u00c8\"\3\2\2\2\u00c9\u00ca\7"+
		"d\2\2\u00ca\u00cb\7c\2\2\u00cb\u00cc\7n\2\2\u00cc\u00cd\7n\2\2\u00cd$"+
		"\3\2\2\2\u00ce\u00cf\7u\2\2\u00cf\u00d0\7s\2\2\u00d0\u00d1\7w\2\2\u00d1"+
		"\u00d2\7c\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4\7g\2\2\u00d4\u00d5\7D\2\2"+
		"\u00d5\u00d6\7w\2\2\u00d6\u00d7\7o\2\2\u00d7\u00d8\7r\2\2\u00d8\u00d9"+
		"\7g\2\2\u00d9\u00da\7t\2\2\u00da&\3\2\2\2\u00db\u00dc\7e\2\2\u00dc\u00dd"+
		"\7k\2\2\u00dd\u00de\7t\2\2\u00de\u00df\7e\2\2\u00df\u00e0\7n\2\2\u00e0"+
		"\u00e1\7g\2\2\u00e1\u00e2\7D\2\2\u00e2\u00e3\7w\2\2\u00e3\u00e4\7o\2\2"+
		"\u00e4\u00e5\7r\2\2\u00e5\u00e6\7g\2\2\u00e6\u00e7\7t\2\2\u00e7(\3\2\2"+
		"\2\u00e8\u00e9\7v\2\2\u00e9\u00ea\7t\2\2\u00ea\u00eb\7k\2\2\u00eb\u00ec"+
		"\7c\2\2\u00ec\u00ed\7p\2\2\u00ed\u00ee\7i\2\2\u00ee\u00ef\7n\2\2\u00ef"+
		"\u00f0\7g\2\2\u00f0\u00f1\7D\2\2\u00f1\u00f2\7w\2\2\u00f2\u00f3\7o\2\2"+
		"\u00f3\u00f4\7r\2\2\u00f4\u00f5\7g\2\2\u00f5\u00f6\7t\2\2\u00f6*\3\2\2"+
		"\2\u00f7\u00f8\7t\2\2\u00f8\u00f9\7k\2\2\u00f9\u00fa\7i\2\2\u00fa\u00fb"+
		"\7j\2\2\u00fb\u00fc\7v\2\2\u00fc\u00fd\7H\2\2\u00fd\u00fe\7n\2\2\u00fe"+
		"\u00ff\7k\2\2\u00ff\u0100\7r\2\2\u0100\u0101\7r\2\2\u0101\u0102\7g\2\2"+
		"\u0102\u0103\7t\2\2\u0103,\3\2\2\2\u0104\u0105\7n\2\2\u0105\u0106\7g\2"+
		"\2\u0106\u0107\7h\2\2\u0107\u0108\7v\2\2\u0108\u0109\7H\2\2\u0109\u010a"+
		"\7n\2\2\u010a\u010b\7k\2\2\u010b\u010c\7r\2\2\u010c\u010d\7r\2\2\u010d"+
		"\u010e\7g\2\2\u010e\u010f\7t\2\2\u010f.\3\2\2\2\u0110\u0111\7c\2\2\u0111"+
		"\u0112\7d\2\2\u0112\u0113\7u\2\2\u0113\u0114\7q\2\2\u0114\u0115\7t\2\2"+
		"\u0115\u0116\7d\2\2\u0116\u0117\7g\2\2\u0117\u0118\7t\2\2\u0118\60\3\2"+
		"\2\2\u0119\u011a\7h\2\2\u011a\u011b\7k\2\2\u011b\u011c\7t\2\2\u011c\u011d"+
		"\7g\2\2\u011d\62\3\2\2\2\u011e\u0120\4\62;\2\u011f\u011e\3\2\2\2\u0120"+
		"\u0121\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\64\3\2\2"+
		"\2\u0123\u0125\7/\2\2\u0124\u0123\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0129"+
		"\3\2\2\2\u0126\u012a\5\67\34\2\u0127\u012a\59\35\2\u0128\u012a\5;\36\2"+
		"\u0129\u0126\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u0128\3\2\2\2\u012a\66"+
		"\3\2\2\2\u012b\u012c\5\63\32\2\u012c8\3\2\2\2\u012d\u012e\5\63\32\2\u012e"+
		"\u012f\7\60\2\2\u012f\u0130\5\63\32\2\u0130:\3\2\2\2\u0131\u0132\7\60"+
		"\2\2\u0132\u0133\5\63\32\2\u0133<\3\2\2\2\u0134\u0138\t\3\2\2\u0135\u0137"+
		"\t\4\2\2\u0136\u0135\3\2\2\2\u0137\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0138"+
		"\u0139\3\2\2\2\u0139>\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u013d\7\17\2\2"+
		"\u013c\u013b\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013f"+
		"\7\f\2\2\u013f@\3\2\2\2\u0140\u0142\t\5\2\2\u0141\u0140\3\2\2\2\u0142"+
		"\u0143\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0145\3\2"+
		"\2\2\u0145\u0146\b!\2\2\u0146B\3\2\2\2\13\2LR\u0121\u0124\u0129\u0138"+
		"\u013c\u0143";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}