package com.iquestint.service;

import com.iquestint.dao.SubgroupDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Subgroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements the SubgroupService interface.
 *
 * @author Georgian Vladutu
 */
@Service("subgroupService")
@Transactional
public class SubgroupServiceImpl implements SubgroupService {

    @Autowired
    SubgroupDao subgroupDao;

    @Override
    public List<Subgroup> getAllSubgroups() {
        return subgroupDao.getAllSubgroups();
    }

    @Override
    public Subgroup getSubgroupByName(String name) throws ServiceEntityNotFoundException {
        try {
            return subgroupDao.getSubgroupByName(name);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}