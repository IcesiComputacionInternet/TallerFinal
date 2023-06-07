package co.com.icesi.eShopBackEnd.unit.service;

import co.com.icesi.eShopBackEnd.dto.CreateCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.CreateRoleDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseCustomerDTO;
import co.com.icesi.eShopBackEnd.mapper.CustomerMapper;
import co.com.icesi.eShopBackEnd.mapper.SalesOrderMapper;
import co.com.icesi.eShopBackEnd.mapper.CustomerMapperImpl;
import co.com.icesi.eShopBackEnd.mapper.SalesOrderMapperImpl;
import co.com.icesi.eShopBackEnd.model.Category;
import co.com.icesi.eShopBackEnd.model.Customer;
import co.com.icesi.eShopBackEnd.model.Role;
import co.com.icesi.eShopBackEnd.repository.CustomerRepository;
import co.com.icesi.eShopBackEnd.repository.RoleRepository;
import co.com.icesi.eShopBackEnd.security.SecurityContext;
import co.com.icesi.eShopBackEnd.service.CustomerService;
import co.com.icesi.eShopBackEnd.service.RoleService;
import co.com.icesi.eShopBackEnd.unit.matcher.CustomerMatcher;
import co.com.icesi.eShopBackEnd.unit.matcher.RoleMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CustomerServiceTest {

    private CustomerService customerService;
    private  CustomerRepository customerRepository;
    private  RoleRepository roleRepository;
    private  CustomerMapper customerMapper;
    private  SalesOrderMapper salesOrderMapper;
    private  PasswordEncoder encoder;

    @BeforeEach
    public void init(){
        customerRepository = mock(CustomerRepository.class);
        roleRepository = mock(RoleRepository.class);

        encoder = mock(PasswordEncoder.class);
        customerMapper = spy(CustomerMapperImpl.class);
        salesOrderMapper = spy(SalesOrderMapperImpl.class);
        SecurityContext context = mock(SecurityContext.class);
        customerService = new CustomerService(customerRepository,roleRepository,customerMapper,salesOrderMapper,encoder,context);
        when(context.getCurrentUserRole()).thenReturn("ADMIN");
    }
    @Test
    public void testCreateResponseCustomer(){
        when(customerRepository.findByEmail(any())).thenReturn(false);
        when(customerRepository.findByPhoneNumber(any())).thenReturn(false);
        when(roleRepository.findByRoleName(any())).thenReturn(Optional.of(defaultRole()));
        when(customerRepository.save(any())).thenReturn(defaultCustomer());
        when(customerMapper.fromUserToResponseUserDTO(any())).thenReturn(defaultResponseCustomerDTO());
        customerService.save(createCustomerDTO());
        assertEquals("call 65 Nº 32",defaultResponseCustomerDTO().getAddress());
        assertEquals("lucho@email.com",defaultResponseCustomerDTO().getEmail());
        verify(customerRepository,times(1)).save(any());

    }

    private Role defaultRole() {
    return Role.builder()
            .roleName("USER")
            .description("Is a teacher the university Icesi")
            .build();
    }
    public CreateRoleDTO createRoleDTO(){
        return CreateRoleDTO.builder()
                .roleName("USER")
                .description("Is a teacher the university Icesi")
                .build();
    }

    private Customer defaultCustomer(){
        return Customer.builder()
                .firstName("luis")
                .lastName("andres")
                .email("lucho@email.com")
                .password("password")
                .phoneNumber("332036584")
                .address("call 65 Nº 32")
                .birthday(LocalDate.parse("2003-06-06"))
                .role(defaultRole())
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

    private ResponseCustomerDTO defaultResponseCustomerDTO(){
        return ResponseCustomerDTO.builder()
                .firstName("luis")
                .lastName("andres")
                .email("lucho@email.com")
                .password("password")
                .phoneNumber("332036584")
                .address("call 65 Nº 32")
                .birthday(LocalDate.parse("2003-06-06"))
                .role(createRoleDTO())
                .build();
    }



}
