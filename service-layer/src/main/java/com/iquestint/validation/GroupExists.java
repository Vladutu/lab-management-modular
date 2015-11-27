package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation express that a group is valid.
 *
 * @author Georgian Vladutu
 */
@Constraint(validatedBy = { GroupValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface GroupExists {

    String message() default "Invalid group";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

