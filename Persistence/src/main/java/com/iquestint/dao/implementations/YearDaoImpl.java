package com.iquestint.dao.implementations;

import com.iquestint.dao.interfaces.YearDao;
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
