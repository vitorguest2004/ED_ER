package Exceptions;

public class PositionAlreadySelectedException extends Exception {

    /**
     * Creates a new instance of <code>PositionOccupiedException</code> without
     * detail message.
     */
    public PositionAlreadySelectedException() {
    }

    /**
     * Constructs an instance of <code>PositionOccupiedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PositionAlreadySelectedException(String msg) {
        super(msg);
    }
}
