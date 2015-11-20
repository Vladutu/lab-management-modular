package com.iquestint.service;

import com.iquestint.dao.interfaces.HourDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Hour;
import com.iquestint.service.HourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vladu
 */
@Service("hourService")
@Transactional
public class HourServiceImpl implements HourService {

    @Autowired
    private HourDao hourDao;

    @Override
    public List<Hour> getAllHours() {
        return hourDao.getAllHours();
    }

    @Override
    public Hour getHourByValue(int value) throws DaoEntityNotFoundException {
        return hourDao.getHourByValue(value);
    }
}
