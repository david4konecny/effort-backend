package com.example.effort.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TimeConsistencyValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TimeConsistent {

    String message() default "endTime cannot be before startTime";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
