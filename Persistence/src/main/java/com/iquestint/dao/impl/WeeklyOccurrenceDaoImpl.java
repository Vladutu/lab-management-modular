package com.iquestint.dao.impl;

import com.iquestint.dao.WeeklyOccurrenceDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.WeeklyOccurrence;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vladu
 */
@Repository("weeklyOccurrenceDao")
public class WeeklyOccurrenceDaoImpl extends AbstractDao<WeeklyOccurrence> implements WeeklyOccurrenceDao {

    @Override
    public WeeklyOccurrence getWeeklyOccurrenceByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<WeeklyOccurrence> getAllWeeklyOccurrences() {
        return getAll();
    }
}
