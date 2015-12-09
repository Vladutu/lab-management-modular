package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author vladu
 */
@Getter
@Setter
public class AttendanceDto {

    private int id;

    private LocalDate date;

    public AttendanceDto(LocalDate date) {
        this.date = date;
    }
}
