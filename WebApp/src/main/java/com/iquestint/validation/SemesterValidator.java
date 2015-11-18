package com.iquestint.validation;

import com.iquestint.model.Semester;
import com.iquestint.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author vladu
 */
public class SemesterValidator implements ConstraintValidator<SemesterExists, String> {

    @Autowired
    private SemesterService semesterService;

    @Override
    public void initialize(SemesterExists semesterExists) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<Semester> semesters = semesterService.getAllSemesters();

        for (Semester semester : semesters) {
            if (semester.getName().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
