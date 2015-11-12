package com.iquestint.enums;

/**
 * @author vladu
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
