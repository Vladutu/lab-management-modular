package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vladu
 */
@Getter
@Setter
public class YearDto {

    private int id;

    private Integer value;

    @Override
    public String toString() {
        return value.toString();
    }
}
