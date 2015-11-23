package com.iquestint.service.interfaces;

import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.UserState;

import java.util.List;

/**
 * This interfaces provides methods for manipulating UserState objects.
 *
 * @author Georgian Vladutu
 */
public interface UserStateService {

    /**
     * This method retrieves a user state by specifying it's name. It throws ServiceEntityNotFoundException if the user state
     * is not found.
     *
     * @param name the name of the user state
     * @return UserState
     * @throws ServiceEntityNotFoundException
     */
    UserState getUserStateByName(String name) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves all user states.
     *
     * @return List<UserState>
     */
    List<UserState> getAllUserStates();
}
