package Exceptions;

public class NoMorePositionsException extends Exception {

    public NoMorePositionsException() {
    }

    public NoMorePositionsException(String message) {
        super(message);
    }
}
