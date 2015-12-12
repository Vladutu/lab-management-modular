package com.iquestint.dao.impl;

import com.iquestint.dao.SubgroupDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Subgroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements SubgroupDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("subgroupDao")
public class SubgroupDaoImpl extends JpaDao<Subgroup> implements SubgroupDao {

    @Override
    public Subgroup getSubgroupByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<Subgroup> getAllSubgroups() {
        return getAll();
    }
}
