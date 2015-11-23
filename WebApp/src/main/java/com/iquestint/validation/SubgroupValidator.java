package com.iquestint.validation;

import com.iquestint.model.Subgroup;
import com.iquestint.service.interfaces.SubgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This class is a validator for the @SubgroupExists annotation.
 *
 * @author Georgian Vladutu
 */
@Component
public class SubgroupValidator implements ConstraintValidator<SubgroupExists, String> {

    @Autowired
    private SubgroupService subgroupService;

    @Override
    public void initialize(SubgroupExists subgroupExists) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<Subgroup> subgroups = subgroupService.getAllSubgroups();

        for (Subgroup subgroup : subgroups) {
            if (subgroup.getName().equals(value)) {
                return true;
            }
        }

        return false;
    }
}