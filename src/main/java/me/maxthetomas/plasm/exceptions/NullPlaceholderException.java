package me.maxthetomas.plasm.exceptions;

public class NullPlaceholderException extends Exception {
    private final Throwable cause;

    public NullPlaceholderException(String cause) {
        this.cause = new Throwable(cause);
    }

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }
}
