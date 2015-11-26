package com.iquestint.dao.impl;

import com.iquestint.dao.YearDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Year;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vladu
 */
@Repository("yearDao")
public class YearDaoImpl extends AbstractDao<Year> implements YearDao {

    @Override
    public Year getYearByValue(int value) throws DaoEntityNotFoundException {
        return getByValue(value);
    }

    @Override
    public List<Year> getAllYears() {
        return getAll();
    }
}