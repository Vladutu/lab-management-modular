package com.iquestint.service;

import com.iquestint.dto.UnregisteredUserDto;
import com.iquestint.dto.UserDto;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;

import java.util.List;

/**
 * This interfaces provides methods for manipulating User objects.
 *
 * @author Georgian Vladutu
 */
public interface AdministrationUserService {

    /**
     * This method saves a user.
     *
     * @param userDto user to be saved
     * @throws ServiceEntityAlreadyExistsException if the user already exists in the repository
     * @throws ServiceEntityNotFoundException      if any of it's component fields are not found
     */
    void saveUser(UserDto userDto) throws ServiceEntityNotFoundException, ServiceEntityAlreadyExistsException;

    /**
     * This method deletes a user by specifying his/her personal numeric code(pnc).
     *
     * @param pnc the personal numeric code of the user
     * @throws ServiceEntityNotFoundException if the user is not found
     */
    void deleteUser(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves a user by specifying it's personal numeric code(pnc).
     *
     * @param pnc the personal numeric code of the user
     * @return User
     * @throws ServiceEntityNotFoundException if the user is not found
     */
    UserDto getUserByPnc(String pnc) throws ServiceEntityNotFoundException;

    /**
     * This method retrieves all user.
     *
     * @return List<User>
     */
    List<UserDto> getAllUsers();

    /**
     * This method update a user.
     *
     * @param userDto the user to be updated
     * @throws ServiceEntityNotFoundException if the user is not found
     */
    void updateUser(UserDto userDto) throws ServiceEntityNotFoundException;

    /**
     * This method update a user without his/her password.
     *
     * @param userDto the user to be updated
     * @throws ServiceEntityNotFoundException if the user is not found
     */
    void updateUserNoPassword(UserDto userDto) throws ServiceEntityNotFoundException;

    /**
     * Returns a person that exists in the repository but is not a user yet by specifying his/her pnc.
     *
     * @param pnc the personal numeric code
     * @return UnregisteredUserDto
     * @throws ServiceEntityNotFoundException if the person is not found
     */
    UnregisteredUserDto getUnregisteredUser(String pnc) throws ServiceEntityNotFoundException;
}
