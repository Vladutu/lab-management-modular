package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author vladu
 */
@Constraint(validatedBy = { ProfessorValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ProfessorExists {

    String message() default "Invalid pnc or it does not correspond to a professor";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
