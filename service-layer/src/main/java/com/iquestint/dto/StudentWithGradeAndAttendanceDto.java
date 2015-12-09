package com.iquestint.dto;

import com.iquestint.validation.Pnc;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author vladu
 */
@Getter
@Setter
public class StudentWithGradeAndAttendanceDto {

    @NotNull
    @Pnc
    private String pnc;

    @NotNull
    private Boolean attendance;

    @Max(10)
    @Min(1)
    @NotNull
    private Integer grade;
}
