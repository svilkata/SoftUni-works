package com.validators;

import com.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private Password password;
    private ConstraintValidatorContext context;
    private String value;
    private int violationsCount = 0;

    private static final List<Character> specialSymbols = Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '<', '>', '?');

    private static final String PASSWORD_TOO_SHORT = "Password should be at least %d characters";
    private static final String PASSWORD_MIN_MAX_LENGTH_MISMATCH = "Password min length cannot be greater than max length";
    private static final String PASSWORD_TOO_LONG = "Password should be no longer than %d characters";
    private static final String PASSWORD_NO_DIGIT = "Password should contain at least 1 digit";
    private static final String PASSWORD_NO_LOWERCASE = "Password should contain at least 1 lowercase letter";
    private static final String PASSWORD_NO_UPPERCASE = "Password should contain at least 1 uppercase letter";
    private static final String PASSWORD_NO_SPECIAL_SYMBOL = "Password should contain at least 1 special symbol (" +
            specialSymbols.stream().map(e -> e + "").collect(Collectors.joining(", ")) + ")";

    private static final int MINLENGTH = 16;
    private static final int MAXLENGTH = 30;
    private static final boolean CONTAINSDIGIT = true;
    private static final boolean CONTAINSLOWERCASE = true;
    private static final boolean CONTAINSUPPERCASE = true;
    private static final boolean CONTAINSSPECIALSYMBOL = true;


    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.password = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        this.value = value;
        this.context = context;
        context.disableDefaultConstraintViolation();

        if (this.value.length() < MINLENGTH) {
            setMessage(String.format(PASSWORD_TOO_SHORT, MINLENGTH));
            violationsCount++;
        }

        if (this.value.length() > MAXLENGTH) {
            setMessage(String.format(PASSWORD_TOO_LONG, MAXLENGTH));
            violationsCount++;
        }

        if (!doesContainDigit()) {
            violationsCount++;
            setMessage(PASSWORD_NO_DIGIT);
        }

        if (!doesContainLowercase()) {
            violationsCount++;
            setMessage(PASSWORD_NO_LOWERCASE);
        }

        if (!doesContainUppercase()) {
            violationsCount++;
            setMessage(PASSWORD_NO_UPPERCASE);
        }

        if (!doesContainSpecialSymbol()) {
            violationsCount++;
            setMessage(PASSWORD_NO_SPECIAL_SYMBOL);
        }

        return violationsCount == 0;
    }

    private void setMessage(String message) {
        this.context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

    private boolean doesContainDigit() {
        if (!CONTAINSDIGIT) {
            return true;
        }

        for (char c : this.value.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean doesContainLowercase() {
        if (!CONTAINSLOWERCASE) {
            return true;
        }

        for (char c : this.value.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean doesContainUppercase() {
        if (!CONTAINSUPPERCASE) {
            return true;
        }

        for (char c : this.value.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean doesContainSpecialSymbol() {
        if (!CONTAINSSPECIALSYMBOL) {
            return true;
        }

        for (char c : this.value.toCharArray()) {
            if (specialSymbols.contains(c)) {
                return true;
            }
        }
        return false;
    }


}
