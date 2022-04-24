package com.example.nlt.util;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class MyValidator {
    private final Validator validator;

    public MyValidator() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <E> boolean isValid(E dto) {
        Set<ConstraintViolation<E>> validationErrors = this.validator.validate(dto);

        if (validationErrors.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
