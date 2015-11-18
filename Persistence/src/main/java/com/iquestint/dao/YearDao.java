package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Year;

import java.util.List;

/**
 * @author vladu
 */
public interface YearDao {

    Year getYearByName(String name) throws DaoEntityNotFoundException;

    List<Year> getAllYears();
}
