package co.edu.icesi.Eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String status;

    private long total;

    private String userEmail;

    private String userPhoneNumber;

    private List<String> items;

    private String response;


}
