package com.iquestint.notUsed;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vladu
 */
@Getter
@Setter
public class WeeklyOccurrenceDto {

    private int id;

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
