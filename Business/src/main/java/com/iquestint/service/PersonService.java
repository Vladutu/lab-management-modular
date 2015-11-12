package com.iquestint.service;

import com.iquestint.enums.Type;
import com.iquestint.exception.ServiceEntityNotFoundException;

/**
 * @author vladu
 */
public interface PersonService {
    Type getPersonType(String firstName, String lastName) throws ServiceEntityNotFoundException;
}
