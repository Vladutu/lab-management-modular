package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class GradeDto {

    private int id;

    private Integer value;

    private LocalDate date;

    public GradeDto() {
    }

    public GradeDto(Integer value, LocalDate date) {
        this.value = value;
        this.date = date;
    }
}
