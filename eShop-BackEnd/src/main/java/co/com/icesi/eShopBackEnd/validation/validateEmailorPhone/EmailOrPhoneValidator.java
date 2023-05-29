package co.com.icesi.eShopBackEnd.validation.validateEmailorPhone;

import co.com.icesi.eShopBackEnd.dto.CreateUserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailOrPhoneValidator implements ConstraintValidator<EmailOrPhoneConstraint, CreateUserDTO> {

    @Override
    public void initialize(EmailOrPhoneConstraint contactNumber) {
    }

    @Override
    public boolean isValid(CreateUserDTO user, ConstraintValidatorContext constraintValidatorContext) {
        return !user.email().isBlank() || !user.phoneNumber().isBlank();
    }
}
