package com.iquestint.dao.impl;

import com.iquestint.dao.UserDao;
import com.iquestint.dao.UserStateDao;
import com.iquestint.dao.UserTypeDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * This class implements UserDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository("userDao")
public class UserDaoImpl extends JpaDao<User> implements UserDao {

    @Autowired
    private UserStateDao userStateDao;

    @Autowired
    private UserTypeDao userTypeDao;

    @Override
    public List<User> getAllUsers() {
        return getAll();
    }

    @Override
    public User getUserByPnc(String pnc) throws DaoEntityNotFoundException {
        TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.pnc = :pnc",
            User.class);
        query.setParameter("pnc", pnc);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public void saveUser(User user) throws DaoEntityAlreadyExists {
        try {
            User u = getUserByPnc(user.getPnc());
        } catch (DaoEntityNotFoundException e) {
            persist(user);
            return;
        }

        throw new DaoEntityAlreadyExists();
    }

    @Override
    public void updateUser(User user) throws DaoEntityNotFoundException {
        User s = getUserByPnc(user.getPnc());
        update(user);
    }

    @Override
    public void updateUserNoPassword(User user) throws DaoEntityNotFoundException {
        User s = getUserByPnc(user.getPnc());

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<User> updateCriteria = cb.createCriteriaUpdate(User.class);
        Root<User> root = updateCriteria.from(User.class);
        updateCriteria.set(root.get("userState"), user.getUserState());
        updateCriteria.set(root.get("userType"), user.getUserType());
        updateCriteria.where(cb.equal(root.get("pnc"), user.getPnc()));

        getEntityManager().createQuery(updateCriteria).executeUpdate();
    }

    @Override
    public void deleteUserByPnc(String pnc) throws DaoEntityNotFoundException {
        User user = getUserByPnc(pnc);
        delete(user);
    }
}
