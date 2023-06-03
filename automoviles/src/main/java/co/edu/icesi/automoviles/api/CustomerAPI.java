package co.edu.icesi.automoviles.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.icesi.automoviles.dto.CustomerCreateDTO;
import co.edu.icesi.automoviles.dto.CustomerShowDTO;

import javax.validation.Valid;


@RequestMapping("/customers")
public interface CustomerAPI {
    
    @PostMapping
    public CustomerShowDTO registerCustomer(@Valid @RequestBody CustomerCreateDTO customerCreateDTO);

}
