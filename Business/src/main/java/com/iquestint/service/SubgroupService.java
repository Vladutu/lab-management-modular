package com.iquestint.service;

import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Subgroup;

import java.util.List;

/**
 * This interface provides methods for manipulating Subgroup objects.
 *
 * @author Georgian Vladutu
 */
public interface SubgroupService {

    /**
     * This method retrieves all subgroups.
     *
     * @return List<Section>
     */
    List<Subgroup> getAllSubgroups();

    /**
     * This method retrieves a subgroup by specifying it's name. It throws ServiceEntityNotFoundException if the subgroup
     * is not found.
     *
     * @param name the name of the subgroup
     * @return Subgroup
     * @throws ServiceEntityNotFoundException
     */
    Subgroup getSubgroupByName(String name) throws ServiceEntityNotFoundException;
}
