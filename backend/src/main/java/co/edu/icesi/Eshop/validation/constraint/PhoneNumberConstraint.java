package co.edu.icesi.Eshop.validation.constraint;

import co.edu.icesi.Eshop.validation.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberConstraint {

    String message() default "Invalid phone number. Should be a Colombian phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
