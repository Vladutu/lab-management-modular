package com.iquestint.validation;

import com.iquestint.dto.ProfessorDto;
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
public class ProfessorValidator implements ConstraintValidator<ProfessorExists, ProfessorDto> {

    @Autowired
    ProfessorService professorService;

    @Override
    public void initialize(ProfessorExists professorExists) {

    }

    @Override
    public boolean isValid(ProfessorDto professorDto, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Professor professor = professorService.getProfessorByPnc(professorDto.getPnc());

            return true;
        }
        catch (ServiceEntityNotFoundException e) {
            return false;
        }
    }
}
