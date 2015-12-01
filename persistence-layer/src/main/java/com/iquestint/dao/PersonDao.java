package com.iquestint.dao;

import com.iquestint.enums.Type;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Person;

/**
 * This interfaces provides methods for retrieving the type of Person objects.
 *
 * @author Georgian Vladutu
 */
public interface PersonDao {

    /**
     * Returns the type of the person by specifying his/her first name and last name.
     *
     * @param firstName the person's first name
     * @param lastName  the person's last name
     * @return Type
     * @throws DaoEntityNotFoundException if the person is not found
     */
    Type getPersonType(String firstName, String lastName) throws DaoEntityNotFoundException;

    /**
     * Returns the type of the person by specifying his/her pnc.
     *
     * @param pnc the pnc of the person
     * @return Type
     * @throws DaoEntityNotFoundException if the person is not found
     */
    Type getPersonType(String pnc) throws DaoEntityNotFoundException;

    /**
     * Returns the person by specifying his/her pnc.
     *
     * @param pnc the pnc of the person
     * @return Person
     * @throws DaoEntityNotFoundException if the person is not found
     */
    Person getPersonByPnc(String pnc) throws DaoEntityNotFoundException;
}
