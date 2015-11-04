package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author vladu
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

    protected List<T> getAll() {
        List<T> list = (List<T>) getEntityManager().createQuery(
            "from " + persistentClass.getSimpleName()).getResultList();

        return list;
    }

    protected T getById(int id) throws DaoEntityNotFoundException {
        T entity = getEntityManager().find(persistentClass, id);

        if (entity == null) {
            throw new DaoEntityNotFoundException();
        }

        return entity;
    }

    protected void persist(T t) {
//        T entity = getEntityManager().find(persistentClass, t);
//        if (entity != null) {
//            return;
//        }
        getEntityManager().merge(t);
    }

    protected void delete(T t) {
        getEntityManager().remove(t);
    }

    protected void update(T t) {
        getEntityManager().merge(t);
    }
}
