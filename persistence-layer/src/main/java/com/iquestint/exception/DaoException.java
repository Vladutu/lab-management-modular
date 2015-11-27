package com.iquestint.exception;

/**
 * This is a generic exception that the persistence layer can throw.
 *
 * @author Georgian Vladutu
 */
public class DaoException extends MyApplicationException {

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
