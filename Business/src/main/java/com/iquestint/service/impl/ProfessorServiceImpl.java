package com.iquestint.service.impl;

import com.iquestint.dao.LaboratoryDao;
import com.iquestint.dao.ProfessorDao;
import com.iquestint.dao.UserDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Laboratory;
import com.iquestint.model.Professor;
import com.iquestint.notUsed.ProfessorDto;
import com.iquestint.service.ProfessorService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This class implements the ProfessorService interfaces.
 *
 * @author Georgian Vladutu
 */
@Service("professorService")
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private LaboratoryDao laboratoryDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDao userDao;

    @Override
    public void saveProfessor(ProfessorDto professorDto) throws ServiceEntityAlreadyExistsException {
        try {
            Professor professor = modelMapper.map(professorDto, Professor.class);
            professorDao.saveProfessor(professor);
        } catch (DaoEntityAlreadyExists e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }
    }

    @Override
    public void deleteProfessor(String pnc) throws ServiceEntityNotFoundException {
        try {
            Professor professor = professorDao.findProfessorByPnc(pnc);
            for (Laboratory laboratory : professor.getLaboratories()) {
                laboratoryDao.deleteLaboratoryById(laboratory.getId());
            }

            userDao.deleteUserByPnc(pnc);
            professorDao.deleteProfessorByPnc(pnc);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public ProfessorDto getProfessorByPnc(String pnc) throws ServiceEntityNotFoundException {
        try {
            Professor professor = professorDao.findProfessorByPnc(pnc);

            return modelMapper.map(professor, ProfessorDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<ProfessorDto> getAllProfessors() {
        List<Professor> professors = professorDao.findAllProfessors();
        Type listType = new TypeToken<List<ProfessorDto>>() {
        }.getType();

        return modelMapper.map(professors, listType);
    }

    @Override
    public void updateProfessor(ProfessorDto professorDto) throws ServiceEntityNotFoundException {
        try {
            Professor professor = modelMapper.map(professorDto, Professor.class);
            professorDao.updateProfessor(professor);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Professor getProfessorByName(String firstName, String lastName) throws ServiceEntityNotFoundException {
        try {
            return professorDao.findProfessorByName(firstName, lastName);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
