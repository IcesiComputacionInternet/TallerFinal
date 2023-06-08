package com.example.eshopbackend.validation;

import com.example.eshopbackend.dto.RoleDTO;
import com.example.eshopbackend.validation.interfaces.NameAndDescriptionRoleConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameAndDescriptionRoleValidator implements ConstraintValidator<NameAndDescriptionRoleConstraint, RoleDTO> {
    @Override
    public void initialize(NameAndDescriptionRoleConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RoleDTO roleDTO, ConstraintValidatorContext constraintValidatorContext) {
        return (!(roleDTO.getRoleName().isBlank()) && !(roleDTO.getDescription().isBlank()));
    }
}
