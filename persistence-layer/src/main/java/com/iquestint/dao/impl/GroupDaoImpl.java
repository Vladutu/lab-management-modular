package com.iquestint.dao.impl;

import com.iquestint.dao.GroupDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements GroupDao interfaces;
 *
 * @author Georgian Vladutu
 */
@Repository("groupDao")
public class GroupDaoImpl extends AbstractDao<Group> implements GroupDao {

    @Override
    public Group getGroupByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<Group> getAllGroups() {
        return getAll();
    }
}
