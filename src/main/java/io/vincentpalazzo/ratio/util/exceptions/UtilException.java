package io.vincentpalazzo.ratio.util.exceptions;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class UtilException extends RuntimeException{
    public UtilException() {
        super();
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

    protected UtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
