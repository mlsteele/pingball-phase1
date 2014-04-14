grammar Board;

// This puts a Java package statement at the top of the output Java files.
@header {
package client.boardlang;
}

// This adds code to the generated lexer and parser.
@members {
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
}

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 * *** ANTLR requires tokens to be CAPITALIZED, like START_ITALIC, END_ITALIC, and TEXT.
 */
COMMENT : '#' ~( '\r' | '\n' )* ;

FIELD_NAME        : 'name' ASSIGN ;
FIELD_GRAVITY     : 'gravity' ASSIGN ;
FIELD_FRICTION1   : 'friction1' ASSIGN ;
FIELD_FRICTION2   : 'friction2' ASSIGN ;
FIELD_X           : 'x' ASSIGN ;
FIELD_Y           : 'y' ASSIGN ;
FIELD_XVELOCITY   : 'xVelocity' ASSIGN ;
FIELD_YVELOCITY   : 'yVelocity' ASSIGN ;
FIELD_ORIENTATION : 'orientation' ASSIGN ;
FIELD_WIDTH       : 'width' ASSIGN ;
FIELD_HEIGHT      : 'height' ASSIGN ;
FIELD_TRIGGER     : 'trigger' ASSIGN ;
FIELD_ACTION      : 'action' ASSIGN ;

START_BOARD          : 'board' ;
START_BALL           : 'ball' ;
START_SQUAREBUMPER   : 'squareBumper' ;
START_CIRCLEBUMPER   : 'circleBumper' ;
START_TRIANGLEBUMPER : 'triangleBumper' ;
START_RIGHTFLIPPER   : 'rightFlipper' ;
START_LEFTFLIPPER    : 'leftFlipper' ;
START_ABSORBER       : 'absorber' ;
START_FIRE           : 'fire' ;

NAME       : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')* ;
ASSIGN     : '=' ;
INTEGER    : ('0'..'9')+ ;
FLOAT      : '-'? (FlOAT1 | FlOAT2 | FlOAT3) ;
FlOAT1     : INTEGER ;
FlOAT2     : INTEGER '.' INTEGER ;
FlOAT3     : '.' INTEGER ;
ORIENTATION  : '0' | '90' | '180' | '270' ;

NEWLINE    : '\r'? '\n' ;
WHITESPACE : [ \t]+ -> skip ;

/*
 * These are the parser rules. They define the structures used by the parser.
 * *** ANTLR requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */
/* boardfile : comments boardinfo entries_and_comments ; */
boardfile : boardinfo entries EOF ;
boardinfo : entry_board NEWLINE ;
entry     : (entry_ball | entry_squarebumper | entry_circlebumper | entry_trianglebumper | entry_rightflipper | entry_leftflipper | entry_absorber | entry_fire) NEWLINE;
entries   : entry* ;
comments  : comment* ;
comment   : COMMENT NEWLINE ;

/* board name=NAME gravity=FLOAT friction1=FLOAT friction2=FLOAT */
entry_board : START_BOARD FIELD_NAME NAME FIELD_GRAVITY FLOAT FIELD_FRICTION1 FLOAT FIELD_FRICTION2 FLOAT ;

/* ball name=NAME x=FLOAT y=FLOAT xVelocity=FLOAT yVelocity=FLOAT */
entry_ball : START_BALL FIELD_NAME NAME FIELD_X FLOAT FIELD_Y FLOAT FIELD_XVELOCITY FLOAT FIELD_YVELOCITY FLOAT ;

/* squareBumper name=NAME x=INTEGER y=INTEGER */
entry_squarebumper : START_SQUAREBUMPER FIELD_NAME NAME FIELD_X INTEGER FIELD_Y INTEGER ;

/* circleBumper name=NAME x=INTEGER y=INTEGER */
entry_circlebumper : START_CIRCLEBUMPER FIELD_NAME NAME FIELD_X INTEGER FIELD_Y INTEGER ;

/* triangleBumper name=NAME x=INTEGER y=INTEGER orientation=ORIENTATION */
entry_trianglebumper : START_TRIANGLEBUMPER FIELD_NAME NAME FIELD_X INTEGER FIELD_Y INTEGER FIELD_ORIENTATION ORIENTATION ;

/* rightFlipper name=NAME x=INTEGER y=INTEGER orientation=ORIENTATION */
entry_rightflipper : START_RIGHTFLIPPER FIELD_NAME NAME FIELD_X INTEGER FIELD_Y INTEGER FIELD_ORIENTATION ORIENTATION ;

/* leftFlipper name=NAME x=INTEGER y=INTEGER orientation=ORIENTATION */
entry_leftflipper : START_LEFTFLIPPER FIELD_NAME NAME FIELD_X INTEGER FIELD_Y INTEGER FIELD_ORIENTATION ORIENTATION ;

/* absorber name=NAME x=INTEGER y=INTEGER width=INTEGER height=INTEGER */
entry_absorber : START_ABSORBER FIELD_NAME NAME FIELD_X INTEGER FIELD_Y INTEGER FIELD_WIDTH INTEGER FIELD_HEIGHT INTEGER ;

/* fire trigger=NAME action=NAME */
entry_fire : START_FIRE FIELD_TRIGGER NAME FIELD_ACTION NAME ;
