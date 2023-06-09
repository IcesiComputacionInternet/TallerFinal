package co.com.icesi.backend.validator;

import co.com.icesi.backend.constraint.PhoneEmailConstraint;
import co.com.icesi.backend.dto.request.RequestUserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneAndEmailValidator implements ConstraintValidator<PhoneEmailConstraint, RequestUserDTO> {
    @Override
    public void initialize(PhoneEmailConstraint phoneEmailConstraint){

    }
    @Override
    public boolean isValid(RequestUserDTO requestUserDTO, ConstraintValidatorContext constraintValidatorContext) {
        return requestUserDTO.getEmail()!=null || requestUserDTO.getPhoneNumber()!=null;
    }
}
