package com.iquestint.exception.generic;

import com.iquestint.exception.MyApplicationException;

/**
 * This is a generic exception that the rest module can throw.
 *
 * @author Georgian Vladutu
 */
public class ControllerException extends MyApplicationException {

    public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }
}
