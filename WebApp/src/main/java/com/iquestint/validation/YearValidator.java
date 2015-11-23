package com.iquestint.validation;

import com.iquestint.model.Year;
import com.iquestint.service.interfaces.YearService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author vladu
 */
public class YearValidator implements ConstraintValidator<YearExists, Integer> {

    @Autowired
    private YearService yearService;

    @Override
    public void initialize(YearExists yearExists) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        List<Year> years = yearService.getAllYears();

        for (Year year : years) {
            if (year.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
