package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Professor;

import java.util.List;

/**
 * This interfaces provides methods for working with Professor entity explicitly (and Professor database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface ProfessorDao {

    /**
     * Returns all Professor entities from the database.
     *
     * @return List<Professor>
     */
    List<Professor> getAllProfessors();

    /**
     * Returns the Professor entity from the database which has the same personal numeric code(pnc) as the method
     * parameter.
     *
     * @param pnc the personal numeric code of the professor
     * @return Professor
     * @throws DaoEntityNotFoundException if the professor with the specified pnc is not found
     */
    Professor getProfessorByPnc(String pnc) throws DaoEntityNotFoundException;

    /**
     * Returns the Professor entity from the database which has the same first name and last name as the method parameters.
     *
     * @param firstName professor's first name
     * @param lastName  professor's last name
     * @return Professor
     * @throws DaoEntityNotFoundException if the professor with the specified name is not found
     */
    Professor getProfessorByName(String firstName, String lastName) throws DaoEntityNotFoundException;

    /**
     * Saves professor into the database.
     *
     * @param professor professor to be saved
     * @throws DaoEntityAlreadyExistsException if the professor already exists in the database
     */
    void saveProfessor(Professor professor) throws DaoEntityAlreadyExistsException;

    /**
     * Updates professor into the database. This professor primary key must already be present in the database.
     *
     * @param professor professor to be updated
     * @throws DaoEntityNotFoundException if the professor is not found
     */
    void updateProfessor(Professor professor) throws DaoEntityNotFoundException;

    /**
     * Deletes the professor from the database by specifying his/her personal numeric code.
     *
     * @param pnc the personal numeric code of the professor
     * @throws DaoEntityNotFoundException if the professor is not found
     */
    void deleteProfessorByPnc(String pnc) throws DaoEntityNotFoundException;
}

