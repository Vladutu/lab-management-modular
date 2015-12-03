package com.iquestint.service;

import com.iquestint.dto.ProfessorDto;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Professor;

import java.util.List;

/**
 * This interfaces provides methods for manipulating Professor objects.
 *
 * @author Georgian Vladutu
 */
public interface AdministrationProfessorService {

    /**
     * This method saves a professor.
     *
     * @param professorDto professor to be saved
     * @throws ServiceEntityAlreadyExistsException if the professor already exists in the repository
     */
    void saveProfessor(ProfessorDto professorDto) throws ServiceEntityAlreadyExistsException;

    /**
     * This method deletes a professor by specifying his/her personal numeric code(pnc).
     *
     * @param pnc the personal numeric code of the professor
     * @throws ServiceEntityNotFoundException if the professor is not found
     */
    void deleteProfessor(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a professor by specifying it's personal numeric code(pnc).
     *
     * @param pnc the personal numeric code of the professor
     * @return Professor
     * @throws ServiceEntityNotFoundException if the professor is not found
     */
    ProfessorDto getProfessorByPnc(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves all professors.
     *
     * @return List<Professor>
     */
    List<ProfessorDto> getAllProfessors();

    /**
     * This method update a professor.
     *
     * @param professorDto the professor to be updated
     * @throws ServiceEntityNotFoundException if the professor is not found
     */
    void updateProfessor(ProfessorDto professorDto) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a professor by specifying his/her first name and last name.
     *
     * @param firstName the professor's first name
     * @param lastName  the professor's last name
     * @return Professor
     * @throws ServiceEntityNotFoundException if the professor is not found
     */
    Professor getProfessorByName(String firstName, String lastName) throws ServiceEntityNotFoundException;
}
