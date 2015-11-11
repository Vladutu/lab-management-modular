package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation express that a section is valid.
 *
 * @author Georgian Vladutu
 */
@Constraint(validatedBy = { SectionValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SectionExists {

    String message() default "Invalid section";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
