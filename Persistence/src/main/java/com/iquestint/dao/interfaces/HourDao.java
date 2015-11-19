package com.iquestint.dao.interfaces;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Hour;

import java.util.List;

/**
 * @author vladu
 */
public interface HourDao {

    Hour getHourByValue(int value) throws DaoEntityNotFoundException;

    List<Hour> getAllHours();
}
