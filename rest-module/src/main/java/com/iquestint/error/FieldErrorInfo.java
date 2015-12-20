package com.iquestint.error;

/**
 * @author vladu
 */
public class FieldErrorInfo {

    private String name;

    private String error;

    public FieldErrorInfo(String name, String error) {
        this.name = name;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
