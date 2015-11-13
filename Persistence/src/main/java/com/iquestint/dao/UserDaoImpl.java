package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    public List<User> findAllUsers() {
        return getAll();
    }

    @Override
    public User findUserByPnc(String pnc) throws DaoEntityNotFoundException {
        TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.pnc = :pnc",
            User.class);
        query.setParameter("pnc", pnc);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public User findUserByName(String firstName, String lastName) throws DaoEntityNotFoundException {
        TypedQuery<User> query = getEntityManager().createQuery(
            "SELECT u FROM User u WHERE u.firstName = :fName AND u.lastName = :lName ",
            User.class);
        query.setParameter("fName", firstName);
        query.setParameter("lName", lastName);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public void saveUser(User user) throws DaoEntityAlreadyExists {
        try {
            User u = findUserByPnc(user.getPnc());
        }
        catch (DaoEntityNotFoundException e) {
            persist(user);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    @Override
    public void updateUser(User user) throws DaoEntityNotFoundException {
        try {
            User s = findUserByPnc(user.getPnc());
            update(user);
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public void deleteUserByPnc(String pnc) throws DaoEntityNotFoundException {
        try {
            User user = findUserByPnc(pnc);
            delete(user);
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }
}
