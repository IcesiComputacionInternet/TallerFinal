package co.icesi.automoviles.mapper;
import org.mapstruct.Mapper;

import co.icesi.automoviles.dto.CustomerCreateDTO;
import co.icesi.automoviles.dto.CustomerShowDTO;
import co.icesi.automoviles.model.Customer;
import co.icesi.automoviles.model.Role;


@Mapper(componentModel = "spring")
public interface CustomerMapper {  

    Customer fromCustomerCreateDTO(CustomerCreateDTO customerCreateDTO);
    CustomerCreateDTO fromCustomerToCustomerCreateDTO(Customer customer);
    Role fromCustomerShowDTO(CustomerShowDTO customerShowDTO);
    CustomerShowDTO fromCustomerToCustomerShowDTO(Customer customer);

}
