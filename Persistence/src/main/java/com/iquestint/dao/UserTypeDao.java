package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.UserType;

import java.util.List;

/**
 * @author vladu
 */
public interface UserTypeDao {

    UserType getUserTypeByName(String name) throws DaoEntityNotFoundException;

    List<UserType> getAllUserTypes();
}
