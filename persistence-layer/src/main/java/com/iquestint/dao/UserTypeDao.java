package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.UserType;

import java.util.List;

/**
 * This interfaces provides methods for working with UserType entity explicitly (and Type database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface UserTypeDao {

    /**
     * Returns the UserType entity from the database which has the same name as the method parameter.
     *
     * @param name the name of the userType
     * @return UserType
     * @throws DaoEntityNotFoundException if the userType with the specified name is not found
     */
    UserType getUserTypeByName(String name) throws DaoEntityNotFoundException;

    /**
     * Returns all UserType entities from the database.
     *
     * @return list of user types
     */
    List<UserType> getAllUserTypes();
}
