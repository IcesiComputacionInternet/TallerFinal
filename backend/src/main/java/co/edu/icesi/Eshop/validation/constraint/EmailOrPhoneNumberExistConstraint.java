package co.edu.icesi.Eshop.validation.constraint;

import co.edu.icesi.Eshop.validation.validator.EmailOrPhoneNumberExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailOrPhoneNumberExistValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailOrPhoneNumberExistConstraint {

    String message() default "Email or phone number are needed!";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}