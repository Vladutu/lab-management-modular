package com.iquestint.exception;

/**
 * This exception is thrown from the service layer when a searched entity is not found.
 *
 * @author Georgian Vladutu
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
