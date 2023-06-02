package co.com.icesi.eShopBackEnd.mapper;

import co.com.icesi.eShopBackEnd.dto.CreateCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseCustomerDTO;
import co.com.icesi.eShopBackEnd.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "birthday", source = "birthday",ignore = true)
    Customer fromCreateUserDTO(CreateCustomerDTO userCreateDTO);

    //@Mapping(target = "role", source = "role",ignore = true)
    CreateCustomerDTO fromUser(Customer customer);


    ResponseCustomerDTO fromUserToResponseUserDTO(Customer customer);
}
