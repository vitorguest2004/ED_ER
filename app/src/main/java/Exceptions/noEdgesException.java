package Exceptions;

public class NoEdgesException extends Exception {

    /**
     * Creates a new instance of <code>NewException</code> without detail
     * message.
     */
    public NoEdgesException() {
    }

    /**
     * Constructs an instance of <code>NewException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NoEdgesException(String msg) {
        super(msg);
    }
}