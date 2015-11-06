package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Subgroup;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
 */
@Repository("subgroupDao")
public class SubgroupDaoImpl extends AbstractDao<Subgroup> implements SubgroupDao {

    @Override
    public Subgroup getSubgroupByName(String name) throws DaoEntityNotFoundException {
        TypedQuery<Subgroup> query = getEntityManager().createQuery("SELECT s FROM Subgroup s WHERE s.name = :name ",
            Subgroup.class);
        query.setParameter("name", name);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public List<Subgroup> getAllSubgroups() {
        return getAll();
    }
}
