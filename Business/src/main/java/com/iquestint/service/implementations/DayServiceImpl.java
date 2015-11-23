package com.iquestint.service.implementations;

import com.iquestint.dao.interfaces.DayDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Day;
import com.iquestint.service.interfaces.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vladu
 */
@Service("dayService")
@Transactional
public class DayServiceImpl implements DayService {

    @Autowired
    private DayDao dayDao;

    @Override
    public List<Day> getAllDays() {
        return dayDao.getAllDays();
    }

    @Override
    public Day getDayByValue(int value) throws DaoEntityNotFoundException {
        return dayDao.getDayByValue(value);
    }
}
