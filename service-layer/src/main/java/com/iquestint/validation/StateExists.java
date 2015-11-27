package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation express that a state is valid.
 *
 * @author Georgian Vladutu
 */
@Constraint(validatedBy = { StateValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface StateExists {

    String message() default "Invalid state";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
