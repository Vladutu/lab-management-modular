package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Subgroup;

import java.util.List;

/**
 * This interfaces provides methods for working with Subgroup entity explicitly (and Subgroup database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface SubgroupDao {

    /**
     * Returns the Subgroup entity from the database which has the same name as the method parameter.
     *
     * @param name the name of the section
     * @return Subgroup
     * @throws DaoEntityNotFoundException if the subgroup with the specified name is not found
     */
    public Subgroup getSubgroupByName(String name) throws DaoEntityNotFoundException;

    /**
     * Returns all Subgroup entities from the database.
     *
     * @return list of subgroups
     */
    public List<Subgroup> getAllSubgroups();
}
