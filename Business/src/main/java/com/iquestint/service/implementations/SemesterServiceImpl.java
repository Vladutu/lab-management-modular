package com.iquestint.service.implementations;

import com.iquestint.dao.interfaces.SemesterDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Semester;
import com.iquestint.service.interfaces.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vladu
 */
@Service("semesterService")
@Transactional
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterDao semesterDao;

    @Override
    public List<Semester> getAllSemesters() {
        return semesterDao.getAllSemesters();
    }

    @Override
    public Semester getSemesterByValue(int value) throws DaoEntityNotFoundException {
        return semesterDao.getSemesterByValue(value);
    }

}
