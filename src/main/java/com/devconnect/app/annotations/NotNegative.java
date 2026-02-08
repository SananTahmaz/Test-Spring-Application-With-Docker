package com.devconnect.app.annotations;

import com.devconnect.app.validators.NotNegativeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotNegativeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNegative {
    String message() default "Value cannot be negative";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
