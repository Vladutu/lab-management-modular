package com.iquestint.dao.implementations;

import com.iquestint.dao.interfaces.UserStateDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.UserState;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements UserStateDao interfaces.
 *
 * @author Georgian Vladutu
 */
@Repository("userStateDao")
public class UserStateDaoImpl extends AbstractDao<UserState> implements UserStateDao {

    @Override
    public UserState getUserStateByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<UserState> getAllUserStates() {
        return getAll();
    }
}
