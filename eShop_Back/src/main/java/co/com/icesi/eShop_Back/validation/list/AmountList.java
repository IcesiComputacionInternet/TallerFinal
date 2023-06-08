package co.com.icesi.eShop_Back.validation.list;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AmountListValidation.class)
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE} )
@Retention(RetentionPolicy.RUNTIME)
public @interface AmountList {
    String message() default "Phone number is not colombian";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
