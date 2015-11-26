package com.iquestint.service;

import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Professor;
import com.iquestint.notUsed.ProfessorDto;

import java.util.List;

/**
 * This interfaces provides methods for manipulating Professor objects.
 *
 * @author Georgian Vladutu
 */
public interface ProfessorService {

    /**
     * This method saves a professor. It throws ServiceEntityAlreadyExistsException if the professor was already saved.
     *
     * @param professorDto professor to be saved
     * @throws ServiceEntityAlreadyExistsException
     */
    void saveProfessor(ProfessorDto professorDto) throws ServiceEntityAlreadyExistsException;

    /**
     * This method deletes a professor by specifying his/her personal numeric code(pnc). It throws ServiceEntityNotFoundException
     * if the professor is not found.
     *
     * @param pnc the personal numeric code of the professor
     * @throws ServiceEntityNotFoundException
     */
    void deleteProfessor(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a professor by specifying it's personal numeric code(pnc). It throws ServiceEntityNotFoundException
     * if the professor is not found.
     *
     * @param pnc the personal numeric code of the professor
     * @return Professor
     * @throws ServiceEntityNotFoundException
     */
    ProfessorDto getProfessorByPnc(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves all professors.
     *
     * @return List<Professor>
     */
    List<ProfessorDto> getAllProfessors();

    /**
     * This method update a professor. It throws ServiceEntityNotFoundException if the professor is not found.
     *
     * @param professorDto the professor to be updated
     * @throws ServiceEntityNotFoundException
     */
    void updateProfessor(ProfessorDto professorDto) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a professor by specifying his/her first name and last name. It throws
     * ServiceEntityNotFoundException if the professor is not found.
     *
     * @param firstName the professor's first name
     * @param lastName  the professor's last name
     * @return Professor
     * @throws ServiceEntityNotFoundException
     */
    Professor getProfessorByName(String firstName, String lastName) throws ServiceEntityNotFoundException;
}
