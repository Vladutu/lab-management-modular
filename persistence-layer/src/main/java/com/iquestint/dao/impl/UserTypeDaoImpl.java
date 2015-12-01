package com.iquestint.dao.impl;

import com.iquestint.dao.UserTypeDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.UserType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements UserTypeDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository("userTypeDao")
public class UserTypeDaoImpl extends AbstractDao<UserType> implements UserTypeDao {

    @Override
    public UserType getUserTypeByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<UserType> getAllUserTypes() {
        return getAll();
    }
}
