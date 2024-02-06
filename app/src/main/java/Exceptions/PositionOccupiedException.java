package Exceptions;

public class PositionOccupiedException extends Exception {

    /**
     * Creates a new instance of <code>PositionOccupiedException</code> without
     * detail message.
     */
    public PositionOccupiedException() {
    }

    /**
     * Constructs an instance of <code>PositionOccupiedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PositionOccupiedException(String msg) {
        super(msg);
    }
}
