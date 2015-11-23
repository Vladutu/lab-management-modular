package com.iquestint.validation;

import com.iquestint.model.Semester;
import com.iquestint.service.interfaces.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author vladu
 */
public class SemesterValidator implements ConstraintValidator<SemesterExists, Integer> {

    @Autowired
    private SemesterService semesterService;

    @Override
    public void initialize(SemesterExists semesterExists) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        List<Semester> semesters = semesterService.getAllSemesters();

        for (Semester semester : semesters) {
            if (semester.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
