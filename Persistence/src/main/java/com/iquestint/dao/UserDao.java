package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.User;

import java.util.List;

/**
 * @author vladu
 */
public interface UserDao {

    List<User> findAllUsers();

    User findUserByPnc(String pnc) throws DaoEntityNotFoundException;

    User findUserByName(String firstName, String lastName) throws DaoEntityNotFoundException;

    void saveUser(User user) throws DaoEntityAlreadyExists;

    void updateUser(User user) throws DaoEntityNotFoundException;

    void updateUserNoPassword(User user) throws DaoEntityNotFoundException;

    void deleteUserByPnc(String pnc) throws DaoEntityNotFoundException;
}
