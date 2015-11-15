package com.iquestint.enums;

/**
 * This enum contains all the states that an account can have.
 *
 * @author Georgian Vladutu
 */
public enum State {
    ACTIVE("Active"), INACTIVE("Inactive"), DELETED("Deleted"), BLOCKED("Blocked");

    private String state;

    private State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
