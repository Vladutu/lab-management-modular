package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Group;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
 */
@Repository("groupDao")
public class GroupDaoImpl extends AbstractDao<Group> implements GroupDao {

    @Override
    public Group getGroupByName(String name) throws DaoEntityNotFoundException {
        TypedQuery<Group> query = getEntityManager().createQuery("SELECT g FROM Group g WHERE g.name = :name ",
            Group.class);
        query.setParameter("name", name);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            throw new DaoEntityNotFoundException();
        }
    }

    @Override
    public List<Group> getAllGroups() {
        return getAll();
    }
}
