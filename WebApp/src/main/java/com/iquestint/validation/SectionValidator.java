package com.iquestint.validation;

import com.iquestint.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author vladu
 */
@Component
public class SectionValidator implements ConstraintValidator<SectionExists, String> {

    @Autowired
    private SectionService sectionService;

    @Override
    public void initialize(SectionExists section) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<com.iquestint.model.Section> sections = sectionService.getAllSections();

        for (com.iquestint.model.Section section : sections) {
            if (section.getName().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
