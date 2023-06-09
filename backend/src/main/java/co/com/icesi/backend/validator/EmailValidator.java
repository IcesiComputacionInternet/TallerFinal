package co.com.icesi.backend.validator;

import co.com.icesi.backend.constraint.EmailConstraint;
import co.com.icesi.backend.dto.request.RequestUserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    @Override
    public void initialize(EmailConstraint emailConstraint){

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.isEmpty() || s.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

}
