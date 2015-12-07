package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vladu
 */
@Getter
@Setter
public class StudentWithGradeAndAttendanceDto {

    private String pnc;

    private Boolean attendance;

    private Integer grade;
}
