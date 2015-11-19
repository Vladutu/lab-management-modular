package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Semester;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vladu
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
