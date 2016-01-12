package com.iquestint.service.impl;

import com.iquestint.dao.PersonDao;
import com.iquestint.dto.WelcomeUserDto;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Person;
import com.iquestint.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * This class implements the UserService interface.
 *
 * @author Georgian Vladutu
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private PersonDao personDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public WelcomeUserDto getWelcomeUser(String pnc) throws ServiceEntityNotFoundException {
        WelcomeUserDto welcomeUserDto = new WelcomeUserDto();

        try {
            Person person = personDao.getPersonByPnc(pnc);

            return modelMapper.map(person, WelcomeUserDto.class);
        } catch (DaoEntityNotFoundException e) {
            LOGGER.debug("DaoEntityNotFoundException");
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
