package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Group;

import java.util.List;

/**
 * This interfaces provides methods for working with Group entity explicitly (and Group database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface GroupDao {

    /**
     * Returns the Group entity from the database which has the same name as the method parameter.
     *
     * @param name the name of the group
     * @return Group
     * @throws DaoEntityNotFoundException if the group with the specified name is not found
     */
    public Group getGroupByName(String name) throws DaoEntityNotFoundException;

    /**
     * Returns all Group entities from the database.
     *
     * @return list of groups
     */
    public List<Group> getAllGroups();
}
