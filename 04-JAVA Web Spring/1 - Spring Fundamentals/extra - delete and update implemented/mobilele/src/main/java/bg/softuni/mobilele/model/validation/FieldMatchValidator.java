package bg.softuni.mobilele.model.validation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String first;
    private String second;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.first = constraintAnnotation.firstField();
        this.second = constraintAnnotation.secondField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //В случая Object value ще е целия модел UserRegisterDTO
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object firstValue = beanWrapper.getPropertyValue(this.first);
        Object secondValue = beanWrapper.getPropertyValue(this.second);

        boolean valid;

        if (firstValue == null) { //ако password от html формуляра е null
            //ако първото е null и второто е null, то не хвърляй грешка
            //ако първото е null а второто има стойност, то хвърли грешка
            valid = secondValue == null;
        } else { //има въведен password от html формуляра
            valid = firstValue.equals(secondValue);  //еднакви ли са password и confirmPassword от html формуляра
        }

        if (!valid) {  //ако не са еднакви паролите, върни error message
            context
                    .buildConstraintViolationWithTemplate(message)  //error message-а от клас Анотацията @FieldMatch на UserRegisterDto
                    .addPropertyNode(this.second) //задай грешката на второто поле от класа UserRegisterDto
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();  //без dafault message при грешка
        }

        return valid;
    }
}
