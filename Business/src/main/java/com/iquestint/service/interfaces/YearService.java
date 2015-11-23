package com.iquestint.service.interfaces;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Year;

import java.util.List;

/**
 * @author vladu
 */
public interface YearService {

    List<Year> getAllYears();

    Year getYearByValue(int value) throws DaoEntityNotFoundException;
}
