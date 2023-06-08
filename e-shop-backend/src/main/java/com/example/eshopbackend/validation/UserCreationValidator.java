package com.example.eshopbackend.validation;

import com.example.eshopbackend.dto.UserDTO;
import com.example.eshopbackend.validation.interfaces.UserCreationConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserCreationValidator implements ConstraintValidator<UserCreationConstraint, UserDTO> {
    @Override
    public void initialize(UserCreationConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext constraintValidatorContext) {
        return !(userDTO.getEmail().isBlank() && userDTO.getPhoneNumber().isBlank());
    }
}
