package com.iquestint.validation;

import com.iquestint.dao.UserTypeDao;
import com.iquestint.model.UserType;
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
    private UserTypeDao userTypeDao;

    @Override
    public void initialize(TypeExists typeExists) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<UserType> userTypeList = userTypeDao.getAllUserTypes();

        for (UserType userType : userTypeList) {
            if (value.equals(userType.getName())) {
                return true;
            }
        }

        return false;
    }
}
