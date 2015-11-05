package com.iquestint.dao;

import com.iquestint.model.Group;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author vladu
 */
@Repository("groupDao")
public class GroupDaoImpl extends AbstractDao<Group> implements GroupDao {

    public Group getGroupByName(String name) {
        TypedQuery<Group> query = getEntityManager().createQuery("SELECT g FROM Group g WHERE g.name = :name ",
            Group.class);
        query.setParameter("name", name);

        return query.getSingleResult();
    }

    public List<Group> getAllGroups() {
        return getAll();
    }
}
