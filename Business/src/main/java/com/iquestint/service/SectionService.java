package com.iquestint.service;

import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Section;

import java.util.List;

/**
 * This interfaces provides methods for manipulating Section objects.
 *
 * @author Georgian Vladutu
 */
public interface SectionService {

    /**
     * This method retrieves all sections.
     *
     * @return List<Section>
     */
    List<Section> getAllSections();

    /**
     * This method retrieves a section by specifying it's name. It throws ServiceEntityNotFoundException if the section
     * is not found.
     *
     * @param name the name of the section
     * @return Section
     * @throws ServiceEntityNotFoundException
     */
    Section getSectionByName(String name) throws ServiceEntityNotFoundException;
}
