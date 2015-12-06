package com.iquestint.service;

import com.iquestint.dto.WelcomeUserDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author vladu
 */
public interface UserService {

    WelcomeUserDto getWelcomeUser(String pnc) throws ServiceEntityNotFoundException;
}
