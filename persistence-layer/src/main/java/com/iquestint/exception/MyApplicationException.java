package com.iquestint.exception;

/**
 * This is the most generic exception that the application can throw.
 *
 * @author Georgian Vladutu
 */
public class MyApplicationException extends Exception {

    public MyApplicationException() {
        super();
    }

    public MyApplicationException(String message) {
        super(message);
    }

    public MyApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyApplicationException(Throwable cause) {
        super(cause);
    }
}
