package com.iquestint.dto;

/**
 * This class is a dto that is passed to the view.
 *
 * @author Georgian Vladutu
 */
public class SectionDto {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
