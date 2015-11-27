package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Year;

import java.util.List;

/**
 * @author vladu
 */
public interface YearDao {

    Year getYearByValue(int value) throws DaoEntityNotFoundException;

    List<Year> getAllYears();
}
