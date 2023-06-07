package co.icesi.automoviles.api;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.icesi.automoviles.dto.CustomerCreateDTO;
import co.icesi.automoviles.dto.CustomerShowDTO;

import javax.validation.Valid;


@RequestMapping("/customers")
public interface CustomerAPI {

    public static final String ROOT_PATH = "/customers";

    @PostMapping
    public CustomerShowDTO registerCustomer(@Valid @RequestBody CustomerCreateDTO customerCreateDTO);

    @PatchMapping("{customerId}/role/{roleName}")  
    public CustomerShowDTO assignRole (@PathVariable("customerId") String customerId, @PathVariable("roleName") String roleName);  

}
