package com.iquestint.dao;

import com.iquestint.model.Section;

import java.util.List;

/**
 * This interface provides methods for working with Section entity explicitly (and Section database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface SectionDao {

    /**
     * Returns the Section entity from the database which has the same name as the method parameter.
     *
     * @param name the name of the section
     * @return Section
     */
    public Section getSectionByName(String name);

    /**
     * Returns all Section entities from the database.
     *
     * @return List<Section>
     */
    public List<Section> getAllSections();
}
