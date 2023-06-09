package co.com.icesi.eShopBackEnd.service;

import co.com.icesi.eShopBackEnd.dto.CreateCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.response.salesOrderResponse.ResponseSalesOrderDTO;
import co.com.icesi.eShopBackEnd.dto.response.RoleDTO;
import co.com.icesi.eShopBackEnd.error.enums.ErrorCode;
import co.com.icesi.eShopBackEnd.error.util.ArgumentsExceptionBuilder;
import co.com.icesi.eShopBackEnd.error.util.DetailBuilder;
import co.com.icesi.eShopBackEnd.mapper.CustomerMapper;
import co.com.icesi.eShopBackEnd.mapper.SalesOrderMapper;
import co.com.icesi.eShopBackEnd.model.Customer;
import co.com.icesi.eShopBackEnd.model.SalesOrder;
import co.com.icesi.eShopBackEnd.repository.RoleRepository;
import co.com.icesi.eShopBackEnd.repository.CustomerRepository;
import co.com.icesi.eShopBackEnd.security.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final CustomerMapper customerMapper;
    private final SalesOrderMapper salesOrderMapper;
    private final PasswordEncoder encoder;
    private final SecurityContext context;


    @Transactional
    public ResponseCustomerDTO updateCustomer(CreateCustomerDTO user) {
        validatePhone( user.phoneNumber());
        Customer newCustomer = customerRepository.findUserByEmail(user.email()).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"customer")
                )
        );

        newCustomer.setFirstName(user.firstName());
        newCustomer.setLastName(user.lastName());
        newCustomer.setAddress(user.address());
        newCustomer.setPhoneNumber(user.phoneNumber());
        newCustomer.setPassword(encoder.encode(user.password()));
        customerRepository.uptadeInformation(newCustomer,newCustomer.getEmail());
        return customerMapper.fromUserToResponseUserDTO(newCustomer);
    }

    public ResponseCustomerDTO save(CreateCustomerDTO user) {
        validateEmailAndPhone(user.email(), user.phoneNumber());

        Customer newCustomer = customerMapper.fromCreateUserDTO(user);
        newCustomer.setBirthday(convertStringToLocalDate(user.birthday()));
        newCustomer.setRole(roleRepository.findByRoleName("USER").orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"role")
                )
        ));

        newCustomer.setCustomerId(UUID.randomUUID());
        newCustomer.setPassword(encoder.encode(user.password()));
        ResponseCustomerDTO userResponse = customerMapper.fromUserToResponseUserDTO(customerRepository.save(newCustomer));
        userResponse.setCustomerId(newCustomer.getCustomerId());

        return userResponse;
    }
    private LocalDate convertStringToLocalDate(String date){
        return LocalDate.parse(date);
    }

    private void validateEmailAndPhone(String userEmail, String userPhone){
        boolean email = customerRepository.findByEmail(userEmail);
        boolean phoneNumber = customerRepository.findByPhoneNumber(userPhone);

        if(email && phoneNumber){
            throw ArgumentsExceptionBuilder.createArgumentsException(
                    "Existing data",
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_403,"Email","Phone number")
            );
            //throw new ArgumentsException("Email and phone number already exist",e);
        }else if (email) {
            throw ArgumentsExceptionBuilder.createArgumentsException(
                    "Existing data",
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_406,"Email")
            );
            //throw new ArgumentsException("Email already exist");
        }else if (phoneNumber) {
            throw ArgumentsExceptionBuilder.createArgumentsException(
                    "Existing data",
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_406,"Phone number")
            );
            //throw new ArgumentsException("Phone number already exist");
        }
    }

    private void validatePhone( String userPhone){
        boolean phoneNumber = customerRepository.findByPhoneNumber(userPhone);

        if (phoneNumber) {
            throw ArgumentsExceptionBuilder.createArgumentsException(
                    "Existing data",
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_406,"Phone number")
            );
            //throw new ArgumentsException("Phone number already exist");
        }
    }
    public List<ResponseSalesOrderDTO> getOrdersByUserEmail(String userEmail){
        Customer customer = customerRepository.findUserByEmail(userEmail).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"customer")
                )
        );

        List<SalesOrder> orders = customer.getSalesOrders();
        return orders.stream().map(salesOrderMapper::fromSalesOrderToResponse).toList();
    }


}
