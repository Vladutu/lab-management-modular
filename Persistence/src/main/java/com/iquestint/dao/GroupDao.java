package com.iquestint.dao;

import com.iquestint.model.Group;

import java.util.List;

/**
 * This interface provides methods for working with Group entity explicitly (and Group database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface GroupDao {

    /**
     * Returns the Group entity from the database which has the same name as the method parameter.
     *
     * @param name the name of the group
     * @return Group
     */
    public Group getGroupByName(String name);

    /**
     * Returns all Group entities from the database.
     *
     * @return List<Group>
     */
    public List<Group> getAllGroups();
}
