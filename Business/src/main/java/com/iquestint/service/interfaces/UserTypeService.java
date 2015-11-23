package com.iquestint.service.interfaces;

import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.UserType;

import java.util.List;

/**
 * This interfaces provides methods for manipulating UserType objects.
 *
 * @author Georgian Vladutu
 */
public interface UserTypeService {

    /**
     * This method retrieves a user type by specifying it's name. It throws ServiceEntityNotFoundException if the user type
     * is not found.
     *
     * @param name the name of the user type
     * @return UserType
     * @throws ServiceEntityNotFoundException
     */
    UserType getUserTypeByName(String name) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves all user types.
     *
     * @return List<UserType>
     */
    List<UserType> getAllUserTypes();
}
