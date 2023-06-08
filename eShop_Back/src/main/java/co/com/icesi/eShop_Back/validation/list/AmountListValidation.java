package co.com.icesi.eShop_Back.validation.list;

import co.com.icesi.eShop_Back.dto.request.RequestItemDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class AmountListValidation implements ConstraintValidator<AmountList, List<RequestItemDTO>> {
    @Override
    public boolean isValid(List<RequestItemDTO> requestItemDTOS, ConstraintValidatorContext constraintValidatorContext) {
        if(requestItemDTOS == null) {return false;}
        return !requestItemDTOS.isEmpty();
    }
}
