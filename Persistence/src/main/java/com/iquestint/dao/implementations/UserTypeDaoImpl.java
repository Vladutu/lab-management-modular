package com.iquestint.dao.implementations;

import com.iquestint.dao.interfaces.UserTypeDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.UserType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements UserTypeDao interfaces.
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
