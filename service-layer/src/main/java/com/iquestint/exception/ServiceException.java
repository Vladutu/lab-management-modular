package com.iquestint.exception;

/**
 * This is a generic exception that the service layer can throw.
 *
 * @author Georgian Vladutu
 */
public class ServiceException extends MyApplicationException {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
