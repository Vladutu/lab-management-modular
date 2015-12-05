package com.iquestint.dao.impl;

import com.iquestint.exception.DaoEntityNotFoundException;

import java.util.List;

/**
 * This class is an abstract generic data access object class that implements the CRUD operations for all entities that
 * extend this class.
 *
 * @param <T> the type of the entity
 * @author Georgian Vladutu
 */
public abstract class Dao<T> {

    public Dao() {
    }

    protected abstract List<T> getAll();

    protected abstract T getById(int id) throws DaoEntityNotFoundException;

    protected abstract void persist(T t);

    protected abstract void delete(T t);

    protected abstract void update(T t);

    protected abstract T getByName(String name) throws DaoEntityNotFoundException;

    protected abstract T getByValue(int value) throws DaoEntityNotFoundException;
}
