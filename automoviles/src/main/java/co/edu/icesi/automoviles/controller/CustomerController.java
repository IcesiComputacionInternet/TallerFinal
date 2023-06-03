package co.edu.icesi.automoviles.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.automoviles.api.CustomerAPI;
import co.edu.icesi.automoviles.dto.CustomerCreateDTO;
import co.edu.icesi.automoviles.dto.CustomerShowDTO;
import co.edu.icesi.automoviles.service.CustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
public class CustomerController implements CustomerAPI {
    
    private final CustomerService customerService;

    @Override
    public CustomerShowDTO registerCustomer(@Valid CustomerCreateDTO customerCreateDTO) {
        return null;
    }
}
