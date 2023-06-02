package com.peliculas.grupo3.validate.phone;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( {ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneConstraint {
    String message() default "El numero no es de Colombia";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}