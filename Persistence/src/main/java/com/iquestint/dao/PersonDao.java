package com.iquestint.dao;

import com.iquestint.enums.Type;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Person;

/**
 * This interface provides methods for retrieving the type of Person objects.
 *
 * @author Georgian Vladutu
 */
public interface PersonDao {

    /**
     * Returns the type of the person by specifying his/her first name and last name. It throws DaoEntityNotFoundException
     * if the person is not found.
     *
     * @param firstName the person's first name
     * @param lastName  the person's last name
     * @return Type
     * @throws DaoEntityNotFoundException
     */
    Type getPersonType(String firstName, String lastName) throws DaoEntityNotFoundException;

    /**
     * Returns the type of the person by specifying his/her pnc. It throws DaoEntityNotFoundException
     * if the person is not found.
     *
     * @param pnc the pnc of the person
     * @return Type
     * @throws DaoEntityNotFoundException
     */
    Type getPersonType(String pnc) throws DaoEntityNotFoundException;

    /**
     * Returns the person by specifying his/her pnc. It throws DaoEntityNotFoundException if the person is not found.
     *
     * @param pnc the pnc of the person
     * @return Person
     * @throws DaoEntityNotFoundException
     */
    Person getPersonByPnc(String pnc) throws DaoEntityNotFoundException;
}
