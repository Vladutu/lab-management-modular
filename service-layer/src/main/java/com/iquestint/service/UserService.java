package com.iquestint.service;

import com.iquestint.dto.WelcomeUserDto;
import com.iquestint.exception.ServiceEntityNotFoundException;

/**
 * This interfaces provides methods for manipulating User objects.
 *
 * @author Georgian Vladutu
 */
public interface UserService {

    /**
     * Returns the User entity mapped as WelcomeUserDto who has the same pnc as the method parameter.
     *
     * @param pnc pnc of the user
     * @return WelcomeUserDto
     * @throws ServiceEntityNotFoundException if the user is not found in the repository
     */
    WelcomeUserDto getWelcomeUser(String pnc) throws ServiceEntityNotFoundException;
}
