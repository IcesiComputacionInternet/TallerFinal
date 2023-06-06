package co.edu.icesi.Eshop.validation.validator;

import co.edu.icesi.Eshop.dto.OrderDTO;
import co.edu.icesi.Eshop.dto.UserDTO;
import co.edu.icesi.Eshop.validation.constraint.EmailOrPhoneNumberExistConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailOrPhoneNumberExistValidator implements ConstraintValidator<EmailOrPhoneNumberExistConstraint, UserDTO> {

    @Override
    public void initialize(EmailOrPhoneNumberExistConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext constraintValidatorContext) {
            return userDTO.getPhoneNumber() != null || userDTO.getEmail() != null;

    }

}
