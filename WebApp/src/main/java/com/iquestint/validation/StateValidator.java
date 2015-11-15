package com.iquestint.validation;

import com.iquestint.model.UserState;
import com.iquestint.service.UserStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This class is a validator for the @StateExists annotation.
 *
 * @author Georgian Vladutu
 */
@Component
public class StateValidator implements ConstraintValidator<StateExists, String> {

    @Autowired
    private UserStateService userStateService;

    @Override
    public void initialize(StateExists stateExists) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<UserState> userStates = userStateService.getAllUserStates();

        for (UserState userState : userStates) {
            if (s.equals(userState.getName())) {
                return true;
            }
        }
        return false;
    }
}
