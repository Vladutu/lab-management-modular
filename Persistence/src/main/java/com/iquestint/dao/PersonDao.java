package com.iquestint.dao;

import com.iquestint.enums.Type;
import com.iquestint.exception.DaoEntityNotFoundException;

/**
 * @author vladu
 */
public interface PersonDao {

    Type getPersonType(String firstName, String lastName) throws DaoEntityNotFoundException;

    Type getPersonType(String pnc) throws DaoEntityNotFoundException;
}
