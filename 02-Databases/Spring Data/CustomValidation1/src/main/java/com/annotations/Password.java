package com.annotations;

import com.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {PasswordValidator.class})
public @interface Password {
    String message() default "Default message for @Password annotation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minLength() default 8;

    int maxLength() default 150;

    boolean shouldContainDigit() default false;

    boolean shouldContainLowercase() default false;

    boolean shouldContainUppercase() default false;

    boolean shouldContainSpecialSymbol() default false;
}
