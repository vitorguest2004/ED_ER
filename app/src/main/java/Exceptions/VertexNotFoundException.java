package Exceptions;

public class VertexNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>VertexNotFoundException</code> without detail
     * message.
     */
    public VertexNotFoundException() {
    }

    /**
     * Constructs an instance of <code>VertexNotFoundException</code> with the
     * specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public VertexNotFoundException(String msg) {
        super(msg);
    }
}
