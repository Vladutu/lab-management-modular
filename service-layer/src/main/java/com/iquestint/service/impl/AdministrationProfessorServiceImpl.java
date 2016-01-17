package com.iquestint.service.impl;

import com.iquestint.dao.LaboratoryDao;
import com.iquestint.dao.ProfessorDao;
import com.iquestint.dao.UserDao;
import com.iquestint.dto.ProfessorDto;
import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Laboratory;
import com.iquestint.model.Professor;
import com.iquestint.service.AdministrationProfessorService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This class implements the AdministrationProfessorService interface.
 *
 * @author Georgian Vladutu
 */
@Service("administrationProfessorService")
@Transactional
public class AdministrationProfessorServiceImpl implements AdministrationProfessorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministrationProfessorServiceImpl.class);

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
        } catch (DaoEntityAlreadyExistsException e) {
            LOGGER.debug("DaoEntityAlreadyExistsException");
            throw new ServiceEntityAlreadyExistsException(e);
        }
    }

    @Override
    public void deleteProfessor(String pnc) throws ServiceEntityNotFoundException {
        try {
            Professor professor = professorDao.getProfessorByPnc(pnc);
            for (Laboratory laboratory : professor.getLaboratories()) {
                laboratoryDao.deleteLaboratoryById(laboratory.getId());
            }

            userDao.deleteUserByPnc(pnc);
            professorDao.deleteProfessorByPnc(pnc);
        } catch (DaoEntityNotFoundException e) {
            LOGGER.debug("DaoEntityNotFoundException");
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public ProfessorDto getProfessorByPnc(String pnc) throws ServiceEntityNotFoundException {
        try {
            Professor professor = professorDao.getProfessorByPnc(pnc);

            return modelMapper.map(professor, ProfessorDto.class);
        } catch (DaoEntityNotFoundException e) {
            LOGGER.debug("DaoEntityNotFoundException");
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<ProfessorDto> getAllProfessors() {
        List<Professor> professors = professorDao.getAllProfessors();
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
            LOGGER.debug("DaoEntityNotFoundException");
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Professor getProfessorByName(String firstName, String lastName) throws ServiceEntityNotFoundException {
        try {
            return professorDao.getProfessorByName(firstName, lastName);
        } catch (DaoEntityNotFoundException e) {
            LOGGER.debug("DaoEntityNotFoundException");
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
