package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.User;

import java.util.List;

/**
 * This interfaces provides methods for working with User entity explicitly (and User database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface UserDao {

    /**
     * Returns all User entities from the database.
     *
     * @return List<User>
     */
    List<User> getAllUsers();

    /**
     * Returns the User entity from the database which has the same personal numeric code(pnc) as the method
     * parameter.
     *
     * @param pnc the personal numeric code of the user
     * @return User
     * @throws DaoEntityNotFoundException if the user with the specified pnc is not found
     */
    User getUserByPnc(String pnc) throws DaoEntityNotFoundException;

    /**
     * Saves  user into the database.
     *
     * @param user the user to be saved
     * @throws DaoEntityAlreadyExistsException if the user already exists in the database.
     */
    void saveUser(User user) throws DaoEntityAlreadyExistsException;

    /**
     * Updates  user into the database. This user primary key must already be present in the database.
     *
     * @param user user to be updated
     * @throws DaoEntityNotFoundException if the user is not found
     */
    void updateUser(User user) throws DaoEntityNotFoundException;

    /**
     * Updates  user into the database without his/her password. This user primary key must already be present in the database.
     *
     * @param user user to be updated
     * @throws DaoEntityNotFoundException if the user is not found
     */
    void updateUserNoPassword(User user) throws DaoEntityNotFoundException;

    /**
     * Deletes the user from the database by specifying his/her personal numeric code.
     *
     * @param pnc the personal numeric code of the user
     * @throws DaoEntityNotFoundException if the user with the specified pnc is not found
     */
    void deleteUserByPnc(String pnc) throws DaoEntityNotFoundException;
}
