package co.edu.icesi.Eshop.validation.validator;

import co.edu.icesi.Eshop.validation.constraint.PhoneNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint,String> {
    @Override
    public void initialize(PhoneNumberConstraint phoneNumber){

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {

        return phoneNumber == null ||  (phoneNumber.matches("[+]573[0-5][0-9]{8}") && phoneNumber.length() == 13);
    }
}
