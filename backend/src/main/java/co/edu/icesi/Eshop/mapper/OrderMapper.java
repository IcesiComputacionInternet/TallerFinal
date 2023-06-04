package co.edu.icesi.Eshop.mapper;

import co.edu.icesi.Eshop.dto.OrderDTO;
import co.edu.icesi.Eshop.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order fromOrderDTO(OrderDTO orderDTO);

    OrderDTO fromOrder(Order order);
}
