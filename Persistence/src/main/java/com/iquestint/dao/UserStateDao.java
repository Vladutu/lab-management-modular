package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.UserState;

import java.util.List;

/**
 * @author vladu
 */
public interface UserStateDao {

    UserState getUserStateByName(String name) throws DaoEntityNotFoundException;

    List<UserState> getAllUserStates();
}
