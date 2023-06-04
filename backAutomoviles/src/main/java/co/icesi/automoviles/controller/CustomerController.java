package co.icesi.automoviles.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import co.icesi.automoviles.api.CustomerAPI;
import co.icesi.automoviles.dto.CustomerCreateDTO;
import co.icesi.automoviles.dto.CustomerShowDTO;
import co.icesi.automoviles.security.ShopSecurityContext;
import co.icesi.automoviles.service.CustomerService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerController implements CustomerAPI {
    
    private final CustomerService customerService;

    @Override
    public CustomerShowDTO registerCustomer(@Valid CustomerCreateDTO customerCreateDTO) {
        String role = ShopSecurityContext.getCurrentUserRole();
        return customerService.registerCustomer(customerCreateDTO, role);
    }

    @Override
    public CustomerShowDTO assignRole(String customerId, String roleName) {
        return customerService.assignRole(customerId, roleName);
    }

    
}
