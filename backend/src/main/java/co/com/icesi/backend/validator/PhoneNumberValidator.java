package co.com.icesi.backend.validator;

import co.com.icesi.backend.constraint.PhoneNumberConstraint;
import co.com.icesi.backend.dto.request.RequestUserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, RequestUserDTO> {
    @Override
    public void initialize(PhoneNumberConstraint phoneNumberConstraint){
        ConstraintValidator.super.initialize(phoneNumberConstraint);
    }
    @Override
    public boolean isValid(RequestUserDTO user, ConstraintValidatorContext constraintValidatorContext) {
        return user.getPhoneNumber() == null || user.getPhoneNumber().matches("^(/+57 3)/d{9}$");
    }
}
