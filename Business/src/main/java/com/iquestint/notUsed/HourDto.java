package com.iquestint.notUsed;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vladu
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