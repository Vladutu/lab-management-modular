package com.iquestint.exception;

/**
 * This exception is thrown from the service layer a date is not part on a university semester.
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
