package com.iquestint.validation;

import com.iquestint.dao.ProfessorDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Professor;
import com.iquestint.dto.FormProfessorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author vladu
 */
@Component
public class ProfessorValidator implements ConstraintValidator<ProfessorExists, FormProfessorDto> {

    @Autowired
    ProfessorDao professorDao;

    @Override
    public void initialize(ProfessorExists professorExists) {

    }

    @Override
    public boolean isValid(FormProfessorDto professorDto, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Professor professor = professorDao.getProfessorByPnc(professorDto.getPnc());

            return true;
        } catch (DaoEntityNotFoundException e) {
            return false;
        }
    }
}
