package com.iquestint.service;

import com.iquestint.dao.PersonDao;
import com.iquestint.dao.UserDao;
import com.iquestint.dao.UserStateDao;
import com.iquestint.dao.UserTypeDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Person;
import com.iquestint.model.User;
import com.iquestint.model.UserState;
import com.iquestint.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements UserService interface.
 *
 * @author Georgian Vladutu
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserTypeDao userTypeDao;

    @Autowired
    private UserStateDao userStateDao;

    @Autowired
    private PersonDao personDao;

    @Override
    public void saveUser(User user) throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException {
        UserType userType = null;
        UserState userState = null;
        Person person = null;

        try {
            userType = userTypeDao.getUserTypeByName(user.getUserType().getName());
            userState = userStateDao.getUserStateByName(user.getUserState().getName());
            person = personDao.getPersonByPnc(user.getPnc());
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

        user.setUserType(userType);
        user.setUserState(userState);
        user.setPerson(person);

        try {
            userDao.saveUser(user);
        }
        catch (DaoEntityAlreadyExists e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }
    }

    @Override
    public void deleteUser(String pnc) throws ServiceEntityNotFoundException {
        try {
            userDao.deleteUserByPnc(pnc);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public User getUserByPnc(String pnc) throws ServiceEntityNotFoundException {
        try {
            return userDao.findUserByPnc(pnc);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public void updateUser(User user) throws ServiceEntityNotFoundException {
        UserType userType = null;
        UserState userState = null;
        Person person = null;

        try {
            userType = userTypeDao.getUserTypeByName(user.getUserType().getName());
            userState = userStateDao.getUserStateByName(user.getUserState().getName());
            person = personDao.getPersonByPnc(user.getPnc());

            user.setUserState(userState);
            user.setUserType(userType);
            user.setPerson(person);

            userDao.updateUser(user);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

    }

    @Override
    public void updateUserNoPassword(User user) throws ServiceEntityNotFoundException {
        UserType userType = null;
        UserState userState = null;
        Person person = null;

        try {
            userType = userTypeDao.getUserTypeByName(user.getUserType().getName());
            userState = userStateDao.getUserStateByName(user.getUserState().getName());
            person = personDao.getPersonByPnc(user.getPnc());

            user.setUserState(userState);
            user.setUserType(userType);
            user.getPerson().setPnc(person.getPnc());

            userDao.updateUserNoPassword(user);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public User getUserByName(String firstName, String lastName) throws ServiceEntityNotFoundException {
        try {
            return userDao.findUserByName(firstName, lastName);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
