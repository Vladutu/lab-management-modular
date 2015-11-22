package com.iquestint.validation;

import com.iquestint.dto.SimplifiedProfessorDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Professor;
import com.iquestint.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author vladu
 */
@Component
public class ProfessorValidator implements ConstraintValidator<ProfessorExists, SimplifiedProfessorDto> {

    @Autowired
    ProfessorService professorService;

    @Override
    public void initialize(ProfessorExists professorExists) {

    }

    @Override
    public boolean isValid(SimplifiedProfessorDto professorDto, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Professor professor = professorService.getProfessorByPnc(professorDto.getPnc());

            return true;
        }
        catch (ServiceEntityNotFoundException e) {
            return false;
        }
    }
}
