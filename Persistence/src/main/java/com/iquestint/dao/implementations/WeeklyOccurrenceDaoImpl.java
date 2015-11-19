package com.iquestint.dao.implementations;

import com.iquestint.dao.interfaces.WeeklyOccurrenceDao;
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
