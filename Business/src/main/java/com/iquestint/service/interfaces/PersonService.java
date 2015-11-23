package com.iquestint.service.interfaces;

import com.iquestint.enums.Type;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Person;

/**
 * This interfaces provides methods for manipulating Person objects.
 *
 * @author Georgian Vladutu
 */
public interface PersonService {

    /**
     * Returns the type of the person by specifying his/her first name and last name. It throws
     * ServiceEntityNotFoundException if the person is not found.
     *
     * @param firstName the person's first name
     * @param lastName  the person's last name
     * @return Type
     * @throws ServiceEntityNotFoundException
     */
    Type getPersonType(String firstName, String lastName) throws ServiceEntityNotFoundException;

    /**
     * Returns the type of the person by specifying his/her pnc. It throws
     * ServiceEntityNotFoundException if the person is not found.
     *
     * @param pnc the personal numeric code of the person
     * @return Type
     * @throws ServiceEntityNotFoundException
     */
    Type getPersonType(String pnc) throws ServiceEntityNotFoundException;

    /**
     * Returns the person by specifying his/her pnc. It throws ServiceEntityNotFoundException if the person is not found.
     *
     * @param pnc the personal numeric code of the person
     * @return Person
     * @throws ServiceEntityNotFoundException
     */
    Person getPersonByPnc(String pnc) throws ServiceEntityNotFoundException;
}
