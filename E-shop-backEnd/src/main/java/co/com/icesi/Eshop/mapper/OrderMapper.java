package co.com.icesi.Eshop.mapper;

import co.com.icesi.Eshop.dto.request.OrderDTO;
import co.com.icesi.Eshop.dto.response.OrderResponseDTO;
import co.com.icesi.Eshop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    //@Mapping(target = "items", ignore = true)
    Order toOrder(OrderDTO orderDTO);
    //@Mapping(target = "items", ignore = true)
    Order toOrder(OrderResponseDTO orderResponseDTO);
    //@Mapping(target = "items", ignore = true)
    OrderDTO toOrderDTO(Order order);
    //@Mapping(target = "items", ignore = true)
    OrderDTO toOrderDTO(OrderResponseDTO orderResponseDTO);
    //@Mapping(target = "items", ignore = true)
    OrderResponseDTO toOrderResponseDTO(Order order);

    OrderResponseDTO toOrderResponseDTO(OrderDTO orderDTO);
}
