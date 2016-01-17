package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Section;

import java.util.List;

/**
 * This interfaces provides methods for working with Section entity explicitly (and Section database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface SectionDao {

    /**
     * Returns the Section entity from the database which has the same name as the method parameter.
     *
     * @param name the name of the section
     * @return Section
     * @throws DaoEntityNotFoundException if the section with the specified name is not found
     */
    public Section getSectionByName(String name) throws DaoEntityNotFoundException;

    /**
     * Returns all Section entities from the database.
     *
     * @return list of sections
     */
    public List<Section> getAllSections();
}
