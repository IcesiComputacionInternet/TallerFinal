package co.com.icesi.backend.mapper;

import co.com.icesi.backend.dto.request.RequestOrderDTO;
import co.com.icesi.backend.dto.response.ResponseOrderDTO;
import co.com.icesi.backend.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "user", source = "userEmail",ignore=true)
    @Mapping(target = "items", source = "items",ignore=true)
    Order fromRequestOrderDTO(RequestOrderDTO requestOrderDTO);
    @Mapping(target = "user", source = "user",ignore=true)
    @Mapping(target = "items", source = "items",ignore=true)
    ResponseOrderDTO fromOrderToResponseOrderDTO(Order order);
}
