package com.iquestint.validation;

import com.iquestint.model.UserType;
import com.iquestint.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This class is a validator for the @TypeExists annotation.
 *
 * @author Georgian Vladutu
 */
@Component
public class TypeValidator implements ConstraintValidator<TypeExists, String> {

    @Autowired
    private UserTypeService userTypeService;

    @Override
    public void initialize(TypeExists typeExists) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<UserType> userTypeList = userTypeService.getAllUserTypes();

        for (UserType userType : userTypeList) {
            if (value.equals(userType.getName())) {
                return true;
            }
        }

        return false;
    }
}
