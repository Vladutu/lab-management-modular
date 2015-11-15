package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation express that a type is valid.
 *
 * @author Georgian Vladutu
 */
@Constraint(validatedBy = { TypeValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface TypeExists {

    String message() default "Invalid type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
