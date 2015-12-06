package com.iquestint.service.impl;

import com.iquestint.dao.UserDao;
import com.iquestint.dto.WelcomeUserDto;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.User;
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
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public WelcomeUserDto getWelcomeUser(String pnc) throws ServiceEntityNotFoundException {
        WelcomeUserDto welcomeUserDto = new WelcomeUserDto();

        try {
            User user = userDao.getUserByPnc(pnc);

            return modelMapper.map(user, WelcomeUserDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
