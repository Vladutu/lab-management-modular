package com.iquestint.validation;

import com.iquestint.model.Year;
import com.iquestint.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author vladu
 */
public class YearValidator implements ConstraintValidator<YearExists, String> {

    @Autowired
    private YearService yearService;

    @Override
    public void initialize(YearExists yearExists) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<Year> years = yearService.getAllYears();

        for (Year year : years) {
            if (year.getName().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
