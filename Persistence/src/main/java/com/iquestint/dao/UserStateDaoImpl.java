package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.UserState;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class implements UserStateDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository("userStateDao")
public class UserStateDaoImpl extends AbstractDao<UserState> implements UserStateDao {

    @Override
    public UserState getUserStateByName(String name) throws DaoEntityNotFoundException {
        TypedQuery<UserState> query = getEntityManager().createQuery(
            "SELECT us FROM UserState us WHERE us.name = :name ",
            UserState.class);
        query.setParameter("name", name);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public List<UserState> getAllUserStates() {
        return getAll();
    }
}
