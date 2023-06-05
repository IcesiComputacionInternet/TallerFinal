package co.edu.icesi.Eshop.mapper;

import co.edu.icesi.Eshop.dto.OrderDTO;
import co.edu.icesi.Eshop.model.EShopOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "items", source = "items",ignore=true)
    EShopOrder fromOrderDTO(OrderDTO orderDTO);

    @Mapping(target = "items", expression = "java(order.getItems().stream().map(item -> item.getName()).toList())")
    @Mapping(target = "userEmail", expression = "java(order.getUser().getEmail())")
    @Mapping(target = "userPhoneNumber", expression = "java(order.getUser().getPhoneNumber())")
    OrderDTO fromOrder(EShopOrder order);
}
