package com.iquestint.service.implementations;

import com.iquestint.dao.interfaces.PersonDao;
import com.iquestint.enums.Type;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Person;
import com.iquestint.service.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * This class implements PersonService interfaces.
 *
 * @author Georgian Vladutu
 */
@Service("personService")
@Transactional
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

    @Override
    public Person getPersonByPnc(String pnc) throws ServiceEntityNotFoundException {
        try {
            return personDao.getPersonByPnc(pnc);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
