package com.iquestint.service;

import com.iquestint.dao.interfaces.GroupDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Group;
import com.iquestint.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements the GroupService interfaces.
 *
 * @author Georgian Vladutu
 */
@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    @Override
    public Group getGroupByName(String name) throws ServiceEntityNotFoundException {
        try {
            return groupDao.getGroupByName(name);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
