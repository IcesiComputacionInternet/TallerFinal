package co.com.icesi.backend.validator;

import co.com.icesi.backend.constraint.PhoneNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
    @Override
    public void initialize(PhoneNumberConstraint phoneNumberConstraint){
        ConstraintValidator.super.initialize(phoneNumberConstraint);
    }
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return phone != null &&
               phone.matches("^(/+57 3)/d{9}$");
    }
}
