package com.iquestint.service;

import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.User;

import java.util.List;

/**
 * @author vladu
 */
public interface UserService {

    void saveUser(User user) throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException;

    void deleteUser(String pnc) throws ServiceEntityNotFoundException;

    User getUserByPnc(String pnc) throws ServiceEntityNotFoundException;

    List<User> getAllUsers();

    void updateUser(User user) throws ServiceEntityNotFoundException;

    User getUserByName(String firstName, String lastName) throws ServiceEntityNotFoundException;
}
