package com.iquestint.exception;

/**
 * This exception is thrown from the service layer when a searched entity already exists.
 *
 * @author Georgian Vladutu
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
