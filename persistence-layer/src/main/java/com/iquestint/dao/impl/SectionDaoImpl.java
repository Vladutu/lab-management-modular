package com.iquestint.dao.impl;

import com.iquestint.dao.SectionDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Section;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements SectionDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("sectionDao")
public class SectionDaoImpl extends AbstractDao<Section> implements SectionDao {

    @Override
    public Section getSectionByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<Section> getAllSections() {
        return getAll();
    }
}
