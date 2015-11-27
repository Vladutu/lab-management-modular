package com.iquestint.service;

import com.iquestint.dto.FormUserDto;
import com.iquestint.dto.UnregisteredUserDto;
import com.iquestint.dto.UserDto;
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
     * @param userDto user to be saved
     * @throws ServiceEntityAlreadyExistsException
     */
    void saveUser(UserDto userDto) throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException;

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
    UserDto getUserByPnc(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves all user.
     *
     * @return List<User>
     */
    List<UserDto> getAllUsers();

    /**
     * This method update a user. It throws ServiceEntityNotFoundException if the user is not found.
     *
     * @param userDto the user to be updated
     * @throws ServiceEntityNotFoundException
     */
    void updateUser(UserDto userDto) throws ServiceEntityNotFoundException;

    /**
     * This method update a user without his/her password. It throws ServiceEntityNotFoundException if the user is not found.
     *
     * @param userDto the user to be updated
     * @throws ServiceEntityNotFoundException
     */
    void updateUserNoPassword(UserDto userDto) throws ServiceEntityNotFoundException;

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

    void initializeFormUserDto(FormUserDto formUserDto);

    UnregisteredUserDto getUnregisteredUser(String pnc) throws ServiceEntityNotFoundException;
}
