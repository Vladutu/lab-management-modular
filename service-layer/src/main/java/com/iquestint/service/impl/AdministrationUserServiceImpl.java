package com.iquestint.service.impl;

import com.iquestint.dao.*;
import com.iquestint.dto.UnregisteredUserDto;
import com.iquestint.dto.UserDto;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.*;
import com.iquestint.service.AdministrationUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This class implements AdministrationUserService interface.
 *
 * @author Georgian Vladutu
 */
@Service("administrationUserService")
@Transactional
public class AdministrationUserServiceImpl implements AdministrationUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserTypeDao userTypeDao;

    @Autowired
    private UserStateDao userStateDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ProfessorDao professorDao;

    @Override
    public void saveUser(UserDto userDto) throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException {
        UserType userType = null;
        UserState userState = null;
        User user = modelMapper.map(userDto, User.class);

        try {
            userType = userTypeDao.getUserTypeByName(user.getUserType().getName());
            userState = userStateDao.getUserStateByName(user.getUserState().getName());
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

        user.setUserType(userType);
        user.setUserState(userState);

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
            UserDto userDto = modelMapper.map(user, UserDto.class);
            Person person = personDao.getPersonByPnc(userDto.getPnc());
            populateUserDtoFromPerson(userDto, person);

            return userDto;
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        Type listType = new TypeToken<List<UserDto>>() {
        }.getType();
        List<UserDto> userDtos = modelMapper.map(users, listType);

        for (UserDto userDto : userDtos) {
            try {
                Person person = personDao.getPersonByPnc(userDto.getPnc());
                populateUserDtoFromPerson(userDto, person);
            } catch (DaoEntityNotFoundException ignored) {
            }
        }

        return userDtos;
    }

    @Override
    public void updateUser(UserDto userDto) throws ServiceEntityNotFoundException {
        UserType userType = null;
        UserState userState = null;
        User user = modelMapper.map(userDto, User.class);

        try {
            userType = userTypeDao.getUserTypeByName(user.getUserType().getName());
            userState = userStateDao.getUserStateByName(user.getUserState().getName());
            user.setUserState(userState);
            user.setUserType(userType);

            userDao.updateUser(user);

            if (userDto.getUserType().equals(com.iquestint.enums.Type.STUDENT.getType())) {
                updateStudent(userDto);
            }
            else {
                updateProfessor(userDto);
            }
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

            user.setUserState(userState);
            user.setUserType(userType);

            userDao.updateUserNoPassword(user);

            if (userDto.getUserType().equals(com.iquestint.enums.Type.STUDENT.getType())) {
                updateStudent(userDto);
            }
            else {
                updateProfessor(userDto);
            }
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
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

    private void updateStudent(UserDto userDto) throws DaoEntityNotFoundException {
        Student student = studentDao.getStudentByPnc(userDto.getPnc());
        student.setFirstName(userDto.getFirstName());
        student.setLastName(userDto.getLastName());
        student.setEmail(userDto.getEmail());

        studentDao.updateStudent(student);
    }

    private void updateProfessor(UserDto userDto) throws DaoEntityNotFoundException {
        Professor professor = professorDao.getProfessorByPnc(userDto.getPnc());
        professor.setFirstName(userDto.getFirstName());
        professor.setLastName(userDto.getLastName());
        professor.setEmail(userDto.getEmail());

        professorDao.updateProfessor(professor);
    }

    private void populateUserDtoFromPerson(UserDto userDto, Person person) {
        userDto.setLastName(person.getLastName());
        userDto.setFirstName(person.getFirstName());
        userDto.setEmail(person.getEmail());
    }
}
