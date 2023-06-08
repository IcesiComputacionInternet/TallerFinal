package co.com.icesi.backend.mapper;

import co.com.icesi.backend.dto.request.RequestNewOrderDTO;
import co.com.icesi.backend.dto.response.ResponseOrderDTO;
import co.com.icesi.backend.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "items", source = "items",ignore=true)
    Order fromRequestOrderDTO(RequestNewOrderDTO requestOrderDTO);
    @Mapping(target = "user", source = "user",ignore=true)
    @Mapping(target = "items", source = "items",ignore=true)
    ResponseOrderDTO fromOrderToResponseOrderDTO(Order order);
    @Mapping(target = "message", source = "message")
    ResponseOrderDTO fromRequestChangeToResponseOrderDTO(Order order, String message);
}
