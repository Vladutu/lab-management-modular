package com.iquestint.exception;

/**
 * @author vladu
 */
public class ServiceEntityAlreadyExistsException extends ServiceException {

    public ServiceEntityAlreadyExistsException() {
        super();
    }

    public ServiceEntityAlreadyExistsException(String message) {
        super(message);
    }

    public ServiceEntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceEntityAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
