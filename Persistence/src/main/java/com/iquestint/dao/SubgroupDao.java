package com.iquestint.dao;

import com.iquestint.model.Subgroup;

import java.util.List;

/**
 * This interface provides methods for working with Subgroup entity explicitly (and Subgroup database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface SubgroupDao {

    /**
     * Returns the Subgroup entity from the database which has the same name as the method parameter.
     *
     * @param name the name of the section
     * @return Subgroup
     */
    public Subgroup getSubgroupByName(String name);

    /**
     * Returns all Subgroup entities from the database.
     *
     * @return List<Subgroup>
     */
    public List<Subgroup> getAllSubgroups();
}
