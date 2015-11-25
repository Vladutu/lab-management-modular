package com.iquestint.service;

import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.User;

import java.util.List;

/**
 * This interfaces provides methods for manipulating User objects.
 *
 * @author Georgian Vladutu
 */
public interface UserService {

    /**
     * This method saves a user. It throws ServiceEntityAlreadyExistsException if the user already exists.
     *
     * @param user user to be saved
     * @throws ServiceEntityAlreadyExistsException
     */
    void saveUser(User user) throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException;

    /**
     * This method deletes a user by specifying his/her personal numeric code(pnc). It throws ServiceEntityNotFoundException
     * if the user is not found.
     *
     * @param pnc the personal numeric code of the user
     * @throws ServiceEntityNotFoundException
     */
    void deleteUser(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a user by specifying it's personal numeric code(pnc). It throws ServiceEntityNotFoundException
     * if the user is not found.
     *
     * @param pnc the personal numeric code of the user
     * @return User
     * @throws ServiceEntityNotFoundException
     */
    User getUserByPnc(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves all user.
     *
     * @return List<User>
     */
    List<User> getAllUsers();

    /**
     * This method update a user. It throws ServiceEntityNotFoundException if the user is not found.
     *
     * @param user the user to be updated
     * @throws ServiceEntityNotFoundException
     */
    void updateUser(User user) throws ServiceEntityNotFoundException;

    /**
     * This method update a user without his/her password. It throws ServiceEntityNotFoundException if the user is not found.
     *
     * @param user the user to be updated
     * @throws ServiceEntityNotFoundException
     */
    void updateUserNoPassword(User user) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a user by specifying his/her first name and last name. It throws
     * ServiceEntityNotFoundException if the user is not found.
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @return User
     * @throws ServiceEntityNotFoundException
     */
    User getUserByName(String firstName, String lastName) throws ServiceEntityNotFoundException;
}
