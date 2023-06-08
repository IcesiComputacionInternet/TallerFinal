package co.com.icesi.eShopBackEnd.controller;

import co.com.icesi.eShopBackEnd.api.CustomerAPI;
import co.com.icesi.eShopBackEnd.dto.CreateCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.response.salesOrderResponse.ResponseSalesOrderDTO;
import co.com.icesi.eShopBackEnd.dto.response.RoleDTO;
import co.com.icesi.eShopBackEnd.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController implements CustomerAPI {

    private final CustomerService customerService;
    @Override
    public ResponseCustomerDTO createUser(CreateCustomerDTO user) {
        return customerService.save(user);
    }

    @Override
    public List<ResponseSalesOrderDTO> getOrdersByUserEmail(String userEmail) {
        return customerService.getOrdersByUserEmail(userEmail);
    }
}
