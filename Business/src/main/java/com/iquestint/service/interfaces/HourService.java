package com.iquestint.service.interfaces;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Hour;

import java.util.List;

/**
 * @author vladu
 */
public interface HourService {

    List<Hour> getAllHours();

    Hour getHourByValue(int value) throws DaoEntityNotFoundException;
}
