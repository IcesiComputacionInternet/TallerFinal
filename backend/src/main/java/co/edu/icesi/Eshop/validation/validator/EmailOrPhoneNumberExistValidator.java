package co.edu.icesi.Eshop.validation.validator;

import co.edu.icesi.Eshop.dto.OrderDTO;
import co.edu.icesi.Eshop.dto.UserDTO;
import co.edu.icesi.Eshop.validation.constraint.EmailOrPhoneNumberExistConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailOrPhoneNumberExistValidator implements ConstraintValidator<EmailOrPhoneNumberExistConstraint, Object> {

    @Override
    public void initialize(EmailOrPhoneNumberExistConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext constraintValidatorContext) {
        if (dto instanceof UserDTO) {
            return ((UserDTO) dto).getPhoneNumber() != null || ((UserDTO) dto).getEmail() != null;
        }
        if (dto instanceof OrderDTO) {
            return ((OrderDTO) dto).getUserPhoneNumber() != null || ((OrderDTO) dto).getUserEmail() != null;
        }
        return false;
    }

}
