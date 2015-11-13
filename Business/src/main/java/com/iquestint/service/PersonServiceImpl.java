package com.iquestint.service;

import com.iquestint.dao.PersonDao;
import com.iquestint.enums.Type;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vladu
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public Type getPersonType(String firstName, String lastName) throws ServiceEntityNotFoundException {
        try {
            return personDao.getPersonType(firstName, lastName);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public Type getPersonType(String pnc) throws ServiceEntityNotFoundException {
        try {
            return personDao.getPersonType(pnc);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
