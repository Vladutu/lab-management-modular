package com.iquestint.service;

import com.iquestint.dao.interfaces.YearDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Year;
import com.iquestint.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vladu
 */
@Service("yearService")
@Transactional
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
