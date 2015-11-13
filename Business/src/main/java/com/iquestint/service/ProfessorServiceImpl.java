package com.iquestint.service;

import com.iquestint.dao.ProfessorDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements the ProfessorService interface.
 *
 * @author Georgian Vladutu
 */
@Service("professorService")
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorDao professorDao;

    @Override
    public void saveProfessor(Professor professor) throws ServiceEntityAlreadyExistsException {
        try {
            professorDao.saveProfessor(professor);
        }
        catch (DaoEntityAlreadyExists e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }
    }

    @Override
    public void deleteProfessor(String pnc) throws ServiceEntityNotFoundException {
        try {
            professorDao.deleteProfessorByPnc(pnc);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Professor getProfessorByPnc(String pnc) throws ServiceEntityNotFoundException {
        try {
            return professorDao.findByPnc(pnc);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<Professor> getAllProfessors() {
        return professorDao.findAllProfessors();
    }

    @Override
    public void updateProfessor(Professor professor) throws ServiceEntityNotFoundException {
        try {
            professorDao.updateProfessor(professor);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Professor getProfessorByName(String firstName, String lastName) throws ServiceEntityNotFoundException {
        try {
            return professorDao.findProfessorByName(firstName, lastName);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
