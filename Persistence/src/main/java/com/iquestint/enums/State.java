package com.iquestint.enums;

/**
 * @author vladu
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
