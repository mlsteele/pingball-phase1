package common.netprotocol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import physics.Vect;
import common.Constants.BoardSide;


/**
 * NetworkMessageBuilder is a static class which
 * creates messages to pass between the client and server.
 *
 * The wire protocol is completely contained within this class
 *
 */
public abstract class NetworkMessage {
    /**
     * Decode from a message received to an instance of a NetworkMessage.
     * See NetworkMessage.serialize for a description of the serialization grammar.
     *
     * Implementations of NetworkMessage should override this method
     * to decode only the BODY of the message and return an instance
     * of that NetworkMessage.
     *
     * This implementation is only responsible for determining which
     * implementation of NetworkMessage should do the rest of the deserialization.
     *
     * @param message string to decode
     * @return decoded NetworkMessage
     * @throws DecodeException indicator of failure
     */
    public static NetworkMessage deserialize(String message) throws DecodeException {
        // Extract the header
        Pattern headerPattern = Pattern.compile("(.*)\n");
        Matcher headerMatcher = headerPattern.matcher(message);
        if (! headerMatcher.find()) {
            throw new DecodeException("No valid header found.");
        }
        String header = headerMatcher.group(1);
        String body = message.substring(header.length() + 1);

        if (header.equals(BallInMessage.class.getSimpleName())) {
            return BallInMessage.deserialize(body);
        } else {
            throw new DecodeException("Unrecognized header: " + header);
        }
    }

    /**
     * Serialize a network message to be sent over the network.
     * All NetworkMessages can be serialized.
     *
     * All serializations result in a string which
     * begins with a header consisting of a string unique
     * to that message type followed by ": " followed
     * by any data which the message class serializes and deserializes.
     *
     * This is the grammar for a NetworkMessage:
     * serialization ::= header body
     * header ::= messagetype newline
     * messagetype ::= <message class name>
     * body ::= <anything decodeable>
     * newline ::= '\n'
     *
     * body is not well specified because each implementation
     * of NetworkMessage will handle the body within its
     * own specific serialize and deserialize methods.
     *
     * For example, for a hypothetical FooMessage,
     * a serialization could be:
     * "FooMessage\nx: 1, y: 2, c: 3"
     *
     * @return string serialization of NetworkMessage
     */
    public abstract String serialize();


    /**
     * Exception thrown when a NetworkMessage message cannot be decoded.
     */
    public static class DecodeException extends Exception {
        private static final long serialVersionUID = 1L;

        /**
         * Constructs a DecodeException with a detail message.
         * @param message description of the error
         */
        public DecodeException(String message) {
            super(message);
        }

        /**
         * Constructs a DecodeException with a detail message and cause.
         * @param message description of the error
         * @param cause exception that caused this error
         */
        public DecodeException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Serialize a Vect.
     * Helper method for message serialization.
     *
     * @param v Vect to serialize
     * @return string representation of Vect
     */
    protected static String serializeVect(Vect v) {
        return "" + v.x() + " " + v.y();
    }

    /**
     * Deserialize a Vect.
     * Helper method for message serialization.
     *
     * @param v string representation of Vect
     * @return deserialized Vect
     */
    protected static Vect deserializeVect(String v) throws DecodeException {
        // Extract the x and y coordinates of the Vect.
        Pattern vectPattern = Pattern.compile("(-?[0-9]+\\.[0-9]+) (-?[0-9]+\\.[0-9]+)");
        Matcher vectMatcher = vectPattern.matcher(v);
        if (! vectMatcher.find()) {
            throw new DecodeException("Could not decode Vect (malformed Vect): " + v);
        }
        String stringX = vectMatcher.group(1);
        String stringY = vectMatcher.group(2);
        try {
            double x = Double.parseDouble(stringX);
            double y = Double.parseDouble(stringY);
            return new Vect(x, y);
        } catch (NumberFormatException e) {
            throw new DecodeException("Could not decode Vect (malformed double): " + v, e);
        }
    }

    /**
     * Serialize a BoardSide.
     * Helper method for message serialization.
     *
     * @param bs BoardSide to serialize
     * @return string representation of BoardSide
     */
    protected static String serializeBoardSide(BoardSide bs) {
        if (bs == BoardSide.LEFT) {
            return "L";
        } else if (bs == BoardSide.RIGHT) {
            return "R";
        } else if (bs == BoardSide.TOP) {
            return "T";
        } else if (bs == BoardSide.BOTTOM) {
            return "B";
        } else {
            // this should never happen.
            throw new RuntimeException("Unknown BoardSide to serialize.");
        }
    }

    /**
     * Deserialize a BoardSide.
     * Helper method for message deserialization.
     *
     * @param bs string representation of BoardSide
     * @return deserialized BoardSide
     * @throws DecodeException indicator of failure
     */
    protected static BoardSide deserializeBoardSide(String bs) throws DecodeException {
        if (bs.equals("L")) {
            return BoardSide.LEFT;
        } else if (bs.equals("R")) {
            return BoardSide.RIGHT;
        } else if (bs.equals("T")) {
            return BoardSide.TOP;
        } else if (bs.equals("B")) {
            return BoardSide.BOTTOM;
        } else {
            throw new DecodeException("Could not deserialize BoardSide: " + bs);
        }
    }
}
