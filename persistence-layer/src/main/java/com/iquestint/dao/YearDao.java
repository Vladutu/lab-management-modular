package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Year;

import java.util.List;

/**
 * This interfaces provides methods for working with Year entity explicitly (and Year database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface YearDao {

    /**
     * Returns the Year entity from the database which has the same value as the method parameter.
     *
     * @param value value of the year
     * @return Year
     * @throws DaoEntityNotFoundException if the year with the specified value is not found
     */
    Year getYearByValue(int value) throws DaoEntityNotFoundException;

    /**
     * Returns all Year entities from the database.
     *
     * @return List<Year>
     */
    List<Year> getAllYears();
}
