package com.iquestint.form;

import com.iquestint.dto.StudentWithGradeAndAttendanceDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vladu
 */
@Getter
@Setter
public class StudentWithGradeAndAttendanceFrom {

    private List<StudentWithGradeAndAttendanceDto> studentsWithGradeAndAttendance = new ArrayList<>(50);
}
