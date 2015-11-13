package com.iquestint.service;

import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.UserType;

import java.util.List;

/**
 * @author vladu
 */
public interface UserTypeService {

    UserType getUserTypeByName(String name) throws ServiceEntityNotFoundException;

    List<UserType> getAllUserTypes();
}
