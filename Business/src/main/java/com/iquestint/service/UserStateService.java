package com.iquestint.service;

import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.UserState;

import java.util.List;

/**
 * @author vladu
 */
public interface UserStateService {

    UserState getUserStateByName(String name) throws ServiceEntityNotFoundException;

    List<UserState> getAllUserStates();
}
