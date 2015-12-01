package com.iquestint.exception;

/**
 * This exception is thrown when a searched entity already exists.
 *
 * @author Georgian Vladutu
 */
public class DaoEntityAlreadyExists extends DaoException {

    public DaoEntityAlreadyExists() {
        super();
    }

    public DaoEntityAlreadyExists(String message) {
        super(message);
    }

    public DaoEntityAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoEntityAlreadyExists(Throwable cause) {
        super(cause);
    }
}
