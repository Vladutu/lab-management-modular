package com.iquestint.exception;

/**
 * This exception is thrown from the service layer when it receives an invalid semester from the user.
 *
 * @author Georgian Vladutu
 */
public class ServiceInvalidSemesterException extends Exception {

    public ServiceInvalidSemesterException() {
        super();
    }

    public ServiceInvalidSemesterException(String message) {
        super(message);
    }

    public ServiceInvalidSemesterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceInvalidSemesterException(Throwable cause) {
        super(cause);
    }
}
