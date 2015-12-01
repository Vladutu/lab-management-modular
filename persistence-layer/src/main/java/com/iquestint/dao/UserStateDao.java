package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.UserState;

import java.util.List;

/**
 * This interfaces provides methods for working with UserState entity explicitly (and State database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface UserStateDao {

    /**
     * Returns the UserState entity from the database which has the same name as the method parameter.
     *
     * @param name the name of the userState
     * @return UserState
     * @throws DaoEntityNotFoundException if the userState with the specified name is not found
     */
    UserState getUserStateByName(String name) throws DaoEntityNotFoundException;

    /**
     * Returns all UserState entities from the database.
     *
     * @return List<UserState>
     */
    List<UserState> getAllUserStates();
}
