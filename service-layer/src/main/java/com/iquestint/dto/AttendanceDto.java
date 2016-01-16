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
public class AttendanceDto {

    private int id;

    private LocalDate date;

    public AttendanceDto() {
    }

    public AttendanceDto(LocalDate date) {
        this.date = date;
    }
}
