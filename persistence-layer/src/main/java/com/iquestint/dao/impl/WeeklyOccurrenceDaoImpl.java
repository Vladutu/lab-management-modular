package com.iquestint.dao.impl;

import com.iquestint.dao.WeeklyOccurrenceDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.WeeklyOccurrence;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements WeeklyOccurrenceDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("weeklyOccurrenceDao")
public class WeeklyOccurrenceDaoImpl extends JpaDao<WeeklyOccurrence> implements WeeklyOccurrenceDao {

    @Override
    public WeeklyOccurrence getWeeklyOccurrenceByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<WeeklyOccurrence> getAllWeeklyOccurrences() {
        return getAll();
    }
}
