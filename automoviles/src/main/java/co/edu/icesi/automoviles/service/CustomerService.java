package co.edu.icesi.automoviles.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.icesi.automoviles.dto.CustomerCreateDTO;
import co.edu.icesi.automoviles.dto.CustomerShowDTO;
import co.edu.icesi.automoviles.error.exception.DetailBuilder;
import co.edu.icesi.automoviles.error.exception.ErrorCode;
import co.edu.icesi.automoviles.error.util.ShopExceptionBuilder;
import co.edu.icesi.automoviles.mapper.CustomerMapper;
import co.edu.icesi.automoviles.model.Customer;
import co.edu.icesi.automoviles.model.Role;
import co.edu.icesi.automoviles.repository.CustomerRepository;
import co.edu.icesi.automoviles.repository.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    public CustomerShowDTO registerCustomer(CustomerCreateDTO customerCreateDTO, String customerRole) {
        ArrayList<String> errors = new ArrayList<>();

        if(!emailAvailable(customerCreateDTO.getEmail())){
            errors.add("There is already a user with the email " + customerCreateDTO.getEmail() + ". ");
        }

        if(!phoneNumberAvailable(customerCreateDTO.getPhoneNumber())){
            errors.add("There is already a user with the phone number " + customerCreateDTO.getPhoneNumber() + ". ");
        }

        if(!errors.isEmpty()){
            String allErrorMessages = errors.stream().reduce("", (errorMessage, error) -> errorMessage + error);
            throw ShopExceptionBuilder.createShopException(
                    allErrorMessages,
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_400, "email or phone number", allErrorMessages)
            ).get();
        }

        Role role = findRoleByName(customerCreateDTO.getRole().getRoleName());
        
        Customer customer = customerMapper.fromCustomerCreateDTO(customerCreateDTO);
        customer.setRole(role);
        customer.setCustomerId(UUID.randomUUID());
        customer.setPassword(passwordEncoder.encode(customerCreateDTO.getPassword()));
        return customerMapper.fromCustomerToCustomerShowDTO(customerRepository.save(customer));
    }

    private boolean emailAvailable(String email){
        if(customerRepository.findByEmail(email).isPresent()){
            return false;
        }
        return true;
    }

    private boolean phoneNumberAvailable(String phoneNumber){
        if(customerRepository.findByPhoneNumber(phoneNumber).isPresent()){
            return false;
        }
        return true;
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name)
            .orElseThrow(() -> ShopExceptionBuilder.createShopException(
                "This role is not present in the database",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "role", "name", name)
            ).get());
    }

    public CustomerShowDTO assignRole(String customerId, String roleName) {
        return null;
    } 
    
}
