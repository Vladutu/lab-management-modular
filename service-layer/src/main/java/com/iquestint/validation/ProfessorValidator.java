package com.iquestint.validation;

import com.iquestint.dao.ProfessorDao;
import com.iquestint.dto.FormProfessorDto;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is a validator for @ProfessorExists annotation.
 *
 * @author Georgian Vladutu
 */
@Component
public class ProfessorValidator implements ConstraintValidator<ProfessorExists, FormProfessorDto> {

    @Autowired
    ProfessorDao professorDao;

    private static final int PNC_LENGTH = 13;

    @Override
    public void initialize(ProfessorExists professorExists) {

    }

    @Override
    public boolean isValid(FormProfessorDto professorDto, ConstraintValidatorContext constraintValidatorContext) {
        try {
            String pnc = professorDto.getCompressedFields().substring(0, PNC_LENGTH);
            Professor professor = professorDao.getProfessorByPnc(pnc);

            return true;
        } catch (DaoEntityNotFoundException e) {
            return false;
        }
    }
}
