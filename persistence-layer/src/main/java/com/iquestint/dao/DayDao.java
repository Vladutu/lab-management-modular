package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Day;

import java.util.List;

/**
 * This interfaces provides methods for working with Day entity explicitly (and Day database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface DayDao {

    /**
     * Returns the Day entity from the database which has the same value as the method parameter.
     *
     * @param value value of the day. Monday is 1 and Friday is 5.
     * @return Day
     * @throws DaoEntityNotFoundException if the day with the specified value is not found
     */
    Day getDayByValue(int value) throws DaoEntityNotFoundException;

    /**
     * Returns all Day entities from the database.
     *
     * @return List<Day>
     */
    List<Day> getAllDays();
}
