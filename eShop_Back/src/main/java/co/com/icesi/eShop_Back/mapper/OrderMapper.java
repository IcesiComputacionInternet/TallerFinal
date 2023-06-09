package co.com.icesi.eShop_Back.mapper;

import co.com.icesi.eShop_Back.dto.request.RequestOrderDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseOrderDTO;
import co.com.icesi.eShop_Back.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "user", source = "user",ignore=true)
    @Mapping(target = "items", source = "items",ignore=true)
    Order orderDTOToOrder(RequestOrderDTO order);
    @Mapping(target = "user", source = "user",ignore=true)
    @Mapping(target = "items", source = "items",ignore=true)
    ResponseOrderDTO orderToOrderDTO(Order order);
}
