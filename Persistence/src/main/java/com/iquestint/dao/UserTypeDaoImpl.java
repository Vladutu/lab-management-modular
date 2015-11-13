package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.UserType;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
 */
@Repository("userTypeDao")
public class UserTypeDaoImpl extends AbstractDao<UserType> implements UserTypeDao {

    @Override
    public UserType getUserTypeByName(String name) throws DaoEntityNotFoundException {
        TypedQuery<UserType> query = getEntityManager().createQuery("SELECT ut FROM UserType ut WHERE ut.name = :name ",
            UserType.class);
        query.setParameter("name", name);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public List<UserType> getAllUserTypes() {
        return getAll();
    }
}
