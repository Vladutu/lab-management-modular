package com.iquestint.exception.generic;

import com.iquestint.exception.MyApplicationException;

/**
 * @author vladu
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
