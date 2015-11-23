package com.iquestint.service.implementations;

import com.iquestint.dao.interfaces.UserStateDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.UserState;
import com.iquestint.service.interfaces.UserStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements UserStateService interfaces.
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
