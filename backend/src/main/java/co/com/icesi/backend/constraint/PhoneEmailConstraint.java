package co.com.icesi.backend.constraint;

import co.com.icesi.backend.validator.PhoneAndEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneAndEmailValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneEmailConstraint {
    String message() default "Email or phone number are required";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
