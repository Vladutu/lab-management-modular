package com.iquestint.exception;

/**
 * This exception is thrown when a searched entity already exists.
 *
 * @author Georgian Vladutu
 */
public class DaoEntityAlreadyExistsException extends DaoException {

    public DaoEntityAlreadyExistsException() {
        super();
    }

    public DaoEntityAlreadyExistsException(String message) {
        super(message);
    }

    public DaoEntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoEntityAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
