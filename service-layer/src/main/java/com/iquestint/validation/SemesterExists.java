package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation express that a semester is valid.
 *
 * @author Georgian Vladutu
 */
@Constraint(validatedBy = { SemesterValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SemesterExists {

    String message() default "Invalid semester";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
