package com.iquestint.dao.interfaces;

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
     * Returns the Group entity from the database which has the same name as the method parameter. This method throws
     * DaoEntityNotFoundException if the group with the specified name is not found.
     *
     * @param name the name of the group
     * @return Group
     * @throws DaoEntityNotFoundException
     */
    public Group getGroupByName(String name) throws DaoEntityNotFoundException;

    /**
     * Returns all Group entities from the database.
     *
     * @return List<Group>
     */
    public List<Group> getAllGroups();
}
