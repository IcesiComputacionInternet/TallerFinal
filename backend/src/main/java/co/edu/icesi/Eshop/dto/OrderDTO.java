package co.edu.icesi.Eshop.dto;

import co.edu.icesi.Eshop.validation.constraint.CustomEmailConstraint;
import co.edu.icesi.Eshop.validation.constraint.EmailOrPhoneNumberExistConstraint;
import co.edu.icesi.Eshop.validation.constraint.PhoneNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @Null
    private String orderId;

    @Null
    private String status;

    private long total;


    @Null
    private String userEmail;


    @Null
    private String userPhoneNumber;

    @NotNull
    private List<ItemDTO> items;



}
