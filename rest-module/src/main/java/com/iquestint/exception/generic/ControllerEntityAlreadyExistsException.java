package com.iquestint.exception.generic;

/**
 * This exception is thrown from the rest module when an entity already exists.
 *
 * @author Georgian Vladutu
 */
public class ControllerEntityAlreadyExistsException extends ControllerException {

    public ControllerEntityAlreadyExistsException(String message) {
        super(message);
    }
}
