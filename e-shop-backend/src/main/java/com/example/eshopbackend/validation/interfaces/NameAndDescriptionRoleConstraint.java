package com.example.eshopbackend.validation.interfaces;

import com.example.eshopbackend.validation.NameAndDescriptionRoleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameAndDescriptionRoleValidator.class)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameAndDescriptionRoleConstraint {

    String message() default "To create a role, both the name and description fields are mandatory";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
