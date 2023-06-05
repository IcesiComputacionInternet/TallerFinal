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
@EmailOrPhoneNumberExistConstraint
public class OrderDTO {

    @Null
    private String orderId;

    @Null
    private String status;

    @Null
    private long total;

    @CustomEmailConstraint
    private String userEmail;

    @PhoneNumberConstraint
    private String userPhoneNumber;

    @NotNull
    private List<String> items;

    @Null
    private String response;


}
