package com.iquestint.service.interfaces;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Day;

import java.util.List;

/**
 * @author vladu
 */
public interface DayService {

    List<Day> getAllDays();

    Day getDayByValue(int value) throws DaoEntityNotFoundException;
}
