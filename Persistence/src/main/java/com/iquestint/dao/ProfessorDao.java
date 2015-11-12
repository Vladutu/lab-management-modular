package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Professor;

import java.util.List;

/**
 * This interface provides methods for working with Professor entity explicitly (and Professor database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface ProfessorDao {

    /**
     * Returns all Professor entities from the database.
     *
     * @return List<Professor>
     */
    List<Professor> findAllProfessors();

    /**
     * Returns the Professor entity from the database which has the same personal numeric code(pnc) as the method
     * parameter. This method throws DaoEntityNotFoundException if the professor with the specified pnc is not found.
     *
     * @param pnc the personal numeric code of the professor
     * @return Professor
     * @throws DaoEntityNotFoundException
     */
    Professor findByPnc(String pnc) throws DaoEntityNotFoundException;

    /**
     * Returns the Professor entity from the database which has the same first name and last name as the method parameters.
     * This method throws DaoEntityNotFoundException if the professor with the specified name is not found.
     *
     * @param firstName professor's first name
     * @param lastName  professor's last name
     * @return Professor
     * @throws DaoEntityNotFoundException
     */
    Professor findProfessorByName(String firstName, String lastName) throws DaoEntityNotFoundException;

    /**
     * Saves the professor into the database. This method throws DaoEntityAlreadyExists if the professor already exists
     * in the database.
     *
     * @param professor the professor to be saved
     * @throws DaoEntityAlreadyExists
     */
    void saveProfessor(Professor professor) throws DaoEntityAlreadyExists;

    /**
     * Updates the professor into the database. This professor primary key must already be present in the database.
     *
     * @param professor professor to be updated
     * @throws DaoEntityNotFoundException
     */
    void updateProfessor(Professor professor) throws DaoEntityNotFoundException;

    /**
     * Deletes the professor from the database by specifying his/her personal numeric code.
     *
     * @param pnc the personal numeric code of the professor
     * @throws DaoEntityNotFoundException
     */
    void deleteProfessorByPnc(String pnc) throws DaoEntityNotFoundException;
}

