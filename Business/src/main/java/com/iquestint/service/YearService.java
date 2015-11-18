package com.iquestint.service;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Year;

import java.util.List;

/**
 * @author vladu
 */
public interface YearService {

    List<Year> getAllYears();

    Year getYearByName(String name) throws DaoEntityNotFoundException;
}
