package co.com.icesi.backend.validator;

import co.com.icesi.backend.constraint.EmailConstraint;
import co.com.icesi.backend.dto.request.RequestUserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, RequestUserDTO> {
    @Override
    public void initialize(EmailConstraint emailConstraint){
        ConstraintValidator.super.initialize(emailConstraint);
    }
    @Override
    public boolean isValid(RequestUserDTO requestUserDTO, ConstraintValidatorContext constraintValidatorContext) {
        return requestUserDTO.getEmail().isEmpty() || requestUserDTO.getEmail().matches("([a-z0-9]+(\\\\.?[a-z0-9])*)+@(([a-z]+)\\\\.([a-z]+))+");
    }
}
