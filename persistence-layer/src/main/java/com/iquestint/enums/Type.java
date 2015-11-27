package com.iquestint.enums;

/**
 * This class contains all the roles that an account can have.
 *
 * @author Georgian Vladutu
 */
public enum Type {
    ADMIN("ADMIN"), PROFESSOR("PROFESSOR"), STUDENT("STUDENT");

    private String type;

    private Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
