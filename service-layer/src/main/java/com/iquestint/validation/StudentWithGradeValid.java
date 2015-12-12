package com.iquestint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation express that a StudentWithGrade object is valid.
 *
 * @author Georgian Vladutu
 */
@Constraint(validatedBy = { StudentWithGradeValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface StudentWithGradeValid {

    String message() default "Present student must have a grade";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
