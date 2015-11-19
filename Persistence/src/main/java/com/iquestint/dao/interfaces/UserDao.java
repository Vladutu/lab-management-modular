package com.iquestint.dao.interfaces;

import com.iquestint.exception.DaoEntityAlreadyExists;
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
    List<User> findAllUsers();

    /**
     * Returns the User entity from the database which has the same personal numeric code(pnc) as the method
     * parameter. This method throws DaoEntityNotFoundException if the user with the specified pnc is not found.
     *
     * @param pnc the personal numeric code of the user
     * @return User
     * @throws DaoEntityNotFoundException
     */
    User findUserByPnc(String pnc) throws DaoEntityNotFoundException;

    /**
     * Returns the User entity from the database which has the same first name and last name as the method parameters.
     * This method throws DaoEntityNotFoundException if the user with the specified name is not found.
     *
     * @param firstName user's first name
     * @param lastName  user's last name
     * @return User
     * @throws DaoEntityNotFoundException
     */
    User findUserByName(String firstName, String lastName) throws DaoEntityNotFoundException;

    /**
     * Saves the user into the database. This method throws DaoEntityAlreadyExists if the user already exists
     * in the database.
     *
     * @param user the user to be saved
     * @throws DaoEntityAlreadyExists
     */
    void saveUser(User user) throws DaoEntityAlreadyExists;

    /**
     * Updates the user into the database. This user primary key must already be present in the database.
     *
     * @param user user to be updated
     * @throws DaoEntityNotFoundException
     */
    void updateUser(User user) throws DaoEntityNotFoundException;

    /**
     * Updates the user into the database without his/her password. This user primary key must already be present in the database.
     *
     * @param user user to be updated
     * @throws DaoEntityNotFoundException
     */
    void updateUserNoPassword(User user) throws DaoEntityNotFoundException;

    /**
     * Deletes the user from the database by specifying his/her personal numeric code.
     *
     * @param pnc the personal numeric code of the user
     * @throws DaoEntityNotFoundException
     */
    void deleteUserByPnc(String pnc) throws DaoEntityNotFoundException;
}
