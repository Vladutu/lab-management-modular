package com.iquestint.dao.implementations;

import com.iquestint.dao.interfaces.SubgroupDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Subgroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements GroupDao interfaces;
 *
 * @author Georgian Vladutu
 */
@Repository("subgroupDao")
public class SubgroupDaoImpl extends AbstractDao<Subgroup> implements SubgroupDao {

    @Override
    public Subgroup getSubgroupByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<Subgroup> getAllSubgroups() {
        return getAll();
    }
}
