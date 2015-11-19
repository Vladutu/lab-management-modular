package com.iquestint.service;

import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Group;

import java.util.List;

/**
 * This interfaces provides methods for manipulating Group objects.
 *
 * @author Georgian Vladutu
 */
public interface GroupService {

    /**
     * This method retrieves all groups.
     *
     * @return List<Section>
     */
    List<Group> getAllGroups();

    /**
     * This method retrieves a group by specifying it's name. It throws ServiceEntityNotFoundException if the group
     * is not found.
     *
     * @param name the name of the group
     * @return Group
     * @throws ServiceEntityNotFoundException
     */
    Group getGroupByName(String name) throws ServiceEntityNotFoundException;
}
