package co.edu.icesi.Eshop.validation.constraint;

import co.edu.icesi.Eshop.validation.validator.LevelOfEfficiencyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LevelOfEfficiencyValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LevelOfEfficiencyConstraint {

    String message() default "Invalid level of efficiency";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
