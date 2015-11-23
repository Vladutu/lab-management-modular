package com.iquestint.service.implementations;

import com.iquestint.dao.interfaces.WeeklyOccurrenceDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.WeeklyOccurrence;
import com.iquestint.service.interfaces.WeeklyOccurrenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vladu
 */
@Service("weeklyOccurrenceService")
@Transactional
public class WeeklyOccurrenceServiceImpl implements WeeklyOccurrenceService {

    @Autowired
    private WeeklyOccurrenceDao weeklyOccurrenceDao;

    @Override
    public List<WeeklyOccurrence> getAllWeeklyOccurrence() {
        return weeklyOccurrenceDao.getAllWeeklyOccurrences();
    }

    @Override
    public WeeklyOccurrence getWeeklyOccurrenceByName(String name) throws DaoEntityNotFoundException {
        return weeklyOccurrenceDao.getWeeklyOccurrenceByName(name);
    }
}
