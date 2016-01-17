package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation express that a file exists.
 *
 * @author Georgian Vladutu
 */
@Constraint(validatedBy = { FileValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface FileExists {

    String message() default "Missing file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
