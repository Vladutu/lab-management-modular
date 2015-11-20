package com.iquestint.service;

import com.iquestint.dao.interfaces.UserTypeDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.UserType;
import com.iquestint.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements UserTypeService interfaces.
 *
 * @author Georgian Vladutu
 */
@Service("userTypeService")
@Transactional
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeDao userTypeDao;

    @Override
    public UserType getUserTypeByName(String name) throws ServiceEntityNotFoundException {
        try {
            return userTypeDao.getUserTypeByName(name);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<UserType> getAllUserTypes() {
        return userTypeDao.getAllUserTypes();
    }
}
