package co.com.icesi.Eshop.mapper;

import co.com.icesi.Eshop.dto.request.OrderDTO;
import co.com.icesi.Eshop.dto.response.OrderResponseDTO;
import co.com.icesi.Eshop.model.OrderStore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "items", ignore = true)
    OrderStore toOrder(OrderDTO orderDTO);
    @Mapping(target = "items", ignore = true)
    OrderStore toOrder(OrderResponseDTO orderResponseDTO);
    @Mapping(target = "items", ignore = true)
    OrderDTO toOrderDTO(OrderStore orderStore);
    @Mapping(target = "items", ignore = true)
    OrderDTO toOrderDTO(OrderResponseDTO orderResponseDTO);
    @Mapping(target = "items", ignore = true)
    OrderResponseDTO toOrderResponseDTO(OrderStore orderStore);

    OrderResponseDTO toOrderResponseDTO(OrderDTO orderDTO);
}
