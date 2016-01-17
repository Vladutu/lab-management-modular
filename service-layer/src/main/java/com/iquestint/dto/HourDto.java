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
public class HourDto {

    private int id;

    private Integer value;

    @Override
    public String toString() {
        return value.toString();
    }
}
