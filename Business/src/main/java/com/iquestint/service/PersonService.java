package com.iquestint.service;

import com.iquestint.enums.Type;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Person;

/**
 * @author vladu
 */
public interface PersonService {
    Type getPersonType(String firstName, String lastName) throws ServiceEntityNotFoundException;

    Type getPersonType(String pnc) throws ServiceEntityNotFoundException;

    Person getPersonByPnc(String pnc) throws ServiceEntityNotFoundException;
}
