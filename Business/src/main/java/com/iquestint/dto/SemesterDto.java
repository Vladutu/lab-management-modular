package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vladu
 */
@Getter
@Setter
public class SemesterDto {

    private int id;

    private Integer value;

    public SemesterDto() {

    }

    public SemesterDto(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
