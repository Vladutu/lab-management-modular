package com.iquestint.dao.impl;

import com.iquestint.dao.SemesterDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Semester;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements SemesterDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("semesterDao")
public class SemesterDaoImpl extends AbstractDao<Semester> implements SemesterDao {

    @Override
    public Semester getSemesterByValue(int value) throws DaoEntityNotFoundException {
        return getByValue(value);
    }

    @Override
    public List<Semester> getAllSemesters() {
        return getAll();
    }
}
