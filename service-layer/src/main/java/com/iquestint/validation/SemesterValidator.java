package com.iquestint.validation;

import com.iquestint.dao.SemesterDao;
import com.iquestint.model.Semester;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This class is a validator for @SemesterExists annotation.
 *
 * @author Georgian Vladutu
 */
public class SemesterValidator implements ConstraintValidator<SemesterExists, Integer> {

    @Autowired
    private SemesterDao semesterDao;

    @Override
    public void initialize(SemesterExists semesterExists) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        List<Semester> semesters = semesterDao.getAllSemesters();

        for (Semester semester : semesters) {
            if (semester.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
