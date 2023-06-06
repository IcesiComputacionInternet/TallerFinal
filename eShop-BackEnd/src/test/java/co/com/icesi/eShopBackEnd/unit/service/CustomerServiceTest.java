package co.com.icesi.eShopBackEnd.unit.service;

import co.com.icesi.eShopBackEnd.dto.CreateCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.CreateRoleDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseCustomerDTO;
import co.com.icesi.eShopBackEnd.mapper.CustomerMapper;
import co.com.icesi.eShopBackEnd.mapper.SalesOrderMapper;
import co.com.icesi.eShopBackEnd.mapper.CustomerMapperImpl;
import co.com.icesi.eShopBackEnd.mapper.SalesOrderMapperImpl;
import co.com.icesi.eShopBackEnd.model.Category;
import co.com.icesi.eShopBackEnd.model.Role;
import co.com.icesi.eShopBackEnd.repository.CustomerRepository;
import co.com.icesi.eShopBackEnd.repository.RoleRepository;
import co.com.icesi.eShopBackEnd.service.CustomerService;
import co.com.icesi.eShopBackEnd.service.RoleService;
import co.com.icesi.eShopBackEnd.unit.matcher.CustomerMatcher;
import co.com.icesi.eShopBackEnd.unit.matcher.RoleMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CustomerServiceTest {
    private  CustomerRepository customerRepository;
    private  RoleRepository roleRepository;
    private  CustomerMapper customerMapper;
    private  SalesOrderMapper salesOrderMapper;
    private  PasswordEncoder encoder;
    private CustomerService customerService;
    @BeforeEach
    public void init(){
        customerRepository = mock(CustomerRepository.class);
        roleRepository = mock(RoleRepository.class);
        encoder = mock(PasswordEncoder.class);
        customerMapper = spy(CustomerMapperImpl.class);
        salesOrderMapper = spy(SalesOrderMapperImpl.class);
        customerService = new CustomerService(customerRepository,roleRepository,customerMapper,salesOrderMapper,encoder);
    }
    @Test
    public void testCreateResponseCustomer(){
        Role role = role();
        when(roleRepository.findByRoleName(any())).thenReturn(Optional.of(role));
        ResponseCustomerDTO customerDTO= customerService.save(createCustomerDTO());
        assertEquals("call 65 Nº 32", customerDTO.getAddress());
        assertEquals("johndoe@email.com", customerDTO.getEmail());


    }

    private Role role() {
    return Role.builder()
            .roleName("USER")
                .description("Is a teacher the university Icesi")
                .build();
    }

    private CreateCustomerDTO createCustomerDTO() {
        return CreateCustomerDTO.builder()
                .firstName("luis")
                .lastName("andres")
                .address("call 65 Nº 32")
                .birthday("2023-06-06")
                .email("johndoe@email.com")
                .phoneNumber("332036584")
                .password("password")
                .build();
    }
    public CreateRoleDTO roleDTO(){
        return CreateRoleDTO.builder()
                .roleName("USER")
                .description("Is a teacher the university Icesi")
                .build();
    }
}
