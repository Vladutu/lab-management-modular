package com.iquestint.exception;

/**
 * This exception is thrown when a searched entity is not found.
 *
 * @author Georgian Vladutu
 */
public class DaoEntityNotFoundException extends DaoException {

    public DaoEntityNotFoundException() {
        super();
    }

    public DaoEntityNotFoundException(String message) {
        super(message);
    }

    public DaoEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoEntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
