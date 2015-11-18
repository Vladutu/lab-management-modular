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
    public Semester getSemesterByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<Semester> getAllSemesters() {
        return getAll();
    }
}
