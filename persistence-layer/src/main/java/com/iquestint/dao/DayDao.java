package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Day;

import java.util.List;

/**
 * @author vladu
 */
public interface DayDao {

    Day getDayByValue(int value) throws DaoEntityNotFoundException;

    List<Day> getAllDays();
}
