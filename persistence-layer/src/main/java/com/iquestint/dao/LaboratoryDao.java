package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.*;

import java.util.List;

/**
 * This interfaces provides methods for working with Laboratory entity explicitly (and Laboratory database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface LaboratoryDao {

    /**
     * Returns all Laboratory entities from the database.
     *
     * @return List<Laboratory>
     */
    List<Laboratory> getAllLaboratories();

    /**
     * Returns all Laboratory entities from the database which have the same section, year and semester as the method parameters.
     *
     * @param section  section
     * @param year     year
     * @param semester semester
     * @return List<Laboratory>
     */
    List<Laboratory> getLaboratories(Section section, Year year, Semester semester);

    /**
     * Returns all Laboratory entities from the database which have the same section, year, semester, group and subgroup
     * as the method parameters.
     *
     * @param section  section
     * @param year     year
     * @param semester semester
     * @param group    group
     * @param subgroup subgroup
     * @return List<Laboratory>
     */
    List<Laboratory> getLaboratories(Section section, Year year, Semester semester, Group group, Subgroup subgroup);

    /**
     * Returns the Laboratory entity from the database which has the same id as the method parameter.
     *
     * @param id laboratory id
     * @return Laboratory
     * @throws DaoEntityNotFoundException if the laboratory with the specified id is not found
     */
    Laboratory getLaboratoryById(int id) throws DaoEntityNotFoundException;

    /**
     * Saves laboratory into the database.
     *
     * @param laboratory laboratory to be saved
     * @throws DaoEntityAlreadyExists if laboratory already exists in the database
     */
    void saveLaboratory(Laboratory laboratory) throws DaoEntityAlreadyExists;

    /**
     * Updates laboratory into the database.
     *
     * @param laboratory laboratory to updated
     * @throws DaoEntityNotFoundException if laboratory is not found in the database
     */
    void updateLaboratory(Laboratory laboratory) throws DaoEntityNotFoundException;

    /**
     * Deletes the laboratory which has the same id as the method parameter from the database.
     *
     * @param id laboratory id
     * @throws DaoEntityNotFoundException if the laboratory with the specified id is not found
     */
    void deleteLaboratoryById(int id) throws DaoEntityNotFoundException;

    List<Laboratory> getLaboratoriesByDateAndTime(String professorPnc, Hour from, Day day);
}
