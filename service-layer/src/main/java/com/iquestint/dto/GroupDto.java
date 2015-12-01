package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class GroupDto {

    private int id;

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
