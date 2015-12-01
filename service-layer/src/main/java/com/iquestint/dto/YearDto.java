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
public class YearDto {

    private int id;

    private Integer value;

    public YearDto() {

    }

    public YearDto(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
