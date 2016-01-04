package com.iquestint.exception.generic;

/**
 * This exception is thrown from the rest module when an entity is not found.
 *
 * @author Georgian Vladutu
 */
public class ControllerEntityNotFoundException extends ControllerException {

    public ControllerEntityNotFoundException(String message) {
        super(message);
    }
}
