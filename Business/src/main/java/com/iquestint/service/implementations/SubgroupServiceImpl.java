package com.iquestint.service.implementations;

import com.iquestint.dao.interfaces.SubgroupDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Subgroup;
import com.iquestint.service.interfaces.SubgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements the SubgroupService interfaces.
 *
 * @author Georgian Vladutu
 */
@Service("subgroupService")
@Transactional
public class SubgroupServiceImpl implements SubgroupService {

    @Autowired
    private SubgroupDao subgroupDao;

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
