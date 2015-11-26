package com.iquestint.dao.impl;

import com.iquestint.dao.HourDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Hour;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vladu
 */
@Repository("hourDao")
public class HourDaoImpl extends AbstractDao<Hour> implements HourDao {

    @Override
    public Hour getHourByValue(int value) throws DaoEntityNotFoundException {
        return getByValue(value);
    }

    @Override
    public List<Hour> getAllHours() {
        return getAll();
    }
}