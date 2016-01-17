package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.WeeklyOccurrence;

import java.util.List;

/**
 * This interfaces provides methods for working with WeeklyOccurrence entity explicitly (and WeeklyOccurrence database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface WeeklyOccurrenceDao {

    /**
     * Returns the WeeklyOccurrence entity from the database which has the same name as the method parameter.
     *
     * @param name name of the weekly occurrence
     * @return WeeklyOccurrence
     * @throws DaoEntityNotFoundException if the weeklyOccurrence with the specified name is not found
     */
    public WeeklyOccurrence getWeeklyOccurrenceByName(String name) throws DaoEntityNotFoundException;

    /**
     * Returns all WeeklyOccurrence entities from the database.
     *
     * @return list of weekly occurrences
     */
    public List<WeeklyOccurrence> getAllWeeklyOccurrences();
}
