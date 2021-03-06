package com.iquestint.dto;

import com.iquestint.validation.StudentWithGradeValid;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class FormStudentsWithGradeAndAttendanceDto {

    private Integer laboratoryId;

    @StudentWithGradeValid
    private List<StudentWithGradeAndAttendanceDto> studentsWithGradeAndAttendance = new ArrayList<>(50);
}
