package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * This class is an abstract generic data access object class that implements the CRUD operations for all entities that
 * extend this class.
 *
 * @param <T> the userType of the entity
 * @author Georgian Vladutu
 */
public abstract class AbstractDao<T> {

    protected final Class<T> persistentClass;

    @PersistenceContext
    private EntityManager entityManager;

    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected List<T> getAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getPersistentClass());
        Root<T> rootEntry = cq.from(getPersistentClass());
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = getEntityManager().createQuery(all);

        return allQuery.getResultList();

    }

    protected T getById(int id) throws DaoEntityNotFoundException {
        return getEntityManager().find(getPersistentClass(), id);
    }

    protected void persist(T t) {
        getEntityManager().merge(t);
    }

    protected void delete(T t) {
        getEntityManager().remove(t);
    }

    protected void update(T t) {
        getEntityManager().merge(t);
    }

    protected T getByName(String name) throws DaoEntityNotFoundException {
        TypedQuery<T> query = getEntityManager().createQuery("SELECT t FROM " +
                getPersistentClass().getSimpleName() +
                " t WHERE t.name = :name ",
            persistentClass);
        query.setParameter("name", name);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    protected T getByValue(int value) throws DaoEntityNotFoundException {
        TypedQuery<T> query = getEntityManager().createQuery("SELECT t FROM " +
                getPersistentClass().getSimpleName() +
                " t WHERE t.value = :value ",
            persistentClass);
        query.setParameter("value", value);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }
}
