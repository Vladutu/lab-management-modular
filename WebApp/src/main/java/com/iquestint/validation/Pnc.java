package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author vladu
 */
@Constraint(validatedBy = { PncValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Pnc {

    String message() default "Invalid pnc.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}