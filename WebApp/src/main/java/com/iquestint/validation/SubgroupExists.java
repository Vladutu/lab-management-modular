package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author vladu
 */
@Constraint(validatedBy = { SubgroupValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SubgroupExists {

    String message() default "Invalid subgroup";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
