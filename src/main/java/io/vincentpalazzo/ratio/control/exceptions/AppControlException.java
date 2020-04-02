package io.vincentpalazzo.ratio.control.exceptions;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class AppControlException extends RuntimeException{

    public AppControlException() {
    }

    public AppControlException(String message) {
        super(message);
    }

    public AppControlException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppControlException(Throwable cause) {
        super(cause);
    }

    public AppControlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
