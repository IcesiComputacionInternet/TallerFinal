package co.edu.icesi.automoviles.mapper;

import org.mapstruct.Mapper;

import co.edu.icesi.automoviles.dto.CustomerCreateDTO;
import co.edu.icesi.automoviles.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer fromCustomerCreateDTO(CustomerCreateDTO customerCreateDTO);
    CustomerCreateDTO fromCustomerToCustomerCreateDTO(Customer customer);
}
