package co.com.icesi.eShopBackEnd.api;

import co.com.icesi.eShopBackEnd.dto.CreateCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.response.salesOrderResponse.ResponseSalesOrderDTO;
import co.com.icesi.eShopBackEnd.dto.response.RoleDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static co.com.icesi.eShopBackEnd.api.CustomerAPI.BASE_USER_URL;


@RequestMapping(value = BASE_USER_URL)
public interface CustomerAPI {
    String BASE_USER_URL = "/user";

    @PostMapping
    ResponseCustomerDTO createUser(@Valid @RequestBody CreateCustomerDTO user);

    @GetMapping("/order/{userEmail}")
    List<ResponseSalesOrderDTO> getOrdersByUserEmail(@PathVariable String userEmail);

    @PutMapping("/updateUser")
    ResponseCustomerDTO updateCustomer(@Valid @RequestBody CreateCustomerDTO user);

    @GetMapping("/all")
    List<ResponseCustomerDTO> getAllCustomers();
}
