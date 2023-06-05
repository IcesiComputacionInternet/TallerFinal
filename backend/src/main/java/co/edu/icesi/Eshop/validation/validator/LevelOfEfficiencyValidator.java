package co.edu.icesi.Eshop.validation.validator;

import co.edu.icesi.Eshop.validation.constraint.LevelOfEfficiencyConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LevelOfEfficiencyValidator implements ConstraintValidator<LevelOfEfficiencyConstraint, String> {

    @Override
    public void initialize(LevelOfEfficiencyConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String levelOfEfficiency, ConstraintValidatorContext constraintValidatorContext) {
        return levelOfEfficiency.matches("[A-G]");
    }
}
