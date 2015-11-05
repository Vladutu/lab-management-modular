package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Section;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
 */
@Repository("sectionDao")
public class SectionDaoImpl extends AbstractDao<Section> implements SectionDao {

    public Section getSectionByName(String name) throws DaoEntityNotFoundException {
        TypedQuery<Section> query = getEntityManager().createQuery("SELECT s FROM Section s WHERE s.name = :name ",
            Section.class);
        query.setParameter("name", name);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    public List<Section> getAllSections() {
        return getAll();
    }
}
