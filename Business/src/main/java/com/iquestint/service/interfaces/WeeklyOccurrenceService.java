package com.iquestint.service.interfaces;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.WeeklyOccurrence;

import java.util.List;

/**
 * @author vladu
 */
public interface WeeklyOccurrenceService {

    List<WeeklyOccurrence> getAllWeeklyOccurrence();

    WeeklyOccurrence getWeeklyOccurrenceByName(String name) throws DaoEntityNotFoundException;
}
