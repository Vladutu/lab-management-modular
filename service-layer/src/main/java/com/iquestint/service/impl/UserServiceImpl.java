package com.iquestint.service.impl;

import com.iquestint.dao.*;
import com.iquestint.dto.FormUserDto;
import com.iquestint.dto.UnregisteredUserDto;
import com.iquestint.dto.UserDto;
import com.iquestint.dto.UserTypeDto;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.*;
import com.iquestint.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This class implements UserService interfaces.
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

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ProfessorDao professorDao;

    @Override
    public void saveUser(UserDto userDto) throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException {
        UserType userType = null;
        UserState userState = null;
        Person person = null;
        User user = modelMapper.map(userDto, User.class);

        try {
            userType = userTypeDao.getUserTypeByName(user.getUserType().getName());
            userState = userStateDao.getUserStateByName(user.getUserState().getName());
            person = personDao.getPersonByPnc(user.getPnc());
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

        user.setUserType(userType);
        user.setUserState(userState);
        user.setPerson(person);

        try {
            userDao.saveUser(user);
        } catch (DaoEntityAlreadyExists e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }
    }

    @Override
    public void deleteUser(String pnc) throws ServiceEntityNotFoundException {
        try {
            userDao.deleteUserByPnc(pnc);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public UserDto getUserByPnc(String pnc) throws ServiceEntityNotFoundException {
        try {
            User user = userDao.getUserByPnc(pnc);

            return modelMapper.map(user, UserDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        Type listType = new TypeToken<List<UserDto>>() {
        }.getType();

        return modelMapper.map(users, listType);
    }

    @Override
    public void updateUser(UserDto userDto) throws ServiceEntityNotFoundException {
        UserType userType = null;
        UserState userState = null;
        Person person = null;
        User user = modelMapper.map(userDto, User.class);

        try {
            userType = userTypeDao.getUserTypeByName(user.getUserType().getName());
            userState = userStateDao.getUserStateByName(user.getUserState().getName());
            person = personDao.getPersonByPnc(user.getPnc());

            user.setUserState(userState);
            user.setUserType(userType);
            user.setPerson(person);

            userDao.updateUser(user);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

    }

    @Override
    public void updateUserNoPassword(UserDto userDto) throws ServiceEntityNotFoundException {
        UserType userType = null;
        UserState userState = null;
        Person person = null;
        User user = modelMapper.map(userDto, User.class);
        try {
            userType = userTypeDao.getUserTypeByName(user.getUserType().getName());
            userState = userStateDao.getUserStateByName(user.getUserState().getName());
            person = personDao.getPersonByPnc(user.getPnc());

            user.setUserState(userState);
            user.setUserType(userType);
            user.getPerson().setPnc(person.getPnc());

            userDao.updateUserNoPassword(user);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public User getUserByName(String firstName, String lastName) throws ServiceEntityNotFoundException {
        try {
            return userDao.getUserByName(firstName, lastName);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void initializeFormUserDto(FormUserDto formUserDto) {
        List<UserType> userTypes = userTypeDao.getAllUserTypes();

        List<UserTypeDto> userTypeDtos = modelMapper.map(userTypes, new TypeToken<List<UserTypeDto>>() {
        }.getType());

        formUserDto.setTypes(userTypeDtos);
    }

    @Override
    public UnregisteredUserDto getUnregisteredUser(String pnc) throws ServiceEntityNotFoundException {
        UnregisteredUserDto unregisteredUserDto = new UnregisteredUserDto();
        try {
            com.iquestint.enums.Type type = personDao.getPersonType(pnc);
            unregisteredUserDto.setUserType(type.getType());

            if (type.equals(com.iquestint.enums.Type.STUDENT)) {
                Student student = studentDao.getStudentByPnc(pnc);
                unregisteredUserDto.setEmail(student.getEmail());
                unregisteredUserDto.setFirstName(student.getFirstName());
                unregisteredUserDto.setLastName(student.getLastName());
            }
            else if (type.equals(com.iquestint.enums.Type.PROFESSOR)) {
                Professor professor = professorDao.getProfessorByPnc(pnc);
                unregisteredUserDto.setEmail(professor.getEmail());
                unregisteredUserDto.setFirstName(professor.getFirstName());
                unregisteredUserDto.setLastName(professor.getLastName());
            }

            return unregisteredUserDto;
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
