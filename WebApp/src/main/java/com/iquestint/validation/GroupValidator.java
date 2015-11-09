package com.iquestint.validation;

import com.iquestint.model.Group;
import com.iquestint.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author vladu
 */
@Component
public class GroupValidator implements ConstraintValidator<GroupExists, String> {

    @Autowired
    private GroupService groupService;

    @Override
    public void initialize(GroupExists groupExists) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<Group> groups = groupService.getAllGroups();

        for (Group group : groups) {
            if (group.getName().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
