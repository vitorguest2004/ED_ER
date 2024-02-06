package Exceptions;

public class InvalidOptionException extends Exception {

    /**
     * Creates a new instance of <code>InnvalidOptionException</code> without
     * detail message.
     */
    public InvalidOptionException() {
    }

    /**
     * Constructs an instance of <code>InnvalidOptionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidOptionException(String msg) {
        super(msg);
    }
}
