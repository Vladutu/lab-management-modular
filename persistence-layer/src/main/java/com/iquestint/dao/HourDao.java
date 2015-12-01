package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Hour;

import java.util.List;

/**
 * This interfaces provides methods for working with Hour entity explicitly (and Hour database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface HourDao {

    /**
     * Returns the Hour entity from the database wich has the same value as the method parameter.
     *
     * @param value the hour value
     * @return Hour
     * @throws DaoEntityNotFoundException if the hour with the specified value is not found
     */
    Hour getHourByValue(int value) throws DaoEntityNotFoundException;

    /**
     * Returns all Hour entities from the database.
     *
     * @return List<Hour>
     */
    List<Hour> getAllHours();
}
