package co.edu.icesi.automoviles.service;

import org.springframework.stereotype.Service;

import co.edu.icesi.automoviles.dto.CustomerShowDTO;
import co.edu.icesi.automoviles.mapper.CustomerMapper;
import co.edu.icesi.automoviles.repository.CustomerRepository;
import co.edu.icesi.automoviles.repository.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final CustomerMapper customerMapper;

    public CustomerShowDTO registerCustomer() {
        return null;
    }
    
    
}
