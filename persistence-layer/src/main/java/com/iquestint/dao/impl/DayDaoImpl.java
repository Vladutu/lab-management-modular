package com.iquestint.dao.impl;

import com.iquestint.dao.DayDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Day;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements DayDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("dayDao")
public class DayDaoImpl extends JpaDao<Day> implements DayDao {

    @Override
    public Day getDayByValue(int value) throws DaoEntityNotFoundException {
        return getByValue(value);
    }

    @Override
    public List<Day> getAllDays() {
        return getAll();
    }
}
