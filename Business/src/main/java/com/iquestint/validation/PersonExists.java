package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation express that a person is valid.
 *
 * @author Georgian Vladutu
 */
@Constraint(validatedBy = { PersonValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface PersonExists {

    String message() default "Invalid person";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

