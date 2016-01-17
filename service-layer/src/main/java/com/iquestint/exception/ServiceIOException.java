package com.iquestint.exception;

/**
 * This exception is thrown from the service layer when an input-output exception is thrown.
 *
 * @author Georgian Vladutu
 */
public class ServiceIOException extends ServiceException {

    public ServiceIOException() {
        super();
    }

    public ServiceIOException(String message) {
        super(message);
    }

    public ServiceIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceIOException(Throwable cause) {
        super(cause);
    }
}
