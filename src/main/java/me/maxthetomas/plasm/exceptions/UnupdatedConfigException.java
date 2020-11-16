package me.maxthetomas.plasm.exceptions;

public class UnupdatedConfigException extends Exception{
    private final Throwable cause;

    public UnupdatedConfigException(String cause) {
        this.cause = new Throwable(cause);
    }

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }
}
