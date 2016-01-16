package com.iquestint.exception;

/**
 * @author vladu
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
