package com.iquestint.dao.implementations;

import com.iquestint.dao.interfaces.DayDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Day;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vladu
 */
@Repository("dayDao")
public class DayDaoImpl extends AbstractDao<Day> implements DayDao {

    @Override
    public Day getDayByValue(int value) throws DaoEntityNotFoundException {
        return getByValue(value);
    }

    @Override
    public List<Day> getAllDays() {
        return getAll();
    }
}
