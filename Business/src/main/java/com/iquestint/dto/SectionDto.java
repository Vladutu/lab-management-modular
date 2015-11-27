package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is a dto that is passed to the view.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class SectionDto {

    private int id;

    private String name;

    public SectionDto() {
    }

    public SectionDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
