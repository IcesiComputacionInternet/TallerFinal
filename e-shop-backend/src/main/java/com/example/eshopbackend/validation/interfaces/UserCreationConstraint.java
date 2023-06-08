package com.example.eshopbackend.validation.interfaces;

import com.example.eshopbackend.validation.NameAndDescriptionRoleValidator;
import com.example.eshopbackend.validation.UserCreationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserCreationValidator.class)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserCreationConstraint {
    String message() default "To create a user, either email or phone are required";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
