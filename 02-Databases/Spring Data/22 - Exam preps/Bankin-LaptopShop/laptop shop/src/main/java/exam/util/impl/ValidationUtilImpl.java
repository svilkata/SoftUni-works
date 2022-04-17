package exam.util.impl;

import exam.util.ValidationUtil;
import org.springframework.stereotype.Component;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil {
    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> boolean isValid(E dto) {
        Set<ConstraintViolation<E>> validationErrors = this.validator.validate(dto);

        if (validationErrors.isEmpty()) {
            return true; //no such dto in the database
        } else {
            return false; //there is such a dto already in the database
        }
    }
}
