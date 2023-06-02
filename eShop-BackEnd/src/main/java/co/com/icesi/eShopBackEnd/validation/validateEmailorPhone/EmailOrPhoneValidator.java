package co.com.icesi.eShopBackEnd.validation.validateEmailorPhone;

import co.com.icesi.eShopBackEnd.dto.CreateCustomerDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailOrPhoneValidator implements ConstraintValidator<EmailOrPhoneConstraint, CreateCustomerDTO> {

    @Override
    public void initialize(EmailOrPhoneConstraint contactNumber) {
    }

    @Override
    public boolean isValid(CreateCustomerDTO user, ConstraintValidatorContext constraintValidatorContext) {
        return !user.email().isBlank() || !user.phoneNumber().isBlank();
    }
}
