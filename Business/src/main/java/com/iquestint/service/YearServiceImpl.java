package com.iquestint.service;

import com.iquestint.dao.interfaces.YearDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vladu
 */
@Service("yearService")
public class YearServiceImpl implements YearService {

    @Autowired
    private YearDao yearDao;

    @Override
    public List<Year> getAllYears() {
        return yearDao.getAllYears();
    }

    @Override
    public Year getYearByValue(int value) throws DaoEntityNotFoundException {
        return yearDao.getYearByValue(value);
    }

}
