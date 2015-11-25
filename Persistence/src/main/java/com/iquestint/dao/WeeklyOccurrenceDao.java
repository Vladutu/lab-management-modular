package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.WeeklyOccurrence;

import java.util.List;

/**
 * @author vladu
 */
public interface WeeklyOccurrenceDao {

    public WeeklyOccurrence getWeeklyOccurrenceByName(String name) throws DaoEntityNotFoundException;

    public List<WeeklyOccurrence> getAllWeeklyOccurrences();
}
