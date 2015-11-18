package com.iquestint.dao;

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
    public Year getYearByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<Year> getAllYears() {
        return getAll();
    }
}
