package com.iquestint.validation;

import com.iquestint.dao.GroupDao;
import com.iquestint.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This class is a validator for the @GroupExists annotation.
 *
 * @author Georgian Vladutu
 */
@Component
public class GroupValidator implements ConstraintValidator<GroupExists, String> {

    @Autowired
    private GroupDao groupDao;

    @Override
    public void initialize(GroupExists groupExists) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<Group> groups = groupDao.getAllGroups();

        for (Group group : groups) {
            if (group.getName().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
