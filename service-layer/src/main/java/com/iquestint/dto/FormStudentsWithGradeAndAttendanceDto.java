package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vladu
 */
@Getter
@Setter
public class FormStudentsWithGradeAndAttendanceDto {

    private Integer laboratoryId;

    private List<StudentWithGradeAndAttendanceDto> studentsWithGradeAndAttendance = new ArrayList<>(50);
}
