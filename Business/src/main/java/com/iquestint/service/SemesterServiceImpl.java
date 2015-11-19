package com.iquestint.service;

import com.iquestint.dao.SemesterDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vladu
 */
@Service("semesterService")
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
