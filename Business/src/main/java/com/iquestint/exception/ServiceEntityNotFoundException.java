package com.iquestint.exception;

/**
 * @author vladu
 */
public class ServiceEntityNotFoundException extends ServiceException {

    public ServiceEntityNotFoundException() {
        super();
    }

    public ServiceEntityNotFoundException(String message) {
        super(message);
    }

    public ServiceEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceEntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
