package com.iquestint.validation;

import com.iquestint.dto.StudentWithGradeAndAttendanceDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author vladu
 */
public class StudentWithGradeValidator
    implements ConstraintValidator<StudentWithGradeValid, List<StudentWithGradeAndAttendanceDto>> {

    @Override
    public void initialize(StudentWithGradeValid studentWithGradeValid) {

    }

    @Override
    public boolean isValid(List<StudentWithGradeAndAttendanceDto> studentWithGradeAndAttendanceDtos,
        ConstraintValidatorContext constraintValidatorContext) {
        for (StudentWithGradeAndAttendanceDto dto : studentWithGradeAndAttendanceDtos) {
            if (dto.getAttendance() == true && dto.getGrade() == null) {
                return false;
            }
        }

        return true;
    }
}
