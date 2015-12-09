package com.iquestint.service.impl;

import com.iquestint.dao.PersonDao;
import com.iquestint.dto.WelcomeUserDto;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Person;
import com.iquestint.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author vladu
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

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
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
