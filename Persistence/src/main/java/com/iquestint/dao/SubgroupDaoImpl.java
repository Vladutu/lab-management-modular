package com.iquestint.dao;

import com.iquestint.model.Subgroup;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
 */
public class SubgroupDaoImpl extends AbstractDao<Subgroup> implements SubgroupDao {

    public Subgroup getSubgroupByName(String name) {
        TypedQuery<Subgroup> query = getEntityManager().createQuery("SELECT s FROM Subgroup s WHERE s.name = :name ",
            Subgroup.class);
        query.setParameter("name", name);

        return query.getSingleResult();
    }

    public List<Subgroup> getAllSubgroups() {
        return getAll();
    }
}
