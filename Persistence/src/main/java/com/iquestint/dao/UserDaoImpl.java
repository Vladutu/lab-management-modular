package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Person;
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
 * @author vladu
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Autowired
    private UserStateDao userStateDao;

    @Autowired
    private UserTypeDao userTypeDao;

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
            "SELECT u FROM User u WHERE u.person.firstName = :fName AND u.person.lastName = :lName ",
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
        User s = findUserByPnc(user.getPnc());
        update(user);
    }

    @Override
    public void updateUserNoPassword(User user) throws DaoEntityNotFoundException {
        User s = findUserByPnc(user.getPnc());

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<User> updateCriteria = cb.createCriteriaUpdate(User.class);
        Root<User> root = updateCriteria.from(User.class);
        updateCriteria.set(root.get("userState"), user.getUserState());
        updateCriteria.set(root.get("userType"), user.getUserType());
        updateCriteria.where(cb.equal(root.get("pnc"), user.getPnc()));
        getEntityManager().createQuery(updateCriteria).executeUpdate();

        CriteriaUpdate<Person> personCriteriaUpdate = cb.createCriteriaUpdate(Person.class);
        Root<Person> personRoot = personCriteriaUpdate.from(Person.class);
        personCriteriaUpdate.set(personRoot.get("firstName"), user.getPerson().getFirstName());
        personCriteriaUpdate.set(personRoot.get("lastName"), user.getPerson().getLastName());
        personCriteriaUpdate.set(personRoot.get("email"), user.getPerson().getEmail());
        personCriteriaUpdate.where(cb.equal(personRoot.get("pnc"), user.getPnc()));
        getEntityManager().createQuery(personCriteriaUpdate).executeUpdate();
    }

    @Override
    public void deleteUserByPnc(String pnc) throws DaoEntityNotFoundException {
        User user = findUserByPnc(pnc);
        delete(user);
    }
}
