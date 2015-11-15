package com.iquestint.service;

import com.iquestint.dao.UserStateDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements UserStateService interface.
 *
 * @author Georgian Vladutu
 */
@Service("userStateService")
@Transactional
public class UserStateServiceImpl implements UserStateService {

    @Autowired
    private UserStateDao userStateDao;

    @Override
    public UserState getUserStateByName(String name) throws ServiceEntityNotFoundException {
        try {
            return userStateDao.getUserStateByName(name);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<UserState> getAllUserStates() {
        return userStateDao.getAllUserStates();
    }
}
